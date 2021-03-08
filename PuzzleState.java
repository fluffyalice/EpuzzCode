


import java.util.*;

public class PuzzleState extends SearchState {

    private int[][] puzzleState;
    private String distance;

    // down, left, up, right
    private int[] xMove = {0, -1, 0, 1};
    private int[] yMove = {-1, 0, 1, 0};


    /**
     * constructor
     * @param initState
     */
    public PuzzleState(int[][] initState, int lc, String distance){
        puzzleState = initState;
        localCost = lc;
        this.distance = distance;
        if (distance.equals("hamming"))
            estRemCost = hammingDistance();
        else
            estRemCost = manhattanDistance();
    }

    /**
     * accessor for content of puzzle
     */
    public int[][] getPuzzleState() {
        return puzzleState;
    }



    /**
     * calculate and estimate cost
     * @return
     */
    public int hammingDistance() {
        int counter = 0;
        for(int i=0; i<puzzleState.length; i++) {
            for(int j=0; j<puzzleState[i].length; j++) {
                if (i == 2 && j == 2 && puzzleState[i][j] != 0)
                    counter++;
                else if ((i != 2 || j != 2) && puzzleState[i][j] != i*3+j+1 && puzzleState[i][j] != 0)
                    counter++;
            }
        }
        return counter;
    }

    /**
     * calculate and estimate cost
     * @return
     */
    public int manhattanDistance() {
        int distance = 0;
        for(int i=0; i<puzzleState.length; i++) {
            for(int j=0; j<puzzleState[i].length; j++) {
                if (puzzleState[i][j] != 0) {
                    if (puzzleState[i][j] % 3 == 0)
                        distance += Math.abs(i-(puzzleState[i][j]/3-1)) +
                                Math.abs(j-2);
                    else
                        distance += Math.abs(i-Math.floor(puzzleState[i][j]/3)) +
                                Math.abs(j-(puzzleState[i][j]%3-1));
                }
            }
        }
        return distance;
    }

    /**
     * goalP
     * @param searcher
     * @return
     */
    public boolean goalP(Search searcher) {
        PuzzleSearch puzzleSearcher = (PuzzleSearch)searcher;
        int[][] targetPuzzle = puzzleSearcher.getTargetPuzzle();
        int counter = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int tempPuzzleValue = puzzleState[i][j];
                int tempTargetValue = targetPuzzle[i][j];
                if(tempPuzzleValue == tempTargetValue){
                    counter = counter + 1;
                }
            }
        }
        if(counter == 9){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * getSuccessors
     * @param searcher
     * @return
     */
    public ArrayList<SearchState> getSuccessors(Search searcher) {
        int[] posArray = getZeroPosition();
        int xPos = posArray[0];
        int yPos = posArray[1];
        int newXPos, newYPos;

        // the list of puzzle states
        ArrayList<PuzzleState> pslis = new ArrayList<PuzzleState>();
        ArrayList<SearchState> slis = new ArrayList<SearchState>();
        for(int i=0; i<4; i++){
            newXPos = xPos + xMove[i];
            newYPos = yPos + yMove[i];
            boolean isLegalMoveFlag = isLegalMove(newXPos, newYPos);
            if(isLegalMoveFlag){
                pslis.add(getPuzzleStateAfterMove(xPos, yPos, newXPos, newYPos));
            }
        }

        for (PuzzleState ps: pslis) {
            slis.add((SearchState)ps);
        }
        return slis;
    }

    /**
     * get the puzzle state after move
     * @param xPos
     * @param yPos
     * @param newXPos
     * @param newYPos
     * @return
     */
    public PuzzleState getPuzzleStateAfterMove(int xPos, int yPos, int newXPos, int newYPos){
        int[][] tempPuzzleState = new int[3][3];
        int tempValue = puzzleState[newXPos][newYPos];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                tempPuzzleState[i][j] = puzzleState[i][j];
                if(i==xPos && j==yPos){
                    tempPuzzleState[i][j] = tempValue;
                }
                else if(i==newXPos && j==newYPos){
                    tempPuzzleState[i][j] = 0;
                }
            }
        }

        PuzzleState puzzleStateAfterMove = new PuzzleState(tempPuzzleState,1,this.distance);
        return puzzleStateAfterMove;
    }

    /**
     * make sure the 0 is legal to move
     * @param x
     * @param y
     * @return
     */
    public boolean isLegalMove(int x, int y){
        return (x >= 0 && x < 3 && y >= 0 && y < 3);
    }

    /**
     * find zero
     * @return
     */
    public int[] getZeroPosition(){
        int xPos = -1;
        int yPos = -1;
        int[] PosArray = new int[2];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(puzzleState[i][j] == 0){
                    xPos = i;
                    yPos = j;
                }
            }
        }
        PosArray[0] = xPos;
        PosArray[1] = yPos;
        return PosArray;
    }

    public boolean sameState(SearchState s) {
        PuzzleState ps = (PuzzleState) s;
        int[][] puzzleState1 = ps.getPuzzleState();
        int counter = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int tempPuzzleValue = puzzleState[i][j];
                int tempPuzzleValue1 = puzzleState1[i][j];
                if(tempPuzzleValue == tempPuzzleValue1){
                    counter = counter + 1;
                }
            }
        }
        if(counter == 9){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * toString
     */
    public String toString() {
        return  " _       _\n" +
                "| " + puzzleState[0][0] + ", " + puzzleState[0][1] + ", " + puzzleState[0][2] + " | \n" +
                "| " + puzzleState[1][0] + ", " + puzzleState[1][1] + ", " + puzzleState[1][2] + " | \n" +
                "|_" + puzzleState[2][0] + ", " + puzzleState[2][1] + ", " + puzzleState[2][2] + "_| \n"
                ;
    }

    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {
        int[][] target = {{1,2,3}, {4,5,6}, {7,8,0}};
        int[][] diffTarget = {{1,2,0}, {4,5,6}, {7,3,8}};
        int[][] sameTarget = {{1,2,3}, {4,5,6}, {7,8,0}};

        //Test hamming Distance method
        SearchState targetSearch1 = (SearchState) new PuzzleState(target,1,"hamming");
        SearchState sameSearch2 = (SearchState) new PuzzleState(sameTarget,1,"hamming");
        SearchState diffSearch3 = (SearchState) new PuzzleState(diffTarget,1,"hamming");

        System.out.println("Hamming Distance(targetSearch):"+targetSearch1.getestRemCost());
        System.out.println("Hamming Distance(sameSearch):"+sameSearch2.getestRemCost());
        System.out.println("Hamming Distance(diffSearch):"+diffSearch3.getestRemCost());

        //Test manhattan Distance method
        SearchState targetSearch4 = (SearchState) new PuzzleState(target,1,"manhattan");
        SearchState sameSearch5 = (SearchState) new PuzzleState(sameTarget,1,"manhattan");
        SearchState diffSearch6 = (SearchState) new PuzzleState(diffTarget,1,"manhattan");

        System.out.println("Manhattan Distance(targetSearch):"+targetSearch4.getestRemCost());
        System.out.println("Manhattan Distance(sameSearch):"+sameSearch5.getestRemCost());
        System.out.println("Manhattan Distance(diffSearch):"+diffSearch6.getestRemCost());


    }




}
