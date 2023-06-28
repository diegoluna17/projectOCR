import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { TesseractService } from 'src/app/Service/tesseract.service';


@Component({
  selector: 'app-tesseract',
  templateUrl: './tesseract.component.html',
  styleUrls: ['./tesseract.component.css']
})
export class TesseractComponent implements OnInit, OnDestroy {
  @Input() image: string = '';
  @Input() lang: string = 'eng';  // eng for English, spa for Spanish

  @Output() ocrText = new EventEmitter<any>();

  text = '';
  tesseract: any;

  constructor() { }

  ngOnInit(): void {
    this.tesseract = new TesseractService();
    this.ocr();
  }

  ngOnDestroy(): void {
    this.tesseract.terminateWorker()
  }

  async ocr() {
    this.tesseract.imageToText(this.image, this.lang).subscribe((res: any) => {
      this.text = res;
      this.ocrText.emit(res);
      this.tesseract.terminateWorker();
    });
  }
}
