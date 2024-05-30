import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Customers } from '../customers';
import { CustomersService } from '../customers.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.css',
})
export class EditComponent {
  customerId!: number;
  customer!: Customers;
  form!: FormGroup;

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

    this.form = new FormGroup({
      customerAddress: new FormControl('', [Validators.required]),
      customerCode: new FormControl('', [Validators.required]),
      customerName: new FormControl('', [Validators.required]),
      customerPhone: new FormControl('', [Validators.required]),
      isActive: new FormControl('', [Validators.required]),
      pic: new FormControl('', [Validators.required]),
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    console.log(this.form.value);
    this.customerService
      .update(this.customerId, this.form.value)
      .subscribe((res: any) => {
        console.log('Post updated successfully!');
        this.router.navigateByUrl('customers/index');
      });
  }
}
