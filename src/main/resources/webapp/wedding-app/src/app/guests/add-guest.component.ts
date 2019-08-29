import { Component, OnInit } from '@angular/core';
import { Guest } from './guest-model';
import { NgForm } from '@angular/forms';
import { GuestsService } from './guests.service';
import { Router } from '@angular/router';

@Component({
  selector: 'wedding-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.css']
})
export class AddGuestComponent implements OnInit {

  guestTypes: string[];
  guestOf: string[];

  guest: Guest = {
    id: null,
    fullName: null,
    type: "REGULAR",
    guestOf: "GROOM",
    arrivalConfirmed: true,
    presentAtReception: false,
    travelingWithBus: false,
    presentAtWedding: true,
    presentAtDinner: true,
    sleeping: false,
    presentAtSundayLunch: false
  };

  constructor(private guestService: GuestsService,
    private router: Router) { }

  ngOnInit() {
    this.guestService.getGuestTypes().subscribe(
      data => this.guestTypes = data
    );

    this.guestService.getGuestOf().subscribe(
      data => this.guestOf = data
    );
  }

  onAddGuestSubmit(addGuestForm: NgForm): void {
    this.guestService.createGuest(this.guest).subscribe(
      data => {
        this.guest = data;
        this.router.navigate(['/guests']);
      }
    );
  }
}
