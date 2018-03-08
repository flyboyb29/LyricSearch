package nlp.musicsearch;

import java.io.Serializable;

/**
 *
 * @author Jason Hall
 */
public class CountProb implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2086922328425537508L;
	//
    private int myCount;
    //
    private double myProb;
    
    /**
     * 
     */
    CountProb() {
        myCount = 0;
        myProb = 0;
    }
    
    /**
     * 
     * @param count 
     */
    CountProb(int count) {
        myCount = count;
        myProb = 0;
    }
    
    /**
     * 
     * @param count
     * @param totalCount 
     */
    CountProb(int count, double totalCount) {
        myCount = count;
        myProb = myCount / totalCount;
    }
    
    /**
     * 
     */
    public void updateCount() {
        myCount++;
    }
    
    /**
     * 
     * @param Count 
     */
    public void updateCount(int Count) {
        myCount = Count;
    }
    
    /**
     * 
     * @param totalCount 
     */
    public void updateProb(double totalCount) {
        myProb = myCount / totalCount;
    }
    
    /**
     * 
     * @return 
     */
    public int getCount () {
        return myCount;
    }
    
    /**
     * 
     * @return 
     */
    public double getProb() {
        return myProb;
    }
}
