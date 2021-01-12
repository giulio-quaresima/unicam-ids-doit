import { Observable } from 'rxjs';
import { HateoasResource } from './hateoas-resource';

export interface Competenza extends HateoasResource {
    tag : string;
}

export interface Competenzas extends HateoasResource {
    _embedded : Embedded
}

interface Embedded {
    Competenzas : Competenza[]
}
