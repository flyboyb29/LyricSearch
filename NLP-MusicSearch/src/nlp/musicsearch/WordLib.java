package nlp.musicsearch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jason Hall
 */
public class WordLib implements Serializable {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6457593418098410500L;
	private StringBuilder sb;
    //
    private final Map<String, SongList> theNormWordInSong;
    //
    private final Map<String, SongList> theWordInSong;
    //
    private int theWordCount;
    //
    private final Map<String, String> theWordToNorm;
    //
    private final Map<String, List<String>> theNormToWord;
    
    /**
     * 
     */
    WordLib() {
        theNormWordInSong = new HashMap<>();
        theWordInSong = new HashMap<>();
        theWordToNorm = new HashMap<>();
        theNormToWord = new HashMap<>();
        theWordCount = 0;
    }
    
    /**
     * getTotalWordCount:
     * 
     * @return the total number of words found in all 
     */
    public int getTotalWordCount() {
        return theWordCount;
    }
    
    /**
     * getSongWordCount:
     * 
     * @param mySong the song that you want the specific word count for
     * @return the number of time the word has been seen in the song
     */
    public int getSongWordCount(String mySong) {
        int wordTot = 0;
        
        for (String key: theWordInSong.keySet()) {
            if (theWordInSong.get(key).hasSong(mySong)) {
                wordTot += theWordInSong.get(key).getTimesFoundInSong(mySong);
            }
        }
        
        return wordTot;
    }
    
    public double getWordProb(String myWord) {
        return theWordInSong.get(myWord.toUpperCase()).getProbWordFound();
    }
    
    /**
     * getWordInfo:
     * 
     * @return returns the word, number of time that word occurs, and the 
     * probability that the word will occur
     */
    public String getWordInfo() {
        sb = new StringBuilder();
        
        theWordInSong.keySet().stream().forEach((key) -> {
            sb.append("Word: ");
            sb.append(key);
            sb.append(", Count: ");
            sb.append(theWordInSong.get(key).getTotalTimesWordFound());
            sb.append(", Probability: ");
            sb.append(theWordInSong.get(key).getProbWordFound());
            sb.append("\n");
        });
        //System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String getSongInfo (String songTitle) {
        sb = new StringBuilder();
        theWordInSong.keySet().stream().forEach((key) -> {
            if (theWordInSong.get(key).hasSong(songTitle)) {
                sb.append("Word: ");
                sb.append(key);
                sb.append(", Count: ");
                sb.append(theWordInSong.get(key).getTimesFoundInSong(songTitle));
                sb.append(", Probability: ");
                sb.append(theWordInSong.get(key).getProbFoundInSong(songTitle));
                sb.append("\n");
            }
        });
        return sb.toString();
    }
    
    /**
     * checkWord:
     * 
     * @param input
     * @return 
     */
    private String checkWord (String input) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(input.charAt(i))) {
                sb.append(input.charAt(i));
            } else if (input.charAt(i) == '\'') {
                sb.append(input.charAt(i));
            }
        }
        
        return sb.toString();
    }
    
    /**
     * trainSong:
     * 
     * @param mySongTitle
     * @param mySongLyrics
     * @return true if we are able to train
     */
    public boolean trainSong(String mySongTitle, String mySongLyrics) {
        boolean finished;
        Scanner word = new Scanner(mySongLyrics);
        int numWords = 0;
        int wordCount;
        String myWord;
        
        try {
            finished = true;
            while (word.hasNext()) {
                numWords++;
                myWord = word.next();
                myWord = checkWord(myWord);
                myWord= myWord.toUpperCase();
                
                if (theWordInSong.containsKey(myWord)) {
                    theWordInSong.get(myWord)
                            .updateList(mySongTitle, theWordCount + numWords);
                } else {
                    theWordInSong.put(myWord, new SongList());
                    theWordInSong.get(myWord)
                            .updateList(mySongTitle, theWordCount + numWords);
                }
            }
            
            wordCount = numWords;
            theWordCount += numWords;
            theWordInSong.keySet().stream().forEach((String key) -> {
                theWordInSong.get(key).updateTotalProb(theWordCount);
                if (theWordInSong.get(key).hasSong(mySongTitle)) {
                    theWordInSong.get(key).updateSongProb(mySongTitle
                            , wordCount);
                }
            });
        } catch (Exception e) {
            finished = false;
        }
        
        word.close();
        
        return finished;
    }

    /**
     * getSongList:
     * 
     * @param myWord is the word that we want the songs that is associated with
     * @return a list of songs
     */
    public String[] getSongList(String myWord) {
        return theWordInSong.get(myWord.toUpperCase()).getSongList();
    }

    /**
     * getWordSongProb: get the probability that a certain word is found in a 
     * specific song.
     * 
     * @param myWord the word that we are going to look at
     * @param mySong the song that we are going to look at
     * @return the probability that the specific word is in the specific song
     */
    public double getWordSongProb(String myWord, String mySong) {
        return theWordInSong.get(myWord.toUpperCase()).getProbFoundInSong(mySong);
    }

    public boolean hasWord(String myWord) {
        return theWordInSong.containsKey(myWord.toUpperCase());
    }
}
