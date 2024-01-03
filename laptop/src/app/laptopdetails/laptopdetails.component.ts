import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LaptopserviceService } from '../services/laptopservice.service';
import { Subscription } from 'rxjs';

export class Lapidetails{
  constructor(
    public id: number,
    public name: string,
    public price: number,
    public brand: String,
    public storage:number,
    public ram:number,
    public processor:String
  ){}

}

@Component({
  selector: 'app-laptopdetails',
  templateUrl: './laptopdetails.component.html',
  styleUrl: './laptopdetails.component.css'
})
export class LaptopdetailsComponent implements OnInit {

  laptops:Lapidetails[]=[];
  newlaptop: Lapidetails = new Lapidetails(0, '', 0, '', 0, 0, '');
  // lap:any;
  editedLaptops: { [id: number]: Lapidetails } = {};
  msg: string = '';
  private subscription: Subscription| undefined;

  constructor(private laptopservice: LaptopserviceService,private cdr: ChangeDetectorRef,
    private router: Router) {}


  ngOnInit(){

    this.refresherlaptop()

  }

  deleteLaptop(id: number) {
    this.laptopservice.deletelaptop(id)
      .subscribe(
        data => {
          console.log(data);
          this.refresherlaptop();
        },
        error => console.log(error));
  }


  refresherlaptop() {
    this.subscription = this.laptopservice.retrivealllaptops().subscribe(
      (response: Lapidetails[]) => {
        console.log(response);
        this.laptops = response;

        this.cdr.detectChanges();
      },
      (error) => {
        console.error('Error retrieving laptops:', error);
        this.msg = 'Failed to retrieve laptop';
      }
    );
  }

  addLaptop() {
    console.log('Adding laptop. Current newlaptop:', this.newlaptop);
    const newlaptop = new Lapidetails(
      this.newlaptop.id,
      this.newlaptop.name,
      this.newlaptop.price,
      this.newlaptop.brand,
      this.newlaptop.storage,
      this.newlaptop.ram,
      this.newlaptop.processor
    );
  
    // Log the values before making the service call
    console.log('Adding laptop. Actual newlaptop object:', newlaptop);
  
    // Call the service to add the new laptop
    this.laptopservice.addlaptop(newlaptop).subscribe(
      (data: Lapidetails) => {
        console.log('Laptop added successfully. Response:', data);
        
        // Refresh the laptop list only after the addition is successful
        this.refresherlaptop();
      },
      (error) => {
        console.error('Error adding laptop:', error);
      }
    );
  }
  
  updateLaptop(id: number, updatedLaptop: Lapidetails) {
    this.laptopservice.updatelaptop(id, updatedLaptop).subscribe(
      (data: Lapidetails) => {
        console.log('Laptop updated successfully. Response:', data);
        this.refresherlaptop();
      },
      (error) => {
        console.error('Error updating laptop:', error);
      }
    );
  }

  startEditing(lap: Lapidetails) {
    // Set the laptop to be edited in the editedLaptops object
    this.editedLaptops[lap.id] = { ...lap };
  }

  saveChanges(id: number) {
    if (this.editedLaptops[id]) {
      const updatedLaptop = { ...this.editedLaptops[id] };
      this.updateLaptop(id, updatedLaptop);
      // Clear the editing state for this laptop
      delete this.editedLaptops[id];
    }
  }

  cancelEditing(id: number) {
    // Clear the editing state for this laptop
    delete this.editedLaptops[id];
  }

  searchByName(name: string) {
    this.subscription = this.laptopservice.retrivelaptopByName(name).subscribe(
      (data: Lapidetails) => {
        console.log('Laptop retrieved successfully. Response:', data);
        this.laptops = [data]; // Update the laptops array with the retrieved laptop
      },
      (error) => {
        console.error('Error retrieving laptop by name:', error);
        this.msg = 'Failed to retrieve laptop';
      }
    );
  }
  searchByPrice(price: number) {
    this.subscription = this.laptopservice.retrivelaptopByPrice(price).subscribe(
      (data: Lapidetails) => {
        console.log('Laptop retrieved successfully. Response:', data);
        this.laptops = [data]; // Update the laptops array with the retrieved laptop
      },
      (error) => {
        console.error('Error retrieving laptop by name:', error);
        this.msg = 'Failed to retrieve laptop';
      }
    );
  }

  searchByBrand(brand: string) {
    this.subscription = this.laptopservice.retrivelaptopByBrand(brand).subscribe(
      (data: Lapidetails[]) => {
        console.log('Laptops retrieved successfully. Response:', data);
        this.laptops = data; // Update the laptops array with the retrieved laptops
      },
      (error) => {
        console.error('Error retrieving laptops by brand:', error);
        this.msg = 'Failed to retrieve laptops';
      }
    );
  }
  

}



