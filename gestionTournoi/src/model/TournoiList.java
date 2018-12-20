/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Norman
 */
public final class TournoiList extends Observable{
    
    public enum TypeNotif { INIT, TOURNOI_SELECTED, TOURNOI_UNSELECTED }
    
    private int numTournoiSelected = -1;
    private List<Tournoi> tournoiList = new ArrayList<>();
    ObservableList<Tournoi> olTournoi = FXCollections.observableList(tournoiList);
    
    public TournoiList() {
        initTournoi();
    }
    
    public List<Tournoi> getTournois() {
        return tournoiList;
    }
    
    public void setTournoiList(List<Tournoi> tournoiList) {
        this.tournoiList = tournoiList;
    }
    
    public Tournoi selectedTournoi(){
        return tournoiList.get(numTournoiSelected);
    }
    
    public int nbTournois(){
        return tournoiList.size();
    }
    
    public boolean addTournoi(Tournoi t){
        tournoiList.add(t);
        return true;
    }
    
    public String nameSelectedTournament() {
        return tournoiList.get(numTournoiSelected).getNomTournoi();
    }
    
    public List<String> getNomTournois(){
        List<String> nameTournamentTab = new ArrayList<>();
        
        tournoiList.forEach((t) -> {
            nameTournamentTab.add(t.getNomTournoi());
        });
        return nameTournamentTab;
    }
    
    public void select(int index) {
        numTournoiSelected = index;
        notif(TypeNotif.TOURNOI_SELECTED);
    }
    
    public int selectIndex(){
        return numTournoiSelected;
    }
    
    public void unselect() {
        numTournoiSelected = -1;
        notif(TypeNotif.TOURNOI_UNSELECTED);
    }
    
    public List<String> getNomJoueursTournoi(){
        return this.selectedTournoi().getNomJoueurs();
    }
    
    public ObservableList<Match> getMatchs(){
        return this.selectedTournoi().getMatchs();
    }
    public void notif(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }
    
    private void initTournoi(){
        Participant norman = new Participant("Norman");
        Participant cesar = new Participant("César");
        Participant jackie = new Participant("Jackie");
        Participant michel = new Participant("Michel");
        
        ArrayList<Participant> joueurs1 = new ArrayList<>();
        joueurs1.add(norman);
        joueurs1.add(cesar);
        joueurs1.add(jackie);
        joueurs1.add(michel);
        
        Match m1 = new Match(norman, cesar, Resultat.JOUEUR1);
        Match m2 = new Match(jackie, michel, Resultat.JOUEUR2);
        
        ObservableList<Match> matchsol1 = FXCollections.observableArrayList();
        matchsol1.add(m1);
        matchsol1.add(m2);
        
        Tournoi t1 = new Tournoi("Premier tournoi", joueurs1, matchsol1);
        
        this.addTournoi(t1);
        
        // tournament 2
        
        Participant leonardo = new Participant("Leonardo");
        Participant michelangelo = new Participant("Michelangelo");
        Participant raphaelo = new Participant("Raphaelo");
        Participant donatello = new Participant("Donatello");
        
        ArrayList<Participant> joueurs2 = new ArrayList<>();
        joueurs2.add(leonardo);
        joueurs2.add(michelangelo);
        joueurs2.add(raphaelo);
        joueurs2.add(donatello);
        
        Match m3 = new Match(leonardo, michelangelo, Resultat.JOUEUR1);
        Match m4 = new Match(raphaelo, donatello, Resultat.EGALITE);
        
        ObservableList<Match> matchsol2 = FXCollections.observableArrayList();
        matchsol2.add(m3);
        matchsol2.add(m4);

        Tournoi t2 = new Tournoi("Deuxième tournoi", joueurs2, matchsol2);
        
        this.addTournoi(t2);
    }
}
