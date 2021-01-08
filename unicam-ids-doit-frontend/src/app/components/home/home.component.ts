import { Component, OnInit } from '@angular/core';
import { GenericService } from '../../services/generic.service'
import { Progetto, Progettoes } from '../../model/progetto';
import { ProgettoService } from '../../services/progetto.service';
import { Observable } from 'rxjs';
import { SoggettoCollettivo } from 'src/app/model/soggetto-collettivo';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  progettoes : Progettoes = <Progettoes>{};

  constructor(private genericService : GenericService, private progettoService : ProgettoService) {}

  ngOnInit(): void {
    this.progettoService.findAll().subscribe(progettoes => {
      for (let progetto of progettoes._embedded.progettoes) {
        progetto.ownerAsync = this.genericService.getAny(progetto._links["owner"].href as string);
      }
      this.progettoes = progettoes;
    });
  }

}
