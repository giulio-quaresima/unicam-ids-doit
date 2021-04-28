import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SoggettoCollettivo, SoggettoCollettivoes } from '../model/soggetto-collettivo';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SoggettoCollettivoService {

  private url = environment.api.baseUrl + "/soggettoCollettivoes";

  getSoggettiUtente() : Observable<SoggettoCollettivoes> {
    return this.http.get<SoggettoCollettivoes>(this.url);
  };

  constructor(private http: HttpClient) { }
}
