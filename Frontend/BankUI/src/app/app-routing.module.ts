import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {RegisterComponent} from './register/register.component';
<<<<<<< HEAD
import { TransferComponent} from './transfer/transfer.component';
import { CommonModule } from '@angular/common';
import { TransfermainComponent } from './transfermain/transfermain.component';
import { TranfertomeComponent } from './tranfertome/tranfertome.component';
=======
import {AdminloginComponent} from './adminlogin/adminlogin.component';
import {CustomerhomeComponent} from './customerhome/customerhome.component';
  import { from } from 'rxjs';
>>>>>>> 68821c54c1382b25f3d4ff369b248574f6a7873e

const routes: Routes = [];

export const router:Routes=[
  
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
<<<<<<< HEAD

  {path:'transfer', component:TransferComponent, outlet :"friendsOutlet"},
  {path:'tranfertome', component:TranfertomeComponent, outlet :"friendsOutlet"}
=======
  { path: 'adminlogin', component: AdminloginComponent },
  { path: 'custhome', component: CustomerhomeComponent }
>>>>>>> 68821c54c1382b25f3d4ff369b248574f6a7873e
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
