import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/http/not-found/not-found.component';
import { CreaProgettoComponent } from './components/crea-progetto/crea-progetto.component';
import { ModificaProgettoComponent } from './components/modifica-progetto/modifica-progetto.component';
import { GestisciCandidaturaComponent } from './components/gestisci-candidatura/gestisci-candidatura.component';
import { CreaCandidaturaProgettoComponent } from './components/crea-candidatura-progetto/crea-candidatura-progetto.component';
import { ListCandidatureComponent } from './components/list-candidature/list-candidature.component';

const routes: Routes = [
  { path : "", component : HomeComponent},
  { path : "crea-progetto", component : CreaProgettoComponent},
  { path : "modifica-progetto/:id", component : ModificaProgettoComponent},
  { path : "crea-candidatura-progetto/:idProgetto", component : CreaCandidaturaProgettoComponent},
  { path : "list-candidature", component : ListCandidatureComponent},
  { path : "gestisci-candidatura/:id", component : GestisciCandidaturaComponent},
  { path : "**", component : NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
