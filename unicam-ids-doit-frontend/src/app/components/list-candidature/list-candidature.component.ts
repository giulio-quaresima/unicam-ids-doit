import { Component, OnInit } from '@angular/core';
import { CandidaturaService } from 'src/app/services/candidatura.service';

@Component({
  selector: 'app-list-candidature',
  templateUrl: './list-candidature.component.html',
  styleUrls: ['./list-candidature.component.css']
})
export class ListCandidatureComponent implements OnInit {

  constructor(public candidaturaService : CandidaturaService) { }

  ngOnInit(): void {
    this.candidaturaService.reload();
  }

}
