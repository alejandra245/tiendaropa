// Paquete al que pertenece esta interfaz, que agrupa los repositorios del proyecto.
package com.tuempresa.tiendaropa.repository;

// Importa la clase Usuario del modelo, que representa la entidad Usuario.
import com.tuempresa.tiendaropa.model.Usuario;
// Importa JpaRepository, que proporciona métodos básicos para interactuar con la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;
// Importa la anotación @Repository para marcar esta interfaz como un componente de Spring.
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar la entidad Usuario.
 * Extiende JpaRepository para heredar métodos comunes de persistencia y permite personalización.
 */
@Repository // Marca esta interfaz como un repositorio Spring.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Método personalizado para buscar un usuario por su nombre de usuario.
     * @param username Nombre de usuario que se busca.
     * @return El usuario encontrado o null si no existe.
     */
    Usuario findByUsername(String username);
}
