import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Order } from './order';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private apiURL = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/v1/api/orders')
      .pipe(catchError(this.errorHandler));
  }

  create(order: Order): Observable<any> {
    return this.httpClient
      .post(
        this.apiURL + '/v1/api/orders',
        JSON.stringify(order),
        this.httpOptions
      )

      .pipe(catchError(this.errorHandler));
  }

  find(orderId: number): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/v1/api/orders/' + orderId)

      .pipe(catchError(this.errorHandler));
  }

  update(orderId: number, order: Order): Observable<any> {
    return this.httpClient
      .put(
        this.apiURL + '/v1/api/orders/' + orderId,
        JSON.stringify(order),
        this.httpOptions
      )

      .pipe(catchError(this.errorHandler));
  }

  delete(orderId: number) {
    return this.httpClient
      .delete(this.apiURL + '/v1/api/orders/' + orderId, this.httpOptions)

      .pipe(catchError(this.errorHandler));
  }

  getReport(format: string): Observable<Blob> {
    return this.httpClient
      .get(`${this.apiURL}/v1/api/orders/report/${format}`, {
        responseType: 'blob',
      })
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
