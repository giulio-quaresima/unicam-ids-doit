import { AbstractEntity } from "./abstract-entity";
import { Progetto } from "./progetto";
import { Soggetto } from "./soggetto";

export interface Candidatura extends AbstractEntity {
    autopromozione : string;
    inviata : boolean;
    selezionata : boolean;
    incarico : String;
    soggetto : Soggetto;
    progetto : Progetto;

}
