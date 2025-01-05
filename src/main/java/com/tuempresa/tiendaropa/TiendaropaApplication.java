// Paquete principal del proyecto.
package com.tuempresa.tiendaropa;

// Importa la clase SpringApplication, que se utiliza para ejecutar la aplicación Spring Boot.
import org.springframework.boot.SpringApplication;
// Importa la anotación @SpringBootApplication, que combina varias anotaciones esenciales.
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 * La anotación @SpringBootApplication marca esta clase como el punto de entrada de la aplicación.
 */
@SpringBootApplication // Combina @Configuration, @EnableAutoConfiguration y @ComponentScan.
public class TiendaropaApplication {

    /**
     * Método principal (main) para ejecutar la aplicación.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        // Ejecuta la aplicación Spring Boot utilizando SpringApplication.run.
        // Esto configura automáticamente la aplicación y la inicia.
        SpringApplication.run(TiendaropaApplication.class, args);
    }
}
