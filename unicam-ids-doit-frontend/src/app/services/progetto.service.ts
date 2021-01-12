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

  find(id : number) : Observable<any> {
    return this.http.get(this.url + "/" + id);
  }

  create(progetto : Progetto) : Observable<any> {
    return this.http.post(this.url, progetto);
  }

  save(progetto : Progetto) : Observable<any> {
    return this.http.put(progetto._links['self'].href, progetto);
  }

  constructor(private http: HttpClient) { }
}
