package edu.eci.arsw.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GrammarChecker {

	SpellChecker sc;

	String x;
        
        
	public SpellChecker getSpellChecker() {
		return sc;
	}

	@Autowired
	public void setSpellChecker(SpellChecker sc) {
		this.sc = sc;
	}


	public String check(String text){
		
		StringBuffer sb=new StringBuffer();
		sb.append("Spell checking output:"+sc.checkSpell(text));
		sb.append("Plagiarism checking output: Not available yet");
<<<<<<< HEAD
=======
		
		
>>>>>>> f5b6c30df07ebb0b3849071b44031aff98fcf4fc
		return sb.toString();
		
	}
	
	
}
