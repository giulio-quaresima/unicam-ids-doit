import { AbstractEntity } from "./abstract-entity";
import { Progetto } from "./progetto";
import { SoggettoCollettivo } from "./soggetto-collettivo";

export interface Candidatura extends AbstractEntity {
    autopromozione : string;
    inviata : boolean;
    selezionata : boolean;
    incarico : String;
    soggetto : SoggettoCollettivo;
    progetto : Progetto;

}
