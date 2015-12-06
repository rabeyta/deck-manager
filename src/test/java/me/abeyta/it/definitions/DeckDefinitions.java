package me.abeyta.it.definitions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import me.abeyta.deckmanager.model.Deck;
import me.abeyta.it.ITTestConfig;
import me.abeyta.it.IntegrationTestState;
import me.abeyta.it.steps.DeckSteps;

@ContextConfiguration(classes=ITTestConfig.class)
public class DeckDefinitions {

	@Autowired
	private IntegrationTestState state;
	
	@Autowired
	private DeckSteps steps;
	
	@Given("a deck named $deckName")
	public void givenADeckNamed(String deckName) {
		state.setDeckName(deckName);
	}
	
	@Given("the deck is not existing")
	public void theDeckIsNotExisting() {
		steps.deleteDeck(state.getDeckName());
	}
	
	@Given("the deck is shuffled")
	public void theDeckIsShuffled() {
		Deck deck = steps.shuffle(state.getDeckName());
		assertDeckIsNotInOriginalOrder(deck);
		state.setDeck(deck);
	}

	@Given("an existing deck named $deckName")
	public void anExistingDeckNamed(String deckName) {
		state.setDeckName(deckName);
		steps.createNewDeck(deckName);
	}

	@When("I request a list of all known decks")
	public void iRequestAListOfAllKnownDecks() {
		state.setDeckList(steps.getDecks());
	}
	
	private void assertDeckIsNotInOriginalOrder(Deck deck) {
		// TODO Auto-generated method stub
	}

	@When("I request a new deck to be created")
	public void iRequestANewDeckToBeCreated() {
		String deckName = state.getDeckName();
		
		steps.createNewDeck(deckName);
		state.setDeck(steps.get(deckName));
	}
	
	@Then("I receive a new unshuffled deck")
	public void iReceiveANewUnsuffledDeck() {
		Deck deck = state.getDeck();
		assertNotNull("Deck should not be null. It should have been retrieved and set in a prior step", deck);
		assertDeckOriginalOrder(deck);
	}

	@Then("I receive list of all known decks")
	public void iReceiveAListOfKnownDecks() {
		List<String> deckList = state.getDeckList();
		
		assertNotNull(deckList);
		assertTrue(deckList.size() > 0);
	}
	
	@Then("the list contains $deckName")
	public void theListContainsDeckName(String deckName) {
		assertTrue(state.getDeckList().contains(deckName));
	}
	
	private void assertDeckOriginalOrder(Deck deck) {
		//TODO: build asserts
	}
}
