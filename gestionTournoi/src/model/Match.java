/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fontaine César
 */
public class Match{
    
    private Participant joueur1; 
    private Participant joueur2;
    private Resultat resultat;
    
    public Match(Participant joueur1, Participant joueur2, Resultat resultat){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.resultat = resultat;
    }
    
    public Match(){}

    public Participant getJoueur1() {
        return joueur1;
    }

    public Participant getJoueur2() {
        return joueur2;
    }

    public Resultat getResult() {
        return resultat;
    }
    
    public String getNomJoueur1(){
        return joueur1.getParticipant();
    }
    
    public String getNomJoueur2(){
        return joueur2.getParticipant();
    }
       
}



    

    

    

    

    

    

    

    

   
