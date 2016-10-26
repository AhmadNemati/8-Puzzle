package com.ahmadnemati.eightpuzzle;



import java.util.ArrayList;


/**
 * Created by Ahmad Nemati on 10/26/2016.
 */

public interface State
{
    
    boolean isGoal();

    
    ArrayList<State> genSuccessors();

    
    double findCost();

    
    public void printState();

    
    public boolean equals(State s);
}
