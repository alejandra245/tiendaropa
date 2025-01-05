// Referencias a los elementos del DOM
const listaProductos = document.getElementById('lista-productos'); // Contenedor para mostrar los productos en el catálogo.

// Función para cargar productos en el catálogo
function cargarProductos() {
    console.log('Cargando productos...');
    fetch('/api/productos') // Conecta con el backend para obtener los productos.
        .then(response => {
            if (!response.ok) { // Verifica si la respuesta no es exitosa.
                throw new Error('Error al obtener los productos');
            }
            return response.json(); // Convierte la respuesta en formato JSON.
        })
        .then(productos => {
            console.log('Productos obtenidos:', productos);
            listaProductos.innerHTML = ''; // Limpia la lista antes de cargar los productos.
            productos.forEach(producto => { // Itera sobre cada producto obtenido.
                const div = document.createElement('div'); // Crea un contenedor para cada producto.
                div.classList.add('producto-card'); // Agrega una clase CSS para el estilo.
                div.innerHTML = `
                    <h3>${producto.nombre}</h3> <!-- Nombre del producto -->
                    <img src="${producto.imagen}" alt="${producto.nombre}" /> <!-- Imagen del producto -->
                    <p>Precio: $${producto.precio.toFixed(2)}</p> <!-- Precio del producto -->
                    <p>Descripción: ${producto.descripcion}</p> <!-- Descripción del producto -->
                    <p>Stock: ${producto.stock > 0 ? producto.stock : '<span class="agotado">No disponible</span>'}</p> <!-- Stock del producto -->
                    ${
                        producto.stock > 0
                            ? `<button onclick="comprarProducto(${producto.id})">Comprar</button>` // Botón de compra si hay stock.
                            : '<span class="agotado">Agotado</span>' // Mensaje de agotado si no hay stock.
                    }
                `;
                listaProductos.appendChild(div); // Agrega el producto al contenedor de la lista.
            });
        })
        .catch(error => { // Manejo de errores.
            console.error('Error al cargar productos:', error);
            alert('Error al cargar los productos. Por favor, intenta más tarde.');
        });
}

// Función para comprar un producto
function comprarProducto(id) {
    console.log(`Intentando comprar producto con ID: ${id}`);
    fetch(`/api/productos/${id}`) // Obtiene los detalles del producto.
        .then(response => {
            if (!response.ok) { // Verifica si la respuesta no es exitosa.
                throw new Error('Error al obtener el producto');
            }
            return response.json(); // Convierte la respuesta en formato JSON.
        })
        .then(producto => {
            console.log('Producto obtenido para compra:', producto);
            if (producto.stock > 0) { // Verifica si hay stock disponible.
                const updatedProduct = { ...producto, stock: producto.stock - 1 }; // Reduce el stock en 1.
                console.log('Actualizando stock con:', updatedProduct);
                return fetch(`/api/productos/${id}`, { // Actualiza el producto en el backend.
                    method: 'PUT', // Usa el método PUT para actualizar.
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(updatedProduct), // Envía los datos actualizados.
                });
            } else {
                alert('Este producto no tiene stock disponible');
                throw new Error('Sin stock disponible'); // Genera un error si no hay stock.
            }
        })
        .then(response => {
            if (!response.ok) { // Verifica si la actualización fue exitosa.
                throw new Error('Error al actualizar el stock del producto');
            }
            return response.json(); // Convierte la respuesta en formato JSON.
        })
        .then(productoActualizado => {
            console.log('Producto actualizado exitosamente:', productoActualizado);
            generarPDF(productoActualizado); // Genera un PDF de la compra.
            cargarProductos(); // Actualiza la lista de productos.
            alert('Compra realizada con éxito');
        })
        .catch(error => { // Manejo de errores.
            console.error('Error al realizar la compra:', error);
            alert('Hubo un problema al realizar la compra. Por favor, intenta más tarde.');
        });
}

// Función para generar el PDF
function generarPDF(producto) {
    try {
        const { jsPDF } = window.jspdf; // Obtiene la clase jsPDF de la biblioteca.
        const doc = new jsPDF(); // Crea una nueva instancia de jsPDF.

        // Agrega contenido al PDF
        doc.setFontSize(16);
        doc.text('Compra realizada con éxito:', 10, 10);
        doc.setFontSize(12);
        doc.text(`Producto: ${producto.nombre}`, 10, 20);
        doc.text(`Precio: $${producto.precio.toFixed(2)}`, 10, 30);
        doc.text(`Descripción: ${producto.descripcion}`, 10, 40);
        doc.text('Gracias por tu compra.', 10, 50);

        // Descarga automáticamente el PDF
        doc.save(`Compra_${producto.nombre}.pdf`); // Nombre del archivo descargado.
    } catch (error) {
        console.error('Error al generar el PDF:', error); // Manejo de errores al generar el PDF.
    }
}

// Llamada para cargar productos al cargar la página
document.addEventListener('DOMContentLoaded', cargarProductos); // Carga los productos al cargar la página.
