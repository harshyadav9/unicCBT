import React, { useState, useEffect } from 'react';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import Button from '@material-ui/core/Button';
import classNames from 'classnames';
import { makeStyles } from '@material-ui/core/styles';
import Brightness1Icon from '@material-ui/icons/Brightness1';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import { green, red, grey } from '@material-ui/core/colors';

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
        color: theme.palette.getContrastText(green[400]),
        backgroundColor: green[400],
        "&:hover": {
            backgroundColor: green[400]
        }
    },
    reviewUnAnsweredColor: {
        margin: '12px',
        borderRadius: '50%',
        padding: '14px 0px',
        color: theme.palette.getContrastText(red[200]),
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

    },
    answeredSample: {
        color: theme.palette.getContrastText(green[900]),
        backgroundColor: green[900]
    },
    unansweredSample: {
        color: theme.palette.getContrastText(red[500]),
        backgroundColor: red[500]
    },
    reviewansweredSample: {
        color: theme.palette.getContrastText(green[400]),
        backgroundColor: green[400]
    },
    reviewunansweredSample: {
        color: theme.palette.getContrastText(red[200]),
        backgroundColor: red[200]
    },
    unvisitedSample: {
        color: theme.palette.getContrastText(grey[500]),
        backgroundColor: grey[500]
    }
}));



function ButtonsGroup({ arrLen, questionInfo, totalQues, changeStep }) {


    console.log("ButtonsGroup*******", arrLen, questionInfo);

    useEffect(() => {
        console.log("useEffect");
        let tempArr = [];
        if (questionInfo.id == "") {
            const createArr = () => {
                for (let i = 0; i < arrLen; i++) {
                    let obj = {};
                    obj['answered'] = false;
                    obj['default'] = true;
                    obj['unanswered'] = false;
                    obj['reviewA'] = false;
                    obj['reviewU'] = false;
                    obj['id'] = i + 1;
                    tempArr.push(obj);

                }
                console.log("newArr123", [...newArr, ...tempArr])
                setnewArr(prevArrValues => ([...prevArrValues, ...tempArr]))
                // setnewArr([...newArr, ...tempArr]);
            }
            createArr();
        } else {
            updateButtons(questionInfo);
        }


    }, [questionInfo]);



    const updateButtons = (questionInfo) => {
        let obj = {};
        // for (let i = 0; i < newArr.length; i++) {
        //     if (newArr[i]['id'] == id) {

        //         obj['answered'] = true;
        //         obj['unanswered'] = false;
        //         obj['review'] = false;
        //         obj['id'] = id;

        //     }

        //     // tempArr.push(obj);

        // }
        setnewArr(state => {
            console.log("state", state);
            const newArr = state.map(item => {
                if (item.id == questionInfo.id) {
                    item.answered = false;
                    item.default = false;
                    item.unanswered = false;
                    item.reviewA = false;
                    item.reviewU = false;
                    item[questionInfo.type] = true;
                    item['id'] = questionInfo.id;

                }
                return item;
            })
            return newArr;
        })
        // setnewArr([...newArr, obj]);
    };

    const classes = useStyles();
    const [itemClicked, setitemClicked] = useState(-1);
    // const [answersCount, setanswersCount] = useState({ unanswered: 0, answered: 0, reviewA: 0, reviewU: 0 });
    const [newArr, setnewArr] = useState([]);
    let unansweredQuestions = totalQues;
    let answeredQuestions = 0;
    let reviewUnQuestions = 0;
    let reviewAnQuestions = 0;
    let unvisitedQuestions = 0;

    let reviewquestionsObj = JSON.parse(localStorage.getItem('reviewQuestionNo'));
    let answeredQuestionsObj = JSON.parse(localStorage.getItem('questionNo'));

    console.log("reviewquestionsObj", reviewquestionsObj);
    console.log("answeredQuestionsObj", answeredQuestionsObj);
    if (reviewquestionsObj !== null) {
        reviewUnQuestions = reviewquestionsObj['reviewU'].length;
        reviewAnQuestions = reviewquestionsObj['reviewA'].length;
    }
    if (answeredQuestionsObj !== null) {
        answeredQuestions = answeredQuestionsObj.length;
    }
    unansweredQuestions = totalQues - (answeredQuestions + reviewAnQuestions + reviewUnQuestions)
    const [isAnsweredColor, setisAnsweredColor] = useState(false);
    // if (id !== undefined) {
    //     updateButtons(id);
    // }

    console.log("newArr", newArr);

    const clicked = (val) => {
        let clickedVal = parseInt(val.target.innerText.trim() - 1);
        setitemClicked(clickedVal);
    }
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
            <div className={classes.legendContainer}>
                <div className={classes.legends} style={{ width: '50%' }}>
                    {/* <Brightness1Icon style={{ color: 'green' }} />1 */}
                    <Avatar className={classes.answeredSample}>{answeredQuestions}</Avatar>
                    <span> Answered Questions</span>
                </div>
                <div className={classes.legends} style={{ width: '44%' }}>
                    <Avatar className={classes.unansweredSample}>{unansweredQuestions}</Avatar>
                    <span> Unanswered Questions</span>
                </div>

                <div className={classes.legends} style={{ width: '44%' }}>
                    <Avatar className={classes.unvisitedSample}>{unvisitedQuestions}</Avatar>
                    <span> Unvisited Questions</span>
                </div>

                <div className={classes.legends} style={{ width: '50%' }}>
                    <Avatar className={classes.reviewansweredSample}>{reviewAnQuestions}</Avatar>
                    <span> Review Answered Question</span>
                </div>
                <div className={classes.legends} style={{ width: '50%' }}>
                    <Avatar className={classes.reviewunansweredSample}>{reviewUnQuestions}</Avatar>
                    <span> Review Unanswered Question</span>
                </div>

            </div>



            <div style={{ width: '100%' }}>
                <h3>Question Numbers</h3>
            </div>
            <div className={classes.buttonContainer}>


                <br />
                {/* <Card className={classes.card}> */}
                {
                    newArr.map((val, key) => {
                        // console.log(val, key);
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
                {/* </Card> */}
            </div>



            {/* </Grid> */}
            {/* </Grid> */}

        </>
    )
}

export default ButtonsGroup
