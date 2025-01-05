// Realiza una solicitud para obtener la lista de productos desde el backend
fetch('/api/productos') // Llama a la API en la ruta '/api/productos'.
    .then(response => response.json()) // Convierte la respuesta en formato JSON.
    .then(productos => { // Recibe la lista de productos.
        const productosDiv = document.getElementById('productos'); // Obtiene el contenedor donde se mostrarán los productos.
        
        // Itera sobre cada producto recibido de la API.
        productos.forEach(producto => {
            const div = document.createElement('div'); // Crea un contenedor `<div>` para el producto.
            div.classList.add('producto'); // Agrega una clase CSS para estilizar el producto.
            div.innerHTML = ` 
                <h2>${producto.nombre}</h2> <!-- Muestra el nombre del producto. -->
                <p>Precio: $${producto.precio}</p> <!-- Muestra el precio del producto. -->
                <p>${producto.descripcion}</p> <!-- Muestra la descripción del producto. -->
                <img src="${producto.imagen}" alt="${producto.nombre}"> <!-- Muestra la imagen del producto con un texto alternativo. -->
            `;
            productosDiv.appendChild(div); // Agrega el contenedor del producto al contenedor principal.
        });
    })
    .catch(error => console.error('Error al cargar productos:', error)); // Maneja errores en caso de fallar la solicitud.
