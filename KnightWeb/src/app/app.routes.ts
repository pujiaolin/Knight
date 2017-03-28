/**
 * Created by Medxi on 2017/3/21.
 */
import { Routes } from '@angular/router';
import { AllJobComponent } from './all-job';
import { JobListenerComponent } from './job-listener';
import { NoContentComponent} from './no-content';
import { AddJobComponent } from './add-job';
import { AddListenerComponent } from './add-listener';

export const ROUTES: Routes = [
    { path: '', component: AllJobComponent },
    { path: 'all-job', component: AllJobComponent },
    { path: 'add-job', component: AddJobComponent },
    { path: 'job-listener', component: JobListenerComponent },
    { path: 'add-listener', component: AddListenerComponent },
    { path: '**', component: NoContentComponent }
];
