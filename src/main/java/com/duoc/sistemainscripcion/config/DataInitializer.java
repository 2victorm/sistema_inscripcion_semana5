package com.duoc.sistemainscripcion.config;

import com.duoc.sistemainscripcion.guias.model.GuiaDespacho;
import com.duoc.sistemainscripcion.guias.repository.GuiaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner cargarDatos(GuiaRepositorio guiaRepositorio) {
        return args -> {
            GuiaDespacho g1 = new GuiaDespacho();
            g1.setNumeroGuia("GD-2024-001");
            g1.setTransportista("Juan Pérez");
            g1.setOrigen("Santiago");
            g1.setDestino("Valparaíso");
            g1.setDestinatario("Carlos López");
            g1.setEstado("PENDIENTE");
            g1.setFecha(LocalDate.now());
            guiaRepositorio.save(g1);

            GuiaDespacho g2 = new GuiaDespacho();
            g2.setNumeroGuia("GD-2024-002");
            g2.setTransportista("María González");
            g2.setOrigen("Concepción");
            g2.setDestino("Temuco");
            g2.setDestinatario("Ana Torres");
            g2.setEstado("EN_TRANSITO");
            g2.setFecha(LocalDate.now());
            guiaRepositorio.save(g2);

            GuiaDespacho g3 = new GuiaDespacho();
            g3.setNumeroGuia("GD-2024-003");
            g3.setTransportista("Juan Pérez");
            g3.setOrigen("Santiago");
            g3.setDestino("Antofagasta");
            g3.setDestinatario("Pedro Soto");
            g3.setEstado("ENTREGADO");
            g3.setFecha(LocalDate.now());
            guiaRepositorio.save(g3);

            System.out.println(">>> Guías de despacho cargadas en H2");
        };
    }
}