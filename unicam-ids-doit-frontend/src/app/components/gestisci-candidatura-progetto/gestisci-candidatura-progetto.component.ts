import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Progetto } from 'src/app/model/progetto';
import { ProgettoService } from 'src/app/services/progetto.service';
import { SoggettoCollettivoService } from '../../services/soggetto-collettivo.service';

@Component({
  selector: 'app-gestisci-candidatura-progetto',
  templateUrl: './gestisci-candidatura-progetto.component.html',
  styleUrls: ['./gestisci-candidatura-progetto.component.css']
})
export class GestisciCandidaturaProgettoComponent implements OnInit {

  progetto : Progetto = <Progetto>{};
  soggettoCandidatura : number = -1;

  constructor(
    private route : ActivatedRoute,
    private progettoService : ProgettoService,
    public soggettoService : SoggettoCollettivoService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.progettoService.find(params['idProgetto']).subscribe(progetto => {
        this.progetto = progetto;
      });
    });
  }

  submit() : void {
    console.log("Candidato per conto di " + this.soggettoCandidatura);
  }

}
