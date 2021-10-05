import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  @Input()
  btnText = ''
  @Input()
  color = 'red'

  hover = false
  rgb = 'rgb(185, 8, 8)'
  rgbDark = 'rgb(95, 0, 0)'

  constructor() { }

  ngOnInit(): void {

    // RED
    if(this.color == 'red'){
      this.rgb = 'rgb(185, 8, 8)'
      this.rgbDark = 'rgb(95, 0, 0)'
    }
    // GREEN
    else if(this.color == 'green'){
      this.rgb = 'rgb(8, 185, 8)'
      this.rgbDark = 'rgb(0, 95, 0)'
    }

    // ORANGE
    else if(this.color == 'orange'){
      this.rgb = 'rgb(233, 100, 0)'
      this.rgbDark = 'rgb(80, 20, 0)'
    }

  }

  onHover(){
    // RED
    if(this.color == 'red'){
      this.rgb = 'rgb(255, 16, 16)'
    }
    // GREEN
    else if(this.color == 'green'){
      this.rgb = 'rgb(16, 225, 16)'
    }
    // ORANGE
    else if(this.color == 'orange'){
      this.rgb = 'rgb(255, 122, 0)'
    }
  }

  offHover(){
    // RED
    if(this.color == 'red'){
      this.rgb = 'rgb(185, 8, 8)'
    }
    // GREEN
    else if(this.color == 'green'){
      this.rgb = 'rgb(8, 185, 8)'
    }
    // ORANGE
    else if(this.color == 'orange'){
      this.rgb = 'rgb(233, 100, 0)'
    }
  }



}
