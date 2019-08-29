import { Injectable } from '@angular/core';
import { Guest } from './guest-model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class GuestsService {

  //private guestEndpoint: string = 'api/guests/guests.json';
  private guestEndpoint: string = '/api/guest';
  private guestTypesEndpoint: string = '/api/guest-type';
  private guestOfEndpoint: string = '/api/guest-of';

  constructor(private http: HttpClient) { }

  public getGuestTypes(): Observable<string[]> {
    return this.http.get<string[]>(this.guestTypesEndpoint);
  }

  public getGuestOf(): Observable<string[]> {
    return this.http.get<string[]>(this.guestOfEndpoint);
  }

  public getGuestById(id: number): Observable<Guest> {
    return this.http.get<Guest>(this.guestEndpoint + '/' + id);
  }

  public getAllGuests(): Observable<Guest[]> {
    return this.http.get<Guest[]>(this.guestEndpoint).pipe(
      tap((result) => console.log('Received response: ' + JSON.stringify(result))),
      catchError(this.handleError)
    );
  }

  public createGuest(guest: Guest): Observable<Guest> {
    return this.http.post<Guest>(this.guestEndpoint, guest);
  }

  public editGuest(guest: Guest): Observable<Guest> {
    return this.http.put<Guest>(this.guestEndpoint, guest);
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage: string;

    if(error.error instanceof ErrorEvent) {
      errorMessage = `An error occured ${error.error.message}`;
    } else {
      errorMessage = `Server returned status ${error.status}`
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
