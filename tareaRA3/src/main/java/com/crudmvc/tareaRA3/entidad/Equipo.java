package com.crudmvc.tareaRA3.entidad;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="equipos")
public class Equipo {
	   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 30)
    private String nombre;
    
    @NotNull(message = "El año es obligatorio")
    @Min(value = 1850, message = "El año debe ser mayor a 1850")
    @Max(value = 2026, message = "El año deber ser inferior o igual al actual")
    private Integer anio_fundacion;
    
    @NotBlank(message = "La ciudad es obligatoria")
    @Size(min = 3, max = 30)
    private String ciudad;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Integer getAnio_fundacion() {
		return anio_fundacion;
	}


	public void setAnio_fundacion(Integer anio_fundacion) {
		this.anio_fundacion = anio_fundacion;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Equipo other = (Equipo) obj;
	    return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
}
