import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Progetto, Progettoes } from '../model/progetto';

@Injectable({
  providedIn: 'root'
})
export class ProgettoService {

  private url = "http://localhost:8080/progettoes";

  findAll() : Observable<Progettoes> {
    return this.http.get<Progettoes>(this.url);
  }

  create(progetto : Progetto) : Observable<any> {
    return this.http.post(this.url, progetto);
  }

  constructor(private http: HttpClient) { }
}
