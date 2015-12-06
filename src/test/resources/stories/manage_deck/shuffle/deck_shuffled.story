Scenario: Shuffling deck changes card order

Given an existing deck named roberts_deck
When I request a deck to be shuffled
Then the deck is in a different order

Scenario: Retrieving a deck after its been shuffled returns the shuffled deck

Given an existing deck named roberts_deck
And the deck is shuffled
When I request the deck
Then the shuffled deck is returned
