import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import custom validator to validate that password and confirm password fields match
import { MustMatch } from '../../assets/must-match.validator';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
    submitted = false;
    public status:string = "";
  constructor(private formBuilder: FormBuilder,private objHttp:HttpClient) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: [''],
      dob: [''],
      pincode: [''],
      userName: ['', Validators.required],
      aadharNumber: ['', [Validators.required, Validators.maxLength(12),Validators.minLength(12)]],
      panNumber: ['', [ Validators.minLength(10),Validators.maxLength(10)]],
      phone: ['', [Validators.required, Validators.maxLength(10),Validators.minLength(10)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      //confirmPassword: ['', Validators.required]
  }, //{
      //validator: MustMatch('password', 'confirmPassword')
  //}
  );
  }
  
    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.registerForm.invalid) {
          return;
      }
      this.PostCall();
      // alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value));
  }
  PostCall(){
    this.objHttp.post("http://172.20.1.8:8100/web/add",
        //   {
    
        //     "firstName": "user9",
        //     "lastName": "last",
        //     "aadharNumber": "223436349",
        //     "panNumber": "afsarx",
        //     "phone": "897622322",
        //     "dob": "2019-08-26",
        //     "email": "sdfgwe23@fg",
        //     "userName": "1abcdefgh2jkl",
        //     "password": "1234567890",
        //     "address": "fdghjbnm,",
        //     "pincode": "45599"
        // }
        this.registerForm.value
        )
        .toPromise() 
        .then(
            data => {
                console.log("POST Request is successful ", data);
                this.status = "POST Request is successful";
            },
            error => {
                console.log("Error", error);
                this.status = "Error";
            }
        );    
  }

}
