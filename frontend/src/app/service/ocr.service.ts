import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TextoOCR } from '../model/texto-ocr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OcrService {

  URL = "http://localhost:8080/ocr/"
  textoOCR: TextoOCR = new TextoOCR ("")
  imagenProcesada!: string

  constructor(private httpClient: HttpClient) { }

/*   public procesarImagen(file: File): Observable<any> {
    const img = new FormData();
    img.append('img', file);
    console.log("Pase por el servidor")
    console.log("img: " + img)
    
    return this.httpClient.post<any>('http://localhost:8080/ocr/procesar', img);
  }

  public getOCR(): Observable<string>{
    return this.httpClient.get<string>(this.URL+"getOCR");
  } */

  public procesarImagen(file: File): Observable<TextoOCR>{
    const img = new FormData()
    img.append("img", file)
    return this.httpClient.post<TextoOCR>(this.URL + "procesar", img)
  }

  public getOCR(): Observable<TextoOCR>{
    return this.httpClient.get<TextoOCR>(this.URL + "getOCR")
  }

/*   verificarBackend(): Observable<any>{
    return this.httpClient.get<any>(this.URL + "verificar")
  }

  getOCR(): Observable<TextoOCR>{
    return this.httpClient.get<TextoOCR>(this.URL + "getOCR")
  } */
}
