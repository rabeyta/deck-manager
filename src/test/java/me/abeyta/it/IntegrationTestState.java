package me.abeyta.it;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import me.abeyta.deckmanager.model.Deck;
import net.serenitybdd.core.Serenity;

@Component
public class IntegrationTestState {

	public void clear() {
		Serenity.getCurrentSession().clear();
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getFromSession(String key) {
		try {
			return (T) Serenity.getCurrentSession().get(key);
		} catch (Exception e) {
			return null; //nothing in session
		}
	}
	
	private void putInSession(String key, Object value) {
		Serenity.getCurrentSession().put(key, value);
	}

	public void setDeckName(String deckName) {
		putInSession("deckName", deckName);
	}
	
	public String getDeckName() {
		return getFromSession("deckName");
	}

	public Deck getDeck() {
		return getFromSession("deck");
	}
	
	public void setDeck(Deck deck) {
		putInSession("deck", deck);
	}
	
	public Deck getShuffledDeck() {
		return getFromSession("shuffledDeck");
	}
	
	public void setShuffledDeck(Deck deck) {
		putInSession("shuffledDeck", deck);
	}

	public void setDeckList(List<String> decks) {
		putInSession("deckList", decks);
	}

	public List<String> getDeckList(){
		return getFromSession("deckList");
	}
	
	public void setHttpClientErrorException(HttpClientErrorException exception) {
		putInSession("clientException", exception);
	}
	
	public HttpClientErrorException getHttpClientErrorException() {
		return getFromSession("clientException");
	}
}
