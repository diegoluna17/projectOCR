import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ComparaImagenesComponent } from './Components/compara-imagenes/compara-imagenes.component';
import { TesseractComponent } from './Components/tesseract/tesseract.component';


@NgModule({
  declarations: [
    AppComponent,
    ComparaImagenesComponent,
    TesseractComponent,
  ],
  imports: [
    BrowserModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
