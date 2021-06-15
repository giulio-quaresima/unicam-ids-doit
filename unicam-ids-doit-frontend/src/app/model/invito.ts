import { AbstractEntity } from "./abstract-entity";
import { Candidatura } from "./candidatura";
import { Soggetto } from "./soggetto";

export interface Invito extends AbstractEntity {
    candidatura : Candidatura;
    invitato : Soggetto;
    accettazione : boolean;
    accettazioneNull : boolean;
    noteInvito : string;
    noteAccettazione : string;
}
