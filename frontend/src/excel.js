import React, { useState, useEffect } from 'react';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import Chip from '@material-ui/core/Chip';
import HomeIcon from '@material-ui/icons/Home';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Button from '@material-ui/core/Button';
import { emphasize, withStyles, makeStyles } from '@material-ui/core/styles';
import axios from 'axios';
import { useSnackbar } from "notistack";
import Slide from '@material-ui/core/Slide';


const useStyles = makeStyles((theme) => ({
    container: {
        marginTop: '60px'
    },
    buttonContainer:{
        display:'flex',
        marginTop: '20px',
        justifyContent: 'space-evenly'
    }

}));
const StyledBreadcrumb = withStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.grey[100],
        height: theme.spacing(3),
        color: theme.palette.grey[800],
        fontWeight: theme.typography.fontWeightRegular,
        '&:hover, &:focus': {
            backgroundColor: theme.palette.grey[300],
        },
        '&:active': {
            boxShadow: theme.shadows[1],
            backgroundColor: emphasize(theme.palette.grey[300], 0.12),
        },
    },
    container: {
        marginTop: '63px',
        width: '100%'
    }
}))(Chip);





export default function Excel(props) {
    const classes = useStyles();
    let endPoint = "";
    // const { enqueueSnackbar } = useSnackbar();
    let [apiName, setApiName] = useState("");
    let [uploadExcelButton, setUploadExcel] = useState(true);
    // const {  error, success } = props;
    // console.log(enqueueSnackbar,error,success)

    function handleClick(event) {
        event.preventDefault();
        console.info('You clicked a breadcrumb.', event.target.outerText);
        setApiName(event.target.outerText);     
        console.log("endPoint", endPoint)
    }


    // function onChanges(e){
    //     setUploadExcel(false);
    //     let files = e.target.files;
    //     let reader = new FileReader();
    //     reader.readAsDataURL(files[0]);
    //     reader.onload = (e) => {
    //         console.log(e);
    //     }
    // }

    function uploadExcel() {
     

        let imagefile = document.querySelector('#file').files[0];
        console.log("imagefile",imagefile)
        if (imagefile && imagefile.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
            let formData = new FormData();
            console.log("imagefile", imagefile)
            formData.append("file", imagefile);
            // console.log("endPoint",endPoint);
            axios.post(`cbt/upload${apiName}`, formData, {
                headers: {
                    'Content-Type': imagefile.type
                }
            }).then(response => {
                alert("excel file uploaded successfully");
                console.log("response excel" , response);
            }).catch(error => {
                alert("There is error in upload the excel kindly select the file and upload again");
                console.log("error in excel" , error);
            })
            // document.querySelector('#file').files = "";document.querySelector('#file')
            document.querySelector('#file').value = "";
            setApiName("");
          
            // enqueueSnackbar({
            //     variant: "success",
            //     anchorOrigin: {
            //       vertical: "bottom",
            //       horizontal: "right",
            //     },

            //   });
        } else {
            setUploadExcel(false);
            // enqueueSnackbar({
            //     variant: "error",
            //     anchorOrigin: {
            //       vertical: "bottom",
            //       horizontal: "right",
            //     },

            //   });
            alert("Either you have not selected the file or the file type is not excel ");
        }

    }

    return (
        <div className={classes.container}>
            <div>
                <h3>Admin Functions</h3>
                <hr />
                <Breadcrumbs aria-label="breadcrumb">
                    <StyledBreadcrumb
                        component="a"
                        href="#"
                        label="/importQuestionMasterData"
                        icon={<HomeIcon fontSize="small" />}
                        onClick={handleClick}
                    />

                    <StyledBreadcrumb
                        component="a"
                        href="#"
                        label="/importInstitutionMasterData"
                        icon={<HomeIcon fontSize="small" />}
                        onClick={handleClick}
                    />

                    <StyledBreadcrumb
                        component="a"
                        href="#"
                        label="/importExamYearMasterData"
                        icon={<HomeIcon fontSize="small" />}
                        onClick={handleClick}
                    />

                    <StyledBreadcrumb
                        component="a"
                        href="#"
                        label="/importConfigData"
                        icon={<HomeIcon fontSize="small" />}
                        onClick={handleClick}
                    />
                    <StyledBreadcrumb
                        component="a"
                        href="#"
                        label="/importCandidateMasterData"
                        icon={<HomeIcon fontSize="small" />}
                        onClick={handleClick}
                    />

                </Breadcrumbs>
                <div className={classes.buttonContainer}>
                    <Button
                        variant="contained"
                        color="primary"
                        disabled={apiName === "" ? true : false}
                        component="label"
                    >
                        Upload File
                    <input id='file'
                            type="file"
                            hidden
                        />
                    </Button>
                    <Button color="secondary"  variant="contained" onClick={uploadExcel}>Upload</Button>
                </div>


               
            </div>
            <div>
            </div>
        </div>
    )
}