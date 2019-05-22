import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates how a ConcurrentModificationException can occur in a single-threaded program
 */
public class SingleThreadConcurrentException {
	
	public static void main(String[] args) {
		
		List<String> words = new ArrayList<>();
		words.add("Java");
		words.add("C++");
		words.add("C#");
		
		for(String word: words) {

			words.add("Angular");
			
			System.out.println("Languages: " + Arrays.toString(words.toArray()));
			
		}
		
	}

}
