import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CustomersService } from '../customers.service';
import { Customers } from '../customers';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './index.component.html',
  styleUrl: './index.component.css',
})
export class IndexComponent {
  customerList: Customers[] = [];
  /*------------------------------------------
  --------------------------------------------
  Created constructor
  --------------------------------------------
  --------------------------------------------*/
  constructor(public customersService: CustomersService) {}

  /**
   * Write code on Method
   *
   * @return response()
   */
  ngOnInit(): void {
    this.customersService.getAll().subscribe((data: Customers[]) => {
      this.customerList = data;
      console.log(this.customerList);
    });
  }
  /**
   * Write code on Method
   *
   * @return response()
   */
  deleteCustomer(customerId: number) {
    this.customersService.delete(customerId).subscribe((res) => {
      this.customerList = this.customerList.filter(
        (item) => item.customerId !== customerId
      );
      console.log('Post Deleted Successfully!');
    });
  }

  getStatusLabel(isActive: boolean): string {
    return isActive ? 'Active' : 'Inactive';
  }
}
