import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthStatus } from 'src/app/model/auth-status';
import { Candidatura } from 'src/app/model/candidatura';
import { Progetto } from 'src/app/model/progetto';
import { SoggettoUtente } from 'src/app/model/soggetto-utente';
import { AuthService } from 'src/app/services/auth.service';
import { CandidaturaService } from 'src/app/services/candidatura.service';
import { ProgettoService } from 'src/app/services/progetto.service';
import { SoggettoCollettivoService } from 'src/app/services/soggetto-collettivo.service';

@Component({
  selector: 'app-crea-candidatura-progetto',
  templateUrl: './crea-candidatura-progetto.component.html',
  styleUrls: ['./crea-candidatura-progetto.component.css']
})
export class CreaCandidaturaProgettoComponent implements OnInit {

  soggettoUtente : SoggettoUtente = <SoggettoUtente>{};
  progetto : Progetto = <Progetto>{};
  candidatura : Candidatura = <Candidatura>{
    autopromozione : "",
    progetto : {},
    soggetto : {}
  };

  constructor(
    private route : ActivatedRoute,
    private progettoService : ProgettoService,
    public soggettoService : SoggettoCollettivoService,
    public authService : AuthService,
    private candidaturaService : CandidaturaService) { }

  ngOnInit(): void {
    this.authService.authStatus.subscribe(this.authEventConsumer.bind(this)); // Non togliere bind!!!
    this.route.params.subscribe(params => {
      this.progettoService.find(params['idProgetto']).subscribe(progetto => {
        this.candidatura.progetto = progetto;
      });
    });
  }

  authEventConsumer(authStatus : AuthStatus) : void {
    this.candidatura.soggetto = this.soggettoUtente = authStatus.utente;
  }
  
  submit() : void {
    console.log(this.candidatura);
    this.candidaturaService.create(this.candidatura).subscribe(createdCandidatura => console.log(createdCandidatura));
  }

}
