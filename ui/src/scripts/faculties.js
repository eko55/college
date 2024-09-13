function showAddFacultyForm() {
  const addFacultyForm = document.getElementById('addFacultyForm');
  hideEditFacultyForm();
  addFacultyForm.style.display = 'block';
}

function getFaculties() {
  fetch('http://localhost:8080/faculties')
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

export async function getFaculty(name) {
  try {
    const response = await fetch(`http://localhost:8080/faculties/${name}`);

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

export async function getFacultyById(id) {
    try {
      const response = await fetch(`http://localhost:8080/faculties/id/${id}`);
  
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
  const url = `http://localhost:8080/faculties/${name}`;

  fetch(url, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json', // Include headers if needed
      //   Authorization: 'Bearer your-token', // Include authorization token if needed
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
