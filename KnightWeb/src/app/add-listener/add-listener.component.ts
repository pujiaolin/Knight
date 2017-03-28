/**
 * Created by Medxi on 2017/3/23.
 */
import { Component } from '@angular/core';
import { JobListener } from './jobListener';
import { ListenerService } from './add-listener.service';
import {NgForm} from "@angular/forms";
@Component({
  selector: 'add-listener',
  templateUrl:"./add-listener.component.html",
  providers:[ListenerService]
})
export class AddListenerComponent {

  public jobListener:JobListener = JobListener.defaultListener;

  constructor(private listenerService : ListenerService){}

  public saveJobListener(addListenerForm:NgForm):void {
    this.listenerService.saveJobListener(this.jobListener).then( msg => {
        console.log(msg);
        addListenerForm.reset();
      });
  }
}

