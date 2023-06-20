import { Component, NgZone } from '@angular/core';
import { OcrService } from '../service/ocr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { TextoOCR } from '../model/texto-ocr';

@Component({
  selector: 'app-ocr',
  templateUrl: './ocr.component.html',
  styleUrls: ['./ocr.component.css']
})
export class OcrComponent {

  public imagenSeleccionada!: string;
  public resultadoOCR!: string;
  public archivos: any = []
  public textoOCR?: TextoOCR


  constructor(private ocrService: OcrService, private sanitizer: DomSanitizer, private ngZone: NgZone) { }
  capturarFile(event: any): void {
    const archivoCapturado = event.target.files[0]
    this.extraerBase64(archivoCapturado).then((imagen: any) =>{
      this.imagenSeleccionada = imagen.base
    })
    this.archivos.push(archivoCapturado)
    }
  

/*   procesarImagen(): void {
    const img = this.archivos[0]
    console.log("IMAGEN: " + img)
    if (img){
      this.ocrService.procesarImagen(img).subscribe( data => {
        console.log("Imagen procesada con éxito...")
      },
      err => {
        alert("Error al procesar imagen !!!!")
        console.log("Error: " + err)
      })
      this.ocrService.getOCR().subscribe( data => {
        this.textoOCR = data[0]
        this.resultadoOCR = this.textoOCR.respuesta
      },
      err => {
        alert("Error al recuperar respuesta...")
        console.log("Error: " + err)
      })
    }
  } */

   run(): void{
    this.procesarImagen()
 /*    this.verificabk()
    this.getOCR() */
  }

  procesarImagen(): void {
    const img = this.archivos[0];
    if (img) {
      this.ocrService.procesarImagen(img).subscribe(respuesta => {
        this.textoOCR = respuesta
        
        alert("IMAGEN PROCESADA...");
      },
      err => {
        alert("Error al procesar la imagen !!!!");
        console.log("Error: " + err);
      });
    }
  }

  getOCR(): void{
    this.ocrService.getOCR().subscribe(data =>{
      this.textoOCR = data
    },
    err =>{
      console.log("ERROR AL OBTENER TEXTO OCR: " + err)
    })
  }

/*     verificabk(): void{
      this.ocrService.verificarBackend()
    }

    getOCR(): void{
      this.ocrService.getOCR().subscribe(data => {
        this.resultadoOCR = data.respuesta
      },
      err => {
        console.log("getOCR: " + err)
      })
    } */
  
  
/*   capturaTexto(): void{
    this.ocrService.generaTextoOCR().subscribe( data => {this.textoOCR = data})
  } */


  

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
  }
  
}
