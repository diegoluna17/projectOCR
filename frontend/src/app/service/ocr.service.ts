import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ocr } from '../model/ocr';

@Injectable({
  providedIn: 'root'
})
export class OcrService {

  private URL = "http://localhost:8080/ocr/"

  constructor(private http: HttpClient) { }

  procesar(file: File): Observable<any>{
    const img = new FormData()
    img.append("img", file)
    return this.http.put<any>(this.URL + 'procesar', img)
  }

  getOcr(): Observable<Ocr>{
    return this.http.get<Ocr>(this.URL + 'getOcr')
  }
}
