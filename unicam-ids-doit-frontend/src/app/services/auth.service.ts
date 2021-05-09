import { HttpClient } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthStatus } from '../model/auth-status';
import { Response } from '../model/response';
import { SoggettoUtente } from '../model/soggetto-utente';
import { AbstractService } from './abstract-service';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends AbstractService {

  baseUrl = this.customApiBaseUrl + "/auth";

  constructor(protected http : HttpClient, private genericService : GenericService) {
    super();
   }

   authenticate(username : string, password : string) : Observable<Response<string>> {
     let params : {[param : string] : string;} = {};
     params['username'] = username;
     params['password'] = password;
     return this.http.post(this.baseUrl + "/login", {}, {params : {"username" : username, "password" : password}}) as Observable<Response<string>>;
   }

   logout() : Observable<Response<string>> {
    return this.http.post(this.baseUrl + "/logout", {}) as Observable<Response<string>>;
   }

   getCurrentUser() : Observable<Response<AuthStatus>> {
    return this.genericService.getAny(this.baseUrl);
   }

   getUsers() : Observable<Response<SoggettoUtente[]>> {
     return this.genericService.getAny(this.baseUrl + "/users");
   }
}
