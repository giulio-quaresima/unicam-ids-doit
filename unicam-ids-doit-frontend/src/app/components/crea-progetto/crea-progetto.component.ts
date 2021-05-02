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
		owner : [''],
		titolo : [''],
    descrizione : [''],
    obiettivi : ['']
	});

  constructor(
    private formBuilder : FormBuilder, 
    private router : Router,
    private progettoService : ProgettoService,
    private soggettoService : SoggettoCollettivoService
    ) { }

    ngOnInit() : void {
      this.soggettoService.getSoggettiUtente().subscribe(soggettoCollettivoes => this.soggettoCollettivoes = soggettoCollettivoes);
    }
    
    submit(progetto : Progetto) : void {
      this.progettoService.create(progetto).subscribe(response => {
        this.router.navigate(['/']);
     });
    }
  
}
