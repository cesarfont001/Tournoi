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
public class Tournoi extends Observable {
    
    
    public enum TypeNotif { INIT, MATCH_ADD, MATCH_DEL, MATCH_SELECT, MATCH_UNSELECT, MATCH_UPD }
    private String name;
    private ArrayList<Participant> participantList;
    private ObservableList<Match> matchList;
    private int numSelectMatch = -1;

    public Tournoi(String name, ArrayList<Participant> inscritsList, ObservableList<Match> matchsList) {
        this.name = name;
        this.participantList = inscritsList;
        this.matchList = matchsList;
    }
    
    public int nbJoeurs() {
        return participantList.size();
    }
    
    public void addJoueur(Participant p){
        participantList.add(p);
    }
    
    public String getNomTournoi() {
        return name;
    }
    
    public ArrayList getParticipants(){
        return participantList;
    }
    
    public ObservableList<Match> getMatchs(){
        return matchList;
    }
    
    
    public int nbMatchs(){
        return matchList.size();
    }
    
    public ArrayList<String> getNomJoueurs(){
        List<String> nomJoueurs = new ArrayList<>();
        
        for(Participant p : participantList){
            nomJoueurs.add(p.getParticipant());
        }
        return (ArrayList<String>) nomJoueurs;
    }
    
    public boolean addMatch(Match match) {
        matchList.add(match);
        numSelectMatch = matchList.size()-1;
        notify(TypeNotif.MATCH_ADD);
        return true;
    }
    
    public boolean updateMatch(Match match) {
        matchList.set(numSelectMatch, match);
        notify(TypeNotif.MATCH_UPD);
        return true;
    }
    
    public boolean deleteMatch() {
        matchList.remove(numSelectMatch);
        numSelectMatch = matchList.size()-1;
        notify(TypeNotif.MATCH_DEL);
        return true;
    }
    public Match selectMatch() {
        return matchList.get(numSelectMatch);
    }
    
    public int SelectMatchIndex(){
        return numSelectMatch;
    }
    
    public void selectIndex(int index) {
        numSelectMatch = index;
        notify(TypeNotif.MATCH_SELECT);
    }
    
    public void unselectIndex() {
        numSelectMatch = -1;
        notify(TypeNotif.MATCH_UNSELECT);
    }
    
    public void notify(TypeNotif typeNotif) {
        setChanged();
        notifyObservers(typeNotif);
    }
    
    public static MatchEnum valider(Match match, ArrayList<Match> ml){ 
        
        if(match.getNomJoueur1().equals(match.getNomJoueur2())) {
            return MatchEnum.PAREIL;
        }
        
        for (Match existing : ml) {
            
            if(existing.getNomJoueur1().equals(match.getNomJoueur1())
                    && existing.getNomJoueur2().equals(match.getNomJoueur2()))
            {
                return MatchEnum.DEJAFAIT;
            }
            
            if(existing.getNomJoueur1().equals(match.getNomJoueur2())
                    && existing.getNomJoueur2().equals(match.getNomJoueur1()))
            {
                return MatchEnum.DEJAFAIT;
            }
        }
        
        return MatchEnum.OK;
    }
    
    public Tournoi(){}
}
    