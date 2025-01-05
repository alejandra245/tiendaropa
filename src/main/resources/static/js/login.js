// Escuchar el evento de envío del formulario
document.getElementById('login-form').addEventListener('submit', (e) => {
    e.preventDefault(); // Prevenir el comportamiento por defecto del formulario

    // Obtener los valores del formulario
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    // Realizar la solicitud POST al backend
    fetch('/api/usuarios/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password }) // Enviar los datos como JSON
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error('Credenciales incorrectas');
            }
            return response.json(); // Convertir la respuesta a JSON
        })
        .then((data) => {
            // Redirigir según el rol del usuario
            if (data.role === 'Administrador') {
                window.location.href = 'admin.html';
            } else if (data.role === 'Usuario') {
                window.location.href = 'cliente.html';
            }
        })
        .catch((error) => {
            alert(error.message); // Mostrar un mensaje de error si ocurre
        });
});
