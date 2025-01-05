// Paquete al que pertenece esta clase. Agrupa las clases relacionadas en el proyecto.
package com.tuempresa.tiendaropa.controller;

// Importa la anotación @Controller, que indica que esta clase actuará como un controlador en Spring MVC.
import org.springframework.stereotype.Controller;
// Importa la anotación @GetMapping, que se usa para definir rutas específicas en el controlador.
import org.springframework.web.bind.annotation.GetMapping;

// Define esta clase como un controlador de Spring MVC.
@Controller
public class ClienteController {

    // Especifica que este método manejará las solicitudes HTTP GET dirigidas a la ruta "/cliente".
    @GetMapping("/cliente")
    public String clientePage() {
        // Devuelve el nombre del archivo "cliente.html", que se espera esté ubicado en la carpeta "static" o configurado como recurso estático.
        return "cliente.html"; 
    }
}
