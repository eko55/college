document.addEventListener('DOMContentLoaded', function () {
  const formEl = document.getElementById('registerForm');

  formEl.addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
    const errorMessage = document.getElementById('error-message');
    const successMessage = document.getElementById('success-message');

    errorMessage.textContent = '';
    successMessage.textContent = '';

    const data = { username, password, email };

    try {
      const response = await fetch('http://localhost:8080/users/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
        .then((response) => {
          if (!response.ok) {
            response.message = 'Registration failed. Please try again.';
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then((data) => {
          console.log('Success:', data);
          successMessage.textContent =
            'Registration successful! Redirecting to login...';

          window.location.href = 'http://localhost:5501/login.html';
        })
        .catch((error) => {
          console.error('There was a problem with the submission:', error);
          alert('Username already in use!');
        });
    } catch (error) {
      console.error('Error during login:', error);
    }
  });
});

