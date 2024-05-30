import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Item } from './item';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private apiURL = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient) {}

  getAll(): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/v1/api/items')
      .pipe(catchError(this.errorHandler));
  }

  create(item: Item): Observable<any> {
    return this.httpClient
      .post(
        this.apiURL + '/v1/api/items',
        JSON.stringify(item),
        this.httpOptions
      )

      .pipe(catchError(this.errorHandler));
  }

  find(itemId: number): Observable<any> {
    return this.httpClient
      .get(this.apiURL + '/v1/api/items/' + itemId)

      .pipe(catchError(this.errorHandler));
  }

  update(itemId: number, item: Item): Observable<any> {
    return this.httpClient
      .put(
        this.apiURL + '/v1/api/items/' + itemId,
        JSON.stringify(item),
        this.httpOptions
      )

      .pipe(catchError(this.errorHandler));
  }

  delete(itemId: number) {
    return this.httpClient
      .delete(this.apiURL + '/v1/api/items/' + itemId, this.httpOptions)

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
