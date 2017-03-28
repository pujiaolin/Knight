/**
 * Created by Medxi on 2017/3/24.
 */
import { Injectable } from '@angular/core';
import { Http,Headers,RequestOptions } from '@angular/http';
import { KNIGHT_URL } from '../app.constant';
import 'rxjs/add/operator/toPromise';
import { ExecuteJob } from './executeJob';

@Injectable()
export class AddJobService{
  private headers = new Headers({'Content-Type': 'application/json;'});
  private options = new RequestOptions({ headers: this.headers });
  constructor(private http:Http ){ }

  public saveExecuteJob(executeJob:ExecuteJob) : Promise<any>{
    const url = KNIGHT_URL + '/job/add-execute-job-trigger';
    return this.http.post(url,JSON.stringify(executeJob),this.options)
      .toPromise()
      .then((msg) => {
        console.log(msg);
      }).catch(AddJobService.handleError);
  }

  private static handleError(error: any): Promise<any> {
    alert(error._body);
    return Promise.reject(error.message || error);
  }
}
