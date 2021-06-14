import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, filter, catchError, mergeMap } from 'rxjs/operators';
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
  username : string = "";
  users : SoggettoUtente[] = [];
  authStatus : AuthStatus = <AuthStatus>{};

  constructor(private router : Router, private authService : AuthService, public activatedRoute : ActivatedRoute) { }

    ngOnInit(): void {
      this.authService.authStatus.subscribe(this.authEventConsumer.bind(this)); // Non togliere bind!!!
      if (this.users.length == 0) {
        this.authService.getUsers().subscribe(response => this.users = response.data);
      }
      this.authService.getCurrentUser().subscribe(response => {
        this.authEventConsumer(response.data);
      });
    }

    currentRouteNameIs(name : string) : Observable<boolean> {
      return this.activatedRoute.data.pipe(map(data => data.name == name));
    }
    
    authEventConsumer(authStatus : AuthStatus) : void {
      console.log("NavbarComponent.authEventConsumer");
      console.log(authStatus);
      console.log(this);
      this.authStatus = authStatus;
      this.username = authStatus.utente?.account?.username || "";
      this.router.navigate(['/']);
    }
  
    login(): void {
    if (this.username) {
      this.authService.authenticate(this.username, this.username);
    } else {
      this.authService.logout();
    }
  }

}
