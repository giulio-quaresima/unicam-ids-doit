import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Invito } from '../model/invito';
import { AbstractService } from './abstract-service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class InvitoService extends AbstractService {

  private url = this.customApiBaseUrl + "/inviti";

  constructor(private http: HttpClient, private authService : AuthService) {
    super();
   }
  
  find(id : number) : Observable<any> {
    return this.http.get(this.url + "/" + id);
  }

  save(invito : Invito) : Observable<Invito> {
    return this.http.put(this.url + "/" + invito.id, invito) as Observable<Invito>;
  }

}
