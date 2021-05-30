import { Component, OnInit } from '@angular/core';
import { ProgettoService } from '../../services/progetto.service';
import { SoggettoCollettivoService } from 'src/app/services/soggetto-collettivo.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    public progettoService : ProgettoService,
    public soggettoService : SoggettoCollettivoService) {}

  ngOnInit(): void {
    this.progettoService.reload();
  }

}
