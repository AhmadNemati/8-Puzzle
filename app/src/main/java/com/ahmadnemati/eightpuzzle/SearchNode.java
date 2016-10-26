package com.ahmadnemati.eightpuzzle;


/**
 * Created by Ahmad Nemati on 10/26/2016.
 */
public class SearchNode
{

    private State curState;
    private SearchNode parent;
    private double cost; 
    private double hCost; 
    private double fCost; 

    
    public SearchNode(State s)
    {
        curState = s;
        parent = null;
        cost = 0;
        hCost = 0;
        fCost = 0;
    }

    
    public SearchNode(SearchNode prev, State s, double c, double h)
    {
        parent = prev;
        curState = s;
        cost = c;
        hCost = h;
        fCost = cost + hCost;
    }

    
    public State getCurState()
    {
        return curState;
    }

    
    public SearchNode getParent()
    {
        return parent;
    }

    
    public double getCost()
    {
        return cost;
    }

    
    public double getHCost()
    {
        return hCost;
    }

    
    public double getFCost()
    {
        return fCost;
    }
}