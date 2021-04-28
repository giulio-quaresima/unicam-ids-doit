export interface HateoasResource {
	id : number;
	_links : Links;
}

export interface Links {
	[key : string] : Resource
}

export interface Resource {
	href : string;
	templated : boolean;
}

