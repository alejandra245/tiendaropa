document.getElementById('register-form').addEventListener('submit', (e) => {
    e.preventDefault(); // Evita el comportamiento por defecto del formulario

    const usuario = {
        username: document.getElementById('username').value.trim(),
        password: document.getElementById('password').value.trim(),
        role: document.getElementById('role').value
    };

    // Validar que los campos no estén vacíos
    if (!usuario.username || !usuario.password || !usuario.role) {
        alert('Por favor, completa todos los campos');
        return;
    }

    // Enviar la solicitud al backend
    fetch('/api/usuarios', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al registrar el usuario');
            }
            return response.json();
        })
        .then(data => {
            alert('Usuario registrado con éxito');
            window.location.href = 'login.html'; // Redirige al login
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un problema al registrar el usuario');
        });
});
