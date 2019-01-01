package de.jsfpraxis.advanced.i18n;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Prüft, ob ein Datum mehr als 18 Jahre in der Vergangenheit liegt.
 * 
 *
 */
public class VolljaehrigValidator implements ConstraintValidator<Volljaehrig, LocalDate> {

	@Override
	public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
		return LocalDate.from(date).until(LocalDate.now(), ChronoUnit.YEARS) >= 18;
	}

}
