import { Component, OnInit } from '@angular/core';
import { GenericService } from '../../services/generic.service';
import { Progetto } from '../../model/progetto';
import { ProgettoService } from '../../services/progetto.service';
import { Observable } from 'rxjs';
import { SoggettoCollettivo } from 'src/app/model/soggetto-collettivo';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { AuthStatus } from 'src/app/model/auth-status';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private router : Router,
    private genericService : GenericService, 
    public progettoService : ProgettoService,
    private authService : AuthService) {}

  ngOnInit(): void {
    console.log(this.authService == this.progettoService.authService);
    console.log(this.authService === this.progettoService.authService);
    this.authService.authStatus.subscribe(this.authEventConsumer.bind(this)); // Non togliere bind!!!
    this.progettoService.reload();
  }

  authEventConsumer(authStatus : AuthStatus) : void {
    console.log("HomeComponent.authEventConsumer");
    console.log(authStatus);
    console.log(this);
  }

  candidati(idProgetto : number) {
    this.router.navigate(['']);
  }

}
