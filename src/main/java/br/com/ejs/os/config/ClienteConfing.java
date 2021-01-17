package br.com.ejs.os.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ejs.os.domain.model.Cliente;

@Configuration
public class ClienteConfing {

	@Bean
	public Cliente cliente() {
		return new Cliente();
	}
}
