
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Shuyang Lin
 * @version Jan07 2022
 */
import java.util.*;

/**
 * Create a new class named FourthRatings.
 * Copy over the following methods from the class ThirdRatings
 * and get FourthRatings to compile.
 * Do not copy over any of the other methods.
 * You should not copy, nor
 * should you have any instance variables in FourthRatings—
 * you'll use RaterDatabase and MovieDatabase
 * static methods in place of instance variables—
 * so where you have code with myRaters,
 * you need to replace the code with calls to methods
 * in the RaterDatabase class.
 * The methods to copy into FourthRatings from ThirdRatings
 * are below (you'll need to modify these after copying):
 * getAverageByID, getAverageRatings,
 * and getAverageRatingsByFilter
 */

public class FourthRatings {
    public FourthRatings(){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double average = 0;
        int count = 0;
        for(Rater rater: RaterDatabase.getRaters()) {
            if(rater.hasRating(id)) {
                count++;
                average += rater.getRating(id);
            }
        }
        return count<minimalRaters?0:average/count;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ans = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie: movies) {
            double avgScore = getAverageByID(movie, minimalRaters);
            if(avgScore > 0)
                ans.add(new Rating(movie, avgScore));
        }
        return ans;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ans = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movie: movies) {
            double avgScore = getAverageByID(movie, minimalRaters);
            if(avgScore > 0)
                ans.add(new Rating(movie, avgScore));
        }
        return ans;
    }
    
    /**
     * In the FourthRatings class, write the following methods—
     * two are private helper methods,
     * and one is the method that will
     * return movie recommendations based on similarities:
     */
    
    /**
     * Write the private helper method named dotProduct,
     * which has two parameters,
     * a Rater named me and a Rater named r.
     * This method should first translate a rating
     * from the scale 0 to 10 to the scale -5 to 5
     * and return the dot product of the ratings
     * of movies that they both rated.
     * This method will be called by getSimilarities.
     */
    
    private double dotProduct(Rater me, Rater r) {
        double ans = 0;
        for(String mine: me.getItemsRated()) {
            if(r.hasRating(mine) && me.getRating(mine)>0 && r.getRating(mine)>0) {
                ans += (me.getRating(mine)-5)*(r.getRating(mine)-5);
            }
        }
        return ans;
    }
    
    /**
     * Write the private method named getSimilarities,
     * which has one String parameter named id—
     * this method computes a similarity rating
     * for each rater in the RaterDatabase
     * (except the rater with the ID given by the parameter)
     * to see how similar they are to the Rater
     * whose ID is the parameter to getSimilarities.
     * This method returns an ArrayList of type Rating
     * sorted by ratings from highest to lowest rating
     * with the highest rating first and only including
     * those raters who have a positive similarity rating
     * since those with negative values are not similar in any way.
     * Note that in each Rating object the item field 
     * is a rater’s ID, and the value field is the dot product
     * comparison between that rater and the rater 
     * whose ID is the parameter to getSimilarities.
     * Be sure not to use the dotProduct method with parameter
     * id and itself!
     */
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> ans = new ArrayList<>();
        for(Rater r: RaterDatabase.getRaters()) {
            if(r.getID().equals(id)){
                continue;
            }
            double temp = dotProduct(RaterDatabase.getRater(id), r);
            if(temp>=0)
                ans.add(new Rating(r.getID(), temp));
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
    
    /**
     * Write the public method named getSimilarRatings,
     * which has three parameters:
     * a String named id representing a rater ID,
     * an integer named numSimilarRaters,
     * and an integer named minimalRaters.
     * This method should return an ArrayList of type Rating,
     * of movies and their weighted average ratings using only
     * the top numSimilarRaters with positive ratings
     * and including only those movies that have
     * at least minimalRaters ratings from those most similar
     * raters (not just minimalRaters ratings overall).
     */
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> ans = new ArrayList<>();
        ArrayList<Rating> sims = getSimilarities(id);
        for(String movie: MovieDatabase.filterBy(new TrueFilter())) {
            int count = 0;
            double sum = 0;
            for(int k=0; k<numSimilarRaters; k++) {
                Rater r = RaterDatabase.getRater(sims.get(k).getItem());
                if(r.hasRating(movie) && r.getRating(movie)>0) {
                    count++;
                    sum += r.getRating(movie)*sims.get(k).getValue();
                }
            }
            if(count >= minimalRaters) {
                ans.add(new Rating(movie, sum/count));
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
    
    /**
     * Write the public method getSimilarRatingsByFilter,
     * which is similar to the getSimilarRatings method
     * but has one additional Filter parameter named
     * filterCriteria and uses that filter to access
     * and rate only those movies that match the filter
     * criteria.
     */
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ans = new ArrayList<>();
        ArrayList<Rating> sims = getSimilarities(id);
        for(String movie: MovieDatabase.filterBy(filterCriteria)) {
            int count = 0;
            double sum = 0;
            for(int k=0; k<numSimilarRaters; k++) {
                Rater r = RaterDatabase.getRater(sims.get(k).getItem());
                if(r.hasRating(movie)) {
                    count++;
                    sum += r.getRating(movie)*sims.get(k).getValue();
                }
            }
            if(count >= minimalRaters) {
                ans.add(new Rating(movie, sum/count));
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
}
