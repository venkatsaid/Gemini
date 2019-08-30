import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {ILogin} from '../login';
import { from } from 'rxjs';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {
  adminForm: FormGroup;
  submitted = false;
  model: ILogin = { userid: "admin", password: "nimda" }  
    public status:string = "";
    public err:string = "";
    msg: string;  
  returnUrl: string; 
  constructor(private formBuilder: FormBuilder,private objHttp:HttpClient,private router: Router,private authService: AuthService) { }

  ngOnInit() {
    this.adminForm = this.formBuilder.group({
      userName: ['', Validators.required],
      
      password: ['', [Validators.required]],
      
  });
  this.returnUrl = '/adminhome';  
this.authService.logout();
  }
  get f() { return this.adminForm.controls; }

    onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.adminForm.invalid) {
          return;
      }

  this.login();
  }
  login(){
    if (this.f.userName.value == this.model.userid && this.f.password.value == this.model.password) {  
      console.log("Login successful");  
      //this.authService.authLogin(this.model);  
      localStorage.setItem('isLoggedIn', "true");  
      localStorage.setItem('token', this.f.userName.value);  
      this.router.navigate([this.returnUrl]);  
   }  
  else {  
   this.msg = "Please check your userid and password";  
   }     
  }

}
