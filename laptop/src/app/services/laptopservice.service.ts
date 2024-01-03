import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Lapidetails } from '../laptopdetails/laptopdetails.component';

@Injectable({
  providedIn: 'root'
})
export class LaptopserviceService {

  constructor( private http:HttpClient) { }

  retrivealllaptops(){
    return this.http.get<Lapidetails[]>(`http://localhost:8080/api/laptops/all`);
    // console.log("this is controller");
  }
  deletelaptop(id:any){
    return this.http.delete(`http://localhost:8080/api/laptops/delete/${id}`);
    // console.log("this is controller");
  }

  updatelaptop(id:any,laptop:Lapidetails){
    return this.http.put<Lapidetails>(`http://localhost:8080/api/laptops/update/${id}`,laptop);
    // console.log("this is controller");
  }

  addlaptop(laptop:Lapidetails){
    return this.http.post<Lapidetails>(`http://localhost:8080/api/laptops/add`,laptop);
    // console.log("this is controller");
  }

  retrivelaptopByName(name:String){
    return this.http.get<Lapidetails>(`http://localhost:8080/api/laptops/byname/${name}`);
    // console.log("this is controller");
  }

  retrivelaptopByBrand(brand:String){
    return this.http.get<Lapidetails[]>(`http://localhost:8080/api/laptops/bybrand/${brand}`);
    // console.log("this is controller");
  }

  retrivelaptopByPrice(price:number){
    return this.http.get<Lapidetails>(`http://localhost:8080/api/laptops/byprice/${price}`);
    // console.log("this is controller");
  }


}


