import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Progetto } from '../../domain/progetto';
import { SoggettoCollettivo } from '../../domain/soggetto-collettivo';
import { ProgettoService } from '../../progetto.service';
import { SoggettoCollettivoService } from '../../soggetto-collettivo.service';

@Component({
  selector: 'app-crea-progetto',
  templateUrl: './crea-progetto.component.html',
  styleUrls: ['./crea-progetto.component.css']
})
export class CreaProgettoComponent implements OnInit {

  soggettiUtente : SoggettoCollettivo[] = [];
	
	creaProgettoForm = this.formBuilder.group({
		soggettoCollettivo : [''],
		titolo : [''],
		descrizione : ['']
	});

  constructor(
    private formBuilder : FormBuilder, 
    private pgService : ProgettoService,
    private sgService : SoggettoCollettivoService
    ) { }

  submit(progetto : Progetto) : void {
    alert(progetto.titolo);
    this.pgService.create(progetto).subscribe((response)=>{
      console.log(response);
   });
  }

  ngOnInit() : void {
    this.sgService.getSoggettiUtente().subscribe(soggettiUtente => this.soggettiUtente = soggettiUtente);
  }
  
}
