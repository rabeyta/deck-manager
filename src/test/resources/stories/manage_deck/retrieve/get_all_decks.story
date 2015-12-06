Scenario: Get a list of all known decks

Given an existing deck named roberts_deck
When I request a list of all known decks
Then I receive list of all known decks
And the list contains roberts_deck
