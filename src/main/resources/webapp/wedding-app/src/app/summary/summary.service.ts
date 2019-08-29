import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Summary } from './summary-model';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SummaryService {

  private summaryEndpoing = '/api/summary/';

  constructor(private http: HttpClient) { }


  getWeddingSummary(): Observable<Summary> {
    return this.http.get<Summary>(this.summaryEndpoing).pipe(
      tap((data) => JSON.stringify(data)),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {

    let errorMessage: string;

    if(error instanceof ErrorEvent) {
      errorMessage = `An error occured ${error.error.message}`;
    } else {
      errorMessage = `Server returned error status ${error.status}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
