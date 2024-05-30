import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-item-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './item-edit.component.html',
  styleUrl: './item-edit.component.css',
})
export class ItemEditComponent {
  itemId!: number;
  item!: Item;
  form!: FormGroup;

  constructor(
    public itemService: ItemService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.itemId = this.route.snapshot.params['itemId'];
    this.itemService.find(this.itemId).subscribe((data: Item) => {
      this.item = data;
    });

    this.form = new FormGroup({
      isAvailable: new FormControl('', [Validators.required]),
      itemCode: new FormControl('', [Validators.required]),
      itemName: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required]),
      stock: new FormControl('', [Validators.required]),
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    console.log(this.form.value);
    this.itemService
      .update(this.itemId, this.form.value)
      .subscribe((res: any) => {
        console.log('Post updated successfully!');
        this.router.navigateByUrl('items/index');
      });
  }
}
