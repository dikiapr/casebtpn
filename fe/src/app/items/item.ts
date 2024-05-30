export interface Item {
  itemId: number;
  isAvailable: boolean;
  itemCode: string;
  itemName: string;
  lastReStock: Date;
  price: number;
  stock: number;
}
