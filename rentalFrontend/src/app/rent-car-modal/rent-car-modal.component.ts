import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA, MatDialogContent, MatDialogTitle} from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Car } from '../Car';
import {MatButton} from '@angular/material/button';
import {CurrencyPipe} from '@angular/common';

@Component({
  selector: 'app-rent-car-modal',
  templateUrl: './rent-car-modal.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatDialogContent,
    MatDialogTitle,
    MatButton,
    CurrencyPipe
  ],
  styleUrls: ['./rent-car-modal.component.css']
})
export class RentCarModalComponent implements OnInit {
  car: Car;
  rentForm: FormGroup;

  private readonly API_URL: string = "http://127.0.0.1:31380/";

  constructor(
    private http: HttpClient,
    public dialogRef: MatDialogRef<RentCarModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder
  ) {
    this.car = data ? data.car : { plateNumber: '', brand: '', price: 0, rent: false, dates: [] };
    this.rentForm = this.fb.group({
      carId:  [this.car.id, Validators.required],
      plateNumber: [this.car.plateNumber, Validators.required],
      begin: [null, Validators.required],
      end: [null, Validators.required]
    });
  }

  ngOnInit(): void {

  }

  onSubmit(): void {
    if (this.rentForm.valid) {
      const formData = this.rentForm.value;
      const datesData = {
        car_id: formData.carId,
        begin: formData.begin,
        end: formData.end
      };

      console.log("dates", datesData);

      // Envoi de la requête PUT pour louer la voiture
      this.http.put(`${this.API_URL}rentalservice/cars/${formData.plateNumber}?rent=true`, datesData).subscribe(
        (response) => {
          console.log("Réponse", response);
          console.log(`Car rented: ${formData.plateNumber}`);
          this.dialogRef.close(this.car); // Fermer la modale après succès
        },
        (error) => {
          console.error('Error updating car status:', error);
        }
      );
    } else {
      console.log('Formulaire invalide');
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
