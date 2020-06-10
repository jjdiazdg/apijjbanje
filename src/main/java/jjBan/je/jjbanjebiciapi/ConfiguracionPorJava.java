package jjBan.je.jjbanjebiciapi;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jjBan.je.core.Actividad;
import jjBan.je.core.DatosTecnicos;
import jjBan.je.core.Ruta;
import jjBan.je.rest.MixIns;

@Configuration
@PropertySource({"classpath:config/rest.properties", "classpath:config/jackson.properties"})
public class ConfiguracionPorJava {

	@Bean
	// Tambien se le aplican las propiedades de jackson aunque se use new ObjectMapper()
	// porque es un bean y se configura (esto seria como su constructor)
	
	public ObjectMapper getObjectMapper() {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Ruta.class, MixIns.Rutas.class);
		mapper.addMixIn(DatosTecnicos.class, MixIns.DatosTecnicos.class);
		mapper.addMixIn(Actividad.class, MixIns.Actividad.class);
		mapper.addMixIn(Object.class, MixIns.IgnoreHibernatePropertiesInJackson.class);
		
		return mapper;
	}
	
//  @Bean
//	Otra forma de personalizar (esto seria como su metodo de configuracion)
  public Jackson2ObjectMapperBuilderCustomizer addCustomSerialization() {
      return new Jackson2ObjectMapperBuilderCustomizer() {

          @Override
          public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
              jacksonObjectMapperBuilder.featuresToDisable(
                      // Tanto para deserializacion como serializacion
                      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                      SerializationFeature.FAIL_ON_EMPTY_BEANS
                  );
              // Add your customization
              // jacksonObjectMapperBuilder.featuresToEnable(...)
              // jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.);
              // jacksonObjectMapperBuilder.autoDetectGettersSetters(false);
              // jacksonObjectMapperBuilder.autoDetectFields(true);
              // Aqui se aplica toda la configuracion (es un metodo)
              
              jacksonObjectMapperBuilder.mixIn(Ruta.class, MixIns.Rutas.class);
              jacksonObjectMapperBuilder.mixIn(DatosTecnicos.class, MixIns.DatosTecnicos.class);
              jacksonObjectMapperBuilder.mixIn(Actividad.class, MixIns.Actividad.class);
              
              jacksonObjectMapperBuilder.visibility(PropertyAccessor.FIELD, Visibility.ANY);
              jacksonObjectMapperBuilder.visibility(PropertyAccessor.GETTER, Visibility.PROTECTED_AND_PUBLIC);
              jacksonObjectMapperBuilder.serializationInclusion(Include.ALWAYS);
          }
      };
  }
	
}
