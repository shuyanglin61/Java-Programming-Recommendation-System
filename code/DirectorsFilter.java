
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author Shuyang Lin 
 * @version Dec14 2021
 */
import java.util.*;

public class DirectorsFilter implements Filter{
    private String[] directors;
    public DirectorsFilter(String directors) {
        this.directors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String director : directors) {
            if(MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
        }
        return false;
    }
}
