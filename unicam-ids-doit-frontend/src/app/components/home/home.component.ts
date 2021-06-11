import { Component, OnInit } from '@angular/core';
import { ProgettoService } from '../../services/progetto.service';
import { SoggettoCollettivoService } from 'src/app/services/soggetto-collettivo.service';
import { AuthService } from 'src/app/services/auth.service';
import { AuthStatus } from 'src/app/model/auth-status';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  authStatus : AuthStatus = <AuthStatus>{};

  constructor(
    public progettoService : ProgettoService,
    public soggettoService : SoggettoCollettivoService,
    private authService : AuthService) {}

  ngOnInit(): void {
    this.progettoService.reload();
    this.authService.authStatus.subscribe(this.authEventConsumer.bind(this));
  }

  authEventConsumer(authStatus : AuthStatus) : void {
    this.authStatus = authStatus;
  }

}
