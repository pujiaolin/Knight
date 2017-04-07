import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, PreloadAllModules } from '@angular/router';

import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { LayoutComponent } from './layout.component';
import { AllJobComponent } from './all-job';
import { JobListenerComponent } from './job-listener';
import { NoContentComponent} from './no-content';
import { AddJobComponent } from './add-job';
import { AddListenerComponent } from './add-listener';
import { JobExceptionComponent } from './job-exception';
import { PageComponent } from './pagination';

@NgModule({
  declarations: [
    PageComponent,
    AppComponent,
    LayoutComponent,
    AllJobComponent,
    JobListenerComponent,
    NoContentComponent,
    AddJobComponent,
    AddListenerComponent,
    JobExceptionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(ROUTES, { useHash: true, preloadingStrategy: PreloadAllModules })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
