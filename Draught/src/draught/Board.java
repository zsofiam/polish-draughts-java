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
        int n = Integer.parseInt(input);
        this.fields = new Pawn[n][n];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if  ((i+j) % 2 != 0){
                    fields[i][j] = new Pawn(i, j,false);
                }
                else{
                    fields[i][j] = null;
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
