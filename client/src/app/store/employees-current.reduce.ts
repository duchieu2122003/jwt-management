import {EmployeeLogin} from "../entitis/EmployeeLogin";
import {createAction, createReducer, on, props} from "@ngrx/store";

const initEmployeesLoginState: EmployeeLogin = {
  token: '',
  lastName: '',
  role: '',
  id:''
}
export const setEmployeeCurrent = createAction(
  '[Set EmployeeLogin] Set EmployeeLogin',
  props<EmployeeLogin>()
);

export const employeeCurrentLogin = createReducer(
  initEmployeesLoginState,
  on(setEmployeeCurrent,
    (state: EmployeeLogin, employee: EmployeeLogin) => {
      state = employee;
      return state;
    })
);



