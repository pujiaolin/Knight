/**
 * Created by Medxi on 2017/3/28.
 */
import { Injectable } from '@angular/core';
import { Http,Headers,RequestOptions } from '@angular/http';
import { KNIGHT_URL } from '../app.constant';
import { JobListener } from './jobListener';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ListenerService {

  private headers = new Headers({'Content-Type': 'application/json;'});
  private options = new RequestOptions({ headers: this.headers });
  constructor(private http:Http ){ }

  public saveJobListener(jobListener:JobListener): Promise<any>{
    const url = KNIGHT_URL + '/job-listener/add-job-listener';
    return this.http.post(url,JSON.stringify(jobListener),this.options)
      .toPromise()
      .then((msg) => {
        console.log(msg);
      }).catch(ListenerService.handleError);
  }

  private static handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
