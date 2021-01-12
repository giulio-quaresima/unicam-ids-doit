import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Progetto } from '../../model/progetto';
import { SoggettoCollettivo, SoggettoCollettivoes } from '../../model/soggetto-collettivo';
import { ProgettoService } from '../../services/progetto.service';
import { SoggettoCollettivoService } from '../../services/soggetto-collettivo.service';

@Component({
  selector: 'app-modifica-progetto',
  templateUrl: './modifica-progetto.component.html',
  styleUrls: ['./modifica-progetto.component.css']
})
export class ModificaProgettoComponent implements OnInit {

  id : number = -1;
  soggettoCollettivoes : SoggettoCollettivoes = <SoggettoCollettivoes>{};
	
	creaProgettoForm = this.formBuilder.group({
		owner : [''],
		titolo : [''],
    descrizione : [''],
    obiettivi : [''],
    tags : ['']
	});

  constructor(
    private formBuilder : FormBuilder, 
    private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.id = params['id']);
  }

}
