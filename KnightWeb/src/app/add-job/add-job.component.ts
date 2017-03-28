/**
 * Created by Medxi on 2017/3/23.
 */
import { Component } from '@angular/core';
import { ExecuteJob } from './executeJob';
import { AddJobService } from './add-job.service';
import {NgForm} from "@angular/forms";

@Component({
  selector: 'add-job',
  templateUrl:"./add-job.component.html",
  providers:[AddJobService]
})
export class AddJobComponent {

  public executeJob: ExecuteJob = ExecuteJob.defaultExecuteJob;
  constructor (private addJobService:AddJobService) {}

  public saveExecuteJob(addJobForm:NgForm):void {
    this.addJobService.saveExecuteJob(this.executeJob).then(response => {
      console.log(response);
      addJobForm.reset();
    });
  }

}
