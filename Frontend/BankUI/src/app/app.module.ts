import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { from } from 'rxjs';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { CustomerhomeComponent } from './customerhome/customerhome.component';
// import {TransfermainComponent} from './transfermain/transfermain.component';
// import {TransferComponent} from './transfer/transfer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AdminloginComponent,
    CustomerhomeComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    // TransfermainComponent,
    // TransferComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
