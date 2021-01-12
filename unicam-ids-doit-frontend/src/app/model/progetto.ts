import { Observable } from 'rxjs';
import { HateoasResource } from './hateoas-resource';
import { SoggettoCollettivo } from './soggetto-collettivo';

export interface Progetto extends HateoasResource {
    stato : string;
    titolo : string;
    descrizione : string;
    obiettivi : string;
    
    owner : Object;
    ownerAsync : Observable<SoggettoCollettivo>;
}

export interface Progettoes extends HateoasResource {
    _embedded : Embedded
}

interface Embedded {
    progettoes : Progetto[]
}
