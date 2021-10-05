import { Component, OnInit } from '@angular/core';
import { RestClientService } from 'src/app/services/rest-client.service';
import { CryptoService } from 'src/app/services/crypto.service';

@Component({
  selector: 'app-create-note',
  templateUrl: './create-note.component.html',
  styleUrls: ['./create-note.component.css']
})
export class CreateNoteComponent implements OnInit {

  id = ''
  generatedLink = ''
  private noteLifespanIndex = 9
  noteLifespanString = '15 minutes'
  noteCreated = false;
  delete = false;

  constructor(private restClientService: RestClientService,
              private cryptoService: CryptoService) { }

  ngOnInit(): void {
  }

  public incrementLifespanIndex(){
    if(this.noteLifespanIndex < 9){
      this.noteLifespanIndex++
      this.updateLifespanString();
    } 
  }

  public decrementLifespanIndex(){
    if(this.noteLifespanIndex > 0){
      this.noteLifespanIndex--
      this.updateLifespanString();
    }
  }

  private updateLifespanString(){
    if(this.noteLifespanIndex == 0) this.noteLifespanString = '30 days'
    else if(this.noteLifespanIndex == 1) this.noteLifespanString = '7 days'
    else if(this.noteLifespanIndex == 2) this.noteLifespanString = '3 days'
    else if(this.noteLifespanIndex == 3) this.noteLifespanString = '1 day'
    else if(this.noteLifespanIndex == 4) this.noteLifespanString = '12 hours'
    else if(this.noteLifespanIndex == 5) this.noteLifespanString = '6 hours'
    else if(this.noteLifespanIndex == 6) this.noteLifespanString = '3 hours'
    else if(this.noteLifespanIndex == 7) this.noteLifespanString = '1 hour'
    else if(this.noteLifespanIndex == 8) this.noteLifespanString = '30 minutes'
    else if(this.noteLifespanIndex == 9) this.noteLifespanString = '15 minutes'
  }

  public submitNote(noteBody: string){
    // Validate note content
    if(noteBody.trim().length <= 0)
      document.getElementById('note-input-field')
      ?.setAttribute('placeholder', 'You have to type in something to save it...')
    else this.saveNote(this.noteLifespanIndex, noteBody)
  }
  
  private saveNote(lifespanIndex: number, noteBody: string){
    // Generate encryption key
    var key = this.cryptoService.generateKey(8);
    // Encrypt note body
    noteBody = this.cryptoService.encrypt(noteBody, key)
    // Save encrypted note to the server
    this.restClientService
      .saveNote(lifespanIndex, noteBody)
      .subscribe(saveNoteResponse => {
        this.id = saveNoteResponse.id
        this.generatedLink = 'localhost:4200/' + this.id + '$' + key;
        this.noteCreated = true;
      })
  }

  public copyLinkToClipboard(){
    navigator.clipboard.writeText(this.generatedLink)
  }

  public createAnotherNote(){
    this.delete = false
    this.noteCreated = false;
    this.generatedLink = ''
  }

  public deleteNote(){
    this.delete = true;
  }

  public dontDelete(){
    this.delete = false
  }

}
