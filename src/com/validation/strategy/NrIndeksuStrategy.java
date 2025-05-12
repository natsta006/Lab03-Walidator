package com.validation.strategy;

import java.lang.reflect.Field;
import java.util.Optional;

import com.validation.annotation.NrIndeksu;

public class NrIndeksuStrategy implements ValidationStrategy {

	@Override
	public Optional<String> validate(Field field, Object value) {
		// Sprawdzenie czy pole ma adnotację NrIndeksu oraz czy wartość jest typu String
		if(field.isAnnotationPresent(NrIndeksu.class) && value instanceof String) {
			// Regex do sprawdzenia poprawności indeksu
			String indexRegex = "^[0-9]+$";
			// Rzutowanie wartości pola na String
			String index = (String) value;
			// Sprawdzenie czy podany indeks jest prawidłowy za pomocą regex
			if(!index.matches(indexRegex)) {
				// Pobranie adnotacji z pola
				NrIndeksu annotation = field.getAnnotation(NrIndeksu.class);
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
