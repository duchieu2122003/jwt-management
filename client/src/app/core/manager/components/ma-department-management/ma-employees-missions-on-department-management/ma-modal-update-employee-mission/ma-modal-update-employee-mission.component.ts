import {Component} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-ma-modal-update-employee-mission',
  templateUrl: './ma-modal-update-employee-mission.component.html',
  styleUrl: './ma-modal-update-employee-mission.component.css'
})
export class MaModalUpdateEmployeeMissionComponent {

  constructor(private dialogRef: MatDialogRef<any>) {
  }

  onCloseDialog() {
    this.dialogRef.close();
  }
}
