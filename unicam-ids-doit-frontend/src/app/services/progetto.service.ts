import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Progetto, Progettoes } from '../model/progetto';
import { CompetenzeService } from './competenze.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProgettoService {

  private url = environment.api.baseUrl + "/progettoes";
  private customUrl = environment.api.baseUrl + "/custom/progettoes";

  findAll() : Observable<Progettoes> {
    return this.http.get<Progettoes>(this.url);
  }

  find(id : number) : Observable<any> {
    console.log(id);
    console.log(this.url + "/" + id);
    return this.http.get(this.url + "/" + id);
  }

  create(progetto : Progetto) : Observable<any> {
    return this.http.post(this.url, progetto);
  }

  save(progetto : Progetto) : Observable<any> {
    return this.http.put(this.customUrl + "/" + progetto.id, progetto);
  }

  constructor(private http: HttpClient) { }
}
