import React, { useContext } from 'react';

import { makeStyles } from '@material-ui/core/styles';
import { ExamDataContext } from './context/ExamDataContext';


const useStyles = makeStyles((theme) => ({
    loader: {
        width: 263,
        height: 227,
        backgroundImage: `url(loader_static.png)`,
        position: 'absolute',
        backgroundRepeat: 'no-repeat',
        backgroundSize: '50% 50%',
        transform: 'translate(50%,-50%)',
        top:'61%',
        right:'47%',
        zIndex:'99999'
    },
    overlay: {
        content: "",
        display: 'block',
        height: '100%',
        position: 'absolute',
        top: '0',
        left: '0',
        width: '100%',
        backgroundColor: 'rgb(93 90 90 / 80%)',
        zIndex:'9999'
    }

}));


export default function Loader() {
    const { state, dispatch } = useContext(ExamDataContext);
    let isLoading = state.isLoading;
    console.log("state,dispatch", state, dispatch);
    const classes = useStyles();
    return (
        <>
            {isLoading && (
                <div>
                    <div className={classes.loader}>
                    </div>
                    <div className={classes.overlay}>
                    </div>
                </div>

            )}
        </>

    )

}