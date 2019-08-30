import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { from } from 'rxjs';
import { AuthGuard } from './auth.guard'; 
import { RegisterComponent } from './register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { CustomerhomeComponent } from './customerhome/customerhome.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { BnNgIdleService } from 'bn-ng-idle';
import { AlltransactionsComponent } from './alltransactions/alltransactions.component';
import { EditcustomerComponent } from './editcustomer/editcustomer.component';
import { TransfermoneyComponent } from './transfermoney/transfermoney.component';

import { AccountcreateComponent } from './accountcreate/accountcreate.component';
import { HomedisplayComponent } from './homedisplay/homedisplay.component';
import { CusthomenavComponent } from './custhomenav/custhomenav.component';
import { CusttransactionsComponent } from './custtransactions/custtransactions.component';
import { AdmindisplayComponent } from './admindisplay/admindisplay.component';



// import {TransferComponent} from './transfer/transfer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AdminloginComponent,
    CustomerhomeComponent,
    AdminhomeComponent,
    AlltransactionsComponent,
    EditcustomerComponent,
    TransfermoneyComponent,
    
    AccountcreateComponent,
    HomedisplayComponent,
    CusthomenavComponent,
    CusttransactionsComponent,
    AdmindisplayComponent,
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    
     
    // TransferComponent
  ],
  providers: [AuthGuard,BnNgIdleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
