import { Routes } from '@angular/router';
import { IndexComponent } from './customers/index/index.component';
import { ViewComponent } from './customers/view/view.component';
import { CreateComponent } from './customers/create/create.component';
import { EditComponent } from './customers/edit/edit.component';
import { ItemIndexComponent } from './items/item-index/item-index.component';
import { ItemViewComponent } from './items/item-view/item-view.component';
import { ItemCreateComponent } from './items/item-create/item-create.component';
import { ItemEditComponent } from './items/item-edit/item-edit.component';

export const routes: Routes = [
  { path: 'customers', redirectTo: 'customers/index', pathMatch: 'full' },
  { path: 'customers/index', component: IndexComponent },
  { path: 'customers/:customerId/view', component: ViewComponent },
  { path: 'customers/create', component: CreateComponent },
  { path: 'customers/:customerId/edit', component: EditComponent },
  { path: 'items', redirectTo: 'items/index', pathMatch: 'full' },
  { path: 'items/index', component: ItemIndexComponent },
  { path: 'items/:itemId/view', component: ItemViewComponent },
  { path: 'items/create', component: ItemCreateComponent },
  { path: 'items/:itemId/edit', component: ItemEditComponent },
];
