document.addEventListener("DOMContentLoaded", function () {
    const passwordField = document.getElementById("contrasena");
    const togglePasswordButton = document.getElementById("togglePassword");
    let isPasswordVisible = false;

    togglePasswordButton.addEventListener("click", function () {
        isPasswordVisible = !isPasswordVisible;
        passwordField.type = isPasswordVisible ? "text" : "password";
        togglePasswordButton.textContent = isPasswordVisible ? "🙈" : "🙉";
    });
});

