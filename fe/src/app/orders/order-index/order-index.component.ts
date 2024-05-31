import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Order } from '../order';
import { OrderService } from '../order.service';
import { ReportService } from '../../report/report.service';

@Component({
  selector: 'app-order-index',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './order-index.component.html',
  styleUrl: './order-index.component.css',
})
export class OrderIndexComponent {
  orderList: Order[] = [];

  constructor(
    public orderService: OrderService,
    private reportService: ReportService
  ) {}

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

  generateReport() {
    const format = 'pdf';
    this.reportService
      .summarize(format, { observe: 'response', responseType: 'blob' })
      .subscribe(
        (response: any) => {
          const contentDispositionHeader: string | null = response.headers.get(
            'Content-Disposition'
          );
          if (contentDispositionHeader) {
            const fileName = contentDispositionHeader
              .split(';')[1]
              .trim()
              .split('=')[1];
            const blobData = new Blob([response.body], {
              type: 'application/pdf',
            });
            const downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(blobData);
            downloadLink.setAttribute('download', fileName);
            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
          }
        },
        (error) => {
          console.error('Error generating PDF report:', error);
          // Tambahkan kode untuk menampilkan pesan kesalahan kepada pengguna
        }
      );
  }
}
