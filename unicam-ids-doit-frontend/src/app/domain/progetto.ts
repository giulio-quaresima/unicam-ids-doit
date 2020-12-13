import {AbstractEntity} from './abstract-entity';

export interface Progetto extends AbstractEntity {
    titolo : string;
    descrizione : string;
}