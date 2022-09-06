package edu.eci.arsw.springdemo;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

<<<<<<< HEAD
//@Service
=======
@Service
>>>>>>> f5b6c30df07ebb0b3849071b44031aff98fcf4fc
public class SpanishSpellChecker implements SpellChecker {

	@Override
	public String checkSpell(String text) {
		return "revisando ("+text+") con el verificador de sintaxis del espanol ";
	}

}
