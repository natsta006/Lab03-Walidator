package com.validation;

import com.validation.exception.ValidationException;
import com.validation.validator.Validator;

public class Main {

	public static void main(String[] args) {
		try {
		 Student student = new Student();
		 student.setEmail("Grzegorz.Brzęczyszczykiewicz#pbs.edu.pl");
		 
		 Validator.validate(student);
		} catch (ValidationException e) {
		 System.out.println(e.getMessage());
		}
		try {

			 Student student2 = new Student();
			 student2.setImie("a");
			 student2.setNazwisko("b");
			 student2.setNrIndeksu("abc");
			 student2.setEmail(" ");
			 
			 Validator.validate(student2);
			} catch (ValidationException e) {
			 System.out.println(e.getMessage());
			}
		 }
}
