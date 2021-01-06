import { Component, OnInit } from '@angular/core';
import { Progetto, Progettoes } from '../../model/progetto';
import { ProgettoService } from '../../services/progetto.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  progettoes : Progettoes = <Progettoes>{};

  constructor(private progettoService : ProgettoService) {}

  ngOnInit(): void {
    this.progettoService.findAll().subscribe(progettoes => this.progettoes = progettoes);
  }

}
