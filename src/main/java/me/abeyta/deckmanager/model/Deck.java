package me.abeyta.deckmanager.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Deck {

	private String name;
	private Set<Card> cards;

	public Deck() {
		this("default");
	}

	public Deck(String deckName) {
		this.name = deckName;
		this.cards = new LinkedHashSet<>();
		populateSuite(Suite.HEARTS);
		populateSuite(Suite.DIAMONDS);
		populateSuite(Suite.CLUBS);
		populateSuite(Suite.SPADES);
	}
	
	public Deck(String deckName, Set<Card> cards) {
		this.name = deckName;
		this.cards = cards;
	}
	
	private void populateSuite(Suite suite) {
		cards.add(new Card(suite, "A"));
		cards.add(new Card(suite, "2"));
		cards.add(new Card(suite, "3"));
		cards.add(new Card(suite, "4"));
		cards.add(new Card(suite, "5"));
		cards.add(new Card(suite, "6"));
		cards.add(new Card(suite, "7"));
		cards.add(new Card(suite, "8"));
		cards.add(new Card(suite, "9"));
		cards.add(new Card(suite, "10"));
		cards.add(new Card(suite, "J"));
		cards.add(new Card(suite, "Q"));
		cards.add(new Card(suite, "K"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Deck rhs = (Deck) obj;
		return StringUtils.equals(name, rhs.getName()) && cardsInSameOrder(cards, rhs.getCards());
	}

	private boolean cardsInSameOrder(Set<Card> lhs, Set<Card> rhs) {
		if(lhs.size() != rhs.size()) {
			return false;
		}
		Card[] leftCards = lhs.toArray(new Card[lhs.size()]);
		Card[] rightCards = rhs.toArray(new Card[rhs.size()]);
		for(int x = 0; x < lhs.size(); x ++) {
			if(!leftCards[x].equals(rightCards[x])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, true);
	}
}
