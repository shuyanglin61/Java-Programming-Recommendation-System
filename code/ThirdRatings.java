
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Shuyang Lin
 * @version Dec13 2021
 */

/**
 * Create a new class named ThirdRatings. Copy your code from SecondRatings into this class.
 * Movies will now be stored in the MovieDatabase instead of in the instance variable myMovies,
 * so you will want to remove the private variable myMovies.
 * The constructor will no longer have a moviefile parameter—movies will be stored in the MovieDatabase class.
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    /**
     * Note that the SecondRatings class has been started for you.
     * This class includes two private variables, one named myMovies of type ArrayList of type Movie,
     * and a second one named myRaters of type ArrayList of type Rater.
     * A default constructor has also been created for you. Until you create the second constructor (see below),
     * the class will not compile.
     */
    
    /**
     * ThirdRatings has only one private variable named myRaters to store an ArrayList of Raters.
     */
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    /**
     * A second constructor should have only one String parameter named ratingsfile.
     * This constructor should call the method loadRaters from the FirstRatings class to fill the myRaters ArrayList.
     */
    public ThirdRatings(String ratingsfile) {
        FirstRatings first = new FirstRatings();
        myRaters = first.loadRaters(ratingsfile);
    }

    
    /**
     * In the SecondRatings class, write a public method named getRaterSize,
     * which returns the number of raters that were read in and stored in the ArrayList of type Rater.
     */
    public int getRaterSize() {
        return myRaters.size();
    }
    
    /**
     * In the SecondRatings class, write a private helper method named getAverageByID that has two parameters:
     * a String named id representing a movie ID and an integer named minimalRaters.
     * This method returns a double representing the average movie rating for this ID
     * if there are at least minimalRaters ratings. If there are not minimalRaters ratings, then it returns 0.0.
     */
    private double getAverageByID(String id, int minimalRaters) {
        double average = 0;
        int count = 0;
        for(Rater rater: myRaters) {
            if(rater.hasRating(id)) {
                count++;
                average += rater.getRating(id);
            }
        }
        return count<minimalRaters?0:average/count;
    }
    
    /**
     * You will need to modify getAverageRatings.
     * Note that myMovies no longer exists.
     * Instead, you’ll need to get all the movies from the MovieDatabase class and store them in an ArrayList of movie IDs.
     * Thus, you will need to modify getAverageRatings to call MovieDatabase with a filter,
     * and in this case you can use the TrueFilter to get every movie.
     */
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
    
    /**
     *  In the ThirdRatings class, write a public helper method named getAverageRatingsByFilter that has two parameters,
     *  an int named minimalRaters for the minimum number of ratings a movie must have and a Filter named filterCriteria.
     *  This method should create and return an ArrayList of type Rating of all the movies that have at least minimalRaters
     *  ratings and satisfies the filter criteria. This method will need to create the ArrayList of type String of movie IDs
     *  from the MovieDatabase using the filterBy method before calculating those averages.
     */
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
}
