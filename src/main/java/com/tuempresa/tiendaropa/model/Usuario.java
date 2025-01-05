// Paquete al que pertenece esta clase, que agrupa las entidades del modelo.
package com.tuempresa.tiendaropa.model;

// Importa las anotaciones de Jakarta Persistence API (JPA) para la configuración de la entidad.
import jakarta.persistence.*;

/**
 * Entidad que representa a los usuarios de la tienda.
 * Cada usuario tiene un nombre de usuario, contraseña y un rol asociado.
 */
@Entity // Indica que esta clase es una entidad JPA, mapeada a una tabla en la base de datos.
@Table(name = "user") // Especifica el nombre de la tabla asociada a esta entidad.
public class Usuario {

    @Id // Indica que este campo es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // Define la estrategia de generación del valor de la clave primaria (generado por la base de datos).
    private Long id; // ID único para cada usuario.

    @Column(nullable = false, unique = true) 
    // Indica que el campo username es obligatorio y debe ser único en la base de datos.
    private String username; // Nombre de usuario.

    @Column(nullable = false) 
    // Indica que el campo password es obligatorio.
    private String password; // Contraseña del usuario.

    @Enumerated(EnumType.STRING) 
    // Especifica que el campo `role` se almacenará como un string (el nombre del valor del enum).
    @Column(nullable = false) 
    // Indica que el campo role es obligatorio.
    private Rol role; // Rol del usuario (Administrador o Usuario).

    // Getters y Setters para acceder y modificar los atributos de la clase.

    public Long getId() {
        return id; // Devuelve el ID del usuario.
    }

    public void setId(Long id) {
        this.id = id; // Establece el ID del usuario.
    }

    public String getUsername() {
        return username; // Devuelve el nombre de usuario.
    }

    public void setUsername(String username) {
        this.username = username; // Establece el nombre de usuario.
    }

    public String getPassword() {
        return password; // Devuelve la contraseña del usuario.
    }

    public void setPassword(String password) {
        this.password = password; // Establece la contraseña del usuario.
    }

    public Rol getRole() {
        return role; // Devuelve el rol del usuario.
    }

    public void setRole(Rol role) {
        this.role = role; // Establece el rol del usuario.
    }
}
