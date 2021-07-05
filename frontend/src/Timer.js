import React, { Component } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/styles';
import { Paper, Typography, Divider, 
     List, ListItem, ListItemText } from '@material-ui/core';
    
const useStyles = theme => ({

    padding: {
        padding: 10
    },
    timerStyle: {
        display: 'grid',
        gridTemplateColumns: 'repeat(3, 1fr)',
        gridGap: 10,
        padding: '20px 10px',
        '& li': {
            textAlign: 'center',
            padding: 0,
            '& div': {
                background: '#2a3b64',
                color: '#fff',
                margin: 0,
                borderRadius: 6,
                padding: '2px 0px 10px',
                '& span': {
                    fontSize: '2rem'
                },
                '& p': {
                color: '#fff',

                }
            }
        }

    }

});
class Timer extends Component {


    state = {
        minutes: 0,
        seconds: 0,
        hours: 2,
        count: 0,
        showWarning: false
    }


    //  1 hr 2 min
    //  1min 10 sec

    componentDidMount() {
        // this.setState({
        //     minutes: this.props.minutes,
        //     seconds: 60,
        //     hours: this.props.minutes,
        //     count: 0,
        //     showWarning: false
        // })
        this.myInterval = setInterval(() => {
            const { seconds, minutes, hours, count } = this.state
            //       00 hrs:00 min :00 sec
            if (seconds > 0) {
                this.setState(({ seconds }) => ({
                    seconds: seconds - 1
                }))
            } else
                // if (seconds === 0) {
                if (hours == 0) {
                    //  1 hr scene
                    if (minutes === 0) {
                        clearInterval(this.myInterval);
                    } else {
                        // sec = 0 , hour = 0 , minutes reduced by 1  ,  sec 10
                        this.setState(({ minutes, count }) => ({
                            minutes: minutes - 1,
                            seconds: 60,
                            count: count + 1
                        }))
                        if (count === 2) {
                            this.setState(({ showWarning }) => ({ showWarning: true }));
                        }
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
                    if (minutes > 0) {
                        this.setState(({ minutes, count }) => ({
                            minutes: minutes - 1,
                            seconds: 60,
                        }))

                    } else {
                        this.setState(({ hours, count }) => ({
                            hours: hours - 1,
                            seconds: 60,
                            minutes: 60,
                        }))
                    }


                }

            // }
        }, 1000)
    }



    componentWillUnmount() {
        clearInterval(this.myInterval)
    }

    render() {
        // const { minutes, hours  , seconds = 60} = this.state;
        // console.log("this.state", this.state);
        const { classes } = this.props;
        // console.log("classes",classes)
        // return (
        //     <div>
        //         {/* { minutes === 0 && seconds === 0 */}
        //         {/* ? <h1>Busted!</h1> */}
        //         <h1>Time Remaining: <span className={classes.showWarningStyl}>
        //             {this.state.hours < 10 ? `0${this.state.hours}hrs` : `${this.state.hours}hrs`} :{this.state.minutes < 10 ? `0${this.state.minutes}min` : `${this.state.minutes}min`}:{this.state.seconds < 10 ? `0${this.state.seconds}sec` : `${this.state.seconds}sec`}
        //         </span>
        //         </h1>
        //         {/* } */}
        //     </div>


        return (
            <>
            <Paper elevation={3}>
                <Typography variant="h6" gutterBottom className={classes.padding}>
                    Time Remaining :
                </Typography>
                <Divider />
                <List className={classes.timerStyle}>
                    <ListItem>
                        <ListItemText primary={this.state.hours < 10 ? `0${this.state.hours}` : `${this.state.hours}`} secondary="HOURS" />
                    </ListItem>
                    <ListItem>
                        <ListItemText primary={this.state.minutes < 10 ? `0${this.state.minutes}` : `${this.state.minutes}`} secondary="MINUTES" />
                    </ListItem>
                    <ListItem>
                        <ListItemText primary={this.state.seconds < 10 ? `0${this.state.seconds}` : `${this.state.seconds}`} secondary="SECONDS" />
                    </ListItem>
                </List>
            </Paper>
            </>
        )
    }
}
export default withStyles(useStyles)(Timer);
