import { SoggettoUtente } from "./soggetto-utente";

export interface AuthStatus {
    authenticated : boolean;
    utente : SoggettoUtente;
}