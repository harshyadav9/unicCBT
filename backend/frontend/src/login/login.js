import React, { useState, useEffect,useContext } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
// import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Footer from '../footer/footer';
import Header from '../header/header';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

import Snackbar from '@material-ui/core/Snackbar';
import Test from '../Test'; import Modal from '@material-ui/core/Modal';
import Dialog from '@material-ui/core/Dialog';
import useMediaQuery from '@material-ui/core/useMediaQuery';
import { useTheme } from '@material-ui/core/styles';
import { ExamDataContext } from '../context/ExamDataContext';
// import {login} from '../../login.png';
const useStyles = makeStyles((theme) => ({

  root: {
    display: 'flex',
    flexDirection: 'column',
    backgroundImage:`url(login.jpg)`,

    backgroundSize:'cover',
    backgroundRepeat:'no-repeat'
  },

  paper: {
    marginTop: theme.spacing(15),
    background:'#fff',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    boxShadow: 'rgba(0, 0, 0, 0.2) 0px 3px 3px -2px, rgba(0, 0, 0, 0.14) 0px 3px 4px 0px, rgba(0, 0, 0, 0.12) 0px 1px 8px 0px',
    padding: '3em'
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.dark,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));


function Copyright() {

  return (
    <Typography variant="body1" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="primary" href="https://www.unicsolutons.edu">
        www.unicsolutons.edu
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>

  )
}




export default function Login() {
  const {state,dispatch} = useContext(ExamDataContext);
  console.log("state,dispatch in header" , state,dispatch);
  const classes = useStyles();
  const [regNo, setregNo] = useState("");
  const [password, setpassword] = useState("");
  const [open, setOpen] = useState(false);
  const [regNoErr, setregNoErr] = useState("");
  const [passwordErr, setpasswordErr] = useState("");
  const [isregValid, setisRegValid] = useState(true)
  const [ispassValid, setisPassValid] = useState(true);
  const theme = useTheme();
  const fullScreen = useMediaQuery(theme.breakpoints.down('lg'));
  const history = useHistory();
  // useEffect(() => {

  //   axios.get('/student/checkStatus', {
  //     headers: {
  //       'Access-Control-Allow-Origin': '*',
  //     },
  //   }).then(res => {
  //     console.log(res);
  //   })
  // }, []);


  const onSubmit = (e) => {
    console.group(e.value);
  };

  const regNoChangeEvent = (e) => {

    if (e.target.value == "") {
      setregNoErr("Empty Registration Number");
      setisRegValid(true);
    } else {
      setisRegValid(false);

    }
  setregNo(e.target.value);
    
    // if (e.target.value.trim() !== "123") {
    //   setregNoErr("Incorrect Registration Number");
    //   setisRegValid(false)
    //   return;
    // }
    // setisRegValid(true)
  
    // setregNoErr("");


  };



  const submitLoginForm = (e) => {
    dispatch({type:'HANDLELOADING' ,isLoading:true});
    e.preventDefault();
    console.log(regNo, password);
    let obj = {
      registrationNo: regNo,
      password: password
    };
    let obj1 = {
      "registrationNo": 2,
      "resp": [
        {
          "id": {
            "registrationNo": 2,
            "questionId": 2,
            "yearOfExam": 2020
          },
          "selectedAnswer": "option1,option2"
        }
      ]
    };
   
     axios.post('cbt/student/login', obj).then(res => {
      //  console.log("res", res);
      //  if (res.status === 200) {
    //  window.open('/instructions', "_blank", 'height=600,width=400,menubar=no,resizable=no,scrollbars=no,status=no,location=no');
    //  history.push('/test');

    //  setOpen(true);
    //  let popup = window.open("/test", "popup", "fullscreen");
    // if (popup.outerWidth < window.screen.availWidth || popup.outerHeight < window.screen.availHeight)
    // {
    //   popup.moveTo(0,0);
    //   popup.resizeTo(window.screen.availWidth, window.screen.availHeight);
    // }

      history.push('/test');
     dispatch({type:'HANDLELOADING' ,isLoading:false});
     }).catch(err => {
       console.log("error in login " , err);
       dispatch({type:'HANDLELOADING' ,isLoading:false});
       setpasswordErr("Either wrong username or password");
       setregNoErr("Either wrong username or password");
     })
    // window.open("/instructions", "_blank", 'menubar=no,width=1000,height=1000,resizable=no,scrollbars,status,toolbar=yes');
    //  }
    //  })
    // var popup = window.open("/test", "popup", "fullscreen");
    // if (popup.outerWidth < window.screen.availWidth || popup.outerHeight < window.screen.availHeight)
    // {
    //   popup.moveTo(0,0);
    //   popup.resizeTo(window.screen.availWidth, window.screen.availHeight);
    // }

  // if (popup.outerWidth < screen.width || popup.outerHeight < screen.height)
  // {
  //   popup.moveTo(0,0);
  //   popup.resizeTo(screen.width, screen.height);
  // }
  }



  const handleClose = () => {
    setOpen(false);
  }

  const passChangeEvent = (e) => {

    if (e.target.value == "") {
      setpasswordErr("Empty Password");
      setisPassValid(true);
    } else {
      setisPassValid(false);
      
    }
    setpassword(e.target.value);
    // if (e.target.value.trim() !== "123") {
    //   setpasswordErr("Incorrect Password");
    //   setisPassValid(false)
    //   return;
    // }
    // setisPassValid(true)
    
    // setpasswordErr("");


  };

  return (



    <div className={classes.root}>

     

      {/* header start */}
      {/* <Header/> */}
      {/*  header end */}
      <Container component="div" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
        </Typography>

          <form className={classes.form} noValidate>
            <TextField
              helperText={regNoErr !== "" ? regNoErr : ""}
              error={regNoErr !== ""}
              onChange={regNoChangeEvent}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="regNo"
              label="Registration Number"
              name="regNo"
              autoFocus
            />
            <TextField
              helperText={passwordErr !== "" ? passwordErr : ""}
              error={passwordErr !== ""}
              onChange={passChangeEvent}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
            />
            <Button
              type="submit"
              disabled={(isregValid === false && ispassValid === false)  ? false : true}
              fullWidth
              onClick={submitLoginForm}
              variant="contained"
              color="primary"
              className={classes.submit}
            >
              Sign In
          </Button>
            {/* <Grid container>
              <Grid item xs>
                <Link href="#" variant="body1">
                  Forgot password?
              </Link>
              </Grid>

            </Grid> */}
            {/* <Alert severity="error">This is an error alert — check it out!</Alert> */}

          </form>
        </div>

        {/* <Dialog fullScreen={fullScreen} onClose={handleClose} aria-labelledby="simple-dialog-title" open={open}>
          <Test />
        </Dialog> */}




        <Box mt={8}>
          <Copyright />
        </Box>
      </Container>
      {/* <Footer /> */}
    </div>

  )

}