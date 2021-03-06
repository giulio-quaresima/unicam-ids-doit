export interface HateoasResource {
	_links : Links;
}

export interface Links {
	[key : string] : Resource
}

export interface Resource {
	href : string;
	templated : boolean;
}

