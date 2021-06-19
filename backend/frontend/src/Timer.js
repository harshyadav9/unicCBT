import React, { Component } from 'react'

export default class Timer extends Component {

    state = {
        minutes: 60,
        seconds: 60,
        hours: 3,
        count: 0
    }

    //  1 hr 2 min
    //  1min 10 sec

    computeTime(flag) {
        if (flag == "reduceOnlyMin") {
            this.setState(({ minutes, count }) => ({
                minutes: minutes - 1,
                seconds: 60,
                count: count + 1
            }))
        }


    }

    componentDidMount() {
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
                        }))
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
        const { minutes, seconds, hours } = this.state
        return (
            <div>
                {/* { minutes === 0 && seconds === 0 */}
                {/* ? <h1>Busted!</h1> */}
                <h1>Time Remaining: {hours < 10 ? `0${hours}hrs` : `${hours}hrs`} :{minutes < 10 ? `0${minutes}min` : `${minutes}min`}:{seconds < 10 ? `0${seconds}sec` : `${seconds}sec`}</h1>
                {/* } */}
            </div>
        )
    }
}
