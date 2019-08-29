import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'wedding-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  constructor() {}

  ngOnInit() {
    console.log(`${AppComponent.name} created`);
  }

  ngOnDestroy() {
    console.log(`Deleting ${AppComponent.name}`);
  }
}
