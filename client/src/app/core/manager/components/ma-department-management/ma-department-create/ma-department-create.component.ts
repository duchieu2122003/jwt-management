import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {MatDialogRef} from "@angular/material/dialog";
import {MaDepartmentsService} from "../../../service/ma-departments.service";

@Component({
  selector: 'app-ma-department-ma-department-create',
  templateUrl: './ma-department-create.component.html',
  styleUrl: './ma-department-create.component.css'
})
export class MaDepartmentCreateComponent implements OnInit {

  objCreate: {
    name: string,
    descriptions: string
  } = {
    name: '',
    descriptions: ''
  }

  constructor(private dialogRef: MatDialogRef<MaDepartmentCreateComponent>,
              private maDepartmentService: MaDepartmentsService,
              private toast: ToastrService) {
  }


  ngOnInit(): void {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  createDepartment() {
    this.maDepartmentService.createDepartments(this.objCreate).subscribe({
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
