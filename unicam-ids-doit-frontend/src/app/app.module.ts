import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreaProgettoComponent } from './components/crea-progetto/crea-progetto.component';
import { HomeComponent } from './components/home/home.component';
import { ModificaProgettoComponent } from './components/modifica-progetto/modifica-progetto.component';
import { NotFoundComponent } from './components/http/not-found/not-found.component';
import { GestisciCandidaturaComponent } from './components/gestisci-candidatura/gestisci-candidatura.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthService } from './services/auth.service';
import { CreaCandidaturaProgettoComponent } from './components/crea-candidatura-progetto/crea-candidatura-progetto.component';
import { ListCandidatureComponent } from './components/list-candidature/list-candidature.component';
import { ListInvitiComponent } from './components/list-inviti/list-inviti.component';
import { GestisciInvitoComponent } from './components/gestisci-invito/gestisci-invito.component';

@NgModule({
  declarations: [
    AppComponent,
    CreaProgettoComponent,
    HomeComponent,
    ModificaProgettoComponent,
    NotFoundComponent,
    GestisciCandidaturaComponent,
    NavbarComponent,
    CreaCandidaturaProgettoComponent,
    ListCandidatureComponent,
    ListInvitiComponent,
    GestisciInvitoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CollapseModule.forRoot()
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
