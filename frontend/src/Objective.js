import React, { useState , useEffect,useContext } from 'react';
import PropTypes from 'prop-types';
import { withStyles,makeStyles } from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import Checkbox from '@material-ui/core/Checkbox';
import {
    FormLabel
} from '@material-ui/core';
import { ExamDataContext } from './context/ExamDataContext';
const styles = makeStyles(theme => ({
    root: {
        display: 'flex',
        marginLeft: 2,
        marginBottom: 49
    },
    // formControl: {
    //     margin: theme.spacing.unit * 3,
    //     [theme.breakpoints.only('xs')]: {
    //         margin: 0
    //     },
    //     [theme.breakpoints.between('xs, sm')]: {
    //         margin: 0
    //     }
    // },
    formControl: {
        margin: '33px 1px 4px 7px',
        height: '240px',
        padding: '6px',
        '& legend': {
            fontSize: 20,
            display: 'flex',
            lineHeight: '1.4',
            marginBottom: 6,
            color: '#000',
        }
    },
    group: {
        margin: '8px 0 0 23px',
    },
    quesNo: {
        marginRight: '8px'
    },
    quesText: {
        textAlign: 'left'
    }
}));




function RadioButtonsGroup(props) {



    const { objective,activeStep } = props;
    // console.log(" objective,activeStep", objective,activeStep)
    const [radioval, setRadio] = useState({
        radio: '',
        checkbox: new Map(),
        reset: false
    });
    const { state, dispatch } = useContext(ExamDataContext);
    // console.log("state",state);
    const classes = styles();
    // let resetFlag = JSON.parse(localStorage.getItem('isReset')) || false;
    // console.log("resetFlag",resetFlag);
    // class RadioButtonsGroup extends React.Component {
    // state = {
    //     radio: '',
    //     checkbox: new Map(),
    //     reset: false
    // };


    // componentDidMount() {
    //     console.log("value", this.state);
    // }


    useEffect(() => {
        console.log("objective called");
        if((objective && objective.id && objective.id.questionNo === (activeStep+1)) && state.questions[activeStep+1] !== undefined && !state.reset){
            console.log("if callled");
            if(typeof(state.questions[activeStep+1]) === "string"){
                setRadio(prevState => ({
                    ...prevState,
                    radio:  state.questions[activeStep+1]
                }));
            } else {
                setRadio(prevState => ({
                    ...prevState,
                    checkbox: state.questions[activeStep+1]
                }));
            }
           
        }
    },[objective]);

   
    useEffect(() => {
        if(state.reset){
            if(objective && objective.id && objective.id.questionNo === (activeStep+1)){
                // if(typeof(state.questions[activeStep+1]) === "string"){
                    let obj = {};
                    if(objective.multiple === 'N'){
                        setRadio(prevState => ({
                            ...prevState,
                            radio: ""
                        }));
                        obj = {...state.questions , ...{[objective.id.questionNo]:""}};
                    } else {
                        let arr = new Map();
                        for (let i = 0; i < 4; i++) {
                            let obj = {};
                            arr.set(`option${i + 1}`, false);
                        }
                        setRadio(prevState => ({
                            ...prevState,
                            checkbox: arr
                        }));
                        obj = {...state.questions , ...{[objective.id.questionNo]:arr}};
                      
                    }
                   
                // } else {
                   
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
                // dispatch({ type: 'RESET', reset: false });
                dispatch({type:'ADD_QUESTION_VALUES' , questions:obj , reset:false});
            }
            // localStorage.setItem('isReset',JSON.stringify({'reset':false}));
           
        }
    
    },[state.reset]);
    
    const handleChange1 = (eventval, option, objective) => {
        let event = { option: option, id: objective.id.questionNo, isradio: true };
        // buttonChangeHandler(event);
        // console.log("event, option, objective", eventval, option, objective);
        setRadio(prevState => ({
            ...prevState,
            radio: eventval
        }));
        let obj = {...state.questions , ...{[objective.id.questionNo]:eventval}};
         dispatch({type:'ADD_QUESTION_VALUES' , questions:obj , reset:false});
        // this.setState(() => ({
        //     radio:event
        // }))
        // this.setState({ radio: event });
        // console.log("this.state",this.state);
        // response();
        updateQuestionNo(event);
    }

    const updateQuestionNo = (event) => {
        // console.log("updateQuestionNo",event.id);
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

    const buttonChangeHandler = (event) => {
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
            dispatch({ type: 'ADD_FINAL_DATA', finalData: [...state.finalData,...resData] });

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
                dispatch({ type: 'ADD_FINAL_DATA', finalData: [...state.finalData,...resData] });
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

                dispatch({ type: 'ADD_FINAL_DATA', finalData: [...state.finalData,...resData] });
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


    const handleChange = (isChecked, name, objective) => {


        setRadio(prevState => ({
            ...prevState,
            checkbox: prevState.checkbox.set(name, isChecked)
        }));

        let arr = new Map();
       if(state.questions[objective.id.questionNo] === undefined){
           arr.set(name,isChecked);

       }else {
            arr = state.questions[objective.id.questionNo];
           arr.set(name,isChecked);
       }

        let obj = {...state.questions , ...{[objective.id.questionNo]:arr}};
        dispatch({type:'ADD_QUESTION_VALUES' , questions:obj , reset:false});
        // this.setState(prevState => ({
        //     checkbox: prevState.checkbox.set(name, isChecked)
        // }));
        // let arr = JSON.parse(localStorage.getItem('questionNo'));
        // console.log("arr", arr)
        // if (arr == null) {
        //     arr = [];
        //     arr.push(quesNo);
        //     localStorage.setItem('questionNo', JSON.stringify(arr));
        // } else {
        //     if (arr.indexOf(quesNo) == -1) {
        //         arr.push(quesNo);
        //         localStorage.setItem('questionNo', JSON.stringify(arr));
        //     }

        // }
        // // localStorage.setItem('questionNo', JSON.stringify(quesNo));


        // if (event === ans) {
            let event = { name: name, isChecked: isChecked, id: objective.id.questionNo, isradio: false };
            // response();
            // buttonChangeHandler(event);
            updateQuestionNo(event);
        // response({ name: name, isChecked: isChecked, id: objective.id.questionNo, isradio: false });
        //     // this.setState({ value: '' });
        // } else {
        //     this.props.checkQuestionNo(quesNo);
        //     // this.setState({ value: '' });
        // }
    }

    const setCheckboxState = (objective) => {
        let arr = new Map();
        for (let i = 0; i < 4; i++) {
            let obj = {};
            arr.set(`option${i + 1}`, false);
        }
        setRadio(prevState => ({
            ...prevState,
            checkbox: arr
        }));
        // this.setState({ checkbox: arr });
    }



   const  resetValues = (resetValue) => {
        console.log("reset called");
        if (resetValue) {
            setRadio(prevState => ({
                ...prevState,
                radio: ''
            }));
            // this.setState({ radio: '' });
        }
    }

    // componentDidMount() {
    //     console.log("this.state", this.state);
    //     if (this.props.objective.single === "N") {
    //         this.setCheckboxState(this.props.objective);
    //     }

    //     // console.log("this.state>>", this.state)

    // }



    // render() {
   
    // useEffect(() => {
    //     // console.log("activeStep",activeStep);
    //     // console.log("objective",objective);
    //     console.log("radio state" , radio);
    //     if(objective.id.questionNo === 1){
    //         setRadio(prevState => ({
    //             ...prevState,
    //             radio: ' A- Thermal pollution'
    //         }));
           
    //     }
    //     if (objective.single === "N") {
    //         setCheckboxState(objective);
    //     }
    //     if (reset === 'radio') {
    //         setRadio(prevState => ({
    //             ...prevState,
    //             radio: ''
    //         }));
    //         // this.props.setReset('');
    //         setReset('')
    //     }
    //     if (reset === 'checkbox') {
    //         // this.setState(() => ({
    //         //     checkbox: new Map()
    //         // }));
    //         setRadio(prevState => ({
    //             ...prevState,
    //             checkbox: new Map()
    //         }));
    //         // this.props.setReset('');
    //         setReset('')
    //     }
    
    // },[objective,reset]);

  
    // console.log("this.props.reset",this.props.reset);

   

  
    // if (isReset) {
    //     this.resetState(objective);
    // }
    // console.log("isReset", isReset)

    return (
        <div className={classes.root}>
            <div>

                <FormControl component="fieldset" className={classes.formControl}>
                    <FormLabel component="legend"><span className={classes.quesNo}>{objective && objective.id && objective.id.questionNo})</span><span className={classes.quesText}>{objective && objective.question}</span></FormLabel>
                    {objective && objective.multiple === 'N' ? (<RadioGroup
                        aria-label="Gender"
                        name="gender1"
                        className={classes.group}
                        value={radioval.radio}
                    // onChange={(e) => this.handleChange(e.target, objective)}
                    >
                        <FormControlLabel value={objective.option1} onChange={(e) => handleChange1(e.target.value, 'option1', objective)} control={<Radio />} label={objective.option1} />
                        <FormControlLabel value={objective.option2} onChange={(e) => handleChange1(e.target.value, 'option2', objective)} control={<Radio />} label={objective.option2} />
                        {objective.option3 === undefined ? null
                            :
                            <FormControlLabel value={objective.option3} onChange={(e) => handleChange1(e.target.value, 'option3', objective)} control={<Radio />} label={objective.option3} />}
                        {objective.option4 === undefined ? null :
                            <FormControlLabel value={objective.option4} onChange={(e) => handleChange1(e.target.value, 'option4', objective)} control={<Radio />} label={objective.option4}
                            />}
                    </RadioGroup>) : (
                            <FormGroup>
                                <FormControlLabel
                                    control={<Checkbox checked={!!radioval.checkbox.get('option1')} onChange={(e) => handleChange(e.target.checked, 'option1', objective)} name={objective && objective.option1} />}
                                    label={objective && objective.option1}
                                />
                                <FormControlLabel
                                    control={<Checkbox checked={!!radioval.checkbox.get('option2')} onChange={(e) => handleChange(e.target.checked, 'option2', objective)} name={objective && objective.option2} />}
                                    label={objective && objective.option2}
                                />
                                <FormControlLabel
                                    control={<Checkbox checked={!!radioval.checkbox.get('option3')} onChange={(e) => handleChange(e.target.checked, 'option3', objective)} name={objective && objective.option3} />}
                                    label={objective && objective.option3}
                                />
                                <FormControlLabel
                                    control={<Checkbox checked={!!radioval.checkbox.get('option4')} onChange={(e) => handleChange(e.target.checked, 'option4', objective)} name={objective && objective.option4} />}
                                    label={objective && objective.option4}
                                />
                            </FormGroup>

                        )}
                </FormControl>
            </div>

        </div>
    );
    // }
}

// RadioButtonsGroup.propTypes = {
//     classes: PropTypes.object.isRequired,
// };

export default RadioButtonsGroup;
