import { Component, OnInit, Input } from '@angular/core';
import { Guest } from './guest-model';
import { faEye, faEdit } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { GuestsService } from './guests.service';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'guests-operations',
  templateUrl: './guests-operations.component.html',
  styleUrls: ['./guests-operations.component.css']
})
export class GuestsOperationsComponent implements OnInit {

  faEye = faEye;
  faEdit = faEdit;

  @Input()
  guest: Guest;

  constructor(private router: Router,
    private guestService: GuestsService) { }

  ngOnInit() {
  }

  onEdit(): void {
    console.log('About to edit guest');
    this.router.navigate(['/edit-guest', this.guest.id]);
  }

  onView(): void {
    console.log('About to view guest details');
    this.router.navigate(['/view-guest', this.guest.id]);
  }
}
