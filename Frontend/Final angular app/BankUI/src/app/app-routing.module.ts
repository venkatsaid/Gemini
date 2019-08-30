import { NgModule } from '@angular/core';
import {AuthGuard} from './auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AdminloginComponent} from './adminlogin/adminlogin.component';
import {CustomerhomeComponent} from './customerhome/customerhome.component';
import {HomeComponent} from './home/home.component';
import {AdminhomeComponent} from './adminhome/adminhome.component';
import {EditcustomerComponent} from './editcustomer/editcustomer.component';
import {AlltransactionsComponent} from './alltransactions/alltransactions.component';
import {TransfermoneyComponent} from './transfermoney/transfermoney.component';

import {AccountcreateComponent} from './accountcreate/accountcreate.component';
  import { from } from 'rxjs';
  import {HomedisplayComponent} from './homedisplay/homedisplay.component';
  import {CusttransactionsComponent} from './custtransactions/custtransactions.component';


const routes: Routes = [
  
  { path: "login",
  component: LoginComponent
},

  { path: "register", component: RegisterComponent,canActivate : [AuthGuard] },
  { path: 'adminlogin', component: AdminloginComponent },
  { path: 'custhome', component: CustomerhomeComponent, canActivate : [AuthGuard] },
  { path: 'adminhome', component: AdminhomeComponent, canActivate : [AuthGuard] },
  { path: 'adminhome', component: AdminhomeComponent, canActivate : [AuthGuard] },
  { path: 'edit', component: EditcustomerComponent, canActivate : [AuthGuard] },
  { path: 'accountcreate', component: AccountcreateComponent, canActivate : [AuthGuard] },
 
  { path: 'transfer', component: TransfermoneyComponent, canActivate : [AuthGuard] },
  { path: 'transactions', component: AlltransactionsComponent, canActivate : [AuthGuard] },
  { path: 'statement', component: CusttransactionsComponent, canActivate : [AuthGuard] },
  { path: '', component: HomedisplayComponent },
  {path:'**',redirectTo:''}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
