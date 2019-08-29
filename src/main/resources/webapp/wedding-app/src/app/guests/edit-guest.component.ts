import { Component, OnInit } from '@angular/core';
import { GuestsService } from './guests.service';
import { Guest } from './guest-model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-guest',
  templateUrl: './edit-guest.component.html',
  styleUrls: ['./edit-guest.component.css']
})
export class EditGuestComponent implements OnInit {
  
  guestTypes: string[];
  guestOf: string[];

  id: number;
  guest: Guest;
  private sub: any;

  constructor(private guestService: GuestsService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(
      params => {
        this.id = +params['id'];
        this.guestService.getGuestById(this.id).subscribe(
          data => this.guest = data
        );
      }
    );

    this.guestService.getGuestTypes().subscribe(
      data => this.guestTypes = data
    );

    this.guestService.getGuestOf().subscribe(
      data => this.guestOf = data
    );
  }

  onEditGuestSubmit(): void {
    console.log('About to save guest: '+ JSON.stringify(this.guest));
    this.guestService.editGuest(this.guest).subscribe(
      data => {
        this.guest = data;
        this.router.navigate(['/view-guest', this.guest.id]);
      }
    );
  }
}
