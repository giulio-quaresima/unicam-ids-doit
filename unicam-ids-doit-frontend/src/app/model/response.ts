export interface Response<T> {
    status : string;
    data : T;
    errorMessage : string;
}