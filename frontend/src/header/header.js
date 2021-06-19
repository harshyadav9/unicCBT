import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import { CountdownCircleTimer } from "react-countdown-circle-timer";
import Timer from '../Timer';

const useStyles = makeStyles((theme) => ({
    root: {
        height: '3em',
        flexGrow: 1,
    },
    appbar: {
        alignItems: 'center',
        display: "flex",
        justifyContent: "space-between"

    },
}));

function Header() {
    const classes = useStyles();
    const childrenFunc = ({ remainingTime }) => {
        const minutes = Math.floor(remainingTime / 60);
        const seconds = remainingTime % 60;

        if (seconds === 0) {
            // return <div className="timer">Your time is over...</div>;
        } else return `${minutes}:${seconds}`;
    };
    return (
        <div className={classes.root}>
            <AppBar position="fixed" color="grey">
                <Toolbar className={classes.appbar}>
                    <Typography variant="h6">
                        CBT-Exam
                </Typography>
                    {/* <div className="timer-wrapper">
                        <CountdownCircleTimer
                            isPlaying
                            duration={115}
                            size={100}
                            colors={[["#004777", 0.33], ["#F7B801", 0.33], ["#A30000"]]}
                            onComplete={() => [false, 10]}
                        >
                            {childrenFunc}
                        </CountdownCircleTimer>
                    </div> */}
                    <Timer />
                    {/* <Typography color="inherit" >Time: 123 </Typography> */}
                </Toolbar>

            </AppBar>
        </div>
    )
}

export default Header
