package demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import demo.model.Name;

public class Sorting {

	public static void main(String[] args) {

		Name nameArray[] = {
				new Name("John", "Smith"),
	            new Name("Karl", "Ng"),
	            new Name("Jeff", "Smith"),
	            new Name("Tom", "Rich")};
	        
	    List<Name> names = Arrays.asList(nameArray);
	    
	    Comparator<Name> byFirstName = (n1, n2) -> n1.getFirstname()
	            .compareTo(n2.getFirstname());

	    Comparator<Name> byLastName = (n1, n2) -> n1.getLastname()
	    		.compareTo(n2.getLastname());
	    
	    Collections.sort(names, new Comparator<Name>() {
			@Override
			public int compare(Name n1, Name n2) {
				return n1.getLastname().compareToIgnoreCase(n2.getLastname());
			}
		});
	    
//	    Collections.sort(names, byLastName.thenComparing(byFirstName));
	    System.out.println(names);
	    
	    System.out.println();
	    
	    names.stream()
	    	.sorted(byLastName.thenComparing(byFirstName))
	    	.forEach(s -> System.out.println(s));
	    
	    System.out.println();
		
		List<String>list = Arrays.asList("d","b","e","c");
		list.stream()
			.sorted((s1, s2) -> s1.compareTo(s2))
			.forEach(s -> System.out.println("\t" + s));
	}
}
