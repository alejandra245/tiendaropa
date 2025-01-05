package com.tuempresa.tiendaropa.controller;

import com.tuempresa.tiendaropa.model.Producto;
import com.tuempresa.tiendaropa.model.Usuario;
import com.tuempresa.tiendaropa.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios") // Base para los endpoints relacionados con usuarios
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Inyección de dependencias
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para listar todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    /**
     * Endpoint para listar todos los administradores.
     *
     * @return Lista de administradores.
     */
    @GetMapping("/administradores")
    public List<Usuario> listarAdministradores() {
        return usuarioService.listarAdministradores();
    }

    /**
     * Endpoint para listar todos los clientes.
     *
     * @return Lista de clientes.
     */
    @GetMapping("/clientes")
    public List<Usuario> listarClientes() {
        return usuarioService.listarClientes();
    }

    /**
     * Endpoint para guardar un nuevo usuario.
     *
     * @param usuario Objeto Usuario con los datos a guardar.
     * @return Usuario guardado.
     */
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Endpoint para buscar un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o un 404 si no existe.
     */
    @GetMapping("/{username}")
    public ResponseEntity<Usuario> buscarPorUsername(@PathVariable String username) {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

            /**
     * Endpoint para iniciar sesión.
     * @param usuario Objeto Usuario con username y password.
     * @return Usuario autenticado o mensaje de error si las credenciales no son correctas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        // Debug: Imprimir valores recibidos
        System.out.println("Intento de login - Username: " + usuario.getUsername());
        System.out.println("Intento de login - Password: " + usuario.getPassword());

        // Buscar el usuario en la base de datos
        Usuario user = usuarioService.buscarPorUsername(usuario.getUsername());
        if (user != null) {
            System.out.println("Usuario encontrado: " + user.getUsername());
            if (user.getPassword().equals(usuario.getPassword())) {
                // Validar contraseña
                return ResponseEntity.ok(user); // Devuelve el usuario si las credenciales coinciden
            } else {
                System.out.println("Contraseña incorrecta para el usuario: " + user.getUsername());
            }
        } else {
            System.out.println("Usuario no encontrado: " + usuario.getUsername());
        }
        // Si no coincide, enviar error
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

}
