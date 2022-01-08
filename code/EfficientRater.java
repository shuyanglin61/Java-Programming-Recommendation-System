/**
 * Write a description of class EfficientRater here.
 * 
 * @author Shuyang Lin
 * @version Dec14 2021
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings;
    
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        return myRatings.containsKey(item)?myRatings.get(item).getValue():-1;
    }

    public int numRatings() {
        return myRatings.size();
    }
    
    public ArrayList<String> getItemsRated() {
        return new ArrayList<>(myRatings.keySet());
    }
}
