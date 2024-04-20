import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";
import {AdDepartmentService} from "../../../service/ad-department.service";

@Component({
  selector: 'app-ad-department-create',
  templateUrl: './ad-department-create.component.html',
  styleUrl: './ad-department-create.component.css'
})
export class AdDepartmentCreateComponent implements OnInit {


  objCreate: {
    name: string,
    descriptions: string
  } = {
    name: '',
    descriptions: ''
  }

  constructor(private dialogRef: MatDialogRef<AdDepartmentCreateComponent>,
              private adDepartmentService: AdDepartmentService,
              private toast: ToastrService) {
  }


  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  createDepartment() {
    this.adDepartmentService.createDepartments(this.objCreate).subscribe({
      next: (response) => {
        this.toast.success("Thêm thành công", "Thông báo", {
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
