import { AbstractEntity } from "./abstract-entity";
import { Competenza } from "./competenza";
import { Invito } from "./invito";

export interface Soggetto extends AbstractEntity {
    type : string;
    denominazione : string;
    invitiRicevuti : Invito[];
    invitiRicevutiNonAncoraAccettati : Invito[];
    competenzas : Competenza[];
}
