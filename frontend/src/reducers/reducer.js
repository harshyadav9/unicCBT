

export const ExamReducer = (state, action) => {
    switch (action.type) {
        case 'ADDINFO':
            console.log(action);
            return {
                ...state,
                ...{
                    hours: action.hours,
                    minutes: action.minutes,
                    name: action.name,
                    photo: action.photo,
                    candidateName: action.candidateName,
                    registrationNo: action.registrationNo,
                    isLoading: false
                }
            };
        case 'HANDLELOADING':
            console.log(action);
            return {
                ...state,
                ...{
                    isLoading: action.isLoading
                }
            };

        case 'ADD_FINAL_DATA':
            console.log(action);
            return {
                ...state,
                ...{
                    finalData: action.finalData
                }
            };


        case 'ADD_QUESTION_VALUES':
            console.log(action);
            return {
                ...state,

                ...{
                    questions: action.questions
                }
            };

            case 'RESET':
            return {
                ...state,
                ...{
                    reset:action.reset
                }
            }
        default:
            return state;
    }
};