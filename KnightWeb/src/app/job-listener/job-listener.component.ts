/**
 * Created by Medxi on 2017/3/21.
 */
import {Component, OnInit} from '@angular/core';
import {Http} from '@angular/http';
import {KNIGHT_URL} from '../app.constant';

@Component({
  selector: 'current-job',
  templateUrl: './job-listener.component.html'
})
export class JobListenerComponent implements OnInit {

  constructor(private http:Http){}

  public allListeners:string[] = [];

  public ngOnInit() :void {
    this.getAllListeners().then(listeners => this.allListeners = listeners);
  }

  public deleteJobListener(listenerKey:string){
    if(confirm("确定要删除该任务监听？")){
      this.deleteListener(listenerKey);
    }
  }

  private getAllListeners(): Promise<string[]> {
    return this.http.get(KNIGHT_URL + '/job-listener/all-job-listener')
      .toPromise()
      .then(response => response.json() as string[])
      .catch(JobListenerComponent.handleError);
  }

  private deleteListener(listenerKey:string):Promise<string>{
    return this.http.delete(KNIGHT_URL + '/job-listener/delete-job-listener/'+listenerKey)
      .toPromise()
      .then(msg => {
        console.log(msg);
        this.allListeners = [];
        this.ngOnInit();
      })
      .catch(JobListenerComponent.handleError);
  }

  private static handleError(error: any): Promise<any> {
    alert(error._body);
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
