/**
 * Created by Medxi on 2017/4/5.
 */
import {Component, OnInit, Output} from '@angular/core';
import {Http} from '@angular/http';
import {KNIGHT_URL} from '../app.constant';
import {JobException} from './jobException';
import { Pagination } from '../pagination/pagination';


@Component({
  selector: 'job-exception',
  templateUrl: './job-exception.component.html'
})
export class JobExceptionComponent implements OnInit {

  constructor(private http:Http){}

  public allJobException:JobException[] = [];

  @Output()
  public pagination:Pagination = Pagination.defaultPagination;

  public ngOnInit() :void {
    this.initJobExceptionList();
    this.pagination.changePage = (() => {
      this.initJobExceptionList();
    });
  }

  private initJobExceptionList(): void {
    let url:string = KNIGHT_URL + '/job-exception/find-all';
    let page = this.pagination.currentPage - 1;
    this.http.get(url + "?page=" + page)
      .toPromise()
      .then(response => response.json())
      .then(page => {
        this.pagination.totalItems = page.totalElements;
        this.allJobException = page.content;
      })
      .catch(JobExceptionComponent.handleError);
  }

  public delete(id:number){
    let url:string = KNIGHT_URL + '/job-exception/delete/' + id;
    this.http.delete(url).toPromise()
      .then(msg => {
        console.log(msg);
        this.allJobException = [];
        this.ngOnInit();
      }).catch(JobExceptionComponent.handleError);
  }
  private static handleError(error: any): Promise<any> {
    alert(error._body);
    return Promise.reject(error.message || error);
  }
}
