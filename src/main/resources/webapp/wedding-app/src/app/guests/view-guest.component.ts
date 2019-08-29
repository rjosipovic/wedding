import { Component, OnInit, OnDestroy } from '@angular/core';
import { Guest } from './guest-model';
import { Route, ActivatedRoute, Router } from '@angular/router';
import { GuestsService } from './guests.service';

@Component({
  selector: 'view-guest',
  templateUrl: './view-guest.component.html',
  styleUrls: ['./view-guest.component.css']
})
export class ViewGuestComponent implements OnInit, OnDestroy {

  id: number;
  guest: Guest;
  private sub: any;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private guestService: GuestsService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(
      params => {
        this.id = +params['id'];
        this.guestService.getGuestById(this.id).subscribe(
          data => this.guest = data
        );
      }
    );
  }

  onEdit(): void {
    console.log('Redirecting to edit view');
    this.router.navigate(['/edit-guest', this.guest.id]);
  }

  onBackToGuests(): void {
    console.log('Redirecting to guests view');
    this.router.navigate(['/guests']);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
