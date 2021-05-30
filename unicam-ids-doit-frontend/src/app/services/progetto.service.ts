import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Progetto } from '../model/progetto';
import { AuthService } from './auth.service';
import { AuthStatus } from '../model/auth-status';
import { AbstractService } from './abstract-service';

@Injectable({
  providedIn: 'root'
})
export class ProgettoService extends AbstractService {

  private url = this.customApiBaseUrl + "/progetti";
  progettiVisibili : Progetto[] = [];

  constructor(private http: HttpClient, private authService : AuthService) {
    super();
    this.authService.authStatus.subscribe(this.authEventConsumer.bind(this)); // Non togliere bind!!!
   }

  authEventConsumer(authStatus : AuthStatus) : void {
    this.reload();
  }

  reload() : void {
    this.http.get<Progetto[]>(this.url).subscribe(progettoes => {console.log(progettoes); this.progettiVisibili = progettoes;});
  }

  find(id : number) : Observable<any> {
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
