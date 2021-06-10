import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Candidatura } from 'src/app/model/candidatura';
import { Invito } from 'src/app/model/invito';
import { SoggettoUtente } from 'src/app/model/soggetto-utente';
import { CandidaturaService } from 'src/app/services/candidatura.service';

@Component({
  selector: 'app-gestisci-candidatura',
  templateUrl: './gestisci-candidatura.component.html',
  styleUrls: ['./gestisci-candidatura.component.css']
})
export class GestisciCandidaturaComponent implements OnInit {

  candidatura : Candidatura = <Candidatura>{};
  inviti : Invito[] = [];

  constructor(private route : ActivatedRoute, 
    private candidaturaService : CandidaturaService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.candidaturaService.get(params['id']).subscribe(this.reset.bind(this));
    });
  }

  reset(candidatura : Candidatura) {
    this.candidatura = candidatura;
    this.inviti = [];
    for (let soggetto of candidatura.soggettiUtenteSuggeritiPerInvito) {
      var invito : Invito = <Invito>{};
      invito.candidatura = <Candidatura>{id : candidatura.id};
      invito.invitato = <SoggettoUtente>{id : soggetto.id, type : soggetto.type};
      this.inviti.push(invito);
    }
  }

  invita(invitoIndex : number) : void {
    console.log(this.inviti[invitoIndex]);
    this.candidaturaService.invita(this.inviti[invitoIndex]).subscribe(candidatura => {
      console.log(candidatura);
      this.reset(candidatura);
    });
  }
  
}
