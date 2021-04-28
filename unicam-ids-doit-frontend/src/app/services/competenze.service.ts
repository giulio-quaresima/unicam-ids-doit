import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Competenza } from '../model/competenza';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompetenzeService {

  constructor(private http: HttpClient) { }

  createIfNotExists(tags : string[]) : Observable<any> {
    return this.http.put(environment.api.baseUrl + "/custom/competenzas/createIfNotExists", tags);
  }
  
}
