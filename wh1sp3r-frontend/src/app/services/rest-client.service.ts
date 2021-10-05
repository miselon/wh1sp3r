import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RestClientService {

  url = 'http://localhost:8080/api/notes/'

  postHttpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  public saveNote(lifespanIndex: number, body: string): Observable<SaveResponse>{
    return this.httpClient.post<SaveResponse>(this.url, new NoteRequest(lifespanIndex, body), this.postHttpOptions)
  }

  public deleteNote(id: string): Observable<boolean>{
    return this.httpClient.delete<boolean>(this.url + id)
  }

  public checkIfNoteExists(id: string): Observable<boolean>{
    return this.httpClient.get<boolean>(this.url + 'check/' + id)
  }

  public getNote(id: string): Observable<Note>{
    return this.httpClient.get<Note>(this.url + id)
  }
  
}

export class NoteRequest{

  lifespanIndex: number
  encryptedMessage: string

  constructor(lifespanIndex: number, encryptedMessage: string){
    this.lifespanIndex = lifespanIndex
    this.encryptedMessage = encryptedMessage
  }

}

export interface Note{
  id: string
  timestamp: number
  encryptedMessage: string
}

export interface SaveResponse{
  id: string
}
