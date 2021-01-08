import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GenericService {
  
  constructor(private http: HttpClient) { }

  getAny(uri : string) : Observable<any> {
    return this.http.get(uri);
  }

}
