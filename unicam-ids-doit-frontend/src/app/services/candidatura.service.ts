import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthStatus } from '../model/auth-status';
import { Candidatura } from '../model/candidatura';
import { AbstractService } from './abstract-service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CandidaturaService extends AbstractService {

  private url = this.customApiBaseUrl + "/candidature";
  proprieCandidature : Candidatura[] = []; 

  constructor(private http: HttpClient, private authService : AuthService) {
    super();
    this.authService.authStatus.subscribe(this.authEventConsumer.bind(this)); // Non togliere bind!!!
  }

  authEventConsumer(authStatus : AuthStatus) : void {
    this.reload();
  }

  reload() : void {
    this.listProprieCandidature().subscribe(result => {
      this.proprieCandidature = result;
      console.log(this.proprieCandidature);
    });
  }

  listProprieCandidature() : Observable<Candidatura[]> {
    return this.http.get<Candidatura[]>(this.url);
  }

  create(candidatura : Candidatura) : Observable<any> {
    return this.http.post(this.url, candidatura);
  }

  toUrl(candidatura : Candidatura) : string {
    return this.url + "/" + candidatura.id;
  }

}
