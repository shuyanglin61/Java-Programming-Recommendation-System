
/**
 * Write a description of SecondRatings here.
 * 
 * @author Shuyang Lin
 * @version Dec13 2021
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    /**
     * Note that the SecondRatings class has been started for you.
     * This class includes two private variables, one named myMovies of type ArrayList of type Movie,
     * and a second one named myRaters of type ArrayList of type Rater.
     * A default constructor has also been created for you. Until you create the second constructor (see below),
     * the class will not compile.
     */
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    /**
     * Write an additional SecondRatings constructor that has two String parameters named moviefile and ratingsfile.
     * The constructor should create a FirstRatings object and then call the loadMovies and loadRaters methods
     * in FirstRatings to read in all the movie and ratings data and store them in the two private ArrayList variables of
     * the SecondRatings class, myMovies and myRaters.
    **/
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingsfile);
    }
    
    /**
     * In the SecondRatings class, write a public method named getMovieSize,
     * which returns the number of movies that were read in and stored in the ArrayList of type Movie.
     */
    public int getMovieSize() {
        return myMovies.size();
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
     * In the SecondRatings class, write a public method named getAverageRatings,
     * which has one int parameter named minimalRaters.
     * This method should find the average rating for every movie that has been rated by at least minimalRaters raters.
     * Store each such rating in a Rating object in which the movie ID and the average rating
     * are used in creating the Rating object.
     * The method getAverageRatings should return an ArrayList of all the Rating objects for movies
     * that have at least the minimal number of raters supplying a rating.
     * For example, if minimalRaters has the value 10, then this method returns rating information
     * (the movie ID and its average rating) for each movie that has at least 10 ratings.
     * You should consider calling the private getAverageByID method.
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ans = new ArrayList<>();
        for(Movie movie: myMovies) {
            double avgScore = getAverageByID(movie.getID(), minimalRaters);
            if(avgScore > 0)
                ans.add(new Rating(movie.getID(), avgScore));
        }
        return ans;
    }
    
    /**
     * In the SecondRatings class, write a method named getTitle that has one String parameter named id,
     * representing the ID of a movie. This method returns the title of the movie with that ID.
     * If the movie ID does not exist, then this method should return a String indicating the ID was not found.
     */
    public String getTitle(String id) {
        for(Movie movie: myMovies) {
            if(movie.getID().equals(id))
                return movie.getTitle();
        }
        return "No Found";
    }
    
    /**
     * In the SecondRatings class, write a method getID that has one String parameter named title representing the title of a movie. This method returns the movie ID of this movie. If the title is not found, return an appropriate message such as “NO SUCH TITLE.”  Note that the movie title must be spelled exactly as it appears in the movie data files.
     */
    public String getID(String title) {
        for(Movie movie: myMovies) {
            if(movie.getTitle().equals(title))
                return movie.getID();
        }
        return "NO SUCH TITLE";
    }
}
