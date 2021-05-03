import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SoggettoCollettivo } from '../model/soggetto-collettivo';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SoggettoCollettivoService {

  private url = environment.api.baseUrl + "/custom/soggettoCollettivoes/currentUser";

  getSoggettiUtente() : Observable<SoggettoCollettivo[]> {
    return this.http.get<SoggettoCollettivo[]>(this.url);
  };

  constructor(private http: HttpClient) { }
}
