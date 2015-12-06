package me.abeyta.it;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("me.abeyta.it")
public class ITTestConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean(name="decksBaseUrl")
	public URI itTestUrl() throws URISyntaxException {
		return new URI("http://localhost:8080/decks");
	}
	
}
