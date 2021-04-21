package draught;

import java.util.Scanner;

public class Board {
    private int n;
    public Pawn[][] fields;
    private Scanner scanner;
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    public static int[] lastMove = new int[2];
    public Board() {
        scanner = new Scanner(System.in);
        while(this.n < 10){
            this.n = getInputFromUser();
        }
        this.fields = new Pawn[n][n];
        placeBlackPawnsOnBoards();
        placeWhitePawnsOnBoards();

    }

    public Pawn[][] getFields() {
        return fields;
    }

    private void placeBlackPawnsOnBoards(){
        int count = 0;
        myBreakLabelBlack:
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if ((i + j) % 2 != 0) {
                    fields[i][j] = new Pawn(this, i, j, false);
                    count++;
                    if (count == n * 2) {
                        break myBreakLabelBlack;
                    }
                }
            }
        }
    }
    private void placeWhitePawnsOnBoards(){
        int count = 0;
        myBreakLabelWhite:
        for (int i = fields.length - 1; i >= 0; i--) {
            for (int j = fields[0].length - 1; j >= 0; j--) {
                if ((i + j) % 2 != 0) {
                    fields[i][j] = new Pawn(this, i, j, true);
                    count++;
                    if (count == n * 2) {
                        break myBreakLabelWhite;
                    }
                }
            }
        }
    }
    private int getInputFromUser(){
        System.out.println("Please enter board size between 10 and 20: ");
        String input = scanner.nextLine();
        int size;
        try {
            size = Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            return -1;
        }
        if(size < 10 || size > 20){
            return -1;
        }
        return size;
    }

    public void removePawn(int x, int y) {
        fields[x][y] = null;
    }

    public void movePawn(int fromX, int fromY, int toX, int toY) {
        Pawn pawn = fields[fromX][fromY];
            fields[toX][toY] = pawn;
            fields[fromX][fromY] = null;
            pawn.setPositionX(toX);
            pawn.setPositionY(toY);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < fields.length; i++) {
            builder.append(alphabet.charAt(i) + " ");
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] == null && (i + j) % 2 == 0) {
                    builder.append(" - ");
                }
                else if (fields[i][j] == null && (i + j) % 2 != 0){
                    builder.append("   ");
                }
                else if (fields[i][j].isWhite) {
                    builder.append(" X ");
                }
                else if (!fields[i][j].isWhite) {
                    builder.append(" O ");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public void printBoard() {
        printBoardHeader();
        printBoardFields();
    }

    private void printBoardHeader(){
        StringBuilder builder = new StringBuilder("   ");
        for (int j = 0; j < fields[0].length; j++) {
            builder.append(String.format("%-3d", (j + 1)));
        }
        builder.append("\n");
        System.out.print(builder.toString());
    }

    private void printBoardFields(){
        System.out.println(toString());
    }

    private boolean isItEmpty(int x, int y) {
        return fields[x][y] == null;
    }


}

