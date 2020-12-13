import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SoggettoCollettivo } from './domain/soggetto-collettivo';

@Injectable({
  providedIn: 'root'
})
export class SoggettoCollettivoService {

  private apiHost = "http://localhost:8080";

  getSoggettiUtente() : Observable<SoggettoCollettivo[]> {
    return this.http.get<SoggettoCollettivo[]>(this.apiHost + "/SoggettoCollettivo/currentUser");
  };

  constructor(private http: HttpClient) { }
}
