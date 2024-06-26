import reactLogo from '../assets/react.svg';
import '../styles/App.css';
import College from './College';

function App() {
  return (
    <>
      <div>
        <img src={reactLogo} className='logo react' alt='React logo' />
      </div>
      <College />
    </>
  );
}

export default App;
