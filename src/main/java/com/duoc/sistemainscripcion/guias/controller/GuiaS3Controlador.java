package com.duoc.sistemainscripcion.guias.controller;

import com.duoc.sistemainscripcion.guias.model.GuiaDespacho;
import com.duoc.sistemainscripcion.guias.service.GuiaServicio;
import com.duoc.sistemainscripcion.s3.GuiaS3Servicio;
import com.duoc.sistemainscripcion.s3.S3Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/guias")
public class GuiaS3Controlador {

    @Autowired
    private S3Servicio s3Servicio;

    @Autowired
    private GuiaServicio guiaServicio;

    @Autowired
    private GuiaS3Servicio guiaS3Servicio;

    @Value("${aws.bucketName}")
    private String bucket;

    // POST /guias/{id}/subir
    @PostMapping("/{id}/subir")
    public ResponseEntity<String> subirGuia(@PathVariable Long id) {
        try {
            GuiaDespacho guia = guiaServicio.obtener(id);
            File archivo = guiaS3Servicio.generarArchivo(guia);
            String resultado = s3Servicio.subirArchivo(bucket, archivo, id);
            archivo.delete();
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // GET /guias/{id}/descargar
    @GetMapping("/{id}/descargar")
    public ResponseEntity<ByteArrayResource> descargarGuia(@PathVariable Long id) {
        try {
            String key = "guias/" + id + "/guia_" + id + ".txt";
            byte[] contenido = s3Servicio.descargarArchivo(bucket, key);
            ByteArrayResource resource = new ByteArrayResource(contenido);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"guia_" + id + ".txt\"")
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(contenido.length)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE /guias/{id}/eliminar-archivo
    @DeleteMapping("/{id}/eliminar-archivo")
    public ResponseEntity<String> eliminarArchivo(@PathVariable Long id) {
        try {
            String key = "guias/" + id + "/guia_" + id + ".txt";
            s3Servicio.eliminarArchivo(bucket, key);
            return ResponseEntity.ok("Archivo eliminado de S3 correctamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // GET /guias/listar
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(s3Servicio.listarArchivos(bucket));
    }
}