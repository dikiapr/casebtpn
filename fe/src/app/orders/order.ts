export interface Order {
  orderId: number;
  orderCode: string;
  customerId: number;
  itemId: number;
  quantity: number;
  orderDate: Date;
  totalPrice: number;
}
