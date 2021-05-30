import { Soggetto } from "./soggetto";

export interface SoggettoUtente extends Soggetto {
    cognome : string;
    nome : string;
    account : Account;
}

export interface Account {
    username : string;
}