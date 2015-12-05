package me.abeyta.deckmanager.controllers.it;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import me.abeyta.deckmanager.config.ControllerTestConfig;
import me.abeyta.deckmanager.delegates.DeckManager;
import me.abeyta.deckmanager.model.Deck;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ControllerTestConfig.class})
@WebIntegrationTest
public class DeckControllerMvcTest {

	@Autowired
	private DeckManager mockManager;
	@Autowired
	private WebApplicationContext context;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void putToDecksCallsManagerToCreateWithGivenName() throws Exception {
		String deckName = "myDeck";
		Deck deck = new Deck(deckName);
		when(mockManager.create(deckName)).thenReturn(deck);
		
		mockMvc.perform(put("/decks/" + deckName,new Object[] {}))
											.andExpect(status().isCreated())
											.andExpect(content().json(new Gson().toJson(deck), false));
	}
}
