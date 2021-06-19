import React, { useState, useEffect } from 'react';
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
const useStyles = makeStyles((theme) => ({

  root: {
    display: 'flex',
    flexDirection: 'column',
    minHeight: '100vh'
  },

  paper: {
    marginTop: theme.spacing(15),
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
      <Link color="inherit" href="https://www.unicsolutons.edu">
        www.unicsolutons.edu
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>

  )
}

export default function Login() {
  const classes = useStyles();
  const [regNo, setregNo] = useState("");
  const [password, setpassword] = useState("");

  const [regNoErr, setregNoErr] = useState("");
  const [passwordErr, setpasswordErr] = useState("");
  const [isregValid, setisRegValid] = useState(false)
  const [ispassValid, setisPassValid] = useState(false)


  useEffect(() => {

    axios.get('/student/checkStatus', {
      headers: {
        'Access-Control-Allow-Origin': '*',
      },
    }).then(res => {
      console.log(res);
    })
  }, []);

  const onSubmit = (e) => {
    console.group(e.value);
  };

  const regNoChangeEvent = (e) => {

    // if (e.target.value == "") {
    //   setregNoErr("Empty Registration Number");
    //   setisRegValid(false)
    //   return;
    // }
    // if (e.target.value.trim() !== "123") {
    //   setregNoErr("Incorrect Registration Number");
    //   setisRegValid(false)
    //   return;
    // }
    // setisRegValid(true)
    setregNo(e.target.value);
    // setregNoErr("");


  };


  const submitLoginForm = (e) => {
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
    axios.put('/student/submitExam', obj1).then(res => {
      console.log("res", res);
    })
  }


  const passChangeEvent = (e) => {

    // if (e.target.value == "") {
    //   setpasswordErr("Empty Password");
    //   setisPassValid(false)
    //   return;
    // }
    // if (e.target.value.trim() !== "123") {
    //   setpasswordErr("Incorrect Password");
    //   setisPassValid(false)
    //   return;
    // }
    // setisPassValid(true)
    setpassword(e.target.value);
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
              //disabled={(isregValid === true && ispassValid === true) === true ? false : true}
              fullWidth
              onClick={submitLoginForm}
              variant="contained"
              color="primary"
              className={classes.submit}
            >
              Sign In
          </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body1">
                  Forgot password?
              </Link>
              </Grid>

            </Grid>
          </form>
        </div>

        <Box mt={8}>
          <Copyright />
        </Box>
      </Container>
      <Footer />
    </div>

  )

}