package me.abeyta.deckmanager.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import me.abeyta.deckmanager.delegates.shuffle.HandShuffler;
import me.abeyta.deckmanager.delegates.shuffle.Shuffler;
import me.abeyta.deckmanager.delegates.shuffle.SimpleShuffler;

@Configuration
public class DeckManagerConfig {

	private static Log logger = LogFactory.getLog(DeckManagerConfig.class);

	@Autowired
	private Environment environment;
	
	@Bean
	public Shuffler shuffler() {
		String shufflerDesired = environment.getProperty("shuffle.technique");
		
		if(StringUtils.equalsIgnoreCase("hand", shufflerDesired)) {
			logger.info("Hand shuffling algorithm being used.");
			return new HandShuffler();
		}
		
		logger.info("Simple shuffling algorithm being used.");
		return new SimpleShuffler();
	}
	
}
