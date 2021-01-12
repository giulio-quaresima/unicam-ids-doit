import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Competenza } from '../model/competenza';

@Injectable({
  providedIn: 'root'
})
export class CompetenzeService {

  private url = "http://localhost:8080/competenzas";

  constructor(private http: HttpClient) { }

  createIfNotExists : Observable<any> {
    
  }
}
