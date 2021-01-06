import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SoggettoCollettivo, SoggettoCollettivoes } from '../model/soggetto-collettivo';

@Injectable({
  providedIn: 'root'
})
export class SoggettoCollettivoService {

  private url = "http://localhost:8080/soggettoCollettivoes";

  getSoggettiUtente() : Observable<SoggettoCollettivoes> {
    return this.http.get<SoggettoCollettivoes>(this.url);
  };

  constructor(private http: HttpClient) { }
}
