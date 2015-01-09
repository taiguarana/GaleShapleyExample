/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gale.shapley.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Taiguara
 */
public class Pessoas {
    List <Integer> preference = new ArrayList<>();
    int pair;
    private boolean free;

    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    public Pessoas() {
        this.free = true;
    }

    public int getPreference(int pos) {
        return this.preference.get(pos);
    }

    public void addPreference(Integer preference) {
        this.preference.add(preference);
    }
    
    public void erasePreference(Integer index) {
        this.preference.set(index, -1);
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
    
    public void shufflePreference() {
        Collections.shuffle(preference);
    }
    
    public int getPreferenceIndex(int n) {
        return this.preference.indexOf(n);
    }
    
    public void printPreference() {
        for(int i=0;i<this.preference.size();i++)
            System.out.print(this.preference.get(i));
    }
}
