import reactLogo from '../assets/react.svg';
import '../styles/App.css';
import EmployeeRecords from './EmployeeRecords';

function App() {
  return (
    <>
      <div>
        <img src={reactLogo} className='logo react' alt='React logo' />
      </div>
      <EmployeeRecords />
    </>
  );
}

export default App;
