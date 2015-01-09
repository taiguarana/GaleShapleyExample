/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gale.shapley.example;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Taiguara
 */
public class GaleShapleyExample {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nPeople = 6;
        
        // Array para receber as pessoas
        List <Pessoas> men = new ArrayList<>();
        List <Pessoas> women = new ArrayList<>();
        Pessoas p;
        
        // Criando pessoas
        for(int i=0;i<nPeople;i++) {
            p = new Pessoas();
            men.add(p);
            p = new Pessoas();
            women.add(p);
            for(int j=0;j<nPeople;j++) {
                men.get(i).addPreference(j);
                women.get(i).addPreference(j);
            }
            men.get(i).shufflePreference();
            women.get(i).shufflePreference();
        }
        
        // Mostrando na tela
        System.out.println("Men:\t\tWomen:");
        for(int i=0;i<nPeople;i++) {
            men.get(i).printPreference();
            System.out.print(" " + i + "\t\t" + i + " ");
            women.get(i).printPreference();
            System.out.println();
        }
        
        // Gale-Shapley
        int vezes = 0, k = 0, man = 0, myGirl = 0, freeCounter = 0;
        while(freeCounter != nPeople) {
            // Escolhe homem livre
            while(!men.get(man).isFree())
                if(++man == nPeople) man = 0;
            System.out.println("Man: "+man);
            
            // Pede a próxima garota da lista de desejo
            while((k < nPeople) && ((myGirl = men.get(man).getPreference(k++)) == -1));
            System.out.println("\tmyGirl: "+myGirl);
            
            // Mulher livre
            if(women.get(myGirl).isFree()) {
                women.get(myGirl).setPair(man);     // Casa mulher com homem
                men.get(man).setPair(myGirl);       // Casa homem com mulher
                men.get(man).setFree(false);        // Tira liberdade homem
                women.get(myGirl).setFree(false);   // Tira liberdade mulher
                System.out.println("\tPrimeiro casamento: "+myGirl);
                freeCounter++;
            } else {
                // Noiva, mas que aceita trocar o noivo
                if(women.get(myGirl).getPreferenceIndex(women.get(myGirl).getPair()) > women.get(myGirl).getPreferenceIndex(man)) {
                    men.get(women.get(myGirl).getPair()).setFree(true); // Libera o par antigo
                    women.get(myGirl).setPair(man);                     // Casa mulher com novo homem
                    men.get(man).setPair(myGirl);                       // Casa homen com mulher
                    men.get(man).setFree(false);                        // Tira liberdade do homem
                    women.get(myGirl).setFree(false);                   // Tira liberdade mulher
                    System.out.println("\tTirou concorrente!"); 
                }
                else // Rejeita novas propostas
                    System.out.println("\tFoi rejeitado!");
            }
            men.get(man).erasePreference(--k);                  // Deixa com -1 na posição da moça
            k=0;
            System.out.println("------------------");
            vezes++;
        }
        
        // Mostrando na tela
        System.out.println("(Men,Women)");
        for(int i=0;i<nPeople;i++)
            System.out.print("(" + i + "," + men.get(i).getPair() + ")");
        System.out.println();
        System.out.println("O algoritmo foi executado " + vezes + " vezes.");
    }
}
