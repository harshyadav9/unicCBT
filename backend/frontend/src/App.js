import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Login from './login/login';
import Instructions from './instructions/instructions';
import NewWindow from 'react-new-window'
// import Header from './header/header';
import Test from './Test';
import Header from './header/header';

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        {/* <MuiThemeProvider theme={theme}> */}

        {/* <div className="App"> */}
        <Header />
        <Switch>
          <Route exact path="/" component={Login} />
          {/* <NewWindow> */}
          <Route path="/instructions" component={Test} />
          {/* </NewWindow> */}
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
