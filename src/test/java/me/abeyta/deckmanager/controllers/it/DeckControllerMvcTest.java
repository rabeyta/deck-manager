package me.abeyta.deckmanager.controllers.it;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Set;

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
	
	private MockMvc mockMvc;
	private Deck deck;
	private String deckName;
	
	@Before
	public void setup() {
		deckName = "myDeck";
		deck = new Deck(deckName);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void putToDecksCallsManagerToCreateWithGivenName() throws Exception {
		when(mockManager.create(isA(Deck.class))).thenReturn(deck);
		
		mockMvc.perform(put(deckNameUrl(),new Object[] {}))
											.andExpect(status().isCreated())
											.andExpect(content().json(getDeckJson(), false));
		
		verify(mockManager).create(isA(Deck.class));
	}

	@Test
	public void getDeck() throws Exception {
		when(mockManager.get(deckName)).thenReturn(deck);
		
		mockMvc.perform(get(deckNameUrl(),new Object[] {}))
								.andExpect(status().isOk())
								.andExpect(content().json(getDeckJson(), false));
		
		verify(mockManager).get(deckName);
	}
	
	@Test
	public void deleteDeck() throws Exception {
		mockMvc.perform(delete(deckNameUrl(),new Object[] {}))
								.andExpect(status().isNoContent());
				
		verify(mockManager).delete(deckName);
	}
	
	@Test
	public void getDeckList() throws Exception {
		Set<String> deckNames = Collections.singleton(deckName);
		
		when(mockManager.getAllDeckNames()).thenReturn(deckNames);
		
		mockMvc.perform(get("/decks/",new Object[] {}))
								.andExpect(status().isOk())
								.andExpect(content().json(new Gson().toJson(deckNames), false));
		
		verify(mockManager).getAllDeckNames();
	}

	@Test
	public void shuffleDeck() throws Exception {
		when(mockManager.shuffle(deckName)).thenReturn(deck);
			
		mockMvc.perform(post(deckNameUrl(),new Object[] {}))
											.andExpect(status().isOk())
											.andExpect(content().json(getDeckJson(), false));
			
		verify(mockManager).shuffle(deckName);
	}
	
	
	private String getDeckJson() {
		return new Gson().toJson(deck);
	}
	
	private String deckNameUrl() {
		return "/decks/" + deckName;
	}
}
