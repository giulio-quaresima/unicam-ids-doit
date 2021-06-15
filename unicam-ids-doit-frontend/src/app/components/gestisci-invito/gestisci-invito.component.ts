import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Invito } from 'src/app/model/invito';
import { AuthService } from 'src/app/services/auth.service';
import { InvitoService } from 'src/app/services/invito.service';

@Component({
  selector: 'app-gestisci-invito',
  templateUrl: './gestisci-invito.component.html',
  styleUrls: ['./gestisci-invito.component.css']
})
export class GestisciInvitoComponent implements OnInit {

  invito : Invito = <Invito>{};

  constructor(
    private route : ActivatedRoute, 
    private invitoService : InvitoService,
    private authService : AuthService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.invitoService.find(params['id']).subscribe(invito => {
        this.invito = invito;
        console.log(this.invito);
      });
    });
  }

  accetta() : void {
    this.invito.accettazione = true;
    this.submit();
  }

  rifiuta() : void {
    this.invito.accettazione = false;
    this.submit();
  }

  submit() : void {
    console.log(this.invito);
    this.invitoService.save(this.invito).subscribe(invito => {
      this.invito = invito;
      console.log(this.invito);
      this.authService.refreshCurrentUser();
    });
  }

}
