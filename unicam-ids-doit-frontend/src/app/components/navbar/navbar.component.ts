import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthStatus } from 'src/app/model/auth-status';
import { SoggettoUtente } from 'src/app/model/soggetto-utente';
import { AuthService } from 'src/app/services/auth.service';
import { ProgettoService } from 'src/app/services/progetto.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  title = 'DoIt';
  isCollapsed = true;
  username = "";
  users : SoggettoUtente[] = [];
  authStatus : AuthStatus = <AuthStatus>{authenticated : false};

  constructor(
    private router : Router,
    private authService : AuthService, 
    private progettoService : ProgettoService) { }

  login(): void {
    if (this.username) {
      this.authService.authenticate(this.username, this.username).subscribe(result => {
        this.router.navigate(['/']);
        this.ngOnInit();
      } );
    } else {
      this.authService.logout().subscribe(result => {
        this.router.navigate(['/']);
        this.ngOnInit();
      });
    }
  }

  ngOnInit(): void {
    if (this.users.length == 0) {
      this.authService.getUsers().subscribe(response => this.users = response.data);
    }
    this.authService.getCurrentUser().subscribe(response => {
      this.authStatus = response.data;
      this.username = this.authStatus.utente?.account?.username;
      this.progettoService.reload();
    });
  }

}
