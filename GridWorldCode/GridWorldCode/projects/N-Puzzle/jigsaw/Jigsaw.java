package jigsaw;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class Jigsaw {
    JigsawNode beginJNode;
    JigsawNode endJNode;
    JigsawNode currentJNode;
    private Vector<JigsawNode> open;
    private Vector<JigsawNode> close;

    private boolean isCompleted;
    private int searchedNodesNum;

    private Vector<JigsawNode> solutionPath;

    public Jigsaw(JigsawNode bNode, JigsawNode eNode)
    {
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);

        this.currentJNode = new JigsawNode(bNode);
        this.solutionPath = null;
        this.isCompleted = false;

        this.open = new Vector<JigsawNode>();
        this.close = new Vector<JigsawNode>();

        this.searchedNodesNum = 0;
    }


    public static JigsawNode scatter(JigsawNode jNode, int len)
    {
        int randomDirection;
        len += (int) (Math.random() * 2);
        JigsawNode jigsawNode = new JigsawNode(jNode);
        for (int t = 0; t < len; t++)
        {
            int[] movable = jigsawNode.canMove();
            do {
                randomDirection = (int) (Math.random() * 4);
            }
            while (0 == movable[randomDirection]);
            jigsawNode.move(randomDirection);
        }
        jigsawNode.setInitial();
        return jigsawNode;
    }


    public JigsawNode getCurrentJNode() {
        return currentJNode;
    }

    public void setBeginJNode(JigsawNode jNode) {
        beginJNode = jNode;
    }

    public JigsawNode getBeginJNode() {
        return beginJNode;
    }

    public void setEndJNode(JigsawNode jNode) {
        this.endJNode = jNode;
    }

    public JigsawNode getEndJNode() {
        return endJNode;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    private boolean calSolutionPath()
    {
        if (!this.isCompleted()) return false;
        else
        {
            JigsawNode jNode = this.currentJNode;
            solutionPath = new Vector<JigsawNode>();
            while (jNode != null) {
                solutionPath.addElement(jNode);
                jNode = jNode.getParent();
            }
            return true;
        }
    }

    public String getSolutionPath()
    {
        String str = new String();
        str += "Begin->";
        if (this.isCompleted)
        {
            for (int i = solutionPath.size() - 1; i >= 0; i--)
                str += solutionPath.elementAt(i).toString() + "->";
            str += "End";
        }
        else
            str = "Jigsaw Not Completed.";
        return str;
    }


    public int getSearchedNodesNum() {
        return searchedNodesNum;
    }

    public void printResult(PrintWriter pw) throws IOException
    {
        boolean flag = false;
        if (pw == null)
        {
            pw = new PrintWriter(new FileWriter("Result.txt"));
            flag = true;
        }
        if (this.isCompleted == true)
        {
            pw.println("Jigsaw Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Solution Path: ");
            pw.println(this.getSolutionPath());
            pw.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());
            pw.println("Length of the solution path is:"
                    + this.getCurrentJNode().getNodeDepth());

            System.out.println("Jigsaw Completed");
            System.out.println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Solution Path: ");
            System.out.println(this.getSolutionPath());
            System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
            System.out.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());
        }
        else {
            pw.println("No solution. Jigsaw Not Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());

            System.out.println("No solution. Jigsaw Not Completed");
            System.out.println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
        }
        if (flag)
            pw.close();
    }

    private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
        JigsawNode tempJNode;
        for (int i = 0; i < 4; i++) {
            tempJNode = new JigsawNode(jNode);
            if (tempJNode.move(i) && !this.close.contains(tempJNode) && !this.open.contains(tempJNode))
                followJNodes.addElement(tempJNode);
        }
        return followJNodes;
    }

    private void sortedInsertOpenList(JigsawNode jNode) {
        this.estimateValue(jNode);
        for (int i = 0; i < this.open.size(); i++) {
            if (jNode.getEstimatedValue() < this.open.elementAt(i)
                    .getEstimatedValue()) {
                this.open.insertElementAt(jNode, i);
                return;
            }
        }
        this.open.addElement(jNode);
    }

    public boolean BFSearch() throws IOException {
        String filePath = "BFSearchDialog.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();

        isCompleted = false;
        this.open.add(this.beginJNode);
        while (this.open.isEmpty() != true) {

            this.currentJNode = this.open.elementAt(0);
            if (this.currentJNode.equals(this.endJNode)) {
                isCompleted = true;
                this.calSolutionPath();
                break;
            }

            this.open.removeElementAt(0);
            this.close.addElement(this.currentJNode);
            searchedNodesNum++;

            pw.println("Searching.....Number of searched nodes:" + this.close.size() + "   Current state:"
                    + this.currentJNode.toString());
            System.out.println("Searching.....Number of searched nodes:"
                    + this.close.size() + "   Current state:"
                    + this.currentJNode.toString());

            followJNodes = this.findFollowJNodes(this.currentJNode);
            while (!followJNodes.isEmpty()) {
                this.open.add(followJNodes.elementAt(0));
                followJNodes.removeElementAt(0);
            }
        }

        this.printResult(pw);
        pw.close();
        System.out.println("Record into " + filePath);
        return isCompleted;
    }

    public boolean ASearch() throws IOException {
        int maxNodesNum = 30000;
        isCompleted = false;

        String filePath = "ASearchDialog.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

        Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();

        this.sortedInsertOpenList(this.beginJNode);

        while (this.open.isEmpty() != true
                && searchedNodesNum <= maxNodesNum) {

            this.currentJNode = this.open.elementAt(0);
            if (this.currentJNode.equals(this.endJNode)) {
                isCompleted = true;
                this.calSolutionPath();
                break;
            }

            this.open.removeElementAt(0);
            this.close.addElement(this.currentJNode);
            searchedNodesNum++;

            pw.println("Searching.....Number of searched nodes:" + this.close.size() + "   Current state:" + this.currentJNode.toString());
            System.out.println("Searching.....Number of searched nodes:" + this.close.size() + "   Current state:" + this.currentJNode.toString());

            followJNodes = this.findFollowJNodes(this.currentJNode);
            while (!followJNodes.isEmpty()) {
                this.sortedInsertOpenList(followJNodes.elementAt(0));
                followJNodes.removeElementAt(0);
            }
        }

        this.printResult(pw);
        pw.close();
        System.out.println("Record into " + filePath);
        return isCompleted;
    }

    private void estimateValue(JigsawNode jNode) {
        final int factor1 = 0;
        final int factor2 = 1;
        final int factor3 = 1;

        int missmatch = getMissMatch(jNode);
        int childrenmissmatch = getChildrenMissMatch(jNode);
        int distance = getDistance(jNode);

        jNode.setEstimatedValue(factor1 * missmatch + factor2
                * childrenmissmatch + factor3 * distance);
    }

    private int getDistance(JigsawNode jNode) {
        int dimension = jNode.getDimension();

        int distance = 0;

        int NodeState[] = jNode.getNodesState();
        int finishedNodeState[] = endJNode.getNodesState();

        for (int i = 1; i <= dimension * dimension; i++)
        {
            if (NodeState[i] != 0 && NodeState[i] != finishedNodeState[i])
            {
                int current_col = (int) (i + 4) % dimension;
                int current_row = (int) (i - 1) / dimension;
                for (int j = 0; j <= dimension * dimension; j++) {
                    if (NodeState[i] == finishedNodeState[j]) {
                        int target_row = (int) (j - 1) / dimension;
                        int target_col = (int) (j + 4) % dimension;
                        distance += (Math.abs(current_row - target_row) + Math
                                .abs(current_col - target_col));
                        break;
                    }
                }
            }
        }
        return distance;
    }

    private int getChildrenMissMatch(JigsawNode jNode) {
        int missmatch = 0;
        int dimension = jNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++)
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1])
                missmatch++;
        return missmatch;
    }

    private int getMissMatch(JigsawNode jNode) {
        int missmatch = 0;
        int dimension = jNode.getDimension();
        int currentJNodeState[] = jNode.getNodesState();
        int targetJNodeState[] = endJNode.getNodesState();
        for (int i = 1; i <= dimension * dimension; i++) {
            if (currentJNodeState[i] != targetJNodeState[i]) missmatch++;
        }
        return missmatch;
    }
}
