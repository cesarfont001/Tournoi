// autor : Norman & César 2018-2019
package ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import model.Match;
import model.MatchEnum;
import model.Tournoi;
import model.TournoiList;

public class Ctrl {

     private final TournoiList tournoiList;
    
    public Ctrl(TournoiList tournoiList){
        this.tournoiList = tournoiList;
    }
    
    public void tournoiSelection(int numTournoi){
        if (numTournoi >= 0 && numTournoi < tournoiList.nbTournois())
            tournoiList.select(numTournoi);
        else
            tournoiList.unselect();
    }
    
    public Tournoi selectedTournoi(){
        return tournoiList.selectedTournoi();
    }
    
    public List<String> getTournoiList(){
        return this.tournoiList.getNomTournois();
    }
    public boolean addMatch(Match match){
        return tournoiList.selectedTournoi().addMatch(match);
    }
    
    public MatchEnum MatchValide(Match match, ArrayList<Match> ml){
        return Tournoi.valider(match, ml);
    }
    
    public void addObserverTournoi(Observer o) {
        this.tournoiList.getTournois().forEach((t) -> {
            t.addObserver(o);
         });
    }
    public void matchSelection(int index) {
        if (index >= 0 && index < this.selectedTournoi().nbMatchs())
            this.selectedTournoi().selectIndex(index);
        else
            this.selectedTournoi().unselectIndex();
    }
    public boolean deleteMatch() {
        return this.selectedTournoi().deleteMatch();
    }
}
