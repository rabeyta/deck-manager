package me.abeyta.deckmanager.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigTest {

	@Test
	public void verifyDocket() {
		Docket docket = new SwaggerConfig().deckApi();
		
		assertEquals("full-deck-api", docket.getGroupName());
		assertNotNull(docket);
	}
	
}
