import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { GuestsComponent } from './guests/guests.component';
import { GuestsSettingsComponent } from './guests/guests-settings.component';
import { HttpClientModule } from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SummaryComponent } from './summary/summary.component';
import { AddGuestComponent } from './guests/add-guest.component';
import { GuestsOperationsComponent } from './guests/guests-operations.component';
import { ViewGuestComponent } from './guests/view-guest.component';
import { RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { EditGuestComponent } from './guests/edit-guest.component';

@NgModule({
  declarations: [
    AppComponent,
    GuestsComponent,
    GuestsSettingsComponent,
    SummaryComponent,
    AddGuestComponent,
    GuestsOperationsComponent,
    ViewGuestComponent,
    WelcomeComponent,
    EditGuestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
    RouterModule.forRoot([
      { path: 'guests', component: GuestsComponent },
      { path: 'add-guest', component: AddGuestComponent },
      { path: 'view-guest/:id', component: ViewGuestComponent },
      { path: 'edit-guest/:id', component: EditGuestComponent },
      { path: 'summary', component: SummaryComponent },
      { path: 'welcome', component: WelcomeComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', redirectTo: 'welcome', pathMatch: 'full'}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
