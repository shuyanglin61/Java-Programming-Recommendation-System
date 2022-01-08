
/**
 * Write a description of MinutesFilter here.
 * 
 * @author Shuyang Lin 
 * @version Dec14 2021
 */
public class MinutesFilter implements Filter{
    private int min, max;
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getMinutes(id) >= min & MovieDatabase.getMinutes(id) <=max)
            return true;
        return false;
    }
}
