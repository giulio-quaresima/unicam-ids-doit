import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Candidatura } from 'src/app/model/candidatura';
import { Progetto } from 'src/app/model/progetto';
import { ProgettoService } from 'src/app/services/progetto.service';
import { SoggettoCollettivoService } from 'src/app/services/soggetto-collettivo.service';

@Component({
  selector: 'app-crea-candidatura-progetto',
  templateUrl: './crea-candidatura-progetto.component.html',
  styleUrls: ['./crea-candidatura-progetto.component.css']
})
export class CreaCandidaturaProgettoComponent implements OnInit {

  progetto : Progetto = <Progetto>{};
  candidatura : Candidatura = <Candidatura>{
    autopromozione : "",
    progetto : {},
    soggetto : {}
  };

  constructor(
    private route : ActivatedRoute,
    private progettoService : ProgettoService,
    public soggettoService : SoggettoCollettivoService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.progettoService.find(params['idProgetto']).subscribe(progetto => {
        this.candidatura.progetto = progetto;
      });
    });
  }

  submit() : void {
    console.log("Candidato per conto di " + this.candidatura.soggetto.id);
    console.log(this.candidatura);
  }

}
