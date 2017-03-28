/**
 * Created by Medxi on 2017/3/21.
 */

import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { Http } from '@angular/http';
import { KNIGHT_URL } from '../app.constant';
import { Job } from './job';
import 'rxjs/add/operator/toPromise';

@Component({
  selector: 'all-job',
  templateUrl: "./all-job.component.html"
})
export class AllJobComponent implements OnInit {

  constructor(private http: Http,private ref : ChangeDetectorRef) {
  }

  public allJobs:Job[] = [];

  public ngOnInit():void {
    this.getAllJobs().then(map =>{
      Object.keys(map).forEach(key => {
        this.allJobs = this.allJobs.concat(map[key]);
      })
    });
  }

  public deleteExecuteJob(jobGroup:string,className:string):void{
    if(confirm("确定删除该任务？")){
      this.deleteJob(jobGroup,className);
    }
  }

  private getAllJobs(): Promise<Map<string, Job[]>> {
    return this.http.get(KNIGHT_URL + '/job/all-job')
      .toPromise()
      .then(response => response.json() as Map<string, Job[]>)
      .catch(AllJobComponent.handleError);
  }

  private deleteJob(jobGroup:string,className:string):Promise<any> {
    let url = KNIGHT_URL + '/job/delete-job/'+className+'/'+jobGroup;
    return this.http.delete(url)
      .toPromise()
      .then(msg => {
        console.log(msg.toString());
        this.allJobs = [];
        this.ngOnInit();
      })
      .catch(AllJobComponent.handleError);
  }

  private static handleError(error: any): Promise<any> {
    alert(error._body);
    return Promise.reject(error.message || error);
  }

}
