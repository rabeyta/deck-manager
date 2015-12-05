package me.abeyta.deckmanager.delegates;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.abeyta.deckmanager.data.DeckDao;
import me.abeyta.deckmanager.model.Deck;

@RunWith(MockitoJUnitRunner.class)
public class DeckManagerTest {

	@InjectMocks
	private DeckManager manager;
	@Mock
	private DeckDao dao;
	
	@Test
	public void createReturnsDeckFromDao() {
		String deckName = "water";
		Deck deck = new Deck(deckName);
		
		when(dao.createOrReplace(deckName)).thenReturn(deck);
		
		Deck output = manager.create(deckName);
		
		assertSame(deck , output);
	}
	
}
