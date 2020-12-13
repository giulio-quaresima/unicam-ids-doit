import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { CreaProgettoComponent } from './crea-progetto/crea-progetto.component';



@NgModule({
  declarations: [CreaProgettoComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class ProponenteModule { }
