package nlp.musicsearch;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jason Hall
 */
public class SongList implements Serializable {
    
    // a map from the song title that contains a certain word to the times it
    // appears and the probability that it will appear.
    private final Map<String, CountProb> theSongs;
    
    // holds the total number of times the word has been seen and the 
    // probability that this word will be seen altogether.
    private final CountProb theWordCount;
    
    /**
     * constructor for SongList. 
     */
    SongList() {
        theSongs = new HashMap<>();
        theWordCount = new CountProb();
    }
    
    /**
     * updateList:
     * This method will try and either update the count and probability that a 
     * word will be seen in a certain song, or add that song and the count and
     * probability for it.
     * 
     * @param mySongTitle is the title if the song.
     * @param myTotalWordCount is the total word count of everything.
     * @return a boolean weather or not we where able to complete the operation.
     */
    public boolean updateList(String mySongTitle, int myTotalWordCount) {
        boolean added;
        
        try {
            theWordCount.updateCount();
            theWordCount.updateProb(myTotalWordCount);
            if (theSongs.containsKey(mySongTitle)) {
                theSongs.get(mySongTitle).updateCount();
                theSongs.get(mySongTitle).updateProb(myTotalWordCount);
            } else {
                theSongs.put(mySongTitle, new CountProb(1, myTotalWordCount));
            }
            added = true;
        } catch (Exception e) {
            added = false;
        }
        
        return added;
    }
    
    /**
     * getSongList: gets a list of songs
     * 
     * @return all the songs that is associated with this word. 
     */
    public String[] getSongList () {
        String[] temp = new String[theSongs.size()];
        int i = 0;
        for (String key: theSongs.keySet()) {
            temp[i] = key;
            i++;
        }
        
        return temp;
    }
    
    /**
     * hasSong: Returns if the word we are looking at contains a certain song.
     * 
     * @param mySong The song that you are wondering about
     * @return a boolean value based on whether the song has a word.
     */
    public boolean hasSong(String mySong) {
        return theSongs.containsKey(mySong);
    }
    
    /**
     * 
     * @param mySong
     * @param myTotalWordCount 
     */
    public void updateSongProb(String mySong, int myTotalWordCount) {
        theSongs.get(mySong).updateProb(myTotalWordCount);
    }
    
    /**
     * 
     * @param myTotalWordCount 
     */
    public void updateTotalProb(int myTotalWordCount) {
        theWordCount.updateProb(myTotalWordCount);
        //theSongs.keySet().stream().forEach((key) -> {
        //    theSongs.get(key).updateProb(myTotalWordCount);
        //});
    }
    
    /**
     * getSongTotalCount:
     * 
     * @return the amount of song titles associated with a word.
     */
    public int getTotalSongCount() {
        return theSongs.size();
    }
    
    /**
     * getTotalTimesWordFound:
     * 
     * @return the total number of times this word has been seen.
     */
    public int getTotalTimesWordFound() {
        return theWordCount.getCount();
    }
    
    /**
     * getProbWordFound:
     * 
     * @return the probability that the word will be seen in any song.
     */
    public double getProbWordFound() {
        return theWordCount.getProb();
    }
    
    /**
     * getTimesFoundInSong:
     * returns the number of times a word is found in a song.
     * 
     * @param mySongTitle the song that you want to get the count from.
     * @return the count of the number of times the word has been seen in the
     *  song.
     */
    public int getTimesFoundInSong(String mySongTitle) {
        return theSongs.get(mySongTitle).getCount();
    }
    
    /**
     * getProbFoundInSong:
     * returns the probability that the word will be found in the song.
     * 
     * @param mySongTitle the song that you want to get the probability from.
     * @return the probability that the word is in the song.
     */
    public double getProbFoundInSong(String mySongTitle) {
        return theSongs.get(mySongTitle).getProb();
    }
}
