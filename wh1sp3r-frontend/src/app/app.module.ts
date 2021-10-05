import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateNoteComponent } from './components/create-note/create-note.component';
import { ReadNoteComponent } from './components/read-note/read-note.component';
import { ButtonComponent } from './components/button/button.component';
import { DeleteNoteComponent } from './components/delete-note/delete-note.component';


@NgModule({
  declarations: [
    AppComponent,
    CreateNoteComponent,
    ReadNoteComponent,
    ButtonComponent,
    DeleteNoteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
