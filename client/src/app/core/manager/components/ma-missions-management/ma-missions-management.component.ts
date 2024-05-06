import {Component, OnDestroy, OnInit} from '@angular/core';
import {MaMissionsService} from "../../service/ma-missions.service";
import {ToastrService} from "ngx-toastr";
import {Subscription} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {MaCreateMissionComponent} from "./ma-create-mission/ma-create-mission.component";
import {MaUpdateMissionComponent} from "./ma-update-mission/ma-update-mission.component";

interface Missions {
  id: string,
  name: string,
  descriptions: string,
  salary:number
}

@Component({
  selector: 'app-ma-missions-management',
  templateUrl: './ma-missions-management.component.html',
  styleUrl: './ma-missions-management.component.css'
})
export class MaMissionsManagementComponent implements OnInit, OnDestroy {

  listMissions: Missions[] = [];
  private subscription: Subscription | undefined;

  constructor(private maMissionsService: MaMissionsService,
              private toast: ToastrService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getAllMission();
    this.subscription = this.maMissionsService.$missionCreate.subscribe(data => {
      if (data.id !== '') {
        this.listMissions.unshift(data);
      }
    })
  }

  getAllMission() {
    this.maMissionsService.getListMission().subscribe({
      next: (response) => {
        this.listMissions = response.data;
      }
    })
  }

  handleOpenCreate() {
    this.dialog.open(MaCreateMissionComponent, {
      minWidth: '60%', width: "auto",
    })
  }

  handleOpenUpdate(missionUpdate: Missions) {
    const dialogRef = this.dialog.open(MaUpdateMissionComponent, {
      minWidth: '60%', width: "auto",
      data: {missionUpdate: missionUpdate}
    })

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const objFind = this.listMissions
          .find(i => i.id === result.data.id);
        if (objFind) {
          Object.assign(objFind, result.data);
        }
      }
    })
  }

  handleDelete(id: string) {
    this.maMissionsService.deleteMission(id).subscribe({
      next: (response) => {
        if (response.data == true) {
          this.listMissions = this.listMissions.filter(i => i.id !== id);
          this.toast.success("Xóa thành công", "Thông báo");
        } else {
          this.toast.error("Xóa thất bại", "Thông báo");
        }
      }, error: (err) => {
        this.toast.error(err.error.message, "Thông báo");
      }
    })
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
