/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ocr.ocr.Controller;

import com.ocr.ocr.Entity.TextoOCR;
import com.ocr.ocr.Service.TesseractService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Diego
 */
@RestController
@RequestMapping("/ocr")
@CrossOrigin(origins = "http://localhost:4200")
public class TextoOCRController {
     
    @Autowired
    private TesseractService tesseractService;
    //@Autowired
   // private ImageHashingService imageHashingService;    
    
    
    @PostMapping("/procesar")
    public ResponseEntity<TextoOCR> realizarOCR(@RequestParam("img") MultipartFile img) throws IOException{
        String resultado = tesseractService.procesar(img.getInputStream());
        TextoOCR ocr = tesseractService.getOne(1).get();
        ocr.setResultado(resultado);
        tesseractService.save(ocr);
        System.out.println("Si ta todo bien, se guardo: " + ocr.getResultado());
        return new ResponseEntity(ocr, HttpStatus.OK);
    }
    
    @GetMapping("/getOCR")
    public ResponseEntity<TextoOCR> getOCR(){
        TextoOCR ocr = tesseractService.getOne(1).get();
        return new ResponseEntity(ocr,HttpStatus.OK);
    }
//
//    @PostMapping("/procesar")
//    public ResponseEntity<TextoOCR> realizarOCR(@RequestParam("img") MultipartFile img) throws IOException{
//        String extraccion = tesseractService.procesar(img.getInputStream());
//        TextoOCR ocr = new TextoOCR("");
//        ocr.setResultado(extraccion);
//        tesseractService.save(ocr);
//        System.out.println("Si ta todo bien, se guardo: " + ocr.getResultado());
//        return new ResponseEntity<TextoOCR>(ocr, HttpStatus.OK);
//    }
    
//    @GetMapping("/verificar")
//    public ResponseEntity<?> verificar(){
//        System.out.println("VERIFICAR");
//        List<TextoOCR> list = tesseractService.list();
//        TextoOCR ocr = list.get(0);
//        System.out.println("\nELEMENTO 0 DEL VERIFICAR: " + ocr.getResultado());
//        return new ResponseEntity(HttpStatus.OK);
//    }
//    
//    @GetMapping("getOCR")
//    public ResponseEntity<TextoOCR> getOCR(){
//        System.out.println("GETOCR");
//        List<TextoOCR> list = tesseractService.list();
//        TextoOCR ocr = list.get(0);
//        System.out.println("\nELEMENTO 0 DEL GETOCR: " + ocr.getResultado());
//        return new ResponseEntity<TextoOCR>(ocr, HttpStatus.OK);
//    }

 
    
    
     @PostMapping("/comparar")
    public boolean compararImagenesPorHash(@RequestParam("img1") MultipartFile imagen1, @RequestParam("img2") MultipartFile imagen2) throws IOException, NoSuchAlgorithmException {
        return tesseractService.compararImagenesPorHash(imagen1.getInputStream(), imagen2.getInputStream());
    }
}
