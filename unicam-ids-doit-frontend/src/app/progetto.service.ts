import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Progetto } from './domain/progetto';

@Injectable({
  providedIn: 'root'
})
export class ProgettoService {

  private apiHost = "http://localhost:8080";

  create(progetto : Progetto) : Observable<any> {
    return this.http.put(this.apiHost + "/Progetto", progetto);
  }

  constructor(private http: HttpClient) { }
}
