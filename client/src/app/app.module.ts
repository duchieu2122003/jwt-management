import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FooterComponent} from './layout/footer/footer.component';
import {SidebarComponent} from './layout/sidebar/sidebar.component';
import {LoginComponent} from './core/common/components/login/login.component';
import {InformationMySelfComponent} from './core/common/components/information-my-self/information-my-self.component';
import {
  MaDepartmentManagementComponent
} from './core/manager/components/ma-department-management/ma-department-management.component';
import {
  StDepartmentMySelfComponent
} from './core/staff/components/st-department-my-self/st-department-my-self.component';
import {CommonHeaderComponent} from './layout/header/common-header/common-header.component';
import {AdminHeaderComponent} from './layout/header/admin-header/admin-header.component';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {
  AdEmployeesManagementComponent
} from './core/admin/components/ad-employees-management/ad-employees-management.component';
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";
import {MatButton} from "@angular/material/button";
import {ForbiddenComponent} from './page/forbidden/forbidden.component';
import {ManagerHeaderComponent} from './layout/header/manager-header/manager-header.component';
import {HomeComponent} from './page/home/home.component';
import {AdminComponent} from './core/admin/admin.component';
import {ManagerComponent} from './core/manager/manager.component';
import {StaffComponent} from './core/staff/staff.component';
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatInput} from "@angular/material/input";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {NgOptimizedImage} from "@angular/common";
import {ToastrModule} from "ngx-toastr";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {StaffHeaderComponent} from './layout/header/staff-header/staff-header.component';
import {HttpConfigInterceptor} from "./interceptors/http-config.interceptor";
import {CreateComponent} from './core/admin/components/ad-employees-management/create/create.component';
import {UpdateComponent} from './core/admin/components/ad-employees-management/update/update.component';
import {MAT_DIALOG_DEFAULT_OPTIONS} from "@angular/material/dialog";
import {ChangePasswordComponent} from './core/common/components/change-password/change-password.component';
import {
  MaDepartmentCreateComponent
} from "./core/manager/components/ma-department-management/ma-department-create/ma-department-create.component";
import {
  MaDepartmentUpdateComponent
} from './core/manager/components/ma-department-management/ma-department-update/ma-department-update.component';
import {
  MaEmployeesMissionsOnDepartmentManagementComponent
} from './core/manager/components/ma-department-management/ma-employees-missions-on-department-management/ma-employees-missions-on-department-management.component';
import {
  MaModalCreateEmployeeMissionComponent
} from './core/manager/components/ma-department-management/ma-employees-missions-on-department-management/ma-modal-create-employee-mission/ma-modal-create-employee-mission.component';
import {
  MaModalUpdateEmployeeMissionComponent
} from './core/manager/components/ma-department-management/ma-employees-missions-on-department-management/ma-modal-update-employee-mission/ma-modal-update-employee-mission.component';
import {NgSelectModule} from "@ng-select/ng-select";

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    SidebarComponent,
    LoginComponent,
    InformationMySelfComponent,
    MaDepartmentManagementComponent,
    StDepartmentMySelfComponent,
    CommonHeaderComponent,
    AdminHeaderComponent,
    AdEmployeesManagementComponent,
    ForbiddenComponent,
    ManagerHeaderComponent,
    HomeComponent,
    AdminComponent,
    ManagerComponent,
    StaffComponent,
    StaffHeaderComponent,
    CreateComponent,
    UpdateComponent,
    ChangePasswordComponent,
    MaDepartmentCreateComponent,
    MaDepartmentUpdateComponent,
    MaEmployeesMissionsOnDepartmentManagementComponent,
    MaModalCreateEmployeeMissionComponent,
    MaModalUpdateEmployeeMissionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatMenuItem,
    MatButton,
    MatMenu,
    MatMenuTrigger,
    MatCardTitle,
    MatCard,
    MatFormField,
    MatCardContent,
    MatLabel,
    FormsModule,
    MatInput,
    MatIcon,
    MatIconModule,
    NgOptimizedImage,
    HttpClientModule,
    NgSelectModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    provideAnimationsAsync(),
    {provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true},
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
