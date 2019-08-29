import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {RegisterComponent} from './register/register.component';
import { TransferComponent} from './transfer/transfer.component';
import { CommonModule } from '@angular/common';
import { TransfermainComponent } from './transfermain/transfermain.component';
import { TranfertomeComponent } from './tranfertome/tranfertome.component';

const routes: Routes = [];

export const router:Routes=[
  
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  {path:'transfer', component:TransferComponent, outlet :"friendsOutlet"},
  {path:'tranfertome', component:TranfertomeComponent, outlet :"friendsOutlet"}
];

@NgModule({
  declarations :[] ,
  imports: [
        CommonModule,  
    RouterModule.forRoot(router)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
