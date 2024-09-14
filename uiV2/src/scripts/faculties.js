function showAddFacultyForm() {
  const addFacultyForm = document.getElementById('addFacultyForm');
  hideEditFacultyForm();
  addFacultyForm.style.display = 'block';
}

function showEditFacultyForm() {
  const editFacultyForm = document.getElementById('editFacultyForm');
  hideAddFacultyForm();
  editFacultyForm.style.display = 'block';
}

function getFaculties() {

  let headers = new Headers();
  headers.set('Authorization', 'Basic ' + btoa('admin:admin'));

  fetch('http://localhost:8080/faculties', {
    method: 'GET',
    headers: headers,
    //credentials: 'user:passwd'
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
      }
      return response.json(); // Parse JSON response into native JavaScript objects
    })
    .then((data) => {
      console.log(data);
      populateFacultiesTable(data);
    })
    .catch((error) => {
      console.error('Fetch error:', error);
    });
}

async function getFaculty(name) {
  try {
    let headers = new Headers();
    headers.set('Authorization', 'Basic ' + btoa('admin:admin'));

    const response = await 
    fetch(`http://localhost:8080/faculties/${name}`, {
      method: 'GET',
      headers: headers,
      // credentials: 'admin:admin'
    });
   // fetch(`http://localhost:8080/faculties/${name}`);

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

async function getFacultyById(id) {
  try {
    let headers = new Headers();
    headers.set('Authorization', 'Basic ' + btoa('admin:admin'));

    const response = await fetch(`http://localhost:8080/faculties/id/${id}`, {
      method: 'GET',
      headers: headers,
    });
    // fetch(`http://localhost:8080/faculties/id/${id}`);

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

// Как да направим API call в браузъра? - използвайки Fetch Api-то. То връща promise (другата опция е Axios, който не е built-in, но има повече feature-и)

// Какво е promise? - обект репрезентиращ резултата от асинхронна операция.
// promise обект има then() метод, чрез който посочваме какво да се случи след като promise-ът е fullfilled или rejected
// promise-ите могат да бъдат chain-вани с then(), за да се handle-нат няколко асинхронни операции последователно
// promise-ите имат catch метод за error-и в chain-a

// Как изглежда базова GET заявки с Featch api-то?
// fetch('https://api.example.com/data')
//   .then(response => {
//     if (!response.ok) {
//       throw new Error('Network response was not ok ' + response.statusText);
//     }
//     return response.json(); // Parse JSON response into native JavaScript objects
//   })
//   .then(data => {
//     console.log(data); // Handle the data from the API
//   })
//   .catch(error => {
//     console.error('There has been a problem with your fetch operation:', error);
//   });

// Как да направим асинхронна GET заявка аналогична на горната?
// async function getData() {
//     try {
//       const response = await fetch('https://api.example.com/data');
//       if (!response.ok) {
//         throw new Error('Network response was not ok ' + response.statusText);
//       }
//       const data = await response.json();
//       console.log(data);
//     } catch (error) {
//       console.error('There has been a problem with your fetch operation:', error);
//     }
//   }

function populateEditFacultyForm(faculty) {
  const editFacultyForm = document.getElementById('editFacultyForm');

  const fId = editFacultyForm.querySelector('[name="id"]');
  fId.value = faculty.id;

  const fName = editFacultyForm.querySelector('[name="faculty"]');
  fName.value = faculty.name;

  const fAddress = editFacultyForm.querySelector('[name="address"]');
  fAddress.value = faculty.address;

  const fPhone = editFacultyForm.querySelector('[name="phone"]');
  fPhone.value = faculty.phone;

  const fEmail = editFacultyForm.querySelector('[name="email"]');
  fEmail.value = faculty.email;
}

async function editFaculty(name) {
  const faculty = await getFaculty(name);

  hideAddFacultyForm();
  showEditFacultyForm();
  //editFacultyForm.style.display = 'block';
  populateEditFacultyForm(faculty);

  //   sent PUT request
}

function deleteFaculty(name) {
  // let headers = new Headers();
  // headers.set('Authorization', 'Basic ' + btoa('admin:admin'));
  // headers.append

  const url = `http://localhost:8080/faculties/${name}`;

  fetch(url, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json', // Include headers if needed
      'Authorization' : 'Basic ' + btoa('admin:admin'), // Include authorization token if needed
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok ' + response.json());
      }
      console.log('Delete was successful');
      location.reload(); //reload the current web page in order to load the latest content and get rid of the obsolete one(the just deleted record)
    })
    .catch((error) => {
      console.error('There was a problem with the delete operation:', error);
    });
}

function populateFacultiesTable(data) {
  const tableBody = document.querySelector('#facultiesTable tbody');

  // Clear any existing rows (optional, if you're refreshing data)
  tableBody.innerHTML = '';

  // Loop through the data and create table rows
  data.forEach((item) => {
    const row = document.createElement('tr');
    // Create table cells for each property

    const id = document.createElement('td');
    id.setAttribute('class', 'idCell');
    id.textContent = item.id;
    row.appendChild(id);

    const name = document.createElement('td');
    name.setAttribute('class', 'nameCell');
    name.textContent = item.name;
    row.appendChild(name);

    const address = document.createElement('td');
    address.setAttribute('class', 'addressCell');
    address.textContent = item.address;
    row.appendChild(address);

    const phone = document.createElement('td');
    phone.setAttribute('class', 'phoneCell');
    phone.textContent = item.phone;
    row.appendChild(phone);

    const email = document.createElement('td');
    email.setAttribute('class', 'emailCell');
    email.textContent = item.email;
    row.appendChild(email);

    const editBtnTd = document.createElement('td');
    const editBtn = document.createElement('button');
    editBtn.onclick = function () {
      editFaculty(item.name);
    };

    editBtn.textContent = 'Edit';
    editBtnTd.appendChild(editBtn);
    row.appendChild(editBtnTd);

    const deleteBtnTd = document.createElement('td');
    const deleteBtn = document.createElement('button');
    deleteBtn.onclick = function () {
      deleteFaculty(item.name);
    };
    deleteBtn.textContent = 'Delete';
    deleteBtnTd.appendChild(deleteBtn);
    row.appendChild(deleteBtnTd);

    // Append the row to the table body
    tableBody.appendChild(row);
  });
}

//Execute getFaculties() after the page is loaded so we can populate the faculties table
document.addEventListener('DOMContentLoaded', getFaculties());

function hideEditFacultyForm() {
  const editFacultyForm = document.getElementById('editFacultyForm');
  editFacultyForm.style.display = 'none';
}

function hideAddFacultyForm() {
  const addFacultyForm = document.getElementById('addFacultyForm');
  addFacultyForm.style.display = 'none';
}

function addFaculty() {
  const facultyInputFieldValue =
    document.getElementById('facultyInputField').value;
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
        'Authorization' : 'Basic ' + btoa('admin:admin'), // Include authorization token if needed
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
      'Authorization' : 'Basic ' + btoa('admin:admin'), // Include authorization token if needed
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
