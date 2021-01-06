import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-modifica-progetto',
  templateUrl: './modifica-progetto.component.html',
  styleUrls: ['./modifica-progetto.component.css']
})
export class ModificaProgettoComponent implements OnInit {

  id : number = -1;

  constructor(private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.id = params['id']);
  }

}
