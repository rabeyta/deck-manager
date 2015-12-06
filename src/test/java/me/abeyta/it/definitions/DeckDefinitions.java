package me.abeyta.it.definitions;

import static org.junit.Assert.assertNotNull;

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

	private void assertDeckOriginalOrder(Deck deck) {
		//TODO: build asserts
	}
}
