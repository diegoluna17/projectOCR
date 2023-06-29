import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { TesseractService } from 'src/app/Service/tesseract.service';


@Component({
  selector: 'app-tesseract',
  templateUrl: './tesseract.component.html',
  styleUrls: ['./tesseract.component.css']
})
export class TesseractComponent implements OnInit, OnDestroy {
  @Output() ocrText = new EventEmitter<string>();

  image: File | null = null;
  lang: string = 'eng'; // eng for English, spa for Spanish
  text: string = '';
  tesseract: TesseractService;

  constructor(private tesseractService: TesseractService) {
    this.tesseract = new TesseractService();
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.tesseract.terminateWorker();
  }

  onFileSelected(event: any): void {
    this.image = event.target.files[0];
  }

  ocr(): void {
    if (this.image) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const imageData = e.target.result;
        this.tesseractService.imageToText(imageData, this.lang).subscribe((res: string) => {
          this.text = res;
          this.ocrText.emit(res);
          this.tesseractService.terminateWorker();
        });
      };
      reader.readAsDataURL(this.image);
    }
  }
}
