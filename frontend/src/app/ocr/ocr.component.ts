import { Component, OnInit } from '@angular/core';
import { OcrService } from '../service/ocr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Ocr } from '../model/ocr';

@Component({
  selector: 'app-ocr',
  templateUrl: './ocr.component.html',
  styleUrls: ['./ocr.component.css']
})
export class OcrComponent implements OnInit{


  // Atributos --------------------------------------------------------------------
  ocrModel = new Ocr("")
  imagenSeleccionada: string = ""
  ocrTexto: string = ""
  archivo: any
  

  //Constructor --------------------------------------------------------------------
  constructor(private ocrService: OcrService, private sanitizer: DomSanitizer) {}


  //Metodos --------------------------------------------------------------------

  ngOnInit(): void {
    this.cargaTexto()
  }

  cargaTexto(): void{
    this.ocrService.getOcr().subscribe( data => {this.ocrModel = data})
  }

  capturarFile(event: any): void{
    this.archivo = event.target.files[0]
    this.extraerBase64(event.target.files[0]).then((imagen: any) =>{
      this.imagenSeleccionada = imagen.base
    })
  }

  procesarImagen(): void {
    if(this.archivo){
      this.ocrService.procesar(this.archivo).subscribe( data =>{
        alert("Imagen procesada con éxito...")
      })
      this.ocrService.getOcr().subscribe( data =>{
        this.ocrModel = data
        this.ocrTexto = data.texto
      })
    }
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
  }
}
