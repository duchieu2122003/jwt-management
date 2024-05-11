import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {AdDepartmentService} from "../../service/ad-department.service";
import {AdDepartmentCreateComponent} from "./ad-department-create/ad-department-create.component";
import {AdDepartmentUpdateComponent} from "./ad-department-update/ad-department-update.component";
import {TranslateService} from "@ngx-translate/core";
import {LoadTranslationService} from "../../../../../assets/i18n/load-translation.service";
@Component({
  selector: 'app-ad-department-management',
  templateUrl: './ad-department-management.component.html',
  styleUrl: './ad-department-management.component.css'
})
export class AdDepartmentManagementComponent implements OnInit {

  listDepartments: {
    id: string,
    stt: number,
    name: string,
    descriptions: string,
    status: string
  }[] = [];

  constructor(private adDepartmentsService: AdDepartmentService,
              private toast: ToastrService,
              private dialog: MatDialog,
              private translationService: TranslateService,
              private loadTranslationService: LoadTranslationService) {
  }

  ngOnInit(): void {
    this.translationService.use(this.loadTranslationService.langStorage()+'departments');
    this.adDepartmentsService.getAllDepartmentView().subscribe({
      next: (response) => {
        this.listDepartments = response.data
      }
    })
  }

  handleDelete(id: string) {
    this.adDepartmentsService.deleteDepartments(id).subscribe({
      next: (response) => {
        if (response.data == true) {
          this.listDepartments = this.listDepartments.filter(i => i.id !== id);
          this.listDepartments.forEach((i, index) => {
            i.stt = index + 1;
          });
          this.toast.success("Xóa thành công", "Thông báo")
        } else {
          this.toast.error("Xóa thất bại", "Thông báo")
        }
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo")
      }
    })
  }

  openShowCreate() {
    const dialogRef = this.dialog.open(AdDepartmentCreateComponent, {
      width: '60%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.listDepartments.push({...result.data, stt: this.listDepartments.length + 1});
      }
    });
  }

  openShowUpdate(department: any) {
    const dialogRef = this.dialog.open(AdDepartmentUpdateComponent, {
      width: '60%',
      data: {obj: department}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const employeeToUpdate = this.listDepartments
          .find(item => item.id === result.data.id);
        if (employeeToUpdate) {
          Object.assign(employeeToUpdate, result.data, {stt: employeeToUpdate.stt});
        }
      }
    });
  }

  createInstance() {
    // i18next.init({}, (err, t) => {
    //   // Hàm này sẽ được gọi khi i18next đã hoàn tất khởi tạo
    //   if (err) {
    //     console.error('Error initializing i18next:', err);
    //     return;
    //   }
    // });

    // i18next.on('languageChanged', () => {
    //   this.updateContent();
    // });

  }

  // updateContent() {
  //   document.getElementById('title_name')!.innerHTML = i18next.t('title', { what: 'i18next' });
  //   document.getElementById('saveBtn')!.innerHTML = i18next.t('common:button.save', { count: Math.floor(Math.random() * 2 + 1) });
  //   document.getElementById('info')!.innerHTML = `Chuyển : "${i18next.language}"  --> qua: "${i18next.languages.join(', ')}"`;
  // }
  // changeLng(lng: any) {
  //   if (typeof i18next !== 'undefined') {
  //     i18next.changeLanguage(lng).then(r => this.toast.info("Chuyển đổi ngôn ngữ qua: "+i18next.language));
  //   } else {
  //    this.toast.error("Lỗi i18");
  //   }
  // }
  changeLanguage(lang: string): void {
    localStorage.setItem('lang', lang);
    this.loadTranslationService.switchLanguage(lang, 'departments');
  }
}
