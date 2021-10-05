import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { RestClientService } from 'src/app/services/rest-client.service';
import { CryptoService } from 'src/app/services/crypto.service';

@Component({
  selector: 'app-read-note',
  templateUrl: './read-note.component.html',
  styleUrls: ['./read-note.component.css']
})
export class ReadNoteComponent implements OnInit {

  id = ''
  private key = ''
  note = ''
  exists = false;
  existsRecieved = false;
  sure = false;
  confirmExit = false;
  delete = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private restClientService: RestClientService,
    private cryptoService: CryptoService) { }

  ngOnInit(): void {
    // Get data from URL
    this.id = this.route.snapshot.paramMap.get('id')!
    this.key = this.route.snapshot.paramMap.get('key')!
    // Check if note exists
    this.restClientService
      .checkIfNoteExists(this.id)
      .subscribe(response => {
        this.existsRecieved = true;
        this.exists = response
      })
  }

  // Retrieve encrypted note and decrypt it using key provided in the link
  readNote(): void{
    this.sure = true;
    this.restClientService
      .getNote(this.id)
      .subscribe(response => {
        this.note = this.cryptoService.decrypt(response.encryptedMessage, this.key)
        this.confirmExit = true
      });
  }

  public deleteNote(){
    this.delete = true;
  }

  public dontDelete(){
    this.delete = false
  }

  public goToHomePage(){
    this.router.navigate(['/'])
  }

}
