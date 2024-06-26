import { useState } from 'react';
import '../styles/College.css';

const College = () => {
  const [responseData, setResponseData] = useState([]);
  const [departmentsResponseData, setDepartmentsResponseData] = useState([]);
  const [coursesResponseData, setCoursesResponseData] = useState([]);
  const [
    coursesRegistrationsResponseData,
    setCoursesRegistrationsResponseData,
  ] = useState([]);
  const [studentsResponseData, setStudentsResponseData] = useState([]);
  const [teachersResponseData, setTeachersResponseData] = useState([]);
  const [headOfDepartmentsResponseData, setHeadOfDepartmentsResponseData] =
    useState([]);

  const [file, setFile] = useState(null);
  // const [style, setStyle] = useState('');

  const formData = new FormData();
  formData.append('file', file);

  const handleFileUpload = (event) => {
    const selectedFile = event.target.files[0];
    setFile(selectedFile);
  };

  const getFaculties = async () => {
    try {
      const response = await fetch(`http://localhost:8080/faculties`, {
        method: 'GET',
      });
      const data = await response.json();
      if (response.status == 200) {
        setResponseData(data);
      } else {
        console.log(response.status, response);
        alert(data.message);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const getDepartments = async () => {
    try {
      const response = await fetch(`http://localhost:8080/departments`, {
        method: 'GET',
      });
      const data = await response.json();
      if (response.status == 200) {
        setDepartmentsResponseData(data);
      } else {
        console.log(response.status, response);
        alert(data.message);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const getCoursesRegistrations = async () => {
    try {
      const response = await fetch(`http://localhost:8080/courseRegistration`, {
        method: 'GET',
      });
      const data = await response.json();
      if (response.status == 200) {
        setCoursesRegistrationsResponseData(data);
      } else {
        console.log(response.status, response);
        alert(data.message);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const getTeachersa = async () => {
    if (!file) {
      alert('Please upload file!');
      return false;
    }

    try {
      const response = await fetch(`http://localhost:8080/employees`, {
        method: 'POST',
        body: formData,
      });
      const data = await response.json();
      if (response.status == 200) {
        setResponseData(data);
      } else {
        console.log(response.status, response);
        alert(data.message);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <div>
      <h1>Barely Functioning College</h1>
      <button>
        <label htmlFor='files'>Upload file </label>
      </button>
      <input
        id='files'
        type='file'
        style={{ visibility: 'hidden', display: 'none' }}
        onChange={handleFileUpload}
      ></input>
      <p>{file ? file.name : null}</p>

      <button onClick={getFaculties}>Факултети</button>
      <table id='facultiesTable' className='centerTable'>
        <thead style={{ backgroundColor: '#f2f2f2' }}>
          <tr>
            <th className='tableHeader'>Факултет</th>
            <th className='tableHeader'>Адрес</th>
            <th className='tableHeader'>Телефон</th>
            <th className='tableHeader'>Email</th>
          </tr>
        </thead>
        <tbody>
          {responseData.map((item, index) => (
            <tr key={index} className='tableRow'>
              <td className='tableCell'>{item.name}</td>
              <td className='tableCell'>{item.address}</td>
              <td className='tableCell'>{item.phone}</td>
              <td className='tableCell'>{item.email}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <button onClick={getDepartments}>Департаменти</button>
      <table className='centerTable'>
        <thead style={{ backgroundColor: '#f2f2f2' }}>
          <tr>
            <th className='tableHeader'>Департамент</th>
            <th className='tableHeader'>Ид на факултет</th>
            <th className='tableHeader'>Телефон</th>
            <th className='tableHeader'>Email</th>
          </tr>
        </thead>
        <tbody>
          {departmentsResponseData.map((item, index) => (
            <tr key={index} className='tableRow'>
              <td className='tableCell'>{item.name}</td>
              <td className='tableCell'>{item.facultyId}</td>
              <td className='tableCell'>{item.phone}</td>
              <td className='tableCell'>{item.email}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <button onClick={getCoursesRegistrations}>Записвания за курсове</button>
      <table className='centerTable'>
        <thead style={{ backgroundColor: '#f2f2f2' }}>
          <tr>
            <th className='tableHeader'>Ид на курса</th>
            <th className='tableHeader'>Ид на студента</th>
          </tr>
        </thead>
        <tbody>
          {coursesRegistrationsResponseData.map((item, index) => (
            <tr key={index} className='tableRow'>
              <td className='tableCell'>{item.courseId}</td>
              <td className='tableCell'>{item.studentId}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default College;
