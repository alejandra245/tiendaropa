// Paquete al que pertenece esta interfaz, agrupando los repositorios del proyecto.
package com.tuempresa.tiendaropa.repository;

// Importa la clase Producto del modelo, que representa la entidad Producto.
import com.tuempresa.tiendaropa.model.Producto;
// Importa JpaRepository, que proporciona métodos básicos para interactuar con la base de datos.
import org.springframework.data.jpa.repository.JpaRepository;
// Importa la anotación @Repository para marcar esta interfaz como un componente de Spring.
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar la entidad Producto.
 * Extiende JpaRepository para heredar métodos comunes de persistencia.
 */
@Repository // Marca esta interfaz como un repositorio Spring.
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository ya incluye métodos básicos como save(), findAll(), findById(), deleteById(), etc.
    // Métodos personalizados pueden ser añadidos aquí si es necesario.
}
