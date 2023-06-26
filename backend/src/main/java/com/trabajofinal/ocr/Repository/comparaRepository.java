package com.trabajofinal.ocr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajofinal.ocr.Entity.Compara;

@Repository
public interface comparaRepository extends JpaRepository<Compara, Integer>{

}
