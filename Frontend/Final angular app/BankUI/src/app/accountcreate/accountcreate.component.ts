import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import custom validator to validate that password and confirm password fields match
import { MustMatch } from '../../assets/must-match.validator';

import {HttpClient, HttpParams} from '@angular/common/http';
import { Router } from '@angular/router';
import { customer } from '../customer';


@Component({
  selector: 'app-accountcreate',
  templateUrl: './accountcreate.component.html',
  styleUrls: ['./accountcreate.component.css']
})
export class AccountcreateComponent implements OnInit {
id:any;
accountForm: FormGroup;
    submitted = false;
    public status:string = "";
  constructor(private formBuilder: FormBuilder,private objHttp:HttpClient,private router:Router) { }

  ngOnInit() {
   this.id= localStorage.getItem('admincustid');
   this.accountForm = this.formBuilder.group({
    accountBalance: ['', Validators.required],
    accountType: ['']});
  }
  get f() { return this.accountForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.accountForm.invalid) {
        return;
    }
    this.PostCall();
  }

  PostCall(){
    let par= new HttpParams();
    par=par.set('customerId',this.id);
    par=par.set('accountBalance',this.accountForm.value.accountBalance);
    par=par.set('accountType',this.accountForm.value.accountType);
    data: customer;
    this.objHttp.post("http://localhost:8100/bankapi/account/add", par)
        .toPromise() 
        .then(
            (data : any) => {
                //console.log("POST Request is successful ", data);
                this.status = "POST Request is successful";
                localStorage.setItem('admincustid',data.customerId);
                alert("Account created successfully");
                this.router.navigate(  ['accountcreate'] );
            },
            error => {
                console.log("Error", error);
                this.status = "Error";
            }
        );    
  }

}
