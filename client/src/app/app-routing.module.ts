import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./core/common/components/login/login.component";
import {
  AdEmployeesManagementComponent
} from "./core/admin/components/ad-employees-management/ad-employees-management.component";
import {
  MaDepartmentManagementComponent
} from "./core/manager/components/ma-department-management/ma-department-management.component";
import {ForbiddenComponent} from "./page/forbidden/forbidden.component";
import {
  StDepartmentMySelfComponent
} from "./core/staff/components/st-department-my-self/st-department-my-self.component";
import {InformationMySelfComponent} from "./core/common/components/information-my-self/information-my-self.component";
import {ChangePasswordComponent} from "./core/common/components/change-password/change-password.component";
import {
  MaEmployeesMissionsOnDepartmentManagementComponent
} from "./core/manager/components/ma-department-management/ma-employees-missions-on-department-management/ma-employees-missions-on-department-management.component";

const routes: Routes = [
  {path: "", redirectTo: "common/login", pathMatch: 'full'},
  {path: "common/login", component: LoginComponent},
  {path: "common/information-my-self", component: InformationMySelfComponent},

  {path: "common/change-password", component: ChangePasswordComponent},

  {path: "admin", redirectTo: "admin/employees-management", pathMatch: "full"},
  {path: "admin/employees-management", component: AdEmployeesManagementComponent},

  {path: "manager", redirectTo: "manager/department-management", pathMatch: "full"},
  {path: "manager/department-management", component: MaDepartmentManagementComponent},
  {path: "manager/employees-missions-on-department/:id", component: MaEmployeesMissionsOnDepartmentManagementComponent},

  {path: "staff", redirectTo: "staff/department-my-self", pathMatch: "full"},
  {path: "staff/department-my-self", component: StDepartmentMySelfComponent},

  {path: "403", component: ForbiddenComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
