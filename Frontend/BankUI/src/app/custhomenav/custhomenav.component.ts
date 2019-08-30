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
  selector: 'app-custhomenav',
  templateUrl: './custhomenav.component.html',
  styleUrls: ['./custhomenav.component.css']
})
export class CusthomenavComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private bnIdle: BnNgIdleService,private objHttp:HttpClient,private router: Router,private authService: AuthService) { }

  ngOnInit() {
  }
  logout() {  
    console.log('logout');  
    this.authService.logout();  
    this.router.navigate(['/login']);  
  }  

}
