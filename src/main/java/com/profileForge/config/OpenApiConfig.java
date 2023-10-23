package com.profileForge.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
       info = @Info(
                contact = @Contact(
                        name = "Kunal Dhanwant",
                        email = "kunaldhanwant8851@gmail.com",
                        url = "https://www.linkedin.com/in/kunal-dhanwant/"

                ),
               description = "OpenApi documentation for ProfileForge",
               title = "ProfileForge",
               version = "1.0"
        ),
        security = {
               @SecurityRequirement(
                       name = "Authorization"
               )
        }

)

@SecurityScheme(
        name = "Authorization",
        description = "JWT Auth Description",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
