import axios from 'axios';

const LOGIN_PATH = "login";
const LOGOUT_PATH = "logout";
const MEETING_PATH = "meeting";

class Rest {

    login(email,password) {
        return axios.post(LOGIN_PATH,{email,password})
    }

    logout() {
        return axios.post(LOGOUT_PATH)
    }

    getMeetings(start,end) {
        return axios.get(`${MEETING_PATH}?start=${start}&end=${end}`)
    }

    createMeeting(event) {
        return axios.post(`${MEETING_PATH}`, event)
    }

    updateMeeting(event) {
        return axios.put(`${MEETING_PATH}`,event)
    }

}

export default Rest;