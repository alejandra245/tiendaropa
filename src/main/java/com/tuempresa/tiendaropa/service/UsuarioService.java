// Paquete al que pertenece esta clase, que agrupa los servicios del proyecto.
package com.tuempresa.tiendaropa.service;

// Importa la clase Rol, que define los roles de usuario.
import com.tuempresa.tiendaropa.model.Rol;
// Importa la clase Usuario, que representa la entidad Usuario.
import com.tuempresa.tiendaropa.model.Usuario;
// Importa el repositorio UsuarioRepository para interactuar con la base de datos.
import com.tuempresa.tiendaropa.repository.UsuarioRepository;
// Importa la anotación @Service para indicar que esta clase es un servicio Spring.
import org.springframework.stereotype.Service;

import java.util.List; // Importa la clase List para manejar listas de usuarios.
import java.util.stream.Collectors; // Importa Collectors para filtrar listas usando streams.

/**
 * Servicio para manejar la lógica relacionada con los usuarios.
 */
@Service // Marca esta clase como un componente de servicio en Spring.
public class UsuarioService {

    // Repositorio para interactuar con la base de datos de usuarios.
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor para inyectar el repositorio de usuarios.
     * @param usuarioRepository Repositorio de usuarios.
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Listar todos los usuarios registrados.
     * @return Lista de todos los usuarios.
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll(); // Obtiene todos los usuarios de la base de datos.
    }

    /**
     * Listar usuarios con rol de Administrador.
     * @return Lista de usuarios con rol Administrador.
     */
    public List<Usuario> listarAdministradores() {
        return usuarioRepository.findAll() // Obtiene todos los usuarios.
                                .stream() // Crea un stream para procesar la lista.
                                .filter(usuario -> usuario.getRole() == Rol.Administrador) // Filtra los usuarios con rol Administrador.
                                .collect(Collectors.toList()); // Convierte el stream filtrado de nuevo a una lista.
    }

    /**
     * Listar usuarios con rol de Usuario (Clientes).
     * @return Lista de usuarios con rol Usuario.
     */
    public List<Usuario> listarClientes() {
        return usuarioRepository.findAll() // Obtiene todos los usuarios.
                                .stream() // Crea un stream para procesar la lista.
                                .filter(usuario -> usuario.getRole() == Rol.Usuario) // Filtra los usuarios con rol Usuario.
                                .collect(Collectors.toList()); // Convierte el stream filtrado de nuevo a una lista.
    }

    /**
     * Guardar un nuevo usuario en la base de datos.
     * @param usuario Usuario a guardar.
     * @return Usuario guardado.
     */
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario); // Guarda el usuario en la base de datos.
    }

    /**
     * Buscar un usuario por su nombre de usuario.
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username); // Busca el usuario por su nombre de usuario.
    }

    /**
     * Buscar un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null); 
        // Busca el usuario por ID y devuelve null si no lo encuentra.
    }

    /**
     * Eliminar un usuario por su ID.
     * @param id ID del usuario a eliminar.
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id); // Elimina el usuario por su ID.
    }
}
