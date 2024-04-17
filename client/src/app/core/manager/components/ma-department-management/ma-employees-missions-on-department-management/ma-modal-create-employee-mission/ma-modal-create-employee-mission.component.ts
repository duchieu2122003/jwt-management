import {Component} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-ma-modal-create-employee-mission',
  templateUrl: './ma-modal-create-employee-mission.component.html',
  styleUrl: './ma-modal-create-employee-mission.component.css'
})
export class MaModalCreateEmployeeMissionComponent {

  constructor(private dialog: MatDialogRef<any>) {
  }

  onCloseDialog() {
    this.dialog.close();
  }
}
