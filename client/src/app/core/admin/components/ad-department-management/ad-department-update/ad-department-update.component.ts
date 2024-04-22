import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {AdDepartmentService} from "../../../service/ad-department.service";

@Component({
  selector: 'app-ad-department-update',
  templateUrl: './ad-department-update.component.html',
  styleUrl: './ad-department-update.component.css'
})
export class AdDepartmentUpdateComponent implements OnInit {

  objUp = {
    id: '',
    name: '',
    descriptions: ''
  }

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adDepartmentService: AdDepartmentService,
    private dialogRef: MatDialogRef<AdDepartmentUpdateComponent>,
    private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.objUp = {
      id: this.data.obj.id,
      name: this.data.obj.name,
      descriptions: this.data.obj.descriptions
    };
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  updateDepartment() {
    this.adDepartmentService.updateDepartments(this.objUp).subscribe({
      next: (response) => {
        this.toast.success("Sửa thành công", "Thông báo", {
          positionClass: 'toast-top-center',
          timeOut: 3000
        })
        this.dialogRef.close(response);
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo", {
          positionClass: 'toast-top-center',
          timeOut: 3000
        })
      }
    })
  }

}
