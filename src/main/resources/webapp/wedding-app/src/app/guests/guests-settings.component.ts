import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { faPlusCircle, faGlassCheers, faBus, faChurch, faBed, faUtensils, faUsers, faEye, faEdit } from '@fortawesome/free-solid-svg-icons';
import { Guest } from './guest-model';

@Component({
  selector: 'guests-settings',
  templateUrl: './guests-settings.component.html',
  styleUrls: ['./guests-settings.component.css']
})
export class GuestsSettingsComponent implements OnInit, OnChanges {

  faPlusCircle = faPlusCircle;
  faGlassCheers = faGlassCheers;
  faBus = faBus;
  faChurch = faChurch;
  faBed = faBed;
  faUtensils = faUtensils;
  faUsers = faUsers;
  
  @Input()
  guest: Guest;
  
  constructor() { }

  ngOnInit() {}

  ngOnChanges() {
  }
}
