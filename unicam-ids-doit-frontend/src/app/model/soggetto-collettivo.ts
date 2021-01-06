import {HateoasResource} from './hateoas-resource';

export interface SoggettoCollettivo extends HateoasResource {
	denominazione : string;
}

export interface SoggettoCollettivoes extends HateoasResource {
	_embedded : Embedded
}

interface Embedded {
    soggettoCollettivoes : SoggettoCollettivo[]
}
