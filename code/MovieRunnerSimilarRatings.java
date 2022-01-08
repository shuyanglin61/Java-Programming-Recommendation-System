

/**
 * Write a description of ThirdRatings here.
 * 
 * @author Shuyang Lin
 * @version Jan07 2022
 */

/**
 * Create a new class named MovieRunnerSimilarRatings.
 * Copy the two methods printAverageRatings 
 * and printAverageRatingsByYearAfterAndGenre 
 * from MovieRunnerWithFilters to this new class 
 * and modify them to work with a FourthRatings object
 * instead of a fourthRatings object.
 * You can copy more of the methods into your new Runner class,
 * but these two should be enough to test that 
 * FourthRatings has been set up correctly.
 * When you run these two you should get the same output you get
 * when those methods run with the fourthRatings object.
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public static void printAverageRatings() {
        //SecondRatings second = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> ratings = fourth.getAverageRatings(35);
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        System.out.println("Score\t\t\tTitle");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + "\t\t\t" + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
    public static void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fourth = new FourthRatings();
        AllFilters all = new AllFilters();
        all.addFilter(new YearAfterFilter(1990));
        all.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = fourth.getAverageRatingsByFilter(8, all);
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()) + "\n\t" + MovieDatabase.getGenres(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
    /**
     * Write a void method printSimilarRatings that has no parameters.
     * This method creates a new FourthRatings object,
     * reads data into the MovieDatabase and RaterDatabase,
     * and then calls getSimilarRatings for a particular rater ID,
     * a number for the top number of similar raters,
     * and a number of minimal rateSimilarRatings,
     * and then lists recommended movies and their similarity ratings.
     */
    
    public static void printSimilarRatings() {
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> ratings = fourth.getSimilarRatings("71", 20, 5);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    /**
     * Write a void method printSimilarRatingsByGenre that has no parameters.
     * This method is similar to printSimilarRatings but also uses a genre filter and then lists recommended movies and their similarity ratings,
     * and for each movie prints the movie and its similarity rating on one line
     * and its genres on a separate line below it.
     */
    public static void printSimilarRatingsByGenre(){
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    /**
     * Write a void method printSimilarRatingsByDirector that has no parameters.
     * This method is similar to printSimilarRatings but also
     * uses a director filter and then lists recommended movies
     * and their similarity ratings,
     * and for each movie prints the movie and its similarity
     * rating on one line and its directors on a separate line
     * below it.
     */
    public static void printSimilarRatingsByDirector() {
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    /**
     * Write a void method printSimilarRatingsByGenreAndMinutes
     * that has no parameters. This method is similar to
     * printSimilarRatings but also uses a genre filter
     * and a minutes filter and then lists recommended
     * movies and their similarity ratings,
     * and for each movie prints the movie, its minutes,
     * and its similarity rating on one line and its genres
     * on a separate line below it. 
     */
    public static void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourth = new FourthRatings();
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("168", 10, 3, allFilters);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    /**
     * Write a void method printSimilarRatingsByYearAfterAndMinutes
     * that has no parameters. This method is similar to
     * printSimilarRatings but also uses a year-after filter
     * and a minutes filter and then lists recommended movies
     * and their similarity ratings, and for each movie prints
     * the movie, its year, its minutes, and its similarity
     * rating on one line.
     */
    public static void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fourth = new FourthRatings();
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> ratings = fourth.getSimilarRatingsByFilter("314", 10, 5, allFilters);
        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }
}
