import { HateoasResource } from './hateoas-resource';
import { SoggettoCollettivo } from './soggetto-collettivo';

export interface Progetto extends HateoasResource {
    stato : string;
    titolo : string;
    descrizione : string;
    owner : Object;
}

export interface Progettoes extends HateoasResource {
    _embedded : Embedded
}

interface Embedded {
    progettoes : Progetto[]
}
