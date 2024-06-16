import { useState } from 'react';
import '../styles/EmployeeRecords.css';

const EmployeeRecords = () => {
  const [responseData, setResponseData] = useState([]);
  const [file, setFile] = useState(null);

  const formData = new FormData();
  formData.append('file', file);

  const handleFileUpload = (event) => {
    const selectedFile = event.target.files[0];
    setFile(selectedFile);
  };

  const handleApiCall = async () => {
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
      <h1>Employee Work Records</h1>
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
      <button onClick={handleApiCall}>
        Get Employees Working Together For Longest
      </button>
      <table className='centerTable'>
        <thead style={{ backgroundColor: '#f2f2f2' }}>
          <tr>
            <th className='tableHeader'>Employee ID #1</th>
            <th className='tableHeader'>Employee ID #2</th>
            <th className='tableHeader'>Project ID</th>
            <th className='tableHeader'>Days worked</th>
          </tr>
        </thead>
        <tbody>
          {responseData.map((item, index) => (
            <tr key={index} className='tableRow'>
              <td className='tableCell'>{item.employeeRecordOne.employeeID}</td>
              <td className='tableCell'>{item.employeeRecordTwo.employeeID}</td>
              <td className='tableCell'>{item.commonProjectId}</td>
              <td className='tableCell'>{item.commonDaysWork}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeRecords;
