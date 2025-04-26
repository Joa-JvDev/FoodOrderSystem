package jva.dev.foodordersystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${api.food.dev-url}")
    private String devUrl;
    @Value("${api.food.prod-url}")
    private String prodUrl;

    //URL PARA ENTRAR A LA UI -> http://localhost:8080/api/v1/swagger-ui/index.html
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(this.devUrl);
        devServer.setDescription("Development Server");

        Server prodServer = new Server();
        prodServer.setUrl(this.prodUrl);
        prodServer.setDescription("Production Server");

        //Informacion de Contacto
        Contact contact = new Contact();
        contact.setEmail("joaquindevgo@gmail.com");
        contact.setName("JvaDev");

        //Licencia
        License mitLicense = new License().name("MIT License").url("https://github.com/joaquindevgo");

        Info info = new Info()
                .title("Food Order System")
                .version("1.0")
                .contact(contact)
                .license(mitLicense)
                .description("Food Order System Description")
                .termsOfService("https://github.com/joaquindevgo");

        //Configuracion de seguridad JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Authorization");

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer)
                .addServersItem(prodServer)
                .components(new Components().addSecuritySchemes("Authorization", securityScheme))
                .addSecurityItem(securityRequirement);
    }

}

