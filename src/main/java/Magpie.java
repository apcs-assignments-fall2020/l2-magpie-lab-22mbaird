/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.isEmpty()){
            System.out.println("You did not input anything");
        }
        else if (findWord(statement,"no") >= 0)
        {
            response = "Why so negative?";
        }
        else if (findWord(statement,"Nathan") >= 0)
        {
            response = "He sounds like a good teacher.";
        }
        else if (findWord(statement,"mother") >= 0
                || findWord(statement,"father") >= 0
                || findWord(statement,"sister") >= 0
                || findWord(statement,"brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (findWord(statement,"dog") >= 0
                || findWord(statement,"cat") >= 0){
            response = "Tell me more about your pets.";

                }
        else if (findWord(statement,"Coach") >= 0)
        {
            response = "I had a pretty cool coach once";
        }
        else if (findWord(statement,"thanks") >= 0)
        {
            response = "No problemo.";
        }
        else if (findWord(statement,"sports") >= 0)
        {
            response = "Sports!";
        }
        else if (findWord(statement, "I want") >= 0){
            response = transformIWantStatement(statement);
        }
        else if (findWord(statement, "I want to") >= 0){
            response = transformIWantToStatement(statement);
        }
        else if (findWord(statement, "I") >= 0 
                && findWord(statement, "you") >= 0){
            response = transformIYouStatement(statement);
        }
        else if (findWord(statement, "You") >= 0 
                && findWord(statement, "Me") >= 0){
            response = transformYouMeStatement(statement);
        }
        else
        {
            
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 7;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
        else if (whichResponse == 4)
        {
            response = "Perhaps";
        }
        else if (whichResponse == 5)
        {
            response = "Anyways, how was your day?";
        }
    
        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String str, String word) {
        String tr = str.trim();
        int word_loc = tr.toLowerCase().indexOf(word.toLowerCase(), 0);
        while (word_loc >=0){
            String nx = " ";
            String x = " ";
            if (word_loc>0){
                nx = tr.substring(word_loc-1, word_loc).toLowerCase();
            }
            if(word_loc+word.length()<tr.length()){
                x = tr.substring(word_loc+word.length(), word_loc+ word.length()+1).toLowerCase();
            }
            if(((nx.compareTo("a")<0||nx.compareTo("z")>0&& x.compareTo("a")<0|| x.compareTo("z")>0))){
                return word_loc;

            }
            word_loc = tr.indexOf(word.toLowerCase(), word_loc + 1);
        }
        return -1;
            
        }
        

    
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement){
                   
        int word_loc = findWord(statement, "I want");
        String towhat = statement.substring(word_loc+7);
        return "Would you really be happy if you had " + towhat + "?";

    }
       

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement){
        
        int x = findWord(statement, "I");
        int y = findWord(statement, "you");
        String towhat = statement.substring(x+2,y-1);
        return "Why do you " + towhat + " me?";
        
    }
   
            
       
    

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement){
        
        int word_loc = findWord(statement, "I want to");
        String towhat = statement.substring(word_loc+10);
        return "What would it mean to " + towhat+ "?";
    }
     
        
    




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement){
        
        int x = findWord(statement, "you");
        int y = findWord(statement, "me");
        String towhat = statement.substring(x+4,y-1);
        return "What makes you think that I " + towhat+ " you?";
    }
   
}
