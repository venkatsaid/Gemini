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
  selector: 'app-transfermoney',
  templateUrl: './transfermoney.component.html',
  styleUrls: ['./transfermoney.component.css']
})
export class TransfermoneyComponent implements OnInit {
  id: string;
  firstname:string;
  status:string;
  message:string;
  details:any;
  transferForm: FormGroup;
    submitted = false;
    returnUrl: string; 
    
    cstid:any;
  constructor(private formBuilder: FormBuilder,private bnIdle: BnNgIdleService,private objHttp:HttpClient,private router: Router,private authService: AuthService) {
    this.bnIdle.startWatching(1000).subscribe((res) => {
      if(res) {
        
          this.logout();
      }
    })
   }

  ngOnInit() {
    this.transferForm = this.formBuilder.group({
      accountType: [''],
      toAccount: ['', Validators.required],
      amount: ['', Validators.required],
      description: ['']
      
  });
  this.returnUrl = '/custhome';  

    this.firstname=localStorage.getItem('customerName'); 
    
    this.id = localStorage.getItem('customerId'); 
    this.Customerwithaccounts();

  }
  get f() { return this.transferForm.controls; }
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.transferForm.invalid) {
        return;
    }
    this.PostCall();
    
}

  logout() {  
    console.log('logout');  
    this.authService.logout();  
    this.router.navigate(['/login']);  
  }  
  Customerwithaccounts(){
    
    this.objHttp.get("http://localhost:8100/bankapi/account/customer/"+this.id
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

  PostCall(){
    let par= new HttpParams();
    par=par.set('fromaccount',this.transferForm.value.accountType);
    par=par.set('toaccount',this.transferForm.value.toAccount);
    par=par.set('amount',this.transferForm.value.amount);
    par=par.set('description',this.transferForm.value.description);
    this.objHttp.post("http://localhost:8100/bankapi/transaction/add", par
        )
        .toPromise() 
        .then(
            data => {
                console.log("POST Request is successful ", data);
                if(data)
                {
                  
                    console.log("transferred successfully");
                    alert("Money Transferred successfully");
                  
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
