import React, { useState, useEffect, useContext, useCallback } from 'react';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import Button from '@material-ui/core/Button';
import classNames from 'classnames';
// import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';
import Brightness1Icon from '@material-ui/icons/Brightness1';
import Questions from './Questions';
import RadioButtonsGroup from './Objective';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import ButtonsGroup from './buttonsGroup/buttonsGroup';
import SwipeableViews from 'react-swipeable-views';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import ArrowForwardIosIcon from '@material-ui/icons/ArrowForwardIos';
import { ExamDataContext } from './context/ExamDataContext';
import DisplayQuesCount from './buttonsGroup/displayQuesCount';
import {
    Divider,
    AppBar,
    Toolbar,
    IconButton
} from '@material-ui/core';



const useStyles = theme => ({
    root: {
        flexGrow: 1
    },
    header: {
        display: 'flex',
        alignItems: 'center',
        height: 50,
        width: '100%',
        marginBottom: 20,
        marginTop: 25,
        backgroundColor: theme.palette.background.default,
        "& p": {
            color: '#000',
            fontSize: '20px',
            lineHeight: '1.4'
        }
    },
    Toolbar: {
        justifyContent: 'center',
        gridGap: 34
    },
    Footer: {
        background: 'transparent',
        boxShadow: 'none'
    },
    leftContainer: {
        display: 'flex',
        flexDirection: 'column',
        background: '#fff',
        borderRadius: 6,
        height: ' calc(100% - 30px)'
    },
    mobileStepper: {
        paddingLeft: theme.spacing.unit * 3,
        paddingRight: theme.spacing.unit * 3
    },

    card: {
        margin: '10px',
        // backgroundColor: 'rgb(0,0,0,0.2)',
    },
    containerStyle: {
        padding: '16px 16px 18px 16px',
        background: '#e4e0f9',
        justifyContent: 'space-between'
    },
    appbar: {
        alignItems: 'center'
    },
    mobileStepper: {
        paddingLeft: theme.spacing.unit * 3,
        paddingRight: theme.spacing.unit * 3
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
    rightIcon: {
        height: 40
    },
    legendContainer: {
        display: 'flex',
        flexWrap: 'wrap',
        width: '96%',
        marginTop: 76,
        marginLeft: 19,
        boxShadow: '0px 4px 13px -1px rgb(0 0 0 / 20%), 0px 1px 10px 0px rgb(0 0 0 / 14%), 0px 1px 20px 0px rgb(0 0 0 / 19%)'
    },
    legends: {
        alignItems: 'center',
        display: 'flex',
        height: '40px',
        boxShadow: '0px 4px 13px -1px rgba(0,0,0,0.2), 0px 1px 10px 0px rgba(0,0,0,0.14), 0px 1px 20px 0px rgba(0,0,0,0.19)',
        borderRadius: '7px',
        margin: '4px'

    },
    swipableViews: {
        position: 'relative',
        width: '100%'
    },
    rightContainer: {
        marginLeft: '24px'
    },
    buttonContainer: {
        display: 'flex',
        justifyContent: 'space-around'
    }
});

function Test(props) {
    const { state, dispatch } = useContext(ExamDataContext);
    // console.log("state,dispatch", state, dispatch);
    const [activeStep, setactiveStep] = useState(0);
    const history = useHistory();
    const [currentQuestion, setCurrentQuestion] = useState({});
    const [finalData, setfinalData] = useState([]);
    const [maxSteps, setMaxSteps] = useState(0);
    // const maxSteps = Questions.length;
    const { classes, theme } = props;
    let quesNo = '';
    var hidden, visibilityChange;
    const [logout, setLogout] = useState(false);
    const [question, setquestion] = useState(0);
    const [questionList, setquestionList] = useState([]);
    const [resetData, setResetData] = useState('');
    const [questionNo, setquestionNo] = useState({ id: '', type: 'unanswered' });
    console.log("Tets component loaded");
    useEffect(() => {
        console.log("test getting called");
        dispatch({ type: 'HANDLELOADING', isLoading: true });
        let keys = {};
        axios.get('cbt/student/getQuestions/1').then(res => {
            let arr = [];
            let unvisitedArr = [];

            for (let i = 0; i < res.data.questionList.Questions.length; i++) {
                unvisitedArr.push(i);
                arr.push(res.data.questionList.Questions[i]);
                if (i === 30) {
                    break
                }
            }
            // console.log("res.data.questionList.Questions",res.data.questionList.Questions);
            setquestionList(arr);
            // console.log("arr.length", arr.length);
            setMaxSteps(arr.length);
            setCurrentQuestion(res.data.questionList.Questions[activeStep]);
            // setquestionList(arr);
            dispatch({
                type: 'ADDINFO', hours: res.data.durationHr, minutes: res.data.durationMin, photo: res.data.photo, name: res.data.instituteName,
                candidateName: res.data.candidateName, registrationNo: res.data.registrationNo, isLoading: false,time:{hours:res.data.durationHr,minutes:res.data.durationMin,seconds:60}
            });
            // populateResultSet();
            localStorage.setItem('unvisitedQues', JSON.stringify(unvisitedArr));
        });


        if (typeof document.hidden !== "undefined") { // Opera 12.10 and Firefox 18 and later support
            hidden = "hidden";
            visibilityChange = "visibilitychange";
        } else if (typeof document.msHidden !== "undefined") {
            hidden = "msHidden";
            visibilityChange = "msvisibilitychange";
        } else if (typeof document.webkitHidden !== "undefined") {
            hidden = "webkitHidden";
            visibilityChange = "webkitvisibilitychange";
        }
        // alert(visibilityChange)




        // window.onbeforeunload = (event) => {
        //     console.log("onbeforeunload called");
        //     const e = event || window.event;
        //     // Cancel the event
        //     var msg = '';
        //     e = e || window.event;
        //     e.preventDefault();
        //     if (e) {
        //         e.returnValue = msg; // Legacy method for cross browser support
        //     }
        //     return msg; // Legacy method for cross browser support
        //     // return "Are you sure you want to navigate away?";
        // };

        document.addEventListener(visibilityChange, handleVisibilityChange, false);

        // document.addEventListener('keydown', (e) => {
        //     console.log(e.which);
        //     // if (e.which === 91 || e.key === "Tab")
        //     keys[e.which] = true;
        //     if (e.keyCode === 18) e.preventDefault()
        //     e.preventDefault();

        // });


        // document.addEventListener('contextmenu', (e) => {
        //     console.log(e.which);

        //     e.preventDefault();
        // });
    }, []);


    // useEffect(() => {
    //     const unblock = history.block((location, action) => {
    //         // if (checkBlockingCondition) {
    //         alert("called");
    //         return window.confirm("Navigate Back?");
    //         // }
    //         // return true;
    //     });
    //     return () => {
    //         unblock();
    //     }
    // }, [])

    // useEffect(() => {
    //     const unblock = history.block((location, action) => {

    //         return window.confirm("Navigate Back?");

    //         // return true;
    //     });

    //     return () => {
    //         unblock();
    //     };
    // }, [])



    const handleVisibilityChange = () => {
        // console.log("called");
        // if (document[hidden]) {
        //     setLogout(true);
        // } else {
        //     history.push('/login');
        // }

        // window.reload();
        // setLogout(true);
        //  

    }

    // const saveQuestion = (e) => {
    //     console.log(e);
    //     console.log("quesNo", quesNo);
    //     // if (e == "save")
    //     //     setquestionNo({ id: 1, type: 'answered' });
    //     // else {
    //     //     if (JSON.parse(localStorage.getItem('questionNo')) === null)
    //     //         setquestionNo({ id: 1, type: 'reviewU' });
    //     //     else
    //     //         setquestionNo({ id: 1, type: 'reviewA' });
    //     // }

    //     if (JSON.parse(localStorage.getItem('questionNo')) === null) {
    //         if (e == "save") {
    //             setquestionNo({ id: 1, type: 'unanswered' });
    //         } else {
    //             setquestionNo({ id: 1, type: 'reviewU' });
    //         }
    //     } else {
    //         if (e == "save") {
    //             setquestionNo({ id: 1, type: 'answered' });
    //         } else {
    //             setquestionNo({ id: 1, type: 'reviewA' });
    //         }

    //     }



    // }


    const submitAnswers = () => {
        // for(let i = 1 ; i < questionList.length;i++){
        //     if(finalData() questionList[i])
        // }
        console.log(JSON.stringify(finalData));
        let obj = { registrationNo: 2, resp: finalData };
        axios.put('/student/submitExam', obj).then(res => {
            console.log("res", res);
        })
        console.log("obj", obj);
    }

    // const reviewQuestion = () => {
    //     let reviewquestions;
    //     if ((JSON.parse(localStorage.getItem('reviewQuestionNo'))) === null) {

    //         localStorage.setItem('reviewQuestionNo', JSON.stringify({ 'reviewU': [], 'reviewA': [] }));
    //     }
    //     reviewquestions = JSON.parse(localStorage.getItem('reviewQuestionNo'));

    //     console.log("activeStep", activeStep)
    //     let activeQues = activeStep + 1;
    //     let questions = JSON.parse(localStorage.getItem('questionNo'));
    //     console.log("reviewquestions", reviewquestions);
    //     if (questions && questions.indexOf(activeQues) > -1) {
    //         // if (JSON.parse(localStorage.getItem('questionNo')) === question) {
    //         if (reviewquestions && reviewquestions['reviewA'].indexOf(activeQues) == -1) {

    //             reviewquestions['reviewA'].push(activeQues);
    //             if (reviewquestions['reviewU'].indexOf(activeQues) > -1) {
    //                 reviewquestions['reviewU'].splice(reviewquestions['reviewU'].indexOf(activeQues), 1);
    //             }
    //         }


    //         setquestionNo({ id: activeQues, type: 'reviewA' });
    //     } else {
    //         if (reviewquestions && reviewquestions['reviewU'].indexOf(activeQues) == -1) {
    //             reviewquestions['reviewU'].push(activeQues);
    //             if (reviewquestions['reviewA'].indexOf(activeQues) > -1) {
    //                 reviewquestions['reviewA'].splice(reviewquestions['reviewA'].indexOf(activeQues), 1);
    //             }
    //         }


    //         setquestionNo({ id: activeQues, type: 'reviewU' });
    //     }
    //     localStorage.setItem('reviewQuestionNo', JSON.stringify(reviewquestions));
    //     setactiveStep(activeStep + 1);
    // }



    const checkVisitedQues = (key) => {
        let unvisitedQues = JSON.parse(localStorage.getItem('unvisitedQues'));
        if (unvisitedQues !== null) {
            if (unvisitedQues.indexOf(key + 1) > -1) {
                unvisitedQues.splice(unvisitedQues.indexOf(key + 1), 1);
            }
            localStorage.setItem('unvisitedQues', JSON.stringify(unvisitedQues));
        }

    }

    const changeActiveStep = (key) => {
        // console.log("key", key);
        checkVisitedQues(key);
        setCurrentQuestion(questionList[key]);
        setactiveStep(key);
    }

    const reviewAndNext = useCallback(() => {
        let activeQues = activeStep + 1;
        checkVisitedQues(activeStep);
        let questions = JSON.parse(localStorage.getItem('questionNo'));
        let obj;
        if (questions === null || (questions.indexOf(activeQues) === -1)) {
            let reviewQues = JSON.parse(localStorage.getItem('reviewQuestionNo'));
            if (reviewQues === null) {
                obj = { reviewU: [activeQues], reviewA: [] };
            } else {
                obj = reviewQues;
                if (obj.reviewU.indexOf(activeQues) === -1) {
                    obj.reviewU = obj.reviewU.concat(activeQues);
                }
                if (obj.reviewA.indexOf(activeQues) > -1) {
                    obj.reviewA.splice(obj.reviewA.indexOf(activeQues), 1);
                }
            }
            setquestionNo({ id: activeQues, type: 'reviewU' });
            localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));

        } else {
            if (questions.indexOf(activeQues) > -1) {
                let reviewQues = JSON.parse(localStorage.getItem('reviewQuestionNo'));
                if (reviewQues === null) {
                    let obj = { reviewU: [], reviewA: [activeQues] };
                    setquestionNo({ id: activeQues, type: 'reviewA' });
                    localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));
                } else {
                    let obj = reviewQues;
                    if (obj.reviewA.indexOf(activeQues) === -1) {
                        obj.reviewA = obj.reviewA.concat(activeQues);
                    }
                    if (obj.reviewU.indexOf(activeQues) > -1) {
                        obj.reviewU.splice(obj.reviewU.indexOf(activeQues), 1);
                    }
                    setquestionNo({ id: activeQues, type: 'reviewA' });
                    localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));
                }
            }
            // else {
            //     let reviewQues = JSON.parse(localStorage.getItem('reviewQuestionNo'));
            //     if(reviewQues === null){
            //         let obj = {reviewU:[activeQues] , reviewA:[]};
            //         localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));
            //           setquestionNo({ id: activeQues, type: 'reviewU' });
            //     } else {
            //         let obj = reviewQues;
            //         obj.reviewU = obj.reviewU.concat(activeQues);
            //         obj.reviewA.splice( obj.reviewA.indexOf(activeQues) , 1);
            //         localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));
            //         setquestionNo({ id: activeQues, type: 'reviewU' });
            //     }
            // }
            // }
        }
        setCurrentQuestion(questionList[(activeStep + 1)]);
        setactiveStep(activeStep + 1);

    }, [activeStep]);
    // () => {

    // }


    // const saveAndNext =  useCallback(() => {
    //     let activeQues = activeStep + 1;
    //     checkVisitedQues(activeStep);
    //     setCurrentQuestion(questionList[activeQues]);
    //     let questions = JSON.parse(localStorage.getItem('questionNo'));
    //     let reviewQues = JSON.parse(localStorage.getItem('reviewQuestionNo'));
    //     if (questions && questions.indexOf(activeQues) > -1) {
    //         setquestionNo({ id: activeQues, type: 'answered' });
    //     }
    //     if (reviewQues !== null) {
    //         let obj = reviewQues;
    //         if (obj.reviewA.indexOf(activeQues) > -1) {
    //             obj.reviewA.splice(obj.reviewA.indexOf(activeQues), 1);
    //         }

    //         if (obj.reviewU.indexOf(activeQues) > -1) {
    //             obj.reviewU.splice(obj.reviewU.indexOf(activeQues), 1);
    //         }

    //         console.log("obj", obj);
    //         localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));
    //     }
    //     setactiveStep(activeStep + 1);
    // },[activeStep]);

    const saveAndNext = () => {
        let activeQues = activeStep + 1;
        checkVisitedQues(activeStep);
        let questions = JSON.parse(localStorage.getItem('questionNo'));
        let reviewQues = JSON.parse(localStorage.getItem('reviewQuestionNo'));
        if (questions && questions.indexOf(activeQues) > -1) {
            setquestionNo({ id: activeQues, type: 'answered' });
        }
        if (reviewQues !== null) {
            let obj = reviewQues;
            if (obj.reviewA.indexOf(activeQues) > -1) {
                obj.reviewA.splice(obj.reviewA.indexOf(activeQues), 1);
            }

            if (obj.reviewU.indexOf(activeQues) > -1) {
                obj.reviewU.splice(obj.reviewU.indexOf(activeQues), 1);
            }

            console.log("obj", obj);
            localStorage.setItem('reviewQuestionNo', JSON.stringify(obj));
        }
        setCurrentQuestion(questionList[(activeStep + 1)]);
        setactiveStep(activeStep + 1);
    }

    const handleNext = () => {
        checkVisitedQues(activeStep);
        setCurrentQuestion(questionList[(activeStep + 1)]);
        setactiveStep(activeStep + 1);
    };


    const resetoption = () => {

        // dispatch({type:'RESET',reset:true});
        
        localStorage.setItem('isReset', JSON.stringify({ 'reset': true }));

        // for (let i = 0; i < questionList.length; i++) {
        //     if (questionList[i].id.questionNo === (activeStep + 1)) {
        //         if (questionList[i].multiple === 'N') {
        //             setResetData('radio');
        //         } else {
        //             setResetData('checkbox');
        //         }
        //     }
        // }
        let answeredQues = JSON.parse(localStorage.getItem('questionNo'));
        let reviewQuestionNo = JSON.parse(localStorage.getItem('reviewQuestionNo'));
        if (answeredQues !== null) {
            if (answeredQues.indexOf(activeStep + 1) > -1) {
                answeredQues.splice(answeredQues.indexOf(activeStep + 1), 1);
            }
            localStorage.setItem('questionNo', JSON.stringify(answeredQues));

        }
        if (reviewQuestionNo !== null) {
            if (reviewQuestionNo.reviewU.indexOf(activeStep + 1) > -1) {
                reviewQuestionNo.reviewU.splice(reviewQuestionNo.reviewU.indexOf(activeStep + 1), 1);
            }
            if (reviewQuestionNo.reviewA.indexOf(activeStep + 1) > -1) {
                reviewQuestionNo.reviewA.splice(reviewQuestionNo.reviewA.indexOf(activeStep + 1), 1);
            }
            localStorage.setItem('reviewQuestionNo', JSON.stringify(reviewQuestionNo));
        }
        setquestionNo({ id: (activeStep + 1), type: 'default' });
        setactiveStep(activeStep);
        dispatch({ type: 'RESET', reset: true });
    }

    const handleBack = () => {
        setactiveStep(activeStep - 1);
        setCurrentQuestion(questionList[(activeStep - 1)]);
    };


    const handleStepChange = activeStep => {
        setactiveStep(activeStep);
    };



    const response = (event) => {
        let index = -1;
        let newAns = [];
        if (event.isradio) {
            let resData = [...state.finalData];
            // let resData = [...finalData];



            for (let i = 0; i < resData.length; i++) {
                if (resData[i]['id']['questionId'] == event.id) {
                    // isUpdate = true;
                    index = i;
                    break;
                }
            }
            newAns.push(event.option);
            let option = newAns[0].trim();
            if (index === -1) {
                let obj = {
                    "id": {
                        registrationNo: 2,
                        questionId: event.id,
                        yearOfExam: 2020
                    },
                    selectedAnswer: option
                }
                resData = [...resData, obj];
            } else {
                resData[index].selectedAnswer = option;
            }

            //  if radio is checked

            // setfinalData(resData);
            dispatch({ type: 'ADD_FINAL_DATA', finalData: resData });

        } else {
            //  if checkbox is checked
            let resData = [...state.finalData];
            for (let i = 0; i < resData.length; i++) {
                if (resData[i]['id']['questionId'] == event.id) {
                    // isUpdate = true;
                    index = i;
                    break;
                }
            }
            if (index === -1) {
                newAns.push(event.name);
                let option = newAns[0].trim();
                let obj = {
                    "id": {
                        registrationNo: 2,
                        questionId: event.id,
                        yearOfExam: 2020
                    },
                    selectedAnswer: option
                }
                resData = [...resData, obj];
                // setfinalData(resData);
                dispatch({ type: 'ADD_FINAL_DATA', finalData: resData });
            } else {
                let selectedOptionObj = resData[index];
                let isElemPresent = false;
                let prevAns = selectedOptionObj['selectedAnswer'];
                // if (prevAns === "") {
                //     newAns.push(event.name);
                // } else {
                newAns = prevAns === "" ? [] : prevAns.split(",");
                // }
                // if (newAns.length === 0) {
                //     newAns.push(event.name);
                // } else {
                for (let i = 0; i < newAns.length; i++) {
                    if (newAns[i].trim() == event.name.trim()) {
                        isElemPresent = true;
                        // if checked flag is turn to false
                        if (event.isChecked === false) {
                            newAns.splice(i, 1);
                            i--;
                        }
                    }
                }
                if (!isElemPresent) {
                    // if checked flag is turned to true
                    newAns.push(event.name);
                }
                if (newAns.length === 0) {
                    resData[index].selectedAnswer = "";
                } else {
                    // let obj = {
                    //     "id": {
                    //         registrationNo: 2,
                    //         questionId: event.id,
                    //         yearOfExam: 2020
                    //     },
                    //     selectedAnswer: (newAns.join(","))
                    // }
                    resData[index].selectedAnswer = (newAns.join(","));
                    // resData = [...resData];
                }

                dispatch({ type: 'ADD_FINAL_DATA', finalData: resData });
                // setfinalData(resData);
                // }
            }
        }
        if (event.id !== undefined) {
            let arr = [];
            const questionNo = JSON.parse(localStorage.getItem('questionNo'));
            if (questionNo === null) {
                arr.push(event.id);
            } else {
                if (questionNo.indexOf(event.id) == -1) {
                    arr = questionNo.concat(event.id);
                } else {
                    arr = questionNo;
                }

            }

            localStorage.setItem('questionNo', JSON.stringify(arr));
        }

    }



    const resetValue = (flag) => {
        setResetData(flag);
    }

    const checkQuestionNo = (quesNo) => {
        console.log("quesNo", quesNo);
        // localStorage.setItem('questionNo', JSON.stringify(quesNo));
        quesNo = quesNo;
        setquestion(quesNo);
        //setquestionNo({ id: quesNo, type: 'answered' });
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

            <Grid container className={classes.containerStyle}>
                <Grid container xs={12} md={8} lg={8} className={classes.leftContainer}>
                    <DisplayQuesCount totalQues={questionList.length} />
                    {/* <Paper square elevation={0} className={classes.header}>
                       <span>
                       <Typography>{questionList && questionList[activeStep] && questionList[activeStep].id && questionList[activeStep].id.questionNo} {questionList && questionList[activeStep] && questionList[activeStep].question}</Typography>
                       </span> 
                    </Paper> */}
                    {/* <Prompt  message="Are you sure you want to logout ?" /> */}
                    {/* {Questions.map(question => ( */}



                    <div>

                        {questionList && (<div>
                            <RadioButtonsGroup
                                // isReset={resetData}
                                activeStep={activeStep}
                                objective={currentQuestion}
                            // setReset={(isReset) => { resetValue(isReset) }}
                            // reset={resetData}

                            // response={(event) => response(event)}
                            // checkQuestionNo={(quesNo) => checkQuestionNo(quesNo)}
                            />
                        </div>)}

                    </div>
                    {/* <div className={classes.swipableViews}>
                        <SwipeableViews
                            axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
                            index={activeStep}
                            key={activeStep}
                            onChangeIndex={handleStepChange}
                            enableMouseEvents
                        >                           
                            {questionList && questionList.map(question => (
                                <div key={question.id.questionNo}>
                                     <RadioButtonsGroup
                                    activeStep={activeStep}
                                    objective={question}
                                     />
                                </div>
                               
                            ))}

                        </SwipeableViews>
                    </div> */}

                    {/* ))} */}
                    {/* <MobileStepper
                        steps={maxSteps}
                        position="static"
                        variant="progress"
                        activeStep={activeStep}
                        className={classes.mobileStepper}
                        nextButton={
                            <Button size="small" onClick={handleNext} disabled={activeStep === maxSteps - 1}>
                                Save & Next
              {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
                            </Button>
                        }
                        backButton={
                            <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
                                {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
                  Back
            </Button>

                        }
                    /> */}
                    <Divider />
                    <AppBar position="static" className={classes.Footer}>
                        <Toolbar className={classes.Toolbar}>
                            <IconButton aria-label="left arrow" disabled={activeStep === 0} onClick={handleBack}>
                                <ArrowBackIosIcon></ArrowBackIosIcon>
                            </IconButton>
                            {/* <Button variant="outlined" onClick={handleBack} endIcon={<ArrowBackIosIcon></ArrowBackIosIcon>} disabled={activeStep === 0}></Button> */}

                            <Button variant="outlined" onClick={resetoption} color="secondary">Reset</Button>

                            <Button type="button" variant="outlined" color="primary" onClick={reviewAndNext} disabled={activeStep === maxSteps - 1}>
                                Review & Next
                    </Button>
                            <Button type="button" variant="outlined" color="primary" onClick={saveAndNext} disabled={activeStep === maxSteps - 1}>
                                Save & Next
                    </Button>
                            <Button color="primary" variant="contained" onClick={submitAnswers}>Submit</Button>
                            {/* <Button color="primary" className={classes.rightIcon} onClick={handleNext} endIcon={<ArrowForwardIosIcon></ArrowForwardIosIcon>} disabled={activeStep === maxSteps - 1} variant="contained"></Button> */}
                            <IconButton aria-label="right arrow" disabled={activeStep === maxSteps - 1} onClick={handleNext}>
                                <ArrowForwardIosIcon></ArrowForwardIosIcon>
                            </IconButton>
                        </Toolbar>
                    </AppBar>

                    {/* <div className={classes.buttonContainer}>

                        <div>
                            <Button size="small" onClick={handleBack} endIcon={<ArrowBackIosIcon></ArrowBackIosIcon>} disabled={activeStep === 0}>
                            </Button>
                        </div>




                        <div>
                            <Button variant="contained" color="primary" size="small" onClick={resetoption} >

                                Reset
                            </Button>

                            <Button variant="contained" color="primary" size="small" onClick={submitAnswers} >

                                Submit
                            </Button>


                            <Button variant="contained" color="primary" size="small" onClick={reviewAndNext} disabled={activeStep === maxSteps - 1}>

                                Review & Next
                            </Button>


                            <Button variant="contained" color="primary" size="small" onClick={saveAndNext} disabled={activeStep === maxSteps - 1}>

                                Save & Next
                            </Button>
                        </div>


                        <div>
                            <Button size="small" onClick={handleNext} endIcon={<ArrowForwardIosIcon></ArrowForwardIosIcon>} disabled={activeStep === maxSteps - 1}>
                            </Button>
                        </div>


                    </div>







                    {/* <Button onClick={() => { saveQuestion('save') }} value="save" variant="contained">Save & Next</Button>
                    <Button onClick={() => { saveQuestion('review') }} onClick={saveQuestion} variant="contained" color="primary">
                        Review
                    </Button> */}
                </Grid>
                <Grid item xs={12} md={4} lg={3} className={classes.rightContainer}>

                    <ButtonsGroup arrLen={questionList} questionInfo={questionNo} changeStep={changeActiveStep} />


                </Grid>
            </Grid>

        </>
    )
}
export default withStyles(useStyles, { withTheme: true })(Test);
// export default Test
