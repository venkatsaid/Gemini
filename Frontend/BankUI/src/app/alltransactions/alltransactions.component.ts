import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import { BnNgIdleService } from 'bn-ng-idle';
import {ILogin} from '../login';
import {customer} from '../customer';
import { from } from 'rxjs';

@Component({
  selector: 'app-alltransactions',
  templateUrl: './alltransactions.component.html',
  styleUrls: ['./alltransactions.component.css']
})
export class AlltransactionsComponent implements OnInit {
  id: string;
  firstname:string;
  status:string;
  message:string;
  details:any;

  constructor(private formBuilder: FormBuilder,private bnIdle: BnNgIdleService,private objHttp:HttpClient,private router: Router,private authService: AuthService) { }

  ngOnInit() {
    this.firstname=localStorage.getItem('customerName'); 
    
    this.id = localStorage.getItem('customerId'); 
    this.Alltransactions();
  }
  logout() {  
    console.log('logout');  
    this.authService.logout();  
    this.router.navigate(['/login']);  
  }  
  Alltransactions(){
    
    this.objHttp.get("http://localhost:8100/bankapi/transaction/getAll/"
        )
        .toPromise() 
        .then(
            data => {
                console.log("Get Request is successful ", data);
                if(data)
                {
                 this.details=data;
                     console.log("success");
                  
                }
                else{
                 this.message = "Please check your userid and password";     
                }
                this.status = "POST Request is successful";
            },
            error => {
                console.log("Error", error);
                this.status = "Error";
            }
        ); 
  
  }


}
