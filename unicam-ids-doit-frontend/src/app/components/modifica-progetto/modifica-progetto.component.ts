import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Progetto } from '../../model/progetto';
import { GenericService } from '../../services/generic.service'
import { CompetenzeService } from '../../services/competenze.service';
import { SoggettoCollettivo } from '../../model/soggetto-collettivo';
import { ProgettoService } from '../../services/progetto.service';
import { SoggettoCollettivoService } from '../../services/soggetto-collettivo.service';
import { Competenza } from 'src/app/model/competenza';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-modifica-progetto',
  templateUrl: './modifica-progetto.component.html',
  styleUrls: ['./modifica-progetto.component.css']
})
export class ModificaProgettoComponent implements OnInit {

  progetto : Progetto = <Progetto>{};
  soggettoCollettivoes : SoggettoCollettivo[] = [];
  newTag : string = '';
	
  constructor(
    private route : ActivatedRoute,
    private genericService : GenericService,
    private progettoService : ProgettoService,
    private cService : CompetenzeService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.progettoService.find(params['id']).subscribe(progetto => {
        this.progetto = progetto;
      });
    });
  }

  addTag() : void {
    this.progettoService.addTag(this.progetto, this.newTag).subscribe(progetto => {
      this.progetto = progetto;
    });
    this.newTag = '';
  }

  delTag(tag : string) : void {
    this.progettoService.delTag(this.progetto, tag).subscribe(progetto => {
      this.progetto = progetto;
    });
  }

  submit() : void {
    console.log(this.progetto);
    this.progettoService.save(this.progetto).subscribe(result => {
      console.log(result);
    });
  }

}
