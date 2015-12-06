package me.abeyta.it;

import org.springframework.stereotype.Component;

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
	
}