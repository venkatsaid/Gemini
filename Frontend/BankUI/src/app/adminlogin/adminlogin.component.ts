import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {
  adminForm: FormGroup;
  submitted = false;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.adminForm = this.formBuilder.group({
      userName: ['', Validators.required],
      
      password: ['', [Validators.required]],
      
  });
  }
  get f() { return this.adminForm.controls; }

    onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.adminForm.invalid) {
          return;
      }

      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.adminForm.value))
  }

}
