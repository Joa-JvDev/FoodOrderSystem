package jva.dev.foodordersystem.service;

import jva.dev.foodordersystem.domain.entity.ShoppingCart;
import jva.dev.foodordersystem.domain.entity.UserEntity;
import jva.dev.foodordersystem.domain.enums.Role;
import jva.dev.foodordersystem.dto.request.AuthLoginRequest;
import jva.dev.foodordersystem.dto.request.AuthRegisterRequest;
import jva.dev.foodordersystem.dto.response.AuthResponse;
import jva.dev.foodordersystem.service.repository.UserRepository;
import jva.dev.foodordersystem.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("User " + email + " not found"));

        List<SimpleGrantedAuthority> authorities = List.of(
                new  SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().name())));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                authorities);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createJwtToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User Loged successfuly", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null) {
        throw new BadCredentialsException("Invalid email or password");}

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }


    public AuthResponse createUser(AuthRegisterRequest authRegisterRequest) {
        ShoppingCart shoppingCart = new ShoppingCart();

        String email = authRegisterRequest.email();
        String password = authRegisterRequest.password();
        String name = authRegisterRequest.name();
        String phone = authRegisterRequest.phone();
        String address = authRegisterRequest.address();

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phone(phone)
                .address(address)
                .role(Role.USER)
                .cart(shoppingCart)
                .isEnabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .build();

        UserEntity userCreated =  userRepository.save(userEntity);

        SecurityContext context = SecurityContextHolder.getContext();

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + userCreated.getRole().name())
        );
        Authentication auth = new UsernamePasswordAuthenticationToken(userCreated.getEmail(),
                null, authorities);

        String accessToken = jwtUtils.createJwtToken(auth);
        AuthResponse authResponse = new AuthResponse(userCreated.getEmail(), "User Created successfuly", accessToken, true);

        return authResponse;
    }
}
