import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SoggettoCollettivo } from '../model/soggetto-collettivo';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SoggettoCollettivoService {

  private baseUrl = environment.api.baseUrl + "/custom/soggettoCollettivoes";

  getSoggettiUtente(autorizzazione : string) : Observable<SoggettoCollettivo[]> {
    return this.http.get<SoggettoCollettivo[]>(this.baseUrl + "/currentUser/autorizzazione/" + autorizzazione);
  };

  constructor(private http: HttpClient) { }
}
