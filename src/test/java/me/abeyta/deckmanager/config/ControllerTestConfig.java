package me.abeyta.deckmanager.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.delegates.DeckManager;

@Configuration
@ComponentScan(basePackages="me.abeyta.deckmanager.controllers")
@EnableWebMvc
public class ControllerTestConfig {

	@Bean
	public DeckManager mockDeckManager() {
		return Mockito.mock(DeckManager.class);
	}
	
	@Bean
	public DeckDao mockDeckDao() {
		return Mockito.mock(DeckDao.class);
	}
}
