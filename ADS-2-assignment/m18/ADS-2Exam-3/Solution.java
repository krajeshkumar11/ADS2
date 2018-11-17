import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		In in = new In(file);
		while(in.hasNextLine()) {
			String stData = in.readLine();
			String[] line = stData.split(" ");
		    for (String linedata: line) {
		    	linedata = linedata.toLowerCase();
		    	if(st.contains(linedata)) {
		    		st.put(linedata, st.get(linedata) + 1);
		    	} else {
		    		st.put(linedata, 1);
		    	}
		    }
		}
		return st;
	}
}

class T9 {
	public TST  tst = null;
	public T9(BinarySearchST<String, Integer> st) {
		Iterable<String> keys = st.keys();
		tst = new TST();
		for (String key : keys) {
			if(key.length() > 0){
				tst.put(key, st.get(key));
			}
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		return tst.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		System.out.println(t9Signature);
		HashMap<Character, Integer> hmA = new HashMap<Character, Integer>();

		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		ArrayList<Integer> al = new ArrayList<Integer>();
		ArrayList<String> alS = new ArrayList<String>();
		for (String word : words) {
			Object freq = tst.get(word);
			hm.put(word, (Integer) freq);
			al.add((Integer) freq);
		}
		Collections.sort(al);
		for (int i = al.size() - 1; i >= 0; i--) {
			for (String word : words) {
				Object freq = tst.get(word);
				if(al.get(i) == (Integer) freq) {
					alS.add(word);
				}
			}
			k--;
			if(k == 0) {
				break;
			}
		}
		Collections.sort(alS);
		return alS;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
