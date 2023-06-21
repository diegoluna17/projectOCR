package com.trabajofinal.ocr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajofinal.ocr.Entity.Ocr;

@Repository
public interface ocrRepository extends JpaRepository<Ocr, Integer>{

}
