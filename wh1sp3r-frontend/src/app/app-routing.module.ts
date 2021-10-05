import { NgModule } from '@angular/core';
import { RouterModule, Routes, UrlSegment } from '@angular/router';
import { CreateNoteComponent } from './components/create-note/create-note.component';
import { ReadNoteComponent } from './components/read-note/read-note.component';

const routes: Routes = [
  // Home page
  {path: '', component: CreateNoteComponent},
  // Custom URL format matcher
  // Only redirect if URL matches, else go to home page
  {
    matcher: (url) => {
      if (url.length === 1 && url[0].path.match(/^\S{8}[$]\S{8}$/g)) {
        return {
          consumed: url,
          posParams: {
            id: new UrlSegment(url[0].path.substr(0, 8), {}),
            key: new UrlSegment(url[0].path.substr(9), {})
          }
        };
      }      return null;
    },
    component: ReadNoteComponent
  },
  // Anything else - redirect to home
  {path: '**', redirectTo: ''}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
