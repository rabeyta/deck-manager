Scenario: Requesting a deck that does not exist results in a not found response

Given a deck named roberts_deck
And the deck is not existing
When I request the deck
Then I receive an error status code of NOT_FOUND
