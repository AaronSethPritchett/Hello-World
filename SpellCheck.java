import java.util.*;

public class SpellCheck 
{	
	private static wordList wL;
    final static char[] characters = "abcdefghijklmnopqrstuvwxyz&-'".toCharArray();
    
	public static void main(String[] args) 
	{
	    SpellCheck sC = new SpellCheck();
	    sC.run();
	}
	
    SpellCheck() 
    {
        wL = new wordList();
        wL.build("words.txt");
    }

    void run() 
    {
        Scanner scan = new Scanner(System.in);
        String in;

        while (true) 
        {
            System.out.print("\nOh, so you need my superior intellect to help you spell a word. Go on with it: ");
            in = scan.nextLine();
            if (in.equals("")) 
            {
                System.out.println("You didn't type anything.. please don't waste my time");
                break;
            }
            if (wL.contains(in)) 
            {
                System.out.println("\n" + in + " is spelled correctly. GOOD JOB :)");
            } 
            else 
            {
                System.out.print("\n" + in + " is misspelled you dummy. ");
                System.out.println(suggestions(in));
            }
        }
    }

    String suggestions(String in) 
    {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> print = makeSuggestions(in);
        if (print.size() == 0) 
        {
            return "I have no earthly clue as to what you mean.\n";
        }
        sb.append("Could you mean..:\n");
        for (String s : print) 
        {
            sb.append("\n" + s);
        }
        return sb.toString();
    }

    private ArrayList<String> makeSuggestions(String in) 
    {
        ArrayList<String> suggestions = new ArrayList<>();
        suggestions.addAll(missingChar(in));
        suggestions.addAll(extraChar(in));
        suggestions.addAll(swapChar(in));
        return suggestions;
    }

    private ArrayList<String> missingChar(String in) 
    { 
        ArrayList<String> missing = new ArrayList<>();
        
        for (char c : characters) 
        {
            String inFront = c + in;
            String behind = in + c;
            if (wL.contains(inFront)) 
            {
                missing.add(inFront);
            }
            if (wL.contains(behind)) 
            {
                missing.add(behind);
            }
        }
        return missing;
    }

    private ArrayList<String> extraChar(String in) 
    {   
        ArrayList<String> subStr= new ArrayList<>();

        if (wL.contains(in.substring(1))) 
        {
            subStr.add(in.substring(1));
        }
        for (int i = 1; i < in.length() - 1; i++) 
        {
            String s = in.substring(0, i);
            s = s.concat(in.substring((i + 1), in.length()));
            
            if (wL.contains(s)) 
            {
                subStr.add(s);
            }
        }
        
        if (wL.contains(in.substring(0, in.length() - 1))) 
        {
            subStr.add(in.substring(0, in.length() - 1));
        }
        return subStr;
    }

    private ArrayList<String> swapChar(String in) 
    {   
        ArrayList<String> swapped = new ArrayList<>();

        for (int i = 0; i < in.length() - 1; i++) 
        {
            String s = in.substring(0, i);
            s = s + in.charAt(i + 1); 
            s = s + in.charAt(i); 
            s = s.concat(in.substring((i + 2))); 
            
            if (wL.contains(s)) 
            {
                swapped.add(s);
            }
        }
        return swapped;
    }
}