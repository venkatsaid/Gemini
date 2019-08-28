import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AdminloginComponent} from './adminlogin/adminlogin.component';
import {CustomerhomeComponent} from './customerhome/customerhome.component';
  import { from } from 'rxjs';


const routes: Routes = [
  
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'adminlogin', component: AdminloginComponent },
  { path: 'custhome', component: CustomerhomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
