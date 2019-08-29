import { Component, OnInit } from '@angular/core';
import { Guest } from './guest-model';
import { GuestsService } from './guests.service';

@Component({
  selector: 'wedding-guests',
  templateUrl: './guests.component.html',
  styleUrls: ['./guests.component.css']
})
export class GuestsComponent implements OnInit {

  guests: Guest[];
  filteredGuests: Guest[];
  _filter: string;

  constructor(private guestsService: GuestsService) { }

  ngOnInit() {
    this.populateGuests();
  }

  get filter(): string {
    return this._filter;
  }

  set filter(value: string) {
    this._filter = value;
    let filterBy = this._filter.toLocaleLowerCase();
    this.filterGuests(filterBy);
  }

  private filterGuests(filterBy: string) {
    if(this._filter) {
      this.filteredGuests = this.guests.filter(
        (guest) => {
          if (guest.fullName.toLocaleLowerCase().indexOf(filterBy) != -1) {
            return true;
          }
        }
      );        
    } else {
      this.filteredGuests = this.guests;
    }
  }

  private populateGuests(): void {
    this.guestsService.getAllGuests().subscribe(
      (data) => {
        this.guests = data;
        this.filteredGuests = this.guests;
        console.log("Guest assigned")
      },
      error => console.log(error)
    );
  }
}