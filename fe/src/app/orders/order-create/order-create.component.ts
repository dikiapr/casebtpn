import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './order-create.component.html',
  styleUrl: './order-create.component.css',
})
export class OrderCreateComponent {
  form!: FormGroup;

  constructor(public orderService: OrderService, private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      orderCode: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required]),
      customerId: new FormControl('', [Validators.required]),
      itemId: new FormControl('', [Validators.required]),
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    console.log(this.form.value);
    this.orderService.create(this.form.value).subscribe((res: any) => {
      console.log('Order created successfully!');
      this.router.navigateByUrl('orders/index');
    });
  }
}
