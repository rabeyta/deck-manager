Scenario: Delete deck removes deck

Given an existing deck named roberts_deck
When I request the deck to be deleted
Then the deck is not able to be retrieved
