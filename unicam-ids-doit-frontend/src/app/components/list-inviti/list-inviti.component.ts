import { Component, OnInit } from '@angular/core';
import { AuthStatus } from 'src/app/model/auth-status';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-list-inviti',
  templateUrl: './list-inviti.component.html',
  styleUrls: ['./list-inviti.component.css']
})
export class ListInvitiComponent implements OnInit {

  authStatus : AuthStatus = <AuthStatus>{};

  constructor(private authService : AuthService) { }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(response => {
      this.authEventConsumer(response.data);
    });
  }

  authEventConsumer(authStatus : AuthStatus) : void {
    this.authStatus = authStatus;
  }

}
