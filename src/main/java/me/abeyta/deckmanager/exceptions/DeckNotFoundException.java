package me.abeyta.deckmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeckNotFoundException extends Exception {

	private static final long serialVersionUID = -3017733455403046961L;

}
