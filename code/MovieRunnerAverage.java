
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Shuyang Lin 
 * @version Dec14 2021
 */
import java.util.*;

public class MovieRunnerAverage {
    /**
     * Create a new class named MovieRunnerAverage.
     * In this class, create a void method named printAverageRatings that has no parameters. This method should:
     * Create a SecondRatings object and use the CSV filenames of movie information and ratings information
     * from the first assignment when calling the constructor.
     * Print the number of movies and number of raters from the two files by calling the appropriate methods
     * in the SecondRatings class.
     * Test your program to make sure it is reading in all the data from the two files.
     * For example, if you run your program on the files ratings_short.csv and ratedmovies_short.csv,
     * you should see 5 raters and 5 movies.
     * We will add more code to this method in a bit.
     */
    
     /**
     * In the MovieRunnerAverage class in the printAverageRatings method,
     * add code to print a list of movies and their average ratings,
     * for all those movies that have at least a specified number of ratings, sorted by averages.
     * Specifically, this method should print the list of movies, one movie per line
     * (print its rating first, followed by its title) in sorted order by ratings,
     * lowest rating to highest rating. For example,
     * if printAverageRatings is called on the files ratings_short.csv and ratedmovies_short.csv with the argument 3,
     * then the output will display two movies:
     */
    
    public static void printAverageRatings() {
        //SecondRatings second = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        SecondRatings second = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        System.out.println("The number of movies are " + second.getMovieSize());
        System.out.println("The number of raters are " + second.getRaterSize());
        ArrayList<Rating> ratings = second.getAverageRatings(12);
        Collections.sort(ratings);
        System.out.println("Score\t\t\tTitle");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + "\t\t\t" + second.getTitle(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
    /**
     * In the MovieRunnerAverage class, write the void method getAverageRatingOneMovie, which has no parameters.
     * This method should first create a SecondRatings object, reading in data from the movie and ratings data files.
     * Then this method should print out the average ratings for a specific movie title, such as  the movie “The Godfather”.
     * If the moviefile is set to the file named ratedmovies_short.csv, and the ratingsfile is set to the file ratings_short.csv,
     * then the average for the movie “The Godfather” would be 9.0.
     */
    public static void getAverageRatingOneMovie() {
        //SecondRatings second = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        SecondRatings second = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        ArrayList<Rating> ratings = second.getAverageRatings(0);
        for(Rating rating: ratings) {
            if(second.getTitle(rating.getItem()).equals("Vacation")) {
                System.out.println(rating.getValue());
                return;
            }
        }
    }
    public static void main(String[] args) {
        MovieRunnerAverage.printAverageRatings();
        MovieRunnerAverage.getAverageRatingOneMovie();
    }  
}
