package com.validation.strategy;

import java.lang.reflect.Field;
import java.util.Optional;

import com.validation.annotation.Email;

public class EmailStrategy implements ValidationStrategy{

	@Override
	public Optional<String> validate(Field field, Object value) {
		// Sprawdzenie czy pole ma adnotację Email, czy wartość jest typu String oraz czy nie jest pusta (jeśli pole jest puste, nie chcemy walidowac poprawności formatu)
		if(field.isAnnotationPresent(Email.class) && value instanceof String && !((String) value).trim().isEmpty()) {
			// Regex do sprawdzenia poprawności adresu 
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			// Rzutowanie wartości pola na String
			String email = (String) value;
			// Sprawdzenie czy podany adres e-mail jest prawidłowy za pomocą regex
			if(!email.matches(emailRegex)) {
				// Pobranie adnotacji z pola
				Email annotation = field.getAnnotation(Email.class);
				// Komunikat błędu
				String errorInfo = String.format("Pole %s: %s", field.getName(), annotation.message());
				// Zwrócenie komunikatu o błędzie
				return Optional.of(errorInfo);
			}
		}
		// Zwócenie pustego optionala, jeśli walidacja się powiedzie
		return Optional.empty();
	}

}
