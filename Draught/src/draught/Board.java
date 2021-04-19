package draught;


import java.util.Scanner;

public class Board {
    private int n;
    private Pawn[][] fields;
    private Scanner scanner;
    public Board() {
        scanner = new Scanner(System.in);
        System.out.println("Enter board size between 10 and 20: ");
        String input = scanner.nextLine();
        this.n = Integer.parseInt(input);
        while (this.n < 10 || this.n > 20) {
            System.out.println("Between 10 and 20: ");
            input = scanner.nextLine();
            this.n = Integer.parseInt(input);
        }
        this.fields = new Pawn[n][n];

//      place black pawns on board
        int count = 0;
        myBreakLabelBlack:
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if  ((i+j) % 2 != 0){
                    fields[i][j] = new Pawn(i, j,false);
                    count ++;
                    if (count == n * 2){
                        break myBreakLabelBlack;
                    }
                }
            }
        }

//      place white pawns on board
        count = 0;
        myBreakLabelWhite:
        for (int i = fields.length-1; i >= 0; i--) {
            for (int j = fields[0].length-1; j >= 0; j--) {
                if  ((i+j) % 2 != 0){
                    fields[i][j] = new Pawn(i, j,true);
                    count ++;
                    if (count == n * 2){
                        break myBreakLabelWhite;
                    }
                }
            }
        }

    }
    public void removePawn(int x, int y){
        fields[x][y] = null;
    }

    public void movePawn(int fromX, int fromY, int toX, int toY){
        Pawn pawn = fields[fromX][fromY];
        fields[toX][toY] = pawn;
        fields[fromX][fromY] = null;
        pawn.setPositionX(toX);
        pawn.setPositionY(toY);
    }

    @Override
    public String toString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String content = "Game{" +
                ", fields=\n";
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                content += "Row " + (i+1) + ", column " + alphabet.charAt(j) + ": " + fields[i][j] + ", \n";
            }
        }
                content += "}";
                return content;
    }
    public static void main(String[] args) {

        Board board = new Board();
        System.out.println(board.toString());
        board.movePawn(0,1,0,2);
        System.out.println(board.toString());
    }

}

