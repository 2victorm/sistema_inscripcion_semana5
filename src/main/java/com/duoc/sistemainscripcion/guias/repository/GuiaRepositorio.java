package com.duoc.sistemainscripcion.guias.repository;

import com.duoc.sistemainscripcion.guias.model.GuiaDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GuiaRepositorio extends JpaRepository<GuiaDespacho, Long> {

    List<GuiaDespacho> findByTransportistaAndFecha(String transportista, LocalDate fecha);

}