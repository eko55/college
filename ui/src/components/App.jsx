import reactLogo from '../assets/react.svg';
import '../styles/App.css';
import College from './College';
import Faculties from './Faculties';

function App() {
  return (
    <>
      <div>
        <img src={reactLogo} className='logo react' alt='React logo' />
      </div>
      {/* <College /> */}
      <Faculties />
    </>
  );
}

export default App;
