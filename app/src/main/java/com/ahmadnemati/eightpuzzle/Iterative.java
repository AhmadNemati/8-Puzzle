package com.ahmadnemati.eightpuzzle;

import com.ahmadnemati.eightpuzzle.model.DoneModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Ahmad Nemati on 10/26/2016.
 */
public class Iterative {
    public static void search(int[] board, boolean d) {
        SearchNode root = new SearchNode(new EightPuzzleState(board));
        Stack<SearchNode> stack = new Stack<SearchNode>();

        stack.add(root);

        performSearch(stack, d);
    }

    public static void search(boolean d) {
        SearchNode root = new SearchNode(new FWGC_State());
        Stack<SearchNode> stack = new Stack<SearchNode>();

        stack.add(root);

        performSearch(stack, d);
    }

    private static boolean checkRepeats(SearchNode n) {
        boolean retValue = false;
        SearchNode checkNode = n;

        while (n.getParent() != null && !retValue) {
            if (n.getParent().getCurState().equals(checkNode.getCurState())) {
                retValue = true;
            }
            n = n.getParent();
        }

        return retValue;
    }

    public static void performSearch(Stack<SearchNode> s, boolean d) {
        int searchCount = 1;

        while (!s.isEmpty()) {
            SearchNode tempNode = (SearchNode) s.pop();
            if (!tempNode.getCurState().isGoal()) {
                ArrayList<State> tempSuccessors = tempNode.getCurState()
                        .genSuccessors();
                for (int i = 0; i < tempSuccessors.size(); i++) {

                    SearchNode newNode = new SearchNode(tempNode,
                            tempSuccessors.get(i), tempNode.getCost()
                            + tempSuccessors.get(i).findCost(), 0);

                    if (!checkRepeats(newNode)) {
                        s.add(newNode);
                    }
                }
                searchCount++;
            } else

            {

                Stack<SearchNode> solutionPath = new Stack<SearchNode>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);
                int loopSize = solutionPath.size();

                for (int i = 0; i < loopSize; i++) {
                    tempNode = solutionPath.pop();
                    tempNode.getCurState().printState();
                    System.out.println();
                    System.out.println();
                }
                DoneModel doneModel = new DoneModel();
                doneModel.setCost(tempNode.getCost() + "");

                if (d) {

                    doneModel.setNodes(String.valueOf(searchCount));
                }
                EventBus.getDefault().post(doneModel);

                return;
            }
        }
        System.out.println("Error! No solution found!");
    }
}
