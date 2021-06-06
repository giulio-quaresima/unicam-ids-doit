import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Candidatura } from 'src/app/model/candidatura';
import { CandidaturaService } from 'src/app/services/candidatura.service';

@Component({
  selector: 'app-gestisci-candidatura',
  templateUrl: './gestisci-candidatura.component.html',
  styleUrls: ['./gestisci-candidatura.component.css']
})
export class GestisciCandidaturaComponent implements OnInit {

  candidatura : Candidatura = <Candidatura>{}

  constructor(private route : ActivatedRoute, 
    private candidaturaService : CandidaturaService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.candidaturaService.get(params['id']).subscribe(candidatura => {
        this.candidatura = candidatura;
      });
    });
  }

}
