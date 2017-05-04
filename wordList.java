import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class wordList 
{
	private int N = 2000;
	private final bucket[] arr;
	
	public wordList()
	{
		arr = new bucket[N];
		for (int i = 0; i < N; i++)
		{
			arr[i] = new bucket();
		}
	}
	
	private int hashIt(String input) 
	{
        return (input.hashCode() & 0x7fffffff) % N;
    }

    public void add(String word) 
    {
        arr[hashIt(word)].placeWord(word);
    }
 
    public boolean contains(String input) 
    {
        input = input.toLowerCase();
        System.out.println("Hash code for " + input + ": " + hashIt(input));
        return arr[hashIt(input)].get(input);
        
    }

    public void build(String file) 
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String newWord;
            
            while ((newWord = br.readLine()) != null) 
            {
                add(newWord);
            }
            
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }

    }
}
