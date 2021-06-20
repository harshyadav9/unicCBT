import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Login from './login/login';
import Instructions from './instructions/instructions';
// import Header from './header/header';
import Test from './Test';
import Header from './header/header';
import NewWindow from 'react-new-window'
import { Prompt } from "react-router";
function App() {
  return (
    <div className="App">

      <BrowserRouter>
        {/* <MuiThemeProvider theme={theme}> */}

        {/* <div className="App"> */}
        <Header />

        <Switch>
          {/* <NewWindow> */}
          <Route exact path="/" component={Login} />
          {/* <NewWindow copyStyles={true}> */}
          <Route path="/instructions" component={Test} />
          <Prompt message="Are you sure you want to go to /test?" />
          {/* </NewWindow> */}

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
