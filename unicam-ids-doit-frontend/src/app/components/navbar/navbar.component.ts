import { Component, OnInit } from '@angular/core';
import { AuthStatus } from 'src/app/model/auth-status';
import { SoggettoUtente } from 'src/app/model/soggetto-utente';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  title = 'DoIt';
  isCollapsed = true;
  username = "ciao";
  user = {username : "dfgdsfgd"};
  users : SoggettoUtente[] = [];
  authStatus : AuthStatus = <AuthStatus>{authenticated : false};

  constructor(private authService : AuthService) { }

  login(event : any): void {
    console.log(event);
    this.authService.authenticate(this.username, this.username).subscribe(result => {
      console.log(result);
      this.authService.getCurrentUser().subscribe(authStatus => this.authStatus = authStatus);
    } );
  }

  ngOnInit(): void {
    this.authService.getUsers().subscribe(
      users => this.users = users
    );
  }

}
