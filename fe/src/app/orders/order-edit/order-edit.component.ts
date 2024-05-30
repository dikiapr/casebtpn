import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Order } from '../order';
import { OrderService } from '../order.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-order-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.css'],
})
export class OrderEditComponent implements OnInit {
  orderId!: number;
  order!: Order;
  form!: FormGroup;

  constructor(
    public orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['orderId'];
    this.orderService.find(this.orderId).subscribe((data: Order) => {
      this.order = data;

      // Initialize form with all fields including orderCode
      this.form = new FormGroup({
        orderCode: new FormControl(this.order.orderCode),
        customerId: new FormControl(this.order.customerId, [
          Validators.required,
        ]),
        itemId: new FormControl(this.order.itemId, [Validators.required]),
        quantity: new FormControl(this.order.quantity, [Validators.required]),
      });
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    console.log(this.form.value);
    this.orderService
      .update(this.orderId, this.form.value)
      .subscribe((res: any) => {
        console.log('Order updated successfully!');
        this.router.navigateByUrl('orders/index');
      });
  }
}
