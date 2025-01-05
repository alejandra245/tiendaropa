// Paquete donde se encuentra la clase, que agrupa las clases relacionadas en el proyecto.
package com.tuempresa.tiendaropa.controller;

// Importa la clase Producto del modelo, que representa la entidad Producto.
import com.tuempresa.tiendaropa.model.Producto;
// Importa el servicio ProductoService que contiene la lógica de negocio para manejar productos.
import com.tuempresa.tiendaropa.service.ProductoService;

// Importa la clase ResponseEntity, que permite personalizar las respuestas HTTP.
import org.springframework.http.ResponseEntity;
// Importa las anotaciones necesarias para manejar solicitudes HTTP RESTful.
import org.springframework.web.bind.annotation.*;

import java.util.List; // Importa la clase List para trabajar con listas de productos.

/**
 * Controlador REST para manejar las operaciones relacionadas con los productos.
 */
@RestController // Indica que esta clase es un controlador REST en Spring.
@RequestMapping("/api/productos") // Define el prefijo de las rutas para todas las operaciones de este controlador.
public class ProductoController {

    // Inyección de dependencia del servicio ProductoService para manejar la lógica de negocio.
    private final ProductoService productoService;

    // Constructor que inicializa el servicio mediante inyección de dependencias.
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Maneja solicitudes GET para obtener la lista de productos.
     * @return Lista de todos los productos disponibles.
     */
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos(); // Llama al servicio para obtener la lista de productos.
    }

    /**
     * Maneja solicitudes POST para registrar un nuevo producto.
     * @param producto Objeto Producto enviado en el cuerpo de la solicitud.
     * @return El producto registrado después de guardarlo en la base de datos.
     */
    @PostMapping
    public Producto registrarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto); // Guarda el nuevo producto y lo retorna.
    }

    /**
     * Maneja solicitudes DELETE para eliminar un producto por su ID.
     * @param id ID del producto que se desea eliminar.
     */
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id); // Llama al servicio para eliminar el producto por ID.
    }

    /**
     * Maneja solicitudes PUT para actualizar un producto existente.
     * @param id ID del producto que se desea actualizar.
     * @param producto Objeto Producto con los datos actualizados.
     * @return El producto actualizado o una respuesta 404 si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        // Busca el producto existente por ID.¿
        Producto productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente == null) { // Si no se encuentra, retorna una respuesta 404.
            return ResponseEntity.notFound().build();
        }
        // Actualiza los campos del producto existente con los nuevos datos.
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setStock(producto.getStock());
        productoExistente.setImagen(producto.getImagen()); // Actualiza el campo de imagen.
        
        // Guarda los cambios del producto actualizado.
        productoService.guardarProducto(productoExistente);
        return ResponseEntity.ok(productoExistente); // Retorna el producto actualizado.
    }

    /**
     * Maneja solicitudes GET para obtener un producto por su ID.
     * @param id ID del producto solicitado.
     * @return El producto si existe, o una respuesta 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id); // Busca el producto por ID.
        if (producto == null) { // Si no se encuentra, retorna una respuesta 404.
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto); // Retorna el producto encontrado.
    }
}
