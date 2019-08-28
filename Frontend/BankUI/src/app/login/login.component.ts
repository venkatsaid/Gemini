import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import custom validator to validate that password and confirm password fields match
import { MustMatch } from '../../assets/must-match.validator';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
    submitted = false;
    public status:string = "";
  constructor(private formBuilder: FormBuilder,private objHttp:HttpClient,private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      
      password: ['', [Validators.required]],
      
  });
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
    this.objHttp.post("http://172.20.1.8:8100/bankapi/login", par
        )
        .toPromise() 
        .then(
            data => {
                console.log("POST Request is successful ", data);
                if(data)
                {
                  console.log("homepage");
                  this.router.navigate(['custhome']);
                }
                else{
                  console.log("error");
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
