import React, { useState, useEffect } from 'react';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import Button from '@material-ui/core/Button';
import classNames from 'classnames';
// import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';
import Brightness1Icon from '@material-ui/icons/Brightness1';
import Questions from './Questions';
import Objective from './Objective';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import ButtonsGroup from './buttonsGroup/buttonsGroup';
import MobileStepper from '@material-ui/core/MobileStepper';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';

const useStyles = theme => ({
    root: {
        flexGrow: 1
    },
    header: {
        display: 'flex',
        alignItems: 'center',
        height: 50,
        width: '100%',
        paddingLeft: theme.spacing.unit * 5,
        marginBottom: 20,
        backgroundColor: theme.palette.background.default,
    },
    leftContainer: {
        display: 'flex',
        flexDirection: 'column'
    },
    mobileStepper: {
        paddingLeft: theme.spacing.unit * 3,
        paddingRight: theme.spacing.unit * 3
    },

    card: {
        margin: '10px',
        // backgroundColor: 'rgb(0,0,0,0.2)',
    },
    container: {
        overflow: 'auto'
    },
    appbar: {
        alignItems: 'center'
    },

    buttonDefault: {
        margin: '12px',
        borderRadius: '50%',
        padding: '14px 0px',
        backgroundColor: '#fff',
        color: 'black',
        "&:hover": {
            backgroundColor: '#ccc',
        }
    },
    answeredColor: {
        // margin: '12px',
        borderRadius: '50%',
        padding: '14px 0px',
        backgroundColor: 'green',
        color: '#fff',
        "&:hover": {
            backgroundColor: '#8bb58b',
        }
    },

    reviewColor: {
        margin: '12px',
        borderRadius: '50%',
        padding: '14px 0px',
        backgroundColor: 'red',
        color: '#fff',
        "&:hover": {
            backgroundColor: 'red',
        }
    },
    buttonContainer: {
        display: 'flex',
        width: 'calc(100% - 17%)',
        flexDirection: 'row',
        flexWrap: 'wrap',
        marginLeft: '44px',
        maxHeight: '600px',
        overflowY: 'scroll',
        boxShadow: '0px 4px 13px -1px rgba(0,0,0,0.2), 0px 1px 10px 0px rgba(0,0,0,0.14), 0px 1px 20px 0px rgba(0,0,0,0.19)',
        borderRadius: '4px',
        border: '2px solid  rgba(0,0,0,0.2)',
        padding: '11px',
        "& button": {
            margin: '5px',

            minWidth: '50px'
        }
    },
    legendContainer: {
        display: 'flex',
        flexWrap: 'wrap',
        width: '100%'
    },
    legends: {
        alignItems: 'center',
        display: 'flex',
        height: '40px',
        boxShadow: '0px 4px 13px -1px rgba(0,0,0,0.2), 0px 1px 10px 0px rgba(0,0,0,0.14), 0px 1px 20px 0px rgba(0,0,0,0.19)',
        borderRadius: '7px',
        margin: '4px'

    }
});

function Test(props) {
    // const classes = useStyles();
    const { classes, theme } = props;
    let quesNo = '';
    const [questionNo, setquestionNo] = useState({ id: '', type: 'unanswered' });
    const saveQuestion = (e) => {
        console.log(e);
        console.log("quesNo", quesNo);
        // if (e == "save")
        //     setquestionNo({ id: 1, type: 'answered' });
        // else {
        //     if (JSON.parse(localStorage.getItem('questionNo')) === null)
        //         setquestionNo({ id: 1, type: 'reviewU' });
        //     else
        //         setquestionNo({ id: 1, type: 'reviewA' });
        // }

        if (JSON.parse(localStorage.getItem('questionNo')) === null) {
            if (e == "save") {
                setquestionNo({ id: 1, type: 'unanswered' });
            } else {
                setquestionNo({ id: 1, type: 'reviewU' });
            }
        } else {
            if (e == "save") {
                setquestionNo({ id: 1, type: 'answered' });
            } else {
                setquestionNo({ id: 1, type: 'reviewA' });
            }

        }



    }
    const response = (quesNo) => {
        console.log("quesNo", quesNo);
        quesNo = quesNo;
        localStorage.setItem('questionNo', JSON.stringify(quesNo));
        // setquestionNo(quesNo);
        // quesNo = String(quesNo);
        // const questionNo = JSON.parse(localStorage.getItem('questionNo'));
        // const result = questionNo.includes(quesNo);

        // if (result === false) {
        //     localStorage.setItem('questionNo', JSON.stringify(questionNo.concat(quesNo)));
        // }

        // const questionNos = JSON.parse(localStorage.getItem('questionNo'));
        // localStorage.setItem('candidateScore', String(questionNos.length));
    };


    const checkQuestionNo = (quesNo) => {
        console.log("quesNo", quesNo);
        localStorage.setItem('questionNo', JSON.stringify(quesNo));
        quesNo = quesNo;
        // setquestionNo(quesNo);
        // quesNo = String(quesNo);
        // const questionNo = JSON.parse(localStorage.getItem('questionNo'));
        // const result = questionNo.includes(quesNo);

        // if (result === true) {
        //     const questionNos = questionNo.filter((questionNumber) => {
        //         return questionNumber !== quesNo;
        //     });

        //     localStorage.setItem('questionNo', JSON.stringify(questionNos));
        //     localStorage.setItem('candidateScore', String(questionNos.length));
        // }
    }
    return (
        <>
            <Grid container className={classes.container}>
                <Grid container xs={12} md={8} lg={9} className={classes.leftContainer}>
                    <Paper square elevation={0} className={classes.header}>
                        <Typography>{Questions[1].id} {Questions[1].label}</Typography>
                    </Paper>

                    {/* {Questions.map(question => ( */}
                    <Objective
                        objective={Questions[1].obj}
                        key={Questions[1].id}
                        response={(quesNo) => response(quesNo)}
                        checkQuestionNo={(quesNo) => checkQuestionNo(quesNo)} />
                    {/* ))} */}

                    <Button onClick={() => { saveQuestion('save') }} value="save" variant="contained">Save & Next</Button>
                    <Button onClick={() => { saveQuestion('review') }} onClick={saveQuestion} variant="contained" color="primary">
                        Review
                    </Button>
                </Grid>
                <Grid item xs={12} md={4} lg={3}>

                    <ButtonsGroup arrLen={50} questionInfo={questionNo} />


                </Grid>
            </Grid>

        </>
    )
}
export default withStyles(useStyles, { withTheme: true })(Test);
// export default Test
