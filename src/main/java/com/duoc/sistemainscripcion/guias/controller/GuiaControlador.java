package com.duoc.sistemainscripcion.guias.controller;

import com.duoc.sistemainscripcion.guias.model.GuiaDespacho;
import com.duoc.sistemainscripcion.guias.service.GuiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/guias")
public class GuiaControlador {

    @Autowired
    private GuiaServicio guiaServicio;

    // POST /guias/crear
    @PostMapping("/crear")
    public ResponseEntity<GuiaDespacho> crear(@RequestBody GuiaDespacho guia) {
        return ResponseEntity.ok(guiaServicio.crear(guia));
    }

    // PUT /guias/{id}/modificar
    @PutMapping("/{id}/modificar")
    public ResponseEntity<GuiaDespacho> modificar(@PathVariable Long id,
                                                   @RequestBody GuiaDespacho datos) {
        return ResponseEntity.ok(guiaServicio.modificar(id, datos));
    }

    // DELETE /guias/{id}/eliminar
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        guiaServicio.eliminar(id);
        return ResponseEntity.ok("Guía eliminada correctamente.");
    }

    // GET /guias/consultar?transportista=Juan&fecha=2024-06-25
    @GetMapping("/consultar")
    public ResponseEntity<List<GuiaDespacho>> consultar(
            @RequestParam String transportista,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(guiaServicio.consultarPorTransportistaYFecha(transportista, fecha));
    }
}