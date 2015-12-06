Scenario: Shuffling deck changes card order

Given an existing deck named roberts_deck
When I request a deck to be shuffled
Then the deck is in a different order
