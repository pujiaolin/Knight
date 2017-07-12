/**
 * Created by Medxi on 2017/3/31.
 */
import {Component, Input, DoCheck} from "@angular/core";

import { Pagination } from "./pagination";

@Component({
  selector: 'page',
  templateUrl: "./page.component.html"
})
export class PageComponent implements DoCheck{

  @Input()
  public pagination:Pagination;

  public pageNum:number;
  public pageList:any[];

  private oldTotalItems:number = 0;
  private oldCurrentPage:number = 1;

  public changeCurrentPage(item:any): void{
    if(typeof item === 'number'){
      this.pagination.currentPage = item;
      this.pagination.changePage();
    }
  }

  public prePage():void {
    if(this.pagination.currentPage != 1){
      this.changeCurrentPage(this.pagination.currentPage - 1);
    }
  }

  public nextPage():void {
    if(this.pagination.currentPage < this.pageNum){
      this.changeCurrentPage(this.pagination.currentPage + 1);
    }
  }

  public initPageList():void {
    //偏移量（因为要除去首页和尾页，所以要-1）
    let offset = Math.floor(this.pagination.pageLength / 2) - 1;
    //如果没有数据显示一页
    this.pagination.totalItems = this.pagination.totalItems > 0 ? this.pagination.totalItems : 1;
    //总页数
    this.pageNum = Math.ceil(this.pagination.totalItems / this.pagination.pageItems);
    this.pageList = [];
    if(this.pageNum <= this.pagination.pageLength){
      for (let i=1;i <= this.pageNum;i++){
        this.pageList.push(i);
      }
    }else {
        //左边没有'...'
      if(this.pagination.currentPage < this.pagination.pageLength - offset){
        for(let i=1;i < this.pagination.pageLength;i++){
          this.pageList.push(i);
        }
        this.pageList.push('...');
        this.pageList.push(this.pageNum);
        //右边没有'...'
      }else if(this.pagination.currentPage >= this.pageNum - offset - 1){
        this.pageList.push(1);
        this.pageList.push('...');
        for(let i=this.pagination.pageLength - 2;i >= 0 ;i--){
          this.pageList.push(this.pageNum - i);
        }
        //两边都有'...'
      }else {
        this.pageList.push(1);
        this.pageList.push('...');
        for(let i= this.pagination.currentPage - offset;i <= this.pagination.currentPage + offset; i++){
          this.pageList.push(i);
        }
        this.pageList.push('...');
        this.pageList.push(this.pageNum);
      }
    }
  }

  ngDoCheck():void {
    if(this.pagination.totalItems != this.oldTotalItems || this.pagination.currentPage != this.oldCurrentPage){
      this.initPageList();
      this.oldTotalItems = this.pagination.totalItems;
      this.oldCurrentPage = this.pagination.currentPage;
    }

    if(this.pagination.currentPage > this.pageNum){
      this.pagination.currentPage = this.pageNum;
      this.pagination.changePage();
    }
  }

}
