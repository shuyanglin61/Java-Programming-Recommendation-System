
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Shuyang Lin 
 * @version Dec14 2021
 */

import java.util.*;

public class MovieRunnerWithFilters {
    /**
     * Create a new class named MovieRunnerWithFilters
     * that you will use to find the average rating of movies using different filters.
     * Copy the printAverageRatings method from the MovieRunnerAverage class into this class.
     * You will make several changes to this method:
       - Instead of creating a SecondRatings object, you will create a ThirdRatings object. Note that this only has one parameter,
         the name of a file with ratings data. 
       - Print the number of raters after creating a ThirdsRating object. 
       - You’ll call the MovieDatabase initialize method with the moviefile to set up the movie database. 
       - Print the number of movies in the database. 
       - You will call getAverageRatings with a minimal number of raters to return an ArrayList of type Rating. 
       - Print out how many movies with ratings are returned, then sort them, and print out the rating and title of each movie. 
       - For example, if you run the printAverageRatings method on the files ratings_short.csv and ratedmovies_short.csv
         with a minimal rater of 1, you should see
     */
    public static void printAverageRatings() {
        //SecondRatings second = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatings(35);
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
    
    /**
     * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByYear
     * that should be similar to printAverageRatings, but should also create a YearAfterFilter
     * and call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies
     * that have a specified number of minimal ratings and came out in a specified year or later.
     * Print the number of movies found, and for each movie found, print its rating, its year, and its title.
     */
    public static void printAverageRatingsByYearAfter() {
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        System.out.println("Score\t\t\tTitle");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
    /**
     * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByGenre
     * that should create a GenreFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating
     * of all the movies that have a specified number of minimal  ratings and include a specified genre.
     * Print the number of movies found, and for each movie, print its rating and its title on one line,
     * and its genres on the next line.
     */
    public static void printAverageRatingsByGenre() {
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        System.out.println("Score\t\t\tTitle");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + "\t\t\t" + MovieDatabase.getTitle(rating.getItem()));
            System.out.print("\t");
            String[] genres = MovieDatabase.getGenres(rating.getItem()).split(", ");
            for(int i=0;i<genres.length-1;i++) {
                System.out.print(genres[i] + ", ");
            }
            System.out.println(genres[genres.length-1]);
        }
        System.out.println("Program ends");
    }
    
    /**
     * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByMinutes
     * that should create a MinutesFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating
     * of all the movies that have a specified number of minimal ratings and their running time is at least
     * a minimum number of minutes and no more than a maximum number of minutes. Print the number of movies found,
     * and for each movie print its rating, its running time, and its title on one line
     */
    public static void printAverageRatingsByMinutes() {
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
    /**
     * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByDirectors
     * that should create a DirectorsFilter and call getAverageRatingsByFilter to get an ArrayList of type Rating
     * of all the movies that have a specified number of minimal ratings and include at least one of the directors specified.
     * Print the number of movies found, and for each movie print its rating and its title on one line,
     * and all its directors on the next line.
     */
    public static void printAverageRatingsByDirectors() {
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + "\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
    /**
     * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByYearAfterAndGenre
     * that should create an AllFilters object that includes criteria based on movies that came out in a specified year
     * or later and have a specified genre as one of its genres.
     * This method should call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies
     * that have a specified number of minimal ratings and the two criteria based on year and genre.
     * Print the number of movies found, and for each movie, print its rating, its year, and its title on one line,
     * and all its genres on the next line.
     */
    public static void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        AllFilters all = new AllFilters();
        all.addFilter(new YearAfterFilter(1990));
        all.addFilter(new GenreFilter("Drama"));
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(8, all);
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
     * In the MovieRunnerWithFilters class, create a void method named printAverageRatingsByDirectorsAndMinutes
     * that should create an AllFilters object that includes criteria based on running time
     * (at least a specified minimum number of minutes and at most a specified maximum number of minutes),
     * and directors (at least one of the directors in a list of specified directors—directors are separated by commas).
     * This method should call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies
     * that have a specified number of minimal ratings and the two criteria based on minutes and directors.
     * Print the number of movies found, and for each movie, print its rating, its time length, and its title on one line,
     * and all its directors on the next line.
     */
    public static void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings third = new ThirdRatings("data/ratings.csv");
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(90, 180));
        all.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        System.out.println("The number of raters are " + third.getRaterSize());
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(3, all);
        Collections.sort(ratings);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies are " + MovieDatabase.size());
        System.out.println("Found " + ratings.size() +" movies.");
        for(Rating rating: ratings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()) + "\n\t" + MovieDatabase.getDirector(rating.getItem()));
        }
        System.out.println("Program ends");
    }
    
}
