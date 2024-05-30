import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Customers } from './customers';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CustomersService {
  private apiURL = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/v1/api/customers')
      .pipe(catchError(this.errorHandler));
  }

  create(customers: Customers): Observable<any> {
    return this.httpClient
      .post(
        this.apiURL + '/v1/api/customers',
        JSON.stringify(customers),
        this.httpOptions
      )

      .pipe(catchError(this.errorHandler));
  }

  find(customerId: number): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/v1/api/customers/' + customerId)

      .pipe(catchError(this.errorHandler));
  }

  update(customerId: number, customers: Customers): Observable<any> {
    return this.httpClient
      .put(
        this.apiURL + '/v1/api/customers/' + customerId,
        JSON.stringify(customers),
        this.httpOptions
      )

      .pipe(catchError(this.errorHandler));
  }

  delete(customerId: number) {
    return this.httpClient
      .delete(this.apiURL + '/v1/api/customers/' + customerId, this.httpOptions)

      .pipe(catchError(this.errorHandler));
  }

  errorHandler(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
