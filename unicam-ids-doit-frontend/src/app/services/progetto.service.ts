import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Progetto } from '../model/progetto';
import { CompetenzeService } from './competenze.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProgettoService {

  private url = environment.api.baseUrl + "/custom/progettoes";

  constructor(private http: HttpClient) { }

  findAll() : Observable<Progetto[]> {
    return this.http.get<Progetto[]>(this.url);
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
    return this.http.put(this.toUrl(progetto), progetto);
  }

  addTag(progetto : Progetto, tag : string) : Observable<any> {
    return this.http.post(this.toUrl(progetto) + "/competenzas", tag);
  }

  delTag(progetto : Progetto, tag : string) : Observable<any> {
    return this.http.delete(this.toUrl(progetto) + "/competenzas/" + tag);
  }

  toUrl(progetto : Progetto) : string {
    return this.url + "/" + progetto.id;
  }

}
