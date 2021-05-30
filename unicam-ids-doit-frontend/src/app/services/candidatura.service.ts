import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidatura } from '../model/candidatura';
import { AbstractService } from './abstract-service';

@Injectable({
  providedIn: 'root'
})
export class CandidaturaService extends AbstractService {

  private url = this.customApiBaseUrl + "/candidature";

  constructor(private http: HttpClient) {
    super();
  }

  create(candidatura : Candidatura) : Observable<any> {
    return this.http.post(this.url, candidatura);
  }

  toUrl(candidatura : Candidatura) : string {
    return this.url + "/" + candidatura.id;
  }

}
