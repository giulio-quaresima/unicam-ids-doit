import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreaProgettoComponent } from './components/crea-progetto/crea-progetto.component';
import { HomeComponent } from './components/home/home.component';
import { ModificaProgettoComponent } from './components/modifica-progetto/modifica-progetto.component';
import { NotFoundComponent } from './components/http/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    CreaProgettoComponent,
    HomeComponent,
    ModificaProgettoComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CollapseModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
