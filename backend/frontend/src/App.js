import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Login from './login/login';
import Instructions from './instructions/instructions';
// import Header from './header/header';
import Test from './Test';
import Header from './header/header';
import Excel from './excel';
import { SnackbarProvider } from 'notistack';
import ExamDataProvider from './context/ExamDataContext';
import Loader from './Loader';

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        <ExamDataProvider>
          {/* <MuiThemeProvider theme={theme}> */}
          <Loader/>
          {/* <div className="App"> */}
          <Header />

          <Switch>
            <Route exact path="/" component={Login} />
            <Route exact path="/test" component={Test} />
            <Route exact path="/uploadExcel" component={Excel} />

          </Switch>

          {/* <Footer /> */}
          {/* </div> */}

          {/* </MuiThemeProvider> */}
        </ExamDataProvider>
      </BrowserRouter>

      {/* <Login/> */}
    </div>
  );
}


export default App;
