

import sheffield.*;
import java.util.*;

public class RunPuzzleSearch {
    public static void main(String[] arg){

        //define the target state and the goal state
        int[][] targetState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        // create an EasyWriter
        EasyWriter writer = new EasyWriter();

        // create a puzzle searcher
        PuzzleSearch searcher = new PuzzleSearch(targetState);



        // No.8
        // BreadthFirst
        // Diff:6
        // Manhattan
        EpuzzGen gen8 = new EpuzzGen(12345);
        int[][] puzzle8 = gen8.puzzGen(6);
        SearchState initState8 = (SearchState) new PuzzleState(puzzle8,1,"Manhattan");

        Float resas8 = searcher.runSearchE(initState8, "BreadthFirst");
        writer.println("<No.8\n" + "BreadFirst\n" + "Diff:6\n" + "Manhattan\n"+resas8+">\n");


        // No.7
        // BreadFirst
        // Diff:6
        // Hamming
        EpuzzGen gen7 = new EpuzzGen(12345);
        int[][] puzzle7 = gen7.puzzGen(6);
        SearchState initState7 = (SearchState) new PuzzleState(puzzle7,1,"Hamming");

        Float resas7 = searcher.runSearchE(initState7, "BreadFirst");
        writer.println("<No.7\n" + "BreadFirst\n" + "Diff:6\n" + "Hamming\n"+resas7+">\n");


        // No.6
        // BreadFirst
        // Diff:8
        // Hamming
        EpuzzGen gen6 = new EpuzzGen(12345);
        int[][] puzzle6 = gen6.puzzGen(8);
        SearchState initState6 = (SearchState) new PuzzleState(puzzle6,1,"Hamming");

        Float resas6 = searcher.runSearchE(initState6, "BreadFirst");
        writer.println("<No.6\n" + "BreadFirst\n" + "Diff:8\n" + "Hamming\n"+resas6+">\n");


        // No.5
        // BreadFirst
        // Diff:8
        // Manhattan
        EpuzzGen gen5 = new EpuzzGen(12345);
        int[][] puzzle5 = gen5.puzzGen(8);
        SearchState initState5 = (SearchState) new PuzzleState(puzzle5,1,"Manhattan");

        Float resas5 = searcher.runSearchE(initState5, "BreadFirst");
        writer.println("<No.5\n" + "BreadFirst\n" + "Diff:8\n" + "Manhattan\n"+resas5+">\n");


        // No.4
        // AStar
        // Diff:6
        // Manhattan
        EpuzzGen gen4 = new EpuzzGen(12345);
        int[][] puzzle4 = gen4.puzzGen(6);
        SearchState initState4 = (SearchState) new PuzzleState(puzzle4,1,"Manhattan");

        Float resas4 = searcher.runSearchE(initState4, "AStar");
        writer.println("<No.4\n" + "AStar\n" + "Diff:6\n" + "Manhattan\n"+resas4+">\n");


        // No.3
        // AStar
        // Diff:6
        // Hamming
        EpuzzGen gen3 = new EpuzzGen(12345);
        int[][] puzzle3 = gen3.puzzGen(6);
        SearchState initState3 = (SearchState) new PuzzleState(puzzle3,1,"hamming");

        Float resas3 = searcher.runSearchE(initState3, "AStar");
        writer.println("<No.3\n" + "AStar\n" + "Diff:6\n" + "Hamming\n"+resas3+">\n");


        // No.2
        // AStar
        // Diff:8
        // Manhattan
        EpuzzGen gen2 = new EpuzzGen(12345);
        int[][] puzzle2 = gen2.puzzGen(8);
        SearchState initState2 = (SearchState) new PuzzleState(puzzle2,1,"manhattan");

        Float resas2 = searcher.runSearchE(initState2, "AStar");
        writer.println("<No.2\n" + "AStar\n" + "Diff:8\n" + "Manhattan\n"+resas2+">\n");


        // No.1
        // AStar
        // Diff:8
        // Hamming
        EpuzzGen gen1 = new EpuzzGen(12345);
        int[][] puzzle1 = gen1.puzzGen(8);
        SearchState initState1 = (SearchState) new PuzzleState(puzzle1,1,"hamming");

        Float resas1 = searcher.runSearchE(initState1, "AStar");
        writer.println("<No.1\n" + "AStar\n" + "Diff:8\n" + "Hamming\n"+resas1+">\n");








        int code;
        int difficulty = 6;
        int size = 20;
        ArrayList<Float> bf = new ArrayList<Float>();
        ArrayList<Float> as = new ArrayList<Float>();
        for(code=0; code<size; code++) {
            EpuzzGen gen = new EpuzzGen(code);
            // generate a puzzle
            int[][] puzz = gen.puzzGen(difficulty);
            SearchState initState = (SearchState) new PuzzleState(puzz,1,"manhattan");

            Float resbf2 = searcher.runSearchE(initState, "breadthFirst");
            bf.add(resbf2);

            //A* search
            Float resas = searcher.runSearchE(initState, "AStar");
            as.add(resas);
        }

        writer.println("breadthFirst: ");
        writer.println("code: " + code);
        writer.println("difficulty: " + difficulty);





        float totalBF = 0;
        float avgBF = 0;
        for (int i=0; i<bf.size(); i++) {
            totalBF += bf.get(i);
        }
        avgBF = totalBF/bf.size();
        writer.println("average efficiency: "+avgBF);

        writer.println("A*(Manhattan): ");
        writer.println("code: " + code);
        writer.println("difficulty: " + difficulty);

        float totalAS = 0;
        float avgAS = 0;
        for (int i=0; i<as.size(); i++) {
            totalAS += as.get(i);
        }
        avgAS = totalAS/as.size();
        writer.println("average efficiency: "+avgAS);







    }
}
