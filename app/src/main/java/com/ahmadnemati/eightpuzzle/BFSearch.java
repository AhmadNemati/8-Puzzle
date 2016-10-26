package com.ahmadnemati.eightpuzzle;



import com.ahmadnemati.eightpuzzle.model.DoneModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Created by Ahmad Nemati on 10/26/2016.
 */
public class BFSearch {
    public static void search(int[] board, boolean d) {
        SearchNode root = new SearchNode(new EightPuzzleState(board));
        Queue<SearchNode> queue = new LinkedList<SearchNode>();

        queue.add(root);

        performSearch(queue, d);
    }


    public static void search(boolean d) {
        SearchNode root = new SearchNode(new FWGC_State());
        Queue<SearchNode> queue = new LinkedList<SearchNode>();

        queue.add(root);

        performSearch(queue, d);
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

    public static void performSearch(Queue<SearchNode> q, boolean d) {
        int searchCount = 1;

        while (!q.isEmpty()) {
            SearchNode tempNode = (SearchNode) q.poll();

            if (!tempNode.getCurState().isGoal())

            {
                ArrayList<State> tempSuccessors = tempNode.getCurState()
                        .genSuccessors();

                for (int i = 0; i < tempSuccessors.size(); i++) {
                    SearchNode newNode = new SearchNode(tempNode,
                            tempSuccessors.get(i), tempNode.getCost()
                            + tempSuccessors.get(i).findCost(), 0);

                    if (!checkRepeats(newNode)) {
                        q.add(newNode);
                    }
                }
                searchCount++;
            } else {
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
