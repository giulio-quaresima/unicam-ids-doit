import { Component, OnInit } from '@angular/core';
import { GenericService } from './services/generic.service';
import { environment } from '../environments/environment';
import { SoggettoUtente } from './model/soggetto-utente';
import { AuthStatus } from './model/auth-status';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'DoIt';
  isCollapsed = true;
  users : SoggettoUtente[] = [];
  username : string = "";
  authStatus : AuthStatus = <AuthStatus>{authenticated : false};

  constructor(private authService : AuthService) {}

  login(): void {

  }

  ngOnInit(): void {
    this.authService.getUsers().subscribe(
      users => this.users = users
    );
  }

}
