package com.validation.strategy;

import java.lang.reflect.Field;
import java.util.Optional;
import com.validation.annotation.NotEmpty;

public class NotEmptyStrategy implements ValidationStrategy {

	@Override
	public Optional<String> validate(Field field, Object value) {
		// Sprawdzenie czy pole posiada adnotację NotEmpty i czy wartość to pusty string (po usunięciu białych znaków) 
		if(field.isAnnotationPresent(NotEmpty.class) && (value instanceof String && ((String) value).trim().isEmpty())){
			// Pobranie adnotacji z pola
			NotEmpty annotation = field.getAnnotation(NotEmpty.class);
			// Komunikat błędu
			String errorInfo = String.format("Pole %s: %s", field.getName(), annotation.message());
			// Zwrócenie komunikatu o błędzie
			return Optional.of(errorInfo);
		}
		// Zwócenie pustego optionala, jeśli walidacja się powiedzie
		return Optional.empty();
	}

}
