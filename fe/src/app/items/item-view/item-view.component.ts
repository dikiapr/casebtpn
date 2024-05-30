import { Component } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-item-view',
  standalone: true,
  imports: [],
  templateUrl: './item-view.component.html',
  styleUrl: './item-view.component.css',
})
export class ItemViewComponent {
  itemId!: number;
  item!: Item;

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
  }

  getStatusLabel(isActive: boolean): string {
    return isActive ? 'Yes' : 'No';
  }
}
