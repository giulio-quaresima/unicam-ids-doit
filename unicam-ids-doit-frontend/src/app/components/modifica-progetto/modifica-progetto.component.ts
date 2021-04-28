import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Progetto } from '../../model/progetto';
import { GenericService } from '../../services/generic.service'
import { CompetenzeService } from '../../services/competenze.service';
import { SoggettoCollettivo, SoggettoCollettivoes } from '../../model/soggetto-collettivo';
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

  progettoUrl : string = '';
  progetto : Progetto = <Progetto>{};
  soggettoCollettivoes : SoggettoCollettivoes = <SoggettoCollettivoes>{};
  formModel : FormModel = new FormModel;
  tags : string[] = [];
  newTag : string = '';
	
  constructor(
    private route : ActivatedRoute,
    private genericService : GenericService,
    private pgService : ProgettoService,
    private cService : CompetenzeService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.progettoUrl = params['url'];
      this.genericService.getAny(this.progettoUrl).subscribe(progetto => {
        this.progetto = progetto;
      });
    });
  }

  addTag() : void {
    this.progetto.competenzeTags.push(this.newTag as string);
    this.newTag = '';
  }

  delTag(index : number) : void {
    this.progetto.competenzeTags.splice(index,1);
  }

  submit() : void {
    		/* 
		 * FIXME Wait for having a better understand of the right way
		 * to interact between Angular and Spring Data Rest 
		 */

    console.log(this.progetto);
    this.pgService.save(this.progetto).subscribe(result => {
      console.log(result);
    });
  }

}

class FormModel {
  tags : string[] = [];
  newTag : string = '';
}
