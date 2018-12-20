/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;

/**
 *
 * @author Fontaine César
 */
public class Participant implements Comparable<Participant>{
    
    private String participant;
    
    public Participant(String player){
        this.participant = player;
    }

    public String getParticipant() {
        return participant;
    }
    
    
    public void setParticipant(String participant) {
        this.participant = participant;
    }


    @Override
    public int compareTo(Participant o) {
        return this.getParticipant().compareTo(o.getParticipant());
    }
    
}
