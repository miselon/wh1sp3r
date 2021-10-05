import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { RestClientService } from 'src/app/services/rest-client.service';

@Component({
  selector: 'app-delete-note',
  templateUrl: './delete-note.component.html',
  styleUrls: ['./delete-note.component.css']
})
export class DeleteNoteComponent implements OnInit {

  @Input() id = ''
  @Output() noteDeletedEvent = new EventEmitter()
  @Output() goBackEvent = new EventEmitter()
  sure = false
  deleted = false
  responseRecieved = false

  constructor(private restClientService: RestClientService) { }

  ngOnInit(): void {
  }

  public delete(){
    this.sure = true
    this.restClientService
    .deleteNote(this.id)
    .subscribe(deleteResponse => {
      this.responseRecieved = true
      this.deleted = deleteResponse
    })
  }

  public goBack(){
    this.responseRecieved = false
    if(this.deleted) this.noteDeletedEvent.emit()
    else this.goBackEvent.emit()
    this.deleted = false
  }

}
