// Escuchar el evento 'submit' del formulario
document.getElementById('register-form').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario.

    // Obtener los valores del formulario
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    // Crear el objeto del usuario
    const nuevoUsuario = {
        username: username,
        password: password,
        role: role,
    };

    // Enviar los datos al backend
    fetch('/api/usuarios', {
        method: 'POST', // Método HTTP
        headers: {
            'Content-Type': 'application/json', // Especificar que los datos son JSON
        },
        body: JSON.stringify(nuevoUsuario), // Convertir el objeto en JSON
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error('Error al registrar el usuario');
            }
            return response.json(); // Convertir la respuesta a JSON
        })
        .then((data) => {
            alert('Usuario registrado con éxito'); // Mostrar mensaje de éxito
            window.location.href = 'login.html'; // Redirigir a la página de inicio de sesión
        })
        .catch((error) => {
            console.error('Error al registrar el usuario:', error); // Mostrar el error en la consola
            alert('Hubo un problema al registrar el usuario.');
        });
});
