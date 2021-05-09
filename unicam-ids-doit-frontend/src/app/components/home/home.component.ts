import { Component, OnInit } from '@angular/core';
import { GenericService } from '../../services/generic.service';
import { Progetto } from '../../model/progetto';
import { ProgettoService } from '../../services/progetto.service';
import { Observable } from 'rxjs';
import { SoggettoCollettivo } from 'src/app/model/soggetto-collettivo';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private genericService : GenericService, public progettoService : ProgettoService) {}

  ngOnInit(): void {
    this.progettoService.reload();
  }

}
