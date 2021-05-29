import { HttpClient } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { EventEmitter, Injectable, OnInit, Output } from '@angular/core';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { AuthStatus } from '../model/auth-status';
import { Response } from '../model/response';
import { SoggettoCollettivo } from '../model/soggetto-collettivo';
import { SoggettoUtente } from '../model/soggetto-utente';
import { AbstractService } from './abstract-service';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends AbstractService {

  baseUrl = this.customApiBaseUrl + "/auth";  
  authStatus = new BehaviorSubject(<AuthStatus>{authenticated : false});

  constructor(protected http : HttpClient, private genericService : GenericService) {
    super();
    console.log("AuthService.constructor");
    this.getCurrentUser().subscribe(response => this.authStatus.next(response.data));
   }

   authenticate(username : string, password : string) : void {
     let params : {[param : string] : string;} = {};
     params['username'] = username;
     params['password'] = password;
     this.http.post(this.baseUrl + "/login", {}, {params : {"username" : username, "password" : password}}).subscribe(
       response => {
         this.authStatus.next((<Response<AuthStatus>>response).data);
       }
     );
   }

   logout() : void {
    this.http.post(this.baseUrl + "/logout", {}).subscribe(
      response => {
        this.authStatus.next((<Response<AuthStatus>>response).data);
      }
    );
   }

   getCurrentUser() : Observable<Response<AuthStatus>> {
    return this.genericService.getAny(this.baseUrl);
   }

   getUsers() : Observable<Response<SoggettoUtente[]>> {
     return this.genericService.getAny(this.baseUrl + "/users");
   }
}
