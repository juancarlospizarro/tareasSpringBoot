package com.crudmvc.tareaRA3.configuracion;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.crudmvc.tareaRA3.entidad.Equipo;
import com.crudmvc.tareaRA3.repositorio.EquipoRepositorio;
import com.github.javafaker.Faker;

@Component
public class IniciarDatos implements CommandLineRunner {

    @Autowired
    private EquipoRepositorio equipoRepositorio;

    private static final int TOTAL_EQUIPOS = 23;
    private static final int ANIO_MIN = 1850;
    private static final int ANIO_MAX = 2026;

    @Override
    public void run(String... args) throws Exception {

        if (equipoRepositorio.count() > 0) {
            return;
        }

        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        for (int i = 0; i < TOTAL_EQUIPOS; i++) {
            Equipo equipo = new Equipo();
            
         // Generar nombre de equipo con longitud entre 3 y 30
            String nombre;
            do {
                nombre = faker.team().name(); 
            } while (nombre.length() < 3 || nombre.length() > 30);
            equipo.setNombre(nombre);

            // Generar ciudad con longitud entre 3 y 30
            String ciudad;
            do {
                ciudad = faker.address().city();
            } while (ciudad.length() < 3 || ciudad.length() > 30);
            equipo.setCiudad(ciudad);
            
            equipo.setAnio_fundacion(faker.number().numberBetween(ANIO_MIN, ANIO_MAX + 1));

            equipoRepositorio.save(equipo);
        }
    }
}
