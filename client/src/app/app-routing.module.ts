import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./core/common/components/login/login.component";
import {
  AdEmployeesManagementComponent
} from "./core/admin/components/ad-employees-management/ad-employees-management.component";
import {
  MaDepartmentManagementComponent
} from "./core/manager/components/ma-department-management/ma-department-management.component";
import {ManagerComponent} from "./core/manager/manager.component";
import {ForbiddenComponent} from "./page/forbidden/forbidden.component";
import {HomeComponent} from "./page/home/home.component";
import {StaffComponent} from "./core/staff/staff.component";

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "common/login", component: LoginComponent},

  {path: "admin", redirectTo: "admin/employees-management", pathMatch: "full"},
  {path: "admin/employees-management", component: AdEmployeesManagementComponent},
  {
    path: "manager", component: ManagerComponent, children: [
      {path: "department-management", component: MaDepartmentManagementComponent}
    ]
  },
  {path: "staff", component: StaffComponent},
  {path: "403", component: ForbiddenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
