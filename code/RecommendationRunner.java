
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Shuyang Lin
 * @version Jan07 2022
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        int size = 20;
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random random = new Random();
        while(ans.size()<size) {
            String temp = movies.get(random.nextInt(movies.size()));
            if(!ans.contains(temp)) {
                ans.add(temp);
            }
        }
        return ans;
    }
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fourth = new FourthRatings();
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        ArrayList<Rating> cans = fourth.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        if(cans.size()==0) {
            System.out.println("No recommendations yet, try again!");
        } else {
            int i=0;
            for(Rating r: cans) {
                i++;
                String poster = MovieDatabase.getPoster(r.getItem());
                if(poster.length()>3) {
                    System.out.println("<img src = \"" + poster + "\" target=_blank></td>");
                }
                System.out.println("<br><b>"+MovieDatabase.getTitle(r.getItem()) + "</b><br>");
                System.out.println("by "+MovieDatabase.getDirector(r.getItem())+"<br>");
                System.out.println(MovieDatabase.getGenres(r.getItem()) + "<br>");
                System.out.println(MovieDatabase.getYear(r.getItem()) + "<br>");
                System.out.println(MovieDatabase.getCountry(r.getItem())+"<br>");
                System.out.println(MovieDatabase.getMinutes(r.getItem()) + " minutes<br>");
                if(i>10)
                    break;
            }
        }
    }
}
