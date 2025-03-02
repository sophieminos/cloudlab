import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CarListComponent } from './car-list/car-list.component';
import {RentCarModalComponent} from './rent-car-modal/rent-car-modal.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CarListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'rentalFrontend';
}
