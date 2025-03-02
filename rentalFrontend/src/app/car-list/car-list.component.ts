import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import {RentCarModalComponent} from '../rent-car-modal/rent-car-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { Car } from '../Car'
@Component({
  selector: 'app-car-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './car-list.component.html',
  styleUrl: './car-list.component.css'
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];
  showDetails: boolean = false;

  private readonly API_URL: string = "http://127.0.0.1:31380/";

  constructor(private http: HttpClient, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.cars = [
      { id: 1, plateNumber: 'ABCD', brand: 'Citroen', price: 10000.0, rent: false, dates: [] },
      { id: 2, plateNumber: 'EFGH', brand: 'Renault', price: 11000.0, rent: false, dates: [] },
      { id: 3, plateNumber: 'IJKL', brand: 'Peugeot', price: 12000.0, rent: false, dates: [] }
    ];
    this.http.get<Car[]>(`${this.API_URL}rentalservice/cars/`).subscribe(
      (data) => (this.cars = data),
      (error) => console.error('Error fetching cars:', error)
    );
  }

  toggleRent(car: Car): void {
    const rentStatus = !car.rent;
    if(rentStatus){
      const dialogRef = this.dialog.open(RentCarModalComponent, {
        width: '300px',
        data: { car: car }  // Passer l'objet voiture à la modale
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          console.log('Location de voiture:', result);
        }
      });
    } else {
      this.http.put(`${this.API_URL}rentalservice/cars/${car.plateNumber}?rent=false`, {}).subscribe(
        () => {
          console.log(`Car returned: ${car.plateNumber}`);
        },
        (error) => console.error('Error updating car status:', error)
      );
    }
    car.rent = rentStatus;
  }

  toggleDetails(): void {
    this.showDetails = !this.showDetails;
  }
}
