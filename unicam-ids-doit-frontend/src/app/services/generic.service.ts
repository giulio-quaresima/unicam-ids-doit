import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GenericService implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() : void {
  }

  getAny(url : string) : Observable<any> {
    return this.http.get(url);
  }

}
