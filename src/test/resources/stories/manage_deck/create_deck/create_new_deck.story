Scenario: Create new deck not existing

Given a deck named roberts_deck
And the deck is not existing
When I request a new deck to be created
Then I receive a new unshuffled deck

Scenario: Create new deck that is already existing

Given a deck named roberts_deck
And the deck is shuffled
When I request a new deck to be created
Then I receive a new unshuffled deck