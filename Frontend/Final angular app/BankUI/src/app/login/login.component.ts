import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {ILogin} from '../login';
import {customer} from '../customer';
import { from } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
    submitted = false;
    model: ILogin = { userid: "user", password: "user@123" }  
    public status:string = "";
    public err:string = "";
    cstid:any;
      
message: string;  
returnUrl: string; 
  constructor(private formBuilder: FormBuilder,private objHttp:HttpClient,private router: Router,private authService: AuthService) { 
    
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      
      password: ['', [Validators.required]],
      
  });
  this.returnUrl = '/custhome';  
this.authService.logout(); 
  }
  
    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }
      this.PostCall();
      
  }
  PostCall(){
    let par= new HttpParams();
    par=par.set('userName',this.loginForm.value.userName);
    par=par.set('pass',this.loginForm.value.password);
    this.objHttp.post("http://localhost:8100/bankapi/customer/login", par
        )
        .toPromise() 
        .then(
            (data : any) => {
                console.log("POST Request is successful ", data);
                if(data)
                {
                  
                      localStorage.setItem('isLoggedIn', "true");  
                      localStorage.setItem('username', this.f.userName.value);
                      localStorage.setItem('customerId', data.customerId);
                      localStorage.setItem('customerName',data.firstName);
                      
                       //console.log(data.customerId);
                    this.router.navigate([this.returnUrl]);  
                  
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
