// import { getFacultyById } from '/src/scripts/faculties.js';

function showAddDepartmentForm() {
    const addDepartmentForm = document.getElementById('addDepartmentForm');
    hideEditDepartmentForm();
    addDepartmentForm.style.display = 'block';
  }
  
  function getDepartments() {
    fetch('http://localhost:8080/departments', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization' : 'Basic ' + btoa('admin:admin'),
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        populateDepartmentsTable(data);
      })
      .catch((error) => {
        console.error('Fetch error:', error);
      });
  }
  
  async function getDepartment(name) {
    try {
      
      const response = await fetch(`http://localhost:8080/departments/${name}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json', // Include headers if needed
          'Authorization' : 'Basic ' + btoa('admin:admin'), // Include authorization token if needed
        },
      })
      //fetch(`http://localhost:8080/departments/${name}`);
  
      if (!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
      }
  
      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      console.error('Fetch error:', error);
    }
  }

//   async function getDepartmentById(id) {
//     try {
//       const response = await fetch(`http://localhost:8080/departments/id/${id}`);
  
//       if (!response.ok) {
//         throw new Error('Network response was not ok ' + response.statusText);
//       }
  
//       const data = await response.json();
//       console.log(data);
//       return data;
//     } catch (error) {
//       console.error('Fetch error:', error);
//     }
//   }
  
  async function populateEditDepartmentForm(department) {
    const editDepartmentForm = document.getElementById('editDepartmentForm');
  
    const fId = editDepartmentForm.querySelector('[name="id"]');
    fId.value = department.id;
  
    const fName = editDepartmentForm.querySelector('[name="department"]');
    fName.value = department.name;
  
    // const faculty = await getFacultyById(department.facultyId);
    const departmentFaculty = editDepartmentForm.querySelector('[name="faculty"]');
    departmentFaculty.value = department.facultyId;//faculty.name;
  
    const fPhone = editDepartmentForm.querySelector('[name="phone"]');
    fPhone.value = department.phone;
  
    const fEmail = editDepartmentForm.querySelector('[name="email"]');
    fEmail.value = department.email;
  }
  
  async function editDepartment(name) {
    const department = await getDepartment(name);
  
    hideAddDepartmentForm();
    showEditDepartmentForm();
    populateEditDepartmentForm(department);
    }
  
  function deleteDepartment(name) {
    const url = `http://localhost:8080/departments/${name}`;
  
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
  
  function populateDepartmentsTable(data) {
    const tableBody = document.querySelector('#departmentsTable tbody');
  
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
  
    //   const faculty = await getFacultyById(department.facultyId);
    //   const departmentFaculty = editDepartmentForm.querySelector('[name="faculty"]');
    //   departmentFaculty.value = faculty.name;

      const facultyId = document.createElement('td');
      facultyId.setAttribute('class', 'addressCell');
      facultyId.textContent = item.facultyId;
      row.appendChild(facultyId);
  
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
        editDepartment(item.name);
      };
  
      editBtn.textContent = 'Edit';
      editBtnTd.appendChild(editBtn);
      row.appendChild(editBtnTd);
  
      const deleteBtnTd = document.createElement('td');
      const deleteBtn = document.createElement('button');
      deleteBtn.onclick = function () {
        deleteDepartment(item.name);
      };
      deleteBtn.textContent = 'Delete';
      deleteBtnTd.appendChild(deleteBtn);
      row.appendChild(deleteBtnTd);
  
      // Append the row to the table body
      tableBody.appendChild(row);
    });
  }
  
  //Execute getDepartments() after the page is loaded so we can populate the departments table
  document.addEventListener('DOMContentLoaded', getDepartments());

  function showEditDepartmentForm() {
    const editDepartmentForm = document.getElementById('editDepartmentForm');
    hideAddDepartmentForm();
    editDepartmentForm.style.display = 'block';
  }
  
  function hideEditDepartmentForm() {
    const editDepartmentForm = document.getElementById('editDepartmentForm');
    editDepartmentForm.style.display = 'none';
  }
  
  function hideAddDepartmentForm() {
    const addDepartmentForm = document.getElementById('addDepartmentForm');
    addDepartmentForm.style.display = 'none';
  }
  
  function addDepartment() {
    const departmentInputFieldValue = document.getElementById('departmentInputField').value;
    console.log(departmentInputFieldValue);
  }
  
  document.addEventListener('DOMContentLoaded', function () {
    const formEl = document.getElementById('addDepartmentForm');
  
    //if this event listener is added outside of the one that is waiting for the whole DOM to be parsed
    //the js script may be executed before the form element is loaded ->  document.getElementById(...) is null
    formEl.addEventListener('submit', function (event) {
      event.preventDefault(); // Prevent the form from submitting the traditional way (refreshing the page)
  
      // Get the form data
      const formData = new FormData(event.target);
  
      // Convert form data to a JSON object (optional but commonly used for APIs)
      const data = {
        name: formData.get('department'),
        facultyId: formData.get('facultyId'),
        phone: formData.get('phone'),
        email: formData.get('email'),
      };
  
      console.log(data); // For debugging, to see the form data
  
      // Make the API request using fetch (this is a POST request example)
      fetch('http://localhost:8080/departments', {
        // Replace with your actual API endpoint
        method: 'POST',
        headers: {
          'Content-Type': 'application/json', // Include headers if needed
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
    const formEl = document.getElementById('editDepartmentForm');
  
    //if this event listener is added outside of the one that is waiting for the whole DOM to be parsed
    //the js script may be executed before the form element is loaded ->  document.getElementById(...) is null
    formEl.addEventListener('submit', function (event) {
      event.preventDefault(); // Prevent the form from submitting the traditional way (refreshing the page)
  
      // Get the form data
      const formData = new FormData(event.target);
  
      // Convert form data to a JSON object (optional but commonly used for APIs)
      const data = {
        id: formData.get('id'),
        name: formData.get('department'),
        facultyId: formData.get('faculty'),
        phone: formData.get('phone'),
        email: formData.get('email'),
      };
  
      console.log(data); // For debugging, to see the form data
  
      // Make the API request using fetch (this is a POST request example)
      fetch(`http://localhost:8080/departments/${data.id}`, {
        // Replace with your actual API endpoint
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json', // Include headers if needed
          'Authorization' : 'Basic ' + btoa('admin:admin'), // Include authorization token if needed
        },
        body: JSON.stringify(data), // Convert the data object to JSON string
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          hideEditDepartmentForm();
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
  
  