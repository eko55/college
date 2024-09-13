import { useState } from 'react';

const Faculties = () => {
  const [responseData, setResponseData] = useState([]);

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

  return (
    <div>
      <h1>Barely Functioning College</h1>
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
    </div>
  );
};

export default Faculties;
