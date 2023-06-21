package com.trabajofinal.ocr.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trabajofinal.ocr.Entity.Ocr;
import com.trabajofinal.ocr.Service.OcrService;

@RestController
@RequestMapping("/ocr")
@CrossOrigin(origins = "http://localhost:4200")
public class OcrController {
	
	@Autowired
	private OcrService ocrService;

	@GetMapping("/getOcr")
	public ResponseEntity<Ocr> getOcr(){
		Ocr ocr = ocrService.getOne(1).get();
		return new ResponseEntity<Ocr>(ocr, HttpStatus.OK);
	}
	
	@PutMapping("/procesar")
	public ResponseEntity<?> procesarImagen(@RequestParam("img") MultipartFile img) throws IOException{
		System.out.println("Pase por procesar........................");
		String resultado = ocrService.procesar(img.getInputStream());
		System.out.println("Resultado del procesar: " + resultado);
		Ocr ocr = ocrService.getOne(1).get();
		ocr.setTexto(resultado);
		ocrService.save(ocr);
		return new ResponseEntity("OK", HttpStatus.OK);
	}
}
