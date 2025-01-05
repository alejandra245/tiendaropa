// Referencias a los elementos del DOM
const formProducto = document.getElementById('form-producto'); // Formulario para agregar o editar productos.
const listaProductos = document.getElementById('lista-productos'); // Contenedor donde se mostrarán los productos.
const listaUsuarios = document.getElementById('lista-usuarios'); // Contenedor donde se mostrarán los usuarios.
const logoutButton = document.getElementById('logout'); // Botón para cerrar sesión.

// Función para cerrar sesión
logoutButton.addEventListener('click', () => {
    sessionStorage.clear(); // Limpia el almacenamiento de sesión.
    window.location.href = 'login.html'; // Redirige a la página de inicio de sesión.
});

// Cargar usuarios y productos al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    cargarUsuarios(); // Llama a la función para cargar los usuarios.
    cargarProductos(); // Llama a la función para cargar los productos.
});

// Función para cargar la lista de usuarios
function cargarUsuarios() {
    fetch('/api/usuarios') // Solicita la lista de usuarios a la API.
        .then(response => response.json())
        .then(usuarios => {
            listaUsuarios.innerHTML = ''; // Limpia la lista antes de llenarla.
            usuarios.forEach(usuario => {
                const li = document.createElement('li'); // Crea un elemento de lista.
                li.textContent = `Nombre: ${usuario.username}, Rol: ${usuario.role}`; // Agrega el texto del usuario.
                listaUsuarios.appendChild(li); // Agrega el elemento a la lista.
            });
        })
        .catch(error => console.error('Error al cargar usuarios:', error)); // Manejo de errores.
}

// Función para cargar la lista de productos
function cargarProductos() {
    fetch('/api/productos') // Solicita la lista de productos a la API.
        .then(response => response.json())
        .then(productos => {
            listaProductos.innerHTML = ''; // Limpia la lista antes de llenarla.
            productos.forEach(producto => {
                const div = document.createElement('div'); // Crea un contenedor para cada producto.
                div.classList.add('producto'); // Agrega una clase para estilos CSS.
                div.innerHTML = `
                    <h3>${producto.nombre}</h3>
                    <p>Precio: $${producto.precio}</p>
                    <p>Descripción: ${producto.descripcion}</p>
                    <p>Stock: ${producto.stock}</p>
                    <img src="${producto.imagen}" alt="${producto.nombre}" style="width: 100px; height: auto;" />
                    <button onclick="editarProducto(${producto.id})">Editar</button>
                    <button onclick="eliminarProducto(${producto.id})">Eliminar</button>
                `; // Genera el contenido HTML del producto.
                listaProductos.appendChild(div); // Agrega el contenedor al DOM.
            });
        })
        .catch(error => console.error('Error al cargar productos:', error)); // Manejo de errores.
}

// Función para agregar o editar un producto
formProducto.addEventListener('submit', (e) => {
    e.preventDefault(); // Previene el comportamiento por defecto del formulario.

    const producto = {
        id: document.getElementById('id').value || null, // Si hay un ID, es edición.
        nombre: document.getElementById('nombre').value,
        precio: parseFloat(document.getElementById('precio').value), // Convierte el precio a número flotante.
        descripcion: document.getElementById('descripcion').value,
        stock: parseInt(document.getElementById('stock').value), // Convierte el stock a número entero.
        imagen: document.getElementById('imagen').value
    };

    const method = producto.id ? 'PUT' : 'POST'; // Si hay ID, usa PUT; si no, usa POST.
    const url = producto.id ? `/api/productos/${producto.id}` : '/api/productos';

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(producto) // Envía el producto como JSON.
    })
        .then(() => {
            cargarProductos(); // Recarga la lista de productos.
            formProducto.reset(); // Limpia el formulario.
            document.getElementById('id').value = ''; // Limpia el campo oculto del ID.
        })
        .catch(error => console.error('Error al guardar el producto:', error)); // Manejo de errores.
});

// Función para editar un producto
function editarProducto(id) {
    fetch(`/api/productos/${id}`) // Solicita el producto por su ID.
        .then(response => response.json())
        .then(producto => {
            // Carga los datos del producto en el formulario.
            document.getElementById('id').value = producto.id;
            document.getElementById('nombre').value = producto.nombre;
            document.getElementById('precio').value = producto.precio;
            document.getElementById('descripcion').value = producto.descripcion;
            document.getElementById('stock').value = producto.stock;
            document.getElementById('imagen').value = producto.imagen; // Agrega el campo de imagen.
        })
        .catch(error => console.error('Error al cargar el producto para editar:', error)); // Manejo de errores.
}

// Función para eliminar un producto
function eliminarProducto(id) {
    fetch(`/api/productos/${id}`, { method: 'DELETE' }) // Envía una solicitud DELETE.
        .then(() => cargarProductos()) // Recarga la lista de productos.
        .catch(error => console.error('Error al eliminar el producto:', error)); // Manejo de errores.
}
