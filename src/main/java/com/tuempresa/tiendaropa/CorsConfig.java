// Paquete principal del proyecto.
package com.tuempresa.tiendaropa;

// Importa la anotación @Bean para definir un bean en el contenedor de Spring.
import org.springframework.context.annotation.Bean;
// Importa la anotación @Configuration para marcar esta clase como una configuración de Spring.
import org.springframework.context.annotation.Configuration;
// Importa CorsRegistry, que permite configurar los mapeos CORS.
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// Importa WebMvcConfigurer, que permite personalizar la configuración de MVC.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para habilitar y personalizar CORS en la aplicación.
 */
@Configuration // Marca esta clase como una configuración para Spring.
public class CorsConfig {

    /**
     * Define un bean para configurar CORS en la aplicación.
     * @return Una instancia de WebMvcConfigurer con la configuración CORS personalizada.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Método para configurar los mapeos CORS.
             * @param registry Registro donde se definen las reglas CORS.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite CORS en todos los endpoints de la aplicación.
                        .allowedOrigins("http://localhost:8080") // Dominios permitidos para realizar solicitudes.
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos.
                        .allowedHeaders("*") // Permite todos los encabezados en las solicitudes.
                        .allowCredentials(true); // Habilita el envío de credenciales (como cookies o encabezados de autenticación).
            }
        };
    }
}
