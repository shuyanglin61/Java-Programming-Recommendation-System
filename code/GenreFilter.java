/**
 * Write a description of GenreFilter here.
 * 
 * @author Shuyang Lin 
 * @version Dec14 2021
 */

public class GenreFilter implements Filter{
    private String myGenre;
    
    public GenreFilter(String genre) {
	myGenre = genre;
    }
	
    @Override
    public boolean satisfies(String id) {
        String[] genres = MovieDatabase.getGenres(id).split(", ");
        for(String genre: genres)
            if(genre.equals(myGenre))
                return true;
        return false;
    }
}
