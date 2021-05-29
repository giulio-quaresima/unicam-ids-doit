import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SoggettoCollettivo } from '../model/soggetto-collettivo';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';
import { AuthStatus } from '../model/auth-status';

@Injectable({
  providedIn: 'root'
})
export class SoggettoCollettivoService {

  private baseUrl = environment.api.baseUrl + "/custom/soggettoCollettivoes";
  soggettiAutorizzazioneCandidatura : SoggettoCollettivo[] = [];
  soggettiAutorizzazioneGestioneProgetto : SoggettoCollettivo[] = [];

  constructor(private http: HttpClient, private authService : AuthService) {
    this.authService.authStatus.subscribe(this.authEventConsumer.bind(this)); // Non togliere bind!!!
  }

  authEventConsumer(authStatus : AuthStatus) : void {
    this.getSoggettiUtente("CANDIDATURA").subscribe(soggettoCollettivoes => {
      this.soggettiAutorizzazioneCandidatura = soggettoCollettivoes;
    });
    this.getSoggettiUtente("GESTIONE_PROGETTO").subscribe(soggettoCollettivoes => {
      this.soggettiAutorizzazioneGestioneProgetto = soggettoCollettivoes;
    });
  }

  getSoggettiUtente(autorizzazione : string) : Observable<SoggettoCollettivo[]> {
    return this.http.get<SoggettoCollettivo[]>(this.baseUrl + "/currentUser/autorizzazione/" + autorizzazione);
  };
}
