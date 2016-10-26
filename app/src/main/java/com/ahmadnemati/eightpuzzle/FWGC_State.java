package com.ahmadnemati.eightpuzzle;



import com.ahmadnemati.eightpuzzle.model.PosModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ahmad Nemati on 10/26/2016.
 */

public class FWGC_State implements State
{
    
    private final Pos[] GOAL = new Pos[]
            { Pos.E, Pos.E, Pos.E, Pos.E };

    
    public enum Pos
    {
        W, E
    };

    
    public Pos[] curState;

    
    public FWGC_State()
    {
        curState = new Pos[]
                { Pos.W, Pos.W, Pos.W, Pos.W };

    }

    
    public FWGC_State(Pos fPos, Pos wPos, Pos gPos, Pos cPos)
    {
        curState = new Pos[]
                { fPos, wPos, gPos, cPos };
    }

    
    public FWGC_State(Pos[] stateArr)
    {
        curState = new Pos[]
                { stateArr[0], stateArr[1], stateArr[2], stateArr[3] };
    }

    
    @Override
    public double findCost()
    {
        return 1;
    }

    
    @Override
    public ArrayList<State> genSuccessors()
    {
        ArrayList<State> successors = new ArrayList<State>();
        Pos[] tempState = Arrays.copyOf(curState, curState.length);
		

        
        if (tempState[0] == Pos.W)
        {
            
            
            if (tempState[1] == Pos.W)
            {
                tempState[0] = Pos.E;
                tempState[1] = Pos.E;
                successors.add(new FWGC_State(tempState));
                tempState = Arrays.copyOf(curState, curState.length);
            }
            
            if (tempState[2] == Pos.W)
            {
                tempState[0] = Pos.E;
                tempState[2] = Pos.E;
                successors.add(new FWGC_State(tempState));
                tempState = Arrays.copyOf(curState, curState.length);
            }
            
            if (tempState[3] == Pos.W)
            {
                tempState[0] = Pos.E;
                tempState[3] = Pos.E;
                successors.add(new FWGC_State(tempState));
                tempState = Arrays.copyOf(curState, curState.length);
            }
            
            tempState[0] = Pos.E;
            successors.add(new FWGC_State(tempState));
            tempState = Arrays.copyOf(curState, curState.length);

        }
        
        else
        {
            
            
            if (tempState[1] == Pos.E)
            {
                tempState[0] = Pos.W;
                tempState[1] = Pos.W;
                successors.add(new FWGC_State(tempState));
                tempState = Arrays.copyOf(curState, curState.length);
            }
            
            if (tempState[2] == Pos.E)
            {
                tempState[0] = Pos.W;
                tempState[2] = Pos.W;
                successors.add(new FWGC_State(tempState));
                tempState = Arrays.copyOf(curState, curState.length);
            }
            
            if (tempState[3] == Pos.E)
            {
                tempState[0] = Pos.W;
                tempState[3] = Pos.W;
                successors.add(new FWGC_State(tempState));
                tempState = Arrays.copyOf(curState, curState.length);
            }
            
            tempState[0] = Pos.W;
            successors.add(new FWGC_State(tempState));
            tempState = Arrays.copyOf(curState, curState.length);

        }
        for (int i = 0; i < successors.size(); i++)
        {
            FWGC_State s = (FWGC_State) successors.get(i);
            tempState = s.curState;
            
            
            if (Arrays.equals(tempState, new Pos[]
                    { Pos.E, Pos.E, Pos.W, Pos.W })
                    || Arrays.equals(tempState, new Pos[]
                    { Pos.E, Pos.W, Pos.W, Pos.W })
                    || Arrays.equals(tempState, new Pos[]
                    { Pos.E, Pos.W, Pos.W, Pos.E })
                    || Arrays.equals(tempState, new Pos[]
                    { Pos.W, Pos.E, Pos.E, Pos.W })
                    || Arrays.equals(tempState, new Pos[]
                    { Pos.W, Pos.W, Pos.E, Pos.E })
                    || Arrays.equals(tempState, new Pos[]
                    { Pos.W, Pos.E, Pos.E, Pos.E })
                    || Arrays.equals(tempState, new Pos[]
                    { Pos.W, Pos.W, Pos.W, Pos.W }))
            {
                successors.remove(i);
                i = 0; 
                
            }
        }
        return successors;
    }

    
    @Override
    public boolean isGoal()
    {
        if (Arrays.equals(curState, GOAL))
        {
            return true;
        }
        return false;
    }

    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        else if (obj == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        FWGC_State other = (FWGC_State) obj;
        if (!curState.equals(other.curState))
            return false;
        return true;
    }

    
    @Override
    public void printState()
    {
        PosModel posModel=new PosModel();
        posModel.setFarmer(String.valueOf(curState[0]));
        posModel.setWolf(String.valueOf(curState[1]));
        posModel.setGoat(String.valueOf(curState[2]));
        posModel.setCabbage(String.valueOf(curState[3]));
        EventBus.getDefault().post(posModel);
    }

    
    @Override
    public boolean equals(State s)
    {
        if (Arrays.equals(curState, ((FWGC_State) s).getCurState()))
        {
            return true;
        }
        else
            return false;

    }

    
    public Pos[] getCurState()
    {
        return curState;
    }
}
