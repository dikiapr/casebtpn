import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const apiUrl = 'http://localhost:8080/v1/api/orders/report';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  constructor(private http: HttpClient) {}

  summarize(format: string, options?: any): Observable<any> {
    return this.http.get(`${apiUrl}/${format}`, options);
  }
}
