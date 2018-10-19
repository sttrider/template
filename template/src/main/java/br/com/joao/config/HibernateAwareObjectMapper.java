package br.com.joao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Configurar jackson.
 * 
 * @author joao.oliveira
 *
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public HibernateAwareObjectMapper() {

		registerModule(new JavaTimeModule());
		disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

}
