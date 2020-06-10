package jjBan.je.rest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class MixIns {
	
	@JsonIgnoreProperties(value = {"id", "nivelDificultad", "provincia",
			"fecha", "nombre", "actividad", "poblacion","descripcion" })
	public static interface DatosTecnicos {
	
	@JsonProperty("duracion")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
	@JsonFormat(pattern = "HH:mm")
		abstract LocalTime getDuracion(); 
	
	}
	
	@JsonIgnoreProperties(value = {"kmsRecorridos", "duracion", "velocidadMedia",
	 "velocidadMaxima", "desnivel" })
	public static interface Rutas {
		
		@JsonProperty("fecha")
//		@JsonDeserialize(using = LocalDateDeserializer.class)
//	    @JsonSerialize(using = LocalDateSerializer.class)
//		@JsonFormat(pattern = "dd MMM. yyyy hh:mm:ss") // Este formato viene arrastrado de Strava..
		abstract LocalDateTime getFecha();
		
		
		
		
	}
	
public static interface Actividad {
		
		@JsonProperty("fecha")
//		@JsonDeserialize(using = LocalDateDeserializer.class)
//	    @JsonSerialize(using = LocalDateSerializer.class)
		abstract LocalDateTime getFecha();
		
	}
	
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public static interface IgnoreHibernatePropertiesInJackson{
	
}

	@JsonIgnoreType // Ignora un tipo por completo
	public static interface IgnorarTipo {}
	
}
