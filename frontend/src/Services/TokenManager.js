import axios from 'axios';

export default {
    setToken : function (token) {
        //axios.defaults.headers.common['Authorization'] = (token) ? "Bearer "+token : null;
    },

   isValidateToken: function() {
        //return axios.defaults.headers.common['Authorization'];
        return true;
    },

    invalidateToken: function() {
        delete axios.defaults.headers.common['Authorization'];
    }
}