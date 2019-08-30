import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  
import { AuthService } from '../auth.service';
import { BnNgIdleService } from 'bn-ng-idle';


@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  id: string;
  constructor(private router: Router, private authService: AuthService,private bnIdle: BnNgIdleService) {
    this.bnIdle.startWatching(300).subscribe((res) => {
      if(res) {
        
          this.logout();
      }
    })
   }
   bankerOperations=[

    {
    icon:"fa-user",
    name:"Create Customer",
    link:"register"
    },
    {
    icon:"fa-cog",
    name:"Edit Customer",
    link:"edit"
    },
    {
    icon:"fa-history",
    name:"View All Transactions",
    link:"transactions"
    }];

  ngOnInit() {
    this.id = localStorage.getItem('token');
  }
  logout() {  
    console.log('logout');  
    
    this.authService.logout();  
    this.router.navigate(['/adminlogin']);  
  }  

}
