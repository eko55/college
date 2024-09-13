function getTeachers() {
  fetch('http://localhost:8080/teachers')
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
      }
      return response.json(); // Parse JSON response into native JavaScript objects
    })
    .then((data) => {
      console.log(data);
      populateTeachersTable(data);
    })
    .catch((error) => {
      console.error('Fetch error:', error);
    });
}

function deleteTeacher(id) {
  // URL of the resource to delete
  const url = `http://localhost:8080/teachers/${id}`; // Replace with your API URL and resource ID

  // Perform the DELETE request
  fetch(url, {
    method: 'DELETE', // Specify the request method
    headers: {
      'Content-Type': 'application/json', // Include headers if needed
      //   'Access-Control-Allow-Origin':
      //   Authorization: 'Bearer your-token', // Include authorization token if needed
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json(); // or response.text() if expecting a text response
    })
    .then((data) => {
      console.log('Delete successful:', data);
    })
    .catch((error) => {
      console.error('There was a problem with the delete operation:', error);
    });
}

async function getTeacher(editTeacherForm) {
  try {
    const response = await fetch(`http://localhost:8080/teachers/${editTeacherForm}`);

    if (!response.ok) {
      throw new Error('Network response was not ok ' + response.statusText);
    }

    const data = await response.json();
    console.log(data);
    return data; // Parse JSON response into native JavaScript objects
  } catch (error) {
    console.error('Fetch error:', error);
  }
}

async function editTeacher(personalNumber) {
  const teacher = await getTeacher(personalNumber);

  hideAddTeacherForm();
  showEditTeacherForm();
  populateEditTeacherForm(teacher);
}

function populateEditTeacherForm(teacher) {
  const editTeacherForm = document.getElementById('editTeacherForm');

  const personalNumber = editTeacherForm.querySelector('[name="personalNumber"]');
  personalNumber.value = teacher.personalNumber;

  const name = editTeacherForm.querySelector('[name="name"]');
  name.value = teacher.name;

  const lastName = editTeacherForm.querySelector('[name="lastName"]');
  lastName.value = teacher.lastName;

  const department = editTeacherForm.querySelector('[name="department"]');
  department.value = teacher.department;
}

function populateTeachersTable(data) {
  const tableBody = document.querySelector('#teachersTable tbody');

  // Clear any existing rows (optional, if you're refreshing data)
  tableBody.innerHTML = '';

  // Loop through the data and create table rows
  data.forEach((item) => {
    const row = document.createElement('tr');

    // Create table cells for each property
    const personalNumber = document.createElement('td');
    personalNumber.textContent = item.personalNumber;
    row.appendChild(personalNumber);

    const firstName = document.createElement('td');
    firstName.textContent = item.firstName;
    row.appendChild(firstName);

    const lastName = document.createElement('td');
    lastName.textContent = item.lastName;
    row.appendChild(lastName);

    const departmentId = document.createElement('td');
    departmentId.textContent = item.departmentId;
    row.appendChild(departmentId);

    const editBtnTd = document.createElement('td');
    const editBtn = document.createElement('button');
    editBtn.onclick = function () {
      editTeacher(item.personalNumber);
    };
    editBtn.textContent = 'Edit';
    editBtnTd.appendChild(editBtn);
    row.appendChild(editBtnTd);

    const deleteBtnTd = document.createElement('td');
    const deleteBtn = document.createElement('button');
    deleteBtn.onclick = function () {
      deleteTeacher(personalNumber.textContent);
    };
    deleteBtn.textContent = 'Delete';
    deleteBtnTd.appendChild(deleteBtn);
    row.appendChild(deleteBtnTd);

    // Append the row to the table body
    tableBody.appendChild(row);
  });
}

function showAddTeacherForm() {
  const addTeacherForm = document.getElementById('addTeacherForm');
  hideEditTeacherForm();
  addTeacherForm.style.display = 'block';
}

function showEditTeacherForm() {
  const editTeacherForm = document.getElementById('editTeacherForm');
  hideAddTeacherForm();
  editTeacherForm.style.display = 'block';
}

function hideEditTeacherForm() {
  const editTeacherForm = document.getElementById('editTeacherForm');
  editTeacherForm.style.display = 'none';
}

function hideAddTeacherForm() {
  const addTeacherForm = document.getElementById('addTeacherForm');
  addTeacherForm.style.display = 'none';
}

function addTeacher() {
  const teacherInputFieldValue = document.getElementById('teacherInputField').value;
  console.log(teacherInputFieldValue);
}

//Execute getFaculties() after the page is loaded so we can populate the faculties table
document.addEventListener('DOMContentLoaded', getTeachers());

document.addEventListener('DOMContentLoaded', function () {
  const formEl = document.getElementById('addTeacherForm');

  //if this event listener is added outside of the one that is waiting for the whole DOM to be parsed
  //the js script may be executed before the form element is loaded ->  document.getElementById(...) is null
  formEl.addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way (refreshing the page)

    // Get the form data
    const formData = new FormData(event.target);

    // Convert form data to a JSON object (optional but commonly used for APIs)
    const data = {
      name: formData.get('teacher'),
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
  const formEl = document.getElementById('editTeacherForm');

  //if this event listener is added outside of the one that is waiting for the whole DOM to be parsed
  //the js script may be executed before the form element is loaded ->  document.getElementById(...) is null
  formEl.addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way (refreshing the page)

    // Get the form data
    const formData = new FormData(event.target);

    // Convert form data to a JSON object (optional but commonly used for APIs)
    const data = {
      id: formData.get('id'),
      name: formData.get('teacher'),
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
        hideEditTeacherForm();
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
