function showEditFacultyForm() {
  const editFacultyForm = document.getElementById('editFacultyForm');
  hideAddFacultyForm();
  editFacultyForm.style.display = 'block';
}

function hideEditFacultyForm() {
  const editFacultyForm = document.getElementById('editFacultyForm');
  editFacultyForm.style.display = 'none';
}

function hideAddFacultyForm() {
  const addFacultyForm = document.getElementById('addFacultyForm');
  addFacultyForm.style.display = 'none';
}

function addFaculty() {
  const facultyInputFieldValue = document.getElementById('facultyInputField').value;
  console.log(facultyInputFieldValue);
}

document.addEventListener('DOMContentLoaded', function () {
  const formEl = document.getElementById('addFacultyForm');

  //if this event listener is added outside of the one that is waiting for the whole DOM to be parsed
  //the js script may be executed before the form element is loaded ->  document.getElementById(...) is null
  formEl.addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way (refreshing the page)

    // Get the form data
    const formData = new FormData(event.target);

    // Convert form data to a JSON object (optional but commonly used for APIs)
    const data = {
      name: formData.get('faculty'),
      address: formData.get('address'),
      phone: formData.get('phone'),
      email: formData.get('email'),
    };

    console.log(data); // For debugging, to see the form data

    // Make the API request using fetch (this is a POST request example)
    fetch('http://localhost:8080/faculties', {
      // Replace with your actual API endpoint
      method: 'POST',
      headers: {
        'Content-Type': 'application/json', // Send JSON data
        // 'Authorization': 'Bearer your-token', // Add if authentication is required
      },
      body: JSON.stringify(data), // Convert the data object to JSON string
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the JSON response
      })
      .then((data) => {
        console.log('Success:', data); // Handle the API response
        location.reload(); //display newly created data
      })
      .catch((error) => {
        console.error('There was a problem with the submission:', error);
        alert('Failed to submit the form.');
      });
  });
});

document.addEventListener('DOMContentLoaded', function () {
  const formEl = document.getElementById('editFacultyForm');

  //if this event listener is added outside of the one that is waiting for the whole DOM to be parsed
  //the js script may be executed before the form element is loaded ->  document.getElementById(...) is null
  formEl.addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way (refreshing the page)

    // Get the form data
    const formData = new FormData(event.target);

    // Convert form data to a JSON object (optional but commonly used for APIs)
    const data = {
      id: formData.get('id'),
      name: formData.get('faculty'),
      address: formData.get('address'),
      phone: formData.get('phone'),
      email: formData.get('email'),
    };

    console.log(data); // For debugging, to see the form data

    // Make the API request using fetch (this is a POST request example)
    fetch(`http://localhost:8080/faculties/${data.id}`, {
      // Replace with your actual API endpoint
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json', // Send JSON data
        // 'Authorization': 'Bearer your-token', // Add if authentication is required
      },
      body: JSON.stringify(data), // Convert the data object to JSON string
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        hideEditFacultyForm();
        return response.json(); // Parse the JSON response
      })
      .then((data) => {
        console.log('Success:', data); // Handle the API response
        location.reload(); //display newly created data
      })
      .catch((error) => {
        console.error('There was a problem with the submission:', error);
        alert('Failed to submit the form.');
      });
  });
});
