import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { from } from 'rxjs';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
<<<<<<< HEAD
import { TransfermainComponent } from './transfermain/transfermain.component';
import { TransferComponent } from './transfer/transfer.component';
import { TranfertomeComponent } from './tranfertome/tranfertome.component';
=======
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { CustomerhomeComponent } from './customerhome/customerhome.component';
// import {TransfermainComponent} from './transfermain/transfermain.component';
// import {TransferComponent} from './transfer/transfer.component';
>>>>>>> 68821c54c1382b25f3d4ff369b248574f6a7873e

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
<<<<<<< HEAD
    TransfermainComponent,
    TransferComponent,
    TranfertomeComponent
=======
    AdminloginComponent,
    CustomerhomeComponent,
    
>>>>>>> 68821c54c1382b25f3d4ff369b248574f6a7873e
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
<<<<<<< HEAD
    RouterModule
=======
    // TransfermainComponent,
    // TransferComponent
>>>>>>> 68821c54c1382b25f3d4ff369b248574f6a7873e
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }