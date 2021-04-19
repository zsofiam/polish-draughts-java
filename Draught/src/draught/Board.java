package draught;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private int n;
    private Pawn[][] fields;
    private Scanner scanner;
    public Board() {
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.n = Integer.parseInt(input);
        this.fields = new Pawn[n][n];

//        initialize every field of board as null
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                fields[i][j] = null;
            }
        }
//        place black pawns on board

        int count = 0;
        myBreakLabelBlack:
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if  ((i+j) % 2 != 0){
                    fields[i][j] = new Pawn(i, j,false);
                    count ++;
                    if (count == n){
                        break myBreakLabelBlack;
                    }
                }
            }
        }

        //        place white pawns on board
        count = 0;
        myBreakLabelWhite:
        for (int i = fields.length-1; i >= 0; i--) {
            for (int j = fields[0].length; j >= 0; j--) {
                if  ((i+j) % 2 != 0){
                    fields[i][j] = new Pawn(i, j,true);
                    count ++;
                    if (count == n){
                        break myBreakLabelWhite;
                    }
                }
            }
        }

    }
    public void removePawn(){
//        TO DO
    }
    public void movePawn(){
//        TO DO
    }
    @Override
    public String toString() {
        return "Game{" +
                "n=" + n +
                ", fields=" + Arrays.toString(fields) +
                ", scanner=" + scanner +
                '}';
    }
}
