import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreaProgettoComponent } from './proponente/crea-progetto/crea-progetto.component';

const routes: Routes = [
  { path : "crea-progetto", component : CreaProgettoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
