import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

import { Progetto } from '../../model/progetto';
import { SoggettoCollettivo } from '../../model/soggetto-collettivo';
import { ProgettoService } from '../../services/progetto.service';
import { SoggettoCollettivoService } from '../../services/soggetto-collettivo.service';

@Component({
  selector: 'app-crea-progetto',
  templateUrl: './crea-progetto.component.html',
  styleUrls: ['./crea-progetto.component.css']
})
export class CreaProgettoComponent implements OnInit {

  soggettoCollettivoes : SoggettoCollettivo[] = [];
	
	creaProgettoForm = this.formBuilder.group({
		proponente : this.formBuilder.group({
      id : ['']
    }),
		titolo : [''],
    descrizione : [''],
    obiettivi : ['']
	});

  constructor(
    private formBuilder : FormBuilder, 
    private router : Router,
    private progettoService : ProgettoService,
    public soggettoService : SoggettoCollettivoService
    ) { }

    ngOnInit() : void {
      this.soggettoService.getSoggettiUtente("GESTIONE_PROGETTO").subscribe(soggettoCollettivoes => {
        this.soggettoCollettivoes = soggettoCollettivoes;
        if (soggettoCollettivoes.length == 1) {
          this.creaProgettoForm.get("owner.id")?.setValue(soggettoCollettivoes[0].id);
        }
      });
    }
    
    submit(progetto : Progetto) : void {
      console.log(progetto);
      this.progettoService.create(progetto).subscribe(response => {
        console.log(response);
        this.router.navigate(['/']);
     });
    }
  
}
