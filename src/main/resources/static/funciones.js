// funciones.js
document.addEventListener('DOMContentLoaded', function () {
    var mostrarContrasenaCheckbox = document.getElementById('mostrarContrasena');
    var contrasenaInput = document.getElementById('contrasena');

    mostrarContrasenaCheckbox.addEventListener('change', function () {
        if (this.checked) {
            contrasenaInput.setAttribute('type', 'text');
        } else {
            contrasenaInput.setAttribute('type', 'password');
        }
    });
});

