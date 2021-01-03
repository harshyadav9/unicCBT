import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Login from './login/login';
import Instructions from './instructions/instructions';
// import Header from './header/header';
import Test from './Test';
function App() {
  return (
    <div className="App">

      <BrowserRouter>
        {/* <MuiThemeProvider theme={theme}> */}

        {/* <div className="App"> */}
        <Switch>
          <Route exact path="/" component={Login} />
          <Route path="/instructions" component={Test} />
        </Switch>
        {/* <Footer /> */}
        {/* </div> */}

        {/* </MuiThemeProvider> */}
      </BrowserRouter>
      {/* <Login/> */}
    </div>
  );
}

export default App;
