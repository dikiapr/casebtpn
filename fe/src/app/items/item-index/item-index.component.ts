import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Item } from '../item';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-item-index',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './item-index.component.html',
  styleUrl: './item-index.component.css',
})
export class ItemIndexComponent {
  itemList: Item[] = [];

  constructor(public itemService: ItemService) {}

  ngOnInit(): void {
    this.itemService.getAll().subscribe((data: Item[]) => {
      this.itemList = data;
      console.log(this.itemList);
    });
  }

  deleteItem(itemId: number) {
    this.itemService.delete(itemId).subscribe((res) => {
      this.itemList = this.itemList.filter((item) => item.itemId !== itemId);
      console.log('Post Deleted Successfully!');
    });
  }

  getStatusLabel(isAvailable: boolean): string {
    return isAvailable ? 'Yes' : 'No';
  }
}
