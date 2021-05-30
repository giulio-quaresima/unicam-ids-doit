import { AbstractEntity } from "./abstract-entity";
import { Competenza } from "./competenza";
import { SoggettoCollettivo } from "./soggetto-collettivo";

export interface Progetto extends AbstractEntity {
    stato : string;
    titolo : string;
    descrizione : string;
    obiettivi : string;

    competenzas : Competenza[];

    proponente : SoggettoCollettivo;
}

