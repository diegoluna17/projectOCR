import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'comparaImagenes';
/* 
  urlImagen: string = ''
  archivo: any

  constructor(private sanitizer: DomSanitizer) { }

  onFileSelected(event: any): void{
    this.archivo = event.target.files[0]
    this.extraerBase64(event.target.files[0]).then((imagen: any) =>{
      this.urlImagen = imagen.base
    })
  }

  extraerBase64 = async ($event: any) => {
    try {
      const unsafeImg = window.URL.createObjectURL($event);
      const image = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
      const reader = new FileReader();
      reader.readAsDataURL($event);
      return new Promise((resolve, reject) => {
        reader.onload = () => {
          resolve({
            base: reader.result
          });
        };
        reader.onerror = error => {
          resolve({
            base: null
          });
        };
      });
    } catch (e) {
      return null;
    }
  } */
}
