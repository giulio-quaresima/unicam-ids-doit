import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SoggettoUtente } from '../model/soggetto-utente';
import { AbstractService } from './abstract-service';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends AbstractService {

  baseUrl = this.customApiBaseUrl + "/auth";

  constructor( private genericService : GenericService) {
    super();
   }

   authenticate(username : string, password : string) : void {
     
   }

   getUsers() : Observable<SoggettoUtente[]> {
     return this.genericService.getAny(this.baseUrl + "/users")
   }
}
