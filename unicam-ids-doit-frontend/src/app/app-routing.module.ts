import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/http/not-found/not-found.component';
import { CreaProgettoComponent } from './components/crea-progetto/crea-progetto.component';
import { ModificaProgettoComponent } from './components/modifica-progetto/modifica-progetto.component';

const routes: Routes = [
  { path : "", component : HomeComponent},
  { path : "crea-progetto", component : CreaProgettoComponent},
  { path : "modifica-progetto/:url", component : ModificaProgettoComponent},
  { path : "**", component : NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
