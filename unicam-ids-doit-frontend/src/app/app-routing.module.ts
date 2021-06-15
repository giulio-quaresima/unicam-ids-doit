import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/http/not-found/not-found.component';
import { CreaProgettoComponent } from './components/crea-progetto/crea-progetto.component';
import { ModificaProgettoComponent } from './components/modifica-progetto/modifica-progetto.component';
import { GestisciCandidaturaComponent } from './components/gestisci-candidatura/gestisci-candidatura.component';
import { CreaCandidaturaProgettoComponent } from './components/crea-candidatura-progetto/crea-candidatura-progetto.component';
import { ListCandidatureComponent } from './components/list-candidature/list-candidature.component';
import { ListInvitiComponent } from './components/list-inviti/list-inviti.component';
import { GestisciInvitoComponent } from './components/gestisci-invito/gestisci-invito.component';

const routes: Routes = [
  { data : {name : "home"}, path : "", component : HomeComponent},
  { data : {name : "crea-progetto"}, path : "crea-progetto", component : CreaProgettoComponent},
  { data : {name : "modifica-progetto"}, path : "modifica-progetto/:id", component : ModificaProgettoComponent},
  { data : {name : "crea-candidatura-progetto"}, path : "crea-candidatura-progetto/:idProgetto", component : CreaCandidaturaProgettoComponent},
  { data : {name : "list-candidature"}, path : "list-candidature", component : ListCandidatureComponent},
  { data : {name : "list-inviti"}, path : "list-inviti", component : ListInvitiComponent},
  { data : {name : "gestisci-invito"}, path : "gestisci-invito/:id", component : GestisciInvitoComponent},
  { data : {name : "gestisci-candidatura"}, path : "gestisci-candidatura/:id", component : GestisciCandidaturaComponent},
  { data : {name : "404"}, path : "**", component : NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
