import { environment } from "src/environments/environment";

export class AbstractService {
    protected hateoasApiBaseUrl = environment.api.baseUrl + "/hateoas";
    protected customApiBaseUrl = environment.api.baseUrl + "/custom";
    constructor() {}
}