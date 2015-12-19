package me.abeyta.it.definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;

import me.abeyta.deckmanager.model.Deck;
import me.abeyta.it.ITTestConfig;
import me.abeyta.it.IntegrationTestState;
import me.abeyta.it.steps.DeckSteps;
import me.abeyta.test.utils.CardUtils;

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
		state.setShuffledDeck(deck);
		assertFalse(CardUtils.isDeckInDefaultOrder(deck));
	}

	@Given("an existing deck named $deckName")
	public void anExistingDeckNamed(String deckName) {
		state.setDeckName(deckName);
		steps.createNewDeck(deckName);
		state.setDeck(steps.get(deckName));
	}
	
	@When("I request a deck to be shuffled")
	public void iREquestADeckToBeShuffled() {
		state.setShuffledDeck(steps.shuffle(state.getDeckName()));
	}

	@When("I request a list of all known decks")
	public void iRequestAListOfAllKnownDecks() {
		state.setDeckList(steps.getDecks());
	}
	
	@When("I request a new deck to be created")
	public void iRequestANewDeckToBeCreated() {
		String deckName = state.getDeckName();
		
		steps.createNewDeck(deckName);
		state.setDeck(steps.get(deckName));
	}
	
	@When("I request the deck")
	public void requestADeck() {
		try {
			state.setDeck(steps.get(state.getDeckName()));	
		} catch (HttpClientErrorException e) {
			state.setHttpClientErrorException(e);
		}
		
	}

	@When("I request the deck to be deleted")
	public void requestTheDeckToBeDeleted() {
		steps.deleteDeck(state.getDeckName());
	}

	@Then("the deck is not able to be retrieved")
	public void theDeckIsNotAbleToBeRetrieved() {
		try {
			steps.get(state.getDeckName());
			fail("should have thrown exception");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
	
	@Then("the shuffled deck is returned")
	public void shuffledDeckIsReturned() {
		System.out.println(state.getDeck());
		System.out.println(state.getShuffledDeck());
		assertTrue("Decks were not equal", state.getDeck().equals(state.getShuffledDeck()));
	}
	
	@Then("the deck is in a different order")
	public void theDeckIsInAdifferentOrder() {
		assertFalse(state.getDeck().equals(state.getShuffledDeck()));
	}
	
	@Then("I receive a new unshuffled deck")
	public void iReceiveANewUnsuffledDeck() {
		Deck deck = state.getDeck();
		assertNotNull("Deck should not be null. It should have been retrieved and set in a prior step", deck);
		assertTrue("Deck was not in original order", CardUtils.isDeckInDefaultOrder(deck));
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
	
	@Then("I receive an error status code of $statusCode")
	public void iReceivedStatusCodeOf(HttpStatus statusCode) {
		assertEquals(statusCode, state.getHttpClientErrorException().getStatusCode());
	}
	
}
