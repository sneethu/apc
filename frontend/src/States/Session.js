import validatorjs from 'validatorjs';
import MobxReactForm from 'mobx-react-form';
import {decorate, observable, action} from "mobx";

import Rest from '../Services/Rest'
import tokenManager from '../Services/TokenManager';

const plugins = { dvr: validatorjs };

const rest = new Rest();

const fields = [{
    name: 'email',
    label: 'Email',
    placeholder: 'Insert Email',
    rules: 'required|email|string|between:5,25',
  }, {
    name: 'password',
    label: 'Password',
    placeholder: 'Insert Password',
    rules: 'required|string|between:5,25',
  }];

class LoginForm extends MobxReactForm {

    options() {
      return {
        validateOnChange: true
      };
    }

}

class LoginSession {
    hooks = {
      async onSuccess(form) {
        try {
          console.log('Form Values!', form.values());
          const {email,password} = form.values();
          this.login(email,password) 
        } catch(error) {
          console.log('Failing login '+error);
          form.invalidate(error.message);
        }
      },
      onError(form) {
        console.log('All form errors', form.errors());
      }
    }

    loginForm = new LoginForm({ fields }, { plugins, hooks: this.hook });

    isActive = tokenManager.isValidateToken();
    
    async login(email,password) {     
        const response = await rest.login(email,password);
        tokenManager.setToken(response.data.token);
    }

    async logout() {
      try {
        await rest.logout();
        tokenManager.invalidateToken();
        this.isActive = tokenManager.isValidateToken();
      } catch(error) {
        console.log('Failing logout '+error);
      }
    }
}

decorate(LoginSession,{
    logout: action,
    isActive: observable
  })

export default LoginSession;

