

public class PuzzleSearch extends Search {

    private int[][] targetPuzzle;

    /**
     * constructor takes puzzle target
     * @param targetPuzzle
     */
    public PuzzleSearch (int[][] targetPuzzle){
        this.targetPuzzle = targetPuzzle;
    }


    /**
     * accessor for target
     */
    public int[][] getTargetPuzzle(){
        return this.targetPuzzle;
    }

}
