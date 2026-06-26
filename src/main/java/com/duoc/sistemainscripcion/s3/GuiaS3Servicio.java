package com.duoc.sistemainscripcion.s3;

import com.duoc.sistemainscripcion.guias.model.GuiaDespacho;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class GuiaS3Servicio {

    public File generarArchivo(GuiaDespacho guia) throws IOException {

        File carpeta = new File("guias");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombreArchivo = "guia_" + guia.getId() + ".txt";
        File archivo = new File(carpeta, nombreArchivo);

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("===== GUIA DE DESPACHO =====\n");
            writer.write("ID: " + guia.getId() + "\n");
            writer.write("Numero Guia: " + guia.getNumeroGuia() + "\n");
            writer.write("Transportista: " + guia.getTransportista() + "\n");
            writer.write("Origen: " + guia.getOrigen() + "\n");
            writer.write("Destino: " + guia.getDestino() + "\n");
            writer.write("Destinatario: " + guia.getDestinatario() + "\n");
            writer.write("Estado: " + guia.getEstado() + "\n");
            writer.write("Fecha: " + guia.getFecha() + "\n");
        }

        return archivo;
    }
}