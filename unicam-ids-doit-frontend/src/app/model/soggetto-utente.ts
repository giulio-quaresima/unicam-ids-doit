import { Competenza } from "./competenza";
import { Soggetto } from "./soggetto";

export interface SoggettoUtente extends Soggetto {
    cognome : string;
    nome : string;
    account : Account;
    competenzas : Competenza[];
}

export interface Account {
    username : string;
}