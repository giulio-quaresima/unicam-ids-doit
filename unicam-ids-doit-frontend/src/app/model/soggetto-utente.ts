import { AbstractEntity } from "./abstract-entity";

export interface SoggettoUtente extends AbstractEntity {
    cognome : string;
    nome : string;
    account : Account;
}

export interface Account {
    username : string;
}