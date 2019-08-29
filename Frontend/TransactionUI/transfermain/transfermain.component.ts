import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-transfermain',
  templateUrl: './transfermain.component.html',
  styleUrls: ['./transfermain.component.css']
})
export class TransfermainComponent implements OnInit {

  
  constructor(private router:Router){}

  ngOnInit() {
  }

  onBtnClick(){
    this.router.navigate( [{ outlets : { friendsOutlet: ['transfer'] }  }] );
  }

  onBtn2Click(){
    this.router.navigate( [{ outlets : { friendsOutlet: ['tranfertome'] } }] );
  }

}
