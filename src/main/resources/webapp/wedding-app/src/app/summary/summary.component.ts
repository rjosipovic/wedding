import { Component, OnInit } from '@angular/core';
import { SummaryService } from './summary.service';
import { Summary } from './summary-model';

@Component({
  selector: 'wedding-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {

  summary: Summary;

  constructor(private summaryService: SummaryService) { }

  ngOnInit() {
    this.summaryService.getWeddingSummary().subscribe(
      (data) => this.summary = data,
    );    
  }
}
