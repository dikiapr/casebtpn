import { Component } from '@angular/core';
import { Customers } from '../customers';
import { CustomersService } from '../customers.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view',
  standalone: true,
  imports: [],
  templateUrl: './view.component.html',
  styleUrl: './view.component.css',
})
export class ViewComponent {
  customerId!: number;
  customer!: Customers;

  constructor(
    public customerService: CustomersService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['customerId'];
    this.customerService.find(this.customerId).subscribe((data: Customers) => {
      this.customer = data;
    });
  }
}
