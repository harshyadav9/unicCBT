import React, { useState } from 'react'
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';
import { Typography } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Avatar from '@material-ui/core/Avatar';
import Divider from '@material-ui/core/Divider';
import ButtonsGroup from '../buttonsGroup/buttonsGroup';
import Header from '../header/header';


// import image from '../../public/'
const useStyles = makeStyles((theme) => ({
    root: {
        minHeight: '40vh',
        display: 'flex'
    },
    InstructionsDiv: {
        padding: '9px',
        boxShadow: '0px 3px 8px 6px #ccc',
        // lineHeight: '4rem'
    },
    root: {
        flexGrow: 1
    },
    img1: {
        width: '100px',
        height: '100px'
    }
}));



function Instructions() {
    const classes = useStyles();
    let buttonVal = null;
    const [questionId, setquestionId] = useState({ id: '', type: '' })

    return (
        <div className={classes.root}>
            <Header />
            <Typography variant="h4" color="textSecondary" align="left">
                Instructions
            </Typography>
            <ButtonsGroup arrLen={200} buttonValue={1} questionInfo={questionId} />
            <Grid container>

                <Grid item xs={12} md={9} >
                    <button onClick={() => {
                        setquestionId({ id: 1, type: 'answered' });
                    }}>click me </button>
                    <button onClick={() => {
                        setquestionId({ id: 2, type: 'answered' });
                    }}>click me </button>
                    <button onClick={() => {
                        setquestionId({ id: 2, type: 'review' });
                    }}>Review </button>
                    <button onClick={() => {

                        setquestionId({ id: 2, type: 'unanswered' });
                    }}>unanswered </button>
                    {/* <Paper elevation={3}>
                        <p className={classes.InstructionsDiv}>
                            <h4>
                                1. After login, the candidate shall be able to see the detailed instructions for the
                                examination. Candidates are advised to go through the instructions carefully regarding the type
                                of questions, marking scheme, procedure to mark & change answer etc. At the designated time
                                of start of the examination, the candidates will be able to proceed and see the questions on the
                                computer screen?
                            </h4>
                            <br />

                            <h4>
                                2.  After login, the candidate shall be able to see the detailed instructions for the
                                examination. Candidates are advised to go through the instructions carefully regarding the type
                                of questions, marking scheme, procedure to mark & change answer etc. At the designated time
                                of start of the examination, the candidates will be able to proceed and see the questions on the
                                computer screen?
                            </h4>



                            <br />
                            <h4>
                                3.  After login, the candidate shall be able to see the detailed instructions for the
                                examination. Candidates are advised to go through the instructions carefully regarding the type
                                of questions, marking scheme, procedure to mark & change answer etc. At the designated time
                                of start of the examination, the candidates will be able to proceed and see the questions on the
                                computer screen?
                            </h4>

                            <br />

                            <h4>
                                4.  After login, the candidate shall be able to see the detailed instructions for the
                                examination. Candidates are advised to go through the instructions carefully regarding the type
                                of questions, marking scheme, procedure to mark & change answer etc. At the designated time
                                of start of the examination, the candidates will be able to proceed and see the questions on the
                                computer screen?
                            </h4>


                        </p>
                    </Paper> */}

                </Grid>

                <Grid item xs={12} md={3} >
                    <Divider orientation="vertical" flexItem />
                    {/* <Avatar alt="Remy Sharp" className={classes.img1} src="image1.jpg" />
                    Name: Harsh Yadav */}
                </Grid>
            </Grid>



        </div>
    )
}

export default Instructions
