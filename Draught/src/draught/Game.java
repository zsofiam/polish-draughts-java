package draught;

import java.util.Scanner;

public class Game {

    private int n;
    private Pawn[][] fields;
    private Scanner scanner;
    public Game() {
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int n = Integer.parseInt(input);
        this.fields = new Pawn[n][n];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if  ((i+j) % 2 != 0){
                    fields[i][j] = new Pawn();
                }
                else{
                    fields[i][j] = null;
                }
            }
        }
    }
}
