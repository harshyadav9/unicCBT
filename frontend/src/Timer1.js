import React, { Component, useEffect, useState,useContext } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/styles';
import { ExamDataContext } from './context/ExamDataContext';


const useStyles = makeStyles((theme) => ({

    showWarningStyl: {
        color: 'red'
    }

}));


function Timer(props) {
    const { state,dispatch } = useContext(ExamDataContext);
    let { classes  } = props;
    let seconds = 60;
    const [timer , setTimer] = useState({hours:0 , minutes:0,seconds:0});
    
        console.log("state in  timer" , state);

        useEffect(() => {
            console.log("state useeffect called");
            // setTimer(prevState => ({
            //             ...prevState,
            //             hours:state.hours,
            //             minutes:state.minutes
            //         }));
                    setTimer(prevState => ({
                        ...prevState,
                        hours:state.hours,
                        minutes:state.minutes
                    }),() => {
                        alert("done")
                    });

                    setTimeout(() => {
                        let myInterval = setInterval(() => {
                            // const { seconds, minutes, hours, count } = this.state
                            //       00 hrs:00 min :00 sec
                            console.log("timer inside interval" , timer);
                            if (seconds > 0) {
                                // setTimer({
                    
                                // })
                                setTimer(prevState => ({
                                    ...prevState,
                                    seconds: prevState.seconds - 1
                                }));
                            } else
                                // if (seconds === 0) {
                                if (state.hours === 0) {
                                    //  1 hr scene
                                    if (state.minutes === 0) {
                                        clearInterval(myInterval);
                                    } else {
                                        // sec = 0 , hour = 0 , minutes reduced by 1  ,  sec 10
                
                                        setTimer( prevState => ({
                                            ...prevState,
                                            minutes: prevState.minutes - 1,
                                            seconds: 60,
                                        }));
                
                
                                        // setTimer(({ minutes, count }) => ({
                                            
                                        //     count: count + 1
                                        // }))
                                        // if (count === 2) {
                                        //     setState(({ showWarning }) => ({ showWarning: true }));
                                        // }
                                        // if (count == 2) {
                                        //     this.setState(({ hours }) => ({
                                        //         hours: hours - 1,
                                        //         count: 0,
                                        //         minutes: 2
                                        //     }))
                                        // }
                                    }
                                } else {
                                    // hours not 0 sec 0
                                    //       01 hrs:00 min :00 sec
                                    if (state.minutes > 0) {
                                        // setTimer(({ minutes, count }) => ({
                                        //     minutes: minutes - 1,
                                        //     seconds: 60,
                                        // }))
                
                
                                        setTimer( prevState => ({
                                            ...prevState,
                                            minutes: prevState.minutes - 1,
                                            seconds: 60,
                                        }));
                    
                                    } else {
                                        // setTimer(({ hours, count }) => ({
                                        //     hours: hours - 1,
                                        //     seconds: 60,
                                        //     minutes: 60,
                                        // }))
                
                                        setTimer( prevState => ({
                                            ...prevState,
                                            hours: prevState.hours - 1,
                                            seconds: 60,
                                            minutes: 60,
                                        }));
                                    }
                    
                    
                                }
                    
                            // }
                        }, 1000);
                    },1000);
                   
                  
           
        },[state]);

  
  
   
    return (
        <div>
            {/* { minutes === 0 && seconds === 0 */}
            {/* ? <h1>Busted!</h1> */}
            <h1>Time Remaining: <span className={classes.showWarningStyl}>
                {timer.hours < 10 ? `0${timer.hours}hrs` : `${timer.hours}hrs`} :{timer.minutes < 10 ? `0${timer.minutes}min` : `${timer.minutes}min`}:{timer.seconds < 10 ? `0${timer.seconds}sec` : `${timer.seconds}sec`}
            </span>
            </h1>
            {/* } */}
        </div>
    )
}


export default withStyles(useStyles, { withTheme: true })(Timer);