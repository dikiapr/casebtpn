import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Order } from '../order';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order-index',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './order-index.component.html',
  styleUrl: './order-index.component.css',
})
export class OrderIndexComponent {
  orderList: Order[] = [];

  constructor(public orderService: OrderService) {}

  ngOnInit(): void {
    this.orderService.getAll().subscribe((data: Order[]) => {
      this.orderList = data;
      console.log(this.orderList);
    });
  }

  deleteOrder(orderId: number) {
    this.orderService.delete(orderId).subscribe((res) => {
      this.orderList = this.orderList.filter(
        (order) => order.orderId !== orderId
      );
      console.log('Post Deleted Successfully!');
    });
  }
}
