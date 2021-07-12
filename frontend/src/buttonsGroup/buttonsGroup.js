import React, { useState, useEffect, useContext } from 'react';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import Button from '@material-ui/core/Button';
import classNames from 'classnames';
import { makeStyles } from '@material-ui/core/styles';
import Brightness1Icon from '@material-ui/icons/Brightness1';
import Avatar from '@material-ui/core/Avatar';
import Paper from '@material-ui/core/Paper';
import { green, red, grey } from '@material-ui/core/colors';
import {
    Fab, Box, Typography, Divider
} from '@material-ui/core';
import Timer from '../Timer';
import { ExamDataContext } from '../context/ExamDataContext';
const useStyles = makeStyles((theme) => ({
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
        margin: '12px',
        color: theme.palette.getContrastText(green[900]),
        backgroundColor: green[900],
        // color: '#fff',
        "&:hover": {
            backgroundColor: green[900]
        }
    },

    reviewAnsweredColor: {
        margin: '12px',
        borderRadius: '50%',
        padding: '14px 0px',
        color: '#fff',
        backgroundColor: green[400],
        "&:hover": {
            backgroundColor: green[400]
        }
    },
    reviewUnAnsweredColor: {
        margin: '12px',
        borderRadius: '50%',
        padding: '14px 0px',
        color: '#fff',
        backgroundColor: red[200],
        "&:hover": {
            backgroundColor: red[200],
        }
    },
    unansweredColor: {
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
        // display: 'flex',
        // width: 'calc(100% - 17%)',
        // flexDirection: 'row',
        // flexWrap: 'wrap',
        // marginLeft: '30px',
        // maxHeight: '600px',
        // overflowY: 'scroll',
        // boxShadow: '0px 4px 13px -1px rgba(0,0,0,0.2), 0px 1px 10px 0px rgba(0,0,0,0.14), 0px 1px 20px 0px rgba(0,0,0,0.19)',
        // borderRadius: '4px',
        // border: '2px solid  rgba(0,0,0,0.2)',
        // padding: '11px',
        // "& button": {
        //     margin: '5px',
        //     height: '43px',
        //     minWidth: '41px'
        // }
        display: 'grid',
        gridTemplateColumns: 'repeat(4, 1fr)',
        placeContent: 'start',
        justifyItems: 'center',
        padding: '8px 15px 30px',
        height: 'calc(100vh - 420px)',
        overflowY: 'auto',
    },
    legendContainer: {
        display: 'flex',
        flexWrap: 'wrap',
        width: '100%',
        marginTop: 76
    },
    legends: {
        alignItems: 'center',
        display: 'flex',
        height: '40px',
        boxShadow: '0px 4px 13px -1px rgba(0,0,0,0.2), 0px 1px 10px 0px rgba(0,0,0,0.14), 0px 1px 20px 0px rgba(0,0,0,0.19)',
        borderRadius: '7px',
        margin: '4px',
        height: 'auto'

    },
    buttonCls: {
        marginTop: '35px'
    },
    // answeredSample: {
    //     color: theme.palette.getContrastText(green[900]),
    //     backgroundColor: green[900]
    // },
    // unansweredSample: {
    //     color: theme.palette.getContrastText(red[500]),
    //     backgroundColor: red[500]
    // },
    // reviewansweredSample: {
    //     color: theme.palette.getContrastText(green[400]),
    //     backgroundColor: green[400]
    // },
    // reviewunansweredSample: {
    //     color: theme.palette.getContrastText(red[200]),
    //     backgroundColor: red[200]
    // },
    // unvisitedSample: {
    //     color: theme.palette.getContrastText(grey[500]),
    //     backgroundColor: grey[500]
    // },
    buttonGroupText: {
        lineHeight: '12px',
        fontSize: ' 12px',
        fontWeight: 'bold'
    },
    footer: {
        padding: 10,
        display: 'flex',
        justifyContent: 'space-between'
    }
}));


function ButtonsGroup({ arrLen, questionInfo, changeStep }) {



    const classes = useStyles();
    // const [itemClicked, setitemClicked] = useState(-1);
    // const [answersCount, setanswersCount] = useState({ unanswered: 0, answered: 0, reviewA: 0, reviewU: 0 });
    const [newArr, setnewArr] = useState({});

    const { state } = useContext(ExamDataContext);
    // console.log("ButtonsGroup*******", arrLen, questionInfo, totalQues, changeStep);

    // useEffect(() => {
    //     console.log("ButtonsGroup called");
    // },[state.reset]);
    console.log("questionInfo in ButtonsGroup", questionInfo);
    useEffect(() => {
        console.log("useEffect else called");
        let tempArr = [];
        if (questionInfo.id == "") {
            const createArr = () => {
                console.log("createArr called");
                let obj = {};
                for (let i = 0; i < arrLen.length; i++) {
                    obj[i + 1] = {};
                    obj[i + 1]['answered'] = false;
                    obj[i + 1]['default'] = true;
                    obj[i + 1]['unanswered'] = false;
                    obj[i + 1]['reviewA'] = false;
                    obj[i + 1]['reviewU'] = false;
                    obj[i + 1]['id'] = i + 1;
                    // tempArr.push(obj);

                }
                // console.log("newArr123", [...newArr, ...tempArr])
                // setnewArr(prevArrValues => (...prevArrValues , ...obj));
                setnewArr(prevState => ({
                    ...prevState,
                    ...obj
                }));
                // setnewArr([...newArr, ...tempArr]);
            }
            if (Object.keys(newArr).length == 0) {
                createArr();
            }

        } else {
            updateButtons(questionInfo);
        }


    }, [arrLen,questionInfo]);



    const updateButtons = (questionInfo) => {
      
        console.log("questionInfo called",questionInfo);
        let buttonState = { ...newArr };
        console.log("buttonState called",buttonState);
        // if (!buttonState[questionInfo.id][questionInfo.type] === true){
            buttonState[questionInfo.id]['answered'] = false;
            buttonState[questionInfo.id]['default'] = false;
            buttonState[questionInfo.id]['unanswered'] = false;
            buttonState[questionInfo.id]['reviewA'] = false;
            buttonState[questionInfo.id]['reviewU'] = false;
            buttonState[questionInfo.id][questionInfo.type] = true;
        // }
       


        setnewArr(prevState => ({
            ...prevState,
            ...buttonState
        }));
        // setnewArr(state => {
        //     const newArr = state.map(item => {
        //         if (item.id == questionInfo.id) {
        //             item.answered = false;
        //             item.default = false;
        //             item.unanswered = false;
        //             item.reviewA = false;
        //             item.reviewU = false;
        //             item[questionInfo.type] = true;
        //             item['id'] = questionInfo.id;

        //         }
        //         return item;
        //     })
        //     return newArr;
        // })
    };


    // let unansweredQuestions = totalQues;
    // let answeredQuestions = 0;
    // let reviewUnQuestions = 0;
    // let reviewAnQuestions = 0;
    // let unvisitedQuestions = 0;

    // let reviewquestionsObj = JSON.parse(localStorage.getItem('reviewQuestionNo'));
    // let answeredQuestionsObj = JSON.parse(localStorage.getItem('questionNo'));
    // let unvisitedQuestions = JSON.parse(localStorage.getItem('unvisitedQues')) === null ? 0 : JSON.parse(localStorage.getItem('unvisitedQues')).length;

    // console.log("unvisitedQuestions", unvisitedQuestions);
    // console.log("answeredQuestionsObj", answeredQuestionsObj);
    // if (reviewquestionsObj !== null) {
    //     reviewUnQuestions = reviewquestionsObj['reviewU'].length;
    //     reviewAnQuestions = reviewquestionsObj['reviewA'].length;
    // }
    // if (answeredQuestionsObj !== null) {
    //     answeredQuestions = answeredQuestionsObj.length;
    // }
    // unansweredQuestions = totalQues - (answeredQuestions)
    // const [isAnsweredColor, setisAnsweredColor] = useState(false);
    // if (id !== undefined) {
    //     updateButtons(id);
    // }

    // console.log("newArr", newArr);

    return (
        <>
            {/* <Grid container className={classes.container}> */}

            {/* <Grid item xs={12} md={4} lg={3}> */}
            {/* <Card className={classes.card}>
                        {
                            newArr.map((val, key) => {
                                console.log(val, key);
                                return (<Button key={key} className={classNames({ [classes.buttonDefault]: (itemClicked === key ? false : true), [classes.answeredColor]: (itemClicked === key ? true : false) })} onClick={(val) => clicked(val)} variant="contained" color="primary">
                                    {val.value}
                                </Button>)
                            })
                        }
                    </Card> */}

            <Timer hours={state.time.hours}  minutes={state.time.minutes}  seconds={state.time.seconds} />
            {/* <div className={classes.legendContainer}>
                <div className={classes.legends} style={{ width: '45%' }}>
                  
                    <Avatar className={classes.answeredSample}>{answeredQuestions}</Avatar>
                    <span className={classes.buttonGroupText}> Answered Questions</span>
                </div>
                <div className={classes.legends} style={{ width: '45%' }}>
                    <Avatar className={classes.unansweredSample}>{unansweredQuestions}</Avatar>
                    <span className={classes.buttonGroupText}> Unanswered Questions</span>
                </div>

                <div className={classes.legends} style={{ width: '45%' }}>
                    <Avatar className={classes.unvisitedSample}>{unvisitedQuestions}</Avatar>
                    <span className={classes.buttonGroupText}> Unvisited Questions</span>
                </div>

                <div className={classes.legends} style={{ width: '45%' }}>
                    <Avatar className={classes.reviewansweredSample}>{reviewAnQuestions}</Avatar>
                    <span className={classes.buttonGroupText}> Review Answered Question</span>
                </div>
                <div className={classes.legends} style={{ width: '45%' }}>
                    <Avatar className={classes.reviewunansweredSample}>{reviewUnQuestions}</Avatar>
                    <span className={classes.buttonGroupText}> Review Unanswered Question</span>
                </div>

            </div> */}



            {/* <div style={{ width: '100%' }}>
                <h3>Question Numbers</h3>
            </div> */}
            {/* <div className={classes.buttonContainer}>


                <br />
                {
                    newArr.map((val, key) => {
                        return (
                            <Button key={key} onClick={() => { changeStep(key) }} className={classNames({
                                [classes.buttonDefault]: (val.default === true ? true : false),
                                [classes.unansweredColor]: (val.unanswered === true ? true : false),
                                [classes.answeredColor]: (val.answered === true ? true : false),
                                [classes.reviewAnsweredColor]: (val.reviewA === true ? true : false),
                                [classes.reviewUnAnsweredColor]: (val.reviewU === true ? true : false)
                            })} variant="contained" color="primary">
                                {key + 1}
                            </Button>)
                    })
                }
            </div> */}

            <Paper elevation={3} className={classes.buttonCls}>
                <Typography variant="h6" gutterBottom className={classes.padding}>
                    Question Numbers
            </Typography>
                <Divider />
                <Box component="div" className={classes.buttonContainer}>
                    {
                        Object.keys(newArr).map((val, key) => {
                            // newArr.map((val, key) => {
                            // console.log(val, key);
                            return (
                                <div key={key}>
                                    <Fab onClick={() => { changeStep(key) }} className={classNames({
                                        [classes.buttonDefault]: (newArr[val].default === true ? true : false),
                                        [classes.unansweredColor]: (newArr[val].unanswered === true ? true : false),
                                        [classes.answeredColor]: (newArr[val].answered === true ? true : false),
                                        [classes.reviewAnsweredColor]: (newArr[val].reviewA === true ? true : false),
                                        [classes.reviewUnAnsweredColor]: (newArr[val].reviewU === true ? true : false)
                                    })} variant="contained" color="primary">
                                        {key + 1}
                                    </Fab>
                                </div>
                            )
                        })
                    }
                </Box>
                <Divider />
                <Typography variant="h6" gutterBottom className={classes.footer} >

                </Typography>
            </Paper>

        </>
    )
}

export default React.memo(ButtonsGroup); 
