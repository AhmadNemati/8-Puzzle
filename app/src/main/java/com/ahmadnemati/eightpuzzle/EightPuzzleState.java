package com.ahmadnemati.eightpuzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ahmad Nemati on 10/26/2016.
 */

public class EightPuzzleState implements State
{

    private final int PUZZLE_SIZE = 9;
    private int outOfPlace = 0;

    private int manDist = 0;

    private final int[] GOAL = new int[]
            { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
    private int[] curBoard;

    /**
     * Constructor for EightPuzzleState
     *
     * @param board
     *            - the board representation for the new state to be constructed
     */
    public EightPuzzleState(int[] board)
    {
        curBoard = board;
        setOutOfPlace();
        setManDist();
    }

    /**
     * How much it costs to come to this state
     */
    @Override
    public double findCost()
    {
        return 1;
    }

    /*
     * Set the 'tiles out of place' distance for the current board
     */
    private void setOutOfPlace()
    {
        for (int i = 0; i < curBoard.length; i++)
        {
            if (curBoard[i] != GOAL[i])
            {
                outOfPlace++;
            }
        }
    }

    private void setManDist()
    {
        int index = -1;
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                index++;
                int val = (curBoard[index] - 1);
                if (val != -1)
                {

                    int horiz = val % 3;

                    int vert = val / 3;

                    manDist += Math.abs(vert - (y)) + Math.abs(horiz - (x));
                }
            }
        }
    }
    private int getHole()
    {
        int holeIndex = -1;

        for (int i = 0; i < PUZZLE_SIZE; i++)
        {
            if (curBoard[i] == 0)
                holeIndex = i;
        }
        return holeIndex;
    }
    public int getOutOfPlace()
    {
        return outOfPlace;
    }
    public int getManDist()
    {
        return manDist;
    }

    /*
     * Makes a copy of the array passed to it
     */
    private int[] copyBoard(int[] state)
    {
        int[] ret = new int[PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++)
        {
            ret[i] = state[i];
        }
        return ret;
    }
    @Override
    public ArrayList<State> genSuccessors()
    {
        ArrayList<State> successors = new ArrayList<State>();
        int hole = getHole();

        // try to generate a state by sliding a tile leftwise into the hole
        // if we CAN slide into the hole
        if (hole != 0 && hole != 3 && hole != 6)
        {
			/*
			 * we can slide leftwise into the hole, so generate a new state for
			 * this condition and throw it into successors
			 */
            swapAndStore(hole - 1, hole, successors);
        }

        // try to generate a state by sliding a tile topwise into the hole
        if (hole != 6 && hole != 7 && hole != 8)
        {
            swapAndStore(hole + 3, hole, successors);
        }

        // try to generate a state by sliding a tile bottomwise into the hole
        if (hole != 0 && hole != 1 && hole != 2)
        {
            swapAndStore(hole - 3, hole, successors);
        }
        // try to generate a state by sliding a tile rightwise into the hole
        if (hole != 2 && hole != 5 && hole != 8)
        {
            swapAndStore(hole + 1, hole, successors);
        }

        return successors;
    }

    /*
     * Switches the data at indices d1 and d2, in a copy of the current board
     * creates a new state based on this new board and pushes into s.
     */
    private void swapAndStore(int d1, int d2, ArrayList<State> s)
    {
        int[] cpy = copyBoard(curBoard);
        int temp = cpy[d1];
        cpy[d1] = curBoard[d2];
        cpy[d2] = temp;
        s.add((new EightPuzzleState(cpy)));
    }

    /**
     * Check to see if the current state is the goal state.
     *
     * @return - true or false, depending on whether the current state matches
     *         the goal
     */
    @Override
    public boolean isGoal()
    {
        if (Arrays.equals(curBoard, GOAL))
        {
            return true;
        }
        return false;
    }

    /**
     * Method to print out the current state. Prints the puzzle board.
     */
    @Override
    public void printState()
    {
        System.out.println(curBoard[0] + " | " + curBoard[1] + " | "
                + curBoard[2]);
        System.out.println("---------");
        System.out.println(curBoard[3] + " | " + curBoard[4] + " | "
                + curBoard[5]);
        System.out.println("---------");
        System.out.println(curBoard[6] + " | " + curBoard[7] + " | "
                + curBoard[8]);

    }

    /**
     * Overloaded equals method to compare two states.
     *
     * @return true or false, depending on whether the states are equal
     */
    @Override
    public boolean equals(State s)
    {
        if (Arrays.equals(curBoard, ((EightPuzzleState) s).getCurBoard()))
        {
            return true;
        }
        else
            return false;

    }

    /**
     * Getter to return the current board array
     *
     * @return the curState
     */
    public int[] getCurBoard()
    {
        return curBoard;
    }

}
