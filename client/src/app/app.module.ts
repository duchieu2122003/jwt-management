import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FooterComponent} from './layout/footer/footer.component';
import {SidebarComponent} from './layout/sidebar/sidebar.component';
import {LoginComponent} from './core/common/components/login/login.component';
import {InformationMySelfComponent} from './core/common/components/information-my-self/information-my-self.component';
import {
  StDepartmentMySelfComponent
} from './core/staff/components/st-department-my-self/st-department-my-self.component';
import {HeaderComponent} from './layout/header/header.component';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {
  AdEmployeesManagementComponent
} from './core/admin/components/ad-employees-management/ad-employees-management.component';
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";
import {MatButton} from "@angular/material/button";
import {ForbiddenComponent} from './page/forbidden/forbidden.component';
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
import {HttpConfigInterceptor} from "./interceptors/http-config.interceptor";
import {CreateComponent} from './core/admin/components/ad-employees-management/create/create.component';
import {UpdateComponent} from './core/admin/components/ad-employees-management/update/update.component';
import {MAT_DIALOG_DEFAULT_OPTIONS} from "@angular/material/dialog";
import {ChangePasswordComponent} from './core/common/components/change-password/change-password.component';
import {NgSelectModule} from "@ng-select/ng-select";
import {
  AdDepartmentManagementComponent
} from './core/admin/components/ad-department-management/ad-department-management.component';
import {
  AdDepartmentCreateComponent
} from './core/admin/components/ad-department-management/ad-department-create/ad-department-create.component';
import {
  AdDepartmentUpdateComponent
} from './core/admin/components/ad-department-management/ad-department-update/ad-department-update.component';
import {
  MaModalCreateEmployeeMissionComponent
} from "./core/manager/components/ma-employees-missions-on-department-management/ma-modal-create-employee-mission/ma-modal-create-employee-mission.component";
import {
  MaModalUpdateEmployeeMissionComponent
} from "./core/manager/components/ma-employees-missions-on-department-management/ma-modal-update-employee-mission/ma-modal-update-employee-mission.component";
import {
  MaEmployeesMissionsOnDepartmentManagementComponent
} from "./core/manager/components/ma-employees-missions-on-department-management/ma-employees-missions-on-department-management.component";
import {
  MaMissionsManagementComponent
} from './core/manager/components/ma-missions-management/ma-missions-management.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
  MaCreateMissionComponent
} from './core/manager/components/ma-missions-management/ma-create-mission/ma-create-mission.component';
import {
  MaUpdateMissionComponent
} from './core/manager/components/ma-missions-management/ma-update-mission/ma-update-mission.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    SidebarComponent,
    LoginComponent,
    InformationMySelfComponent,
    StDepartmentMySelfComponent,
    HeaderComponent,
    AdEmployeesManagementComponent,
    ForbiddenComponent,
    AdminComponent,
    ManagerComponent,
    StaffComponent,
    CreateComponent,
    UpdateComponent,
    ChangePasswordComponent,
    MaEmployeesMissionsOnDepartmentManagementComponent,
    MaModalCreateEmployeeMissionComponent,
    MaModalUpdateEmployeeMissionComponent,
    AdDepartmentManagementComponent,
    AdDepartmentCreateComponent,
    AdDepartmentUpdateComponent,
    MaMissionsManagementComponent,
    MaCreateMissionComponent,
    MaUpdateMissionComponent
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
    BrowserAnimationsModule,
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
