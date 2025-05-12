package com.validation.strategy;

import java.lang.reflect.Field;
import java.util.Optional;
import com.validation.annotation.Size;


public class SizeStrategy implements ValidationStrategy {

	@Override
	public Optional<String> validate(Field field, Object value) {
		// Sprawdzenie czy pole ma adnotację Size oraz czy wartość jest typu String
		if(field.isAnnotationPresent(Size.class) && value instanceof String) {
			// Pobranie adnotacji z pola
			Size annotation = field.getAnnotation(Size.class);
			// Pobranie z adnotacji wartości maksymalnej i minimalnej dla długości pola 
			int min = annotation.min();
			int max = annotation.max();
			// Rzutowanie wartości na String
			String stringValue = (String) value;
			// Sprawdzenie czy długość wartości mieści się w podanym zakresie
			if(stringValue.length() < min || stringValue.length() > max) {
				// Pobranie kominukatu z adnotacji
				String message = annotation.message();
				// Podmiana min i max na wartości 
				message = message.replace("{min}", String.valueOf(min));
				message = message.replace("{max}", String.valueOf(max));
				// Komunikat błędu
				String errorInfo = String.format("Pole %s: %s", field.getName(), message);
				// Zwrócenie komunikatu o błędzie
				return Optional.of(errorInfo);
			}
		}
		// Zwócenie pustego optionala, jeśli walidacja się powiedzie
		return Optional.empty();
	}
}
