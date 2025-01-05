// Paquete al que pertenece esta clase, que agrupa las entidades del modelo.
package com.tuempresa.tiendaropa.model;

// Importa las anotaciones de Jakarta Persistence API (JPA) para la configuración de la entidad.
import jakarta.persistence.*;

/**
 * Entidad que representa a los productos que se venden en la tienda.
 * Cada producto tiene atributos como nombre, precio, imagen, descripción y stock.
 */
@Entity // Indica que esta clase es una entidad JPA, mapeada a una tabla en la base de datos.
@Table(name = "producto") // Especifica el nombre de la tabla asociada a esta entidad.
public class Producto {

    @Id // Indica que este campo es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // Define la estrategia de generación del valor de la clave primaria. 
    // Aquí se usa `IDENTITY`, donde el valor es generado por la base de datos automáticamente.
    private Long id;

    @Column(nullable = false) // Indica que este campo no puede ser nulo en la base de datos.
    private String nombre;

    @Column(nullable = false) // El precio del producto tampoco puede ser nulo.
    private Double precio;

    // Campo para almacenar la ruta o URL de la imagen del producto.
    private String imagen;

    @Column(length = 500) // Especifica un límite de 500 caracteres para la descripción.
    private String descripcion;

    @Column(nullable = false) // El stock del producto no puede ser nulo.
    private Integer stock;

    // Getters y Setters para acceder y modificar los atributos de la clase.

    public Long getId() {
        return id; // Devuelve el ID del producto.
    }

    public void setId(Long id) {
        this.id = id; // Establece el ID del producto.
    }

    public String getNombre() {
        return nombre; // Devuelve el nombre del producto.
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el nombre del producto.
    }

    public Double getPrecio() {
        return precio; // Devuelve el precio del producto.
    }

    public void setPrecio(Double precio) {
        this.precio = precio; // Establece el precio del producto.
    }

    public String getImagen() {
        return imagen; // Devuelve la ruta o URL de la imagen.
    }

    public void setImagen(String imagen) {
        this.imagen = imagen; // Establece la ruta o URL de la imagen.
    }

    public String getDescripcion() {
        return descripcion; // Devuelve la descripción del producto.
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; // Establece la descripción del producto.
    }

    public Integer getStock() {
        return stock; // Devuelve la cantidad en stock del producto.
    }

    public void setStock(Integer stock) {
        this.stock = stock; // Establece la cantidad en stock del producto.
    }
}
