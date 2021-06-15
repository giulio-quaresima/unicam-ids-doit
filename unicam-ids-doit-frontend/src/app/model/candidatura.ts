import { AbstractEntity } from "./abstract-entity";
import { Invito } from "./invito";
import { Progetto } from "./progetto";
import { Soggetto } from "./soggetto";
import { SoggettoCollettivo } from "./soggetto-collettivo";
import { SoggettoUtente } from "./soggetto-utente";

export interface Candidatura extends AbstractEntity {
    autopromozione : string;
    inviata : boolean;
    selezionata : boolean;
    incarico : String;
    soggetto : Soggetto;
    denominazioneCandidato : string;
    progetto : Progetto;
    inviti : Invito[];
    currentUserOwner : boolean;
    currentUserInvitato : boolean;
    soggettiUtenteSuggeritiPerInvito : SoggettoUtente[];
    soggettiCollettiviSuggeritiPerInvito : SoggettoCollettivo[];
}
