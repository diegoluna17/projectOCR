import { Component } from '@angular/core';
import spark from 'spark-md5';

@Component({
  selector: 'app-compara-imagenes',
  templateUrl: './compara-imagenes.component.html',
  styleUrls: ['./compara-imagenes.component.css']
})
export class ComparaImagenesComponent {
  image1: File | undefined;
  image2: File | undefined;

  onFileSelected(event: Event, imageNumber: number) {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput && fileInput.files && fileInput.files.length > 0) {
      const file = fileInput.files[0];
      if (imageNumber === 1) {
        this.image1 = file;
      } else if (imageNumber === 2) {
        this.image2 = file;
      }
    }
  }

  async compareImages() {
    if (this.image1 && this.image2) {
      const hash1 = await this.computeImageHash(this.image1);
      const hash2 = await this.computeImageHash(this.image2);

      console.log('Hash 1:', hash1);
      console.log('Hash 2:', hash2);

      if (hash1 === hash2) {
        alert('Las imágenes son idénticas');
        console.log('Las imágenes son idénticas');
      } else {
        alert('Las imágenes son diferentes');
        console.log('Las imágenes son diferentes');
      }
    } else {
      console.log('Debes seleccionar dos imágenes para comparar');
    }
  }

  private computeImageHash(image: File): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => {
        const buffer = reader.result as ArrayBuffer;
        const uint8Array = new Uint8Array(buffer);
        const hashArray = Array.from(uint8Array).reduce((accumulator: string, byte: number) => {
          return accumulator + byte.toString(16).padStart(2, '0');
        }, '');
        const hash = spark.hashBinary(hashArray);
        resolve(hash);
      };
      reader.onerror = (error) => reject(error);
      reader.readAsArrayBuffer(image);
    });
  }
}
