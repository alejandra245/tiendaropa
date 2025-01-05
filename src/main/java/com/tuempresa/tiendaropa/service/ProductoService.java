// Paquete al que pertenece esta clase, que agrupa los servicios del proyecto.
package com.tuempresa.tiendaropa.service;

// Importa la clase Producto del modelo, que representa la entidad Producto.
import com.tuempresa.tiendaropa.model.Producto;
// Importa el repositorio ProductoRepository para interactuar con la base de datos.
import com.tuempresa.tiendaropa.repository.ProductoRepository;
// Importa la anotación @Service para indicar que esta clase es un servicio Spring.
import org.springframework.stereotype.Service;

import java.util.List; // Importa la clase List para manejar listas de productos.

/**
 * Servicio para manejar la lógica relacionada con los productos.
 */
@Service // Marca esta clase como un componente de servicio en Spring.
public class ProductoService {

    // Repositorio para interactuar con la base de datos.
    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyectar el repositorio de productos.
     * @param productoRepository Repositorio de productos.
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene todos los productos de la base de datos.
     * @return Lista de productos.
     */
    public List<Producto> listarProductos() {
        return productoRepository.findAll(); // Llama al repositorio para obtener todos los productos.
    }

    /**
     * Guarda un producto en la base de datos.
     * Si el producto ya existe (tiene un ID), se actualiza.
     * @param producto Producto a guardar.
     * @return Producto guardado.
     */
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto); // Llama al repositorio para guardar o actualizar el producto.
    }

    /**
     * Elimina un producto por su ID.
     * @param id ID del producto a eliminar.
     */
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id); // Llama al repositorio para eliminar el producto por ID.
    }

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto.
     * @return Producto si se encuentra, o null si no existe.
     */
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null); 
        // Llama al repositorio para buscar el producto por ID.
        // Si no lo encuentra, retorna null.
    }
}
