package draught;

import java.util.ArrayList;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class Game {
    public Scanner input = new Scanner(System.in);
    public int player;
    private Board board;
    final String alphabet = "ABCDEFGHIJKLMONPQRSTUVWXYZ";

    public Game() {
        this.player = 1;
    }

    public Board getBoard(){
        return this.board;
    }
    public void start() {
        board = new Board();
        board.printBoard();
    }

    public void playRound() {
        System.out.println(this.player + "'s move");
        int[] startPosition = null;
        int[] endPosition = null;
        while (startPosition == null || endPosition == null) {
            System.out.println("Enter starting coordinates:");
            String start = input.nextLine();
            System.out.println("Enter target coordinates:");
            String target = input.nextLine();
            startPosition = convertPlacement(start);
            endPosition = convertPlacement(target);
        }
//check if move is valid,
        board.movePawn(startPosition[0], startPosition[1], endPosition[0],endPosition[1]);
//        checkForWinner();
        swapPlayer();
    }

    private void swapPlayer() {
        if (this.player == 1){
            this.player = 2;
        }
        else {
            this.player = 1;
        }
    }

    public void tryToMakeMove() {

    }

    public int[] choosePawn() {
        System.out.println("Choose your pawn to move: ");
        String coordinate = input.nextLine();
        int row = convertPlacement(coordinate)[0];
        int col = convertPlacement(coordinate)[1];
        return new int[]{row, col};
    }

    public int[] convertPlacement(String coordinate) {
        try {
            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            String letter = coordinate.substring(0, 1).toLowerCase();
            int row = new String(alphabet).indexOf(letter);
            int col = Integer.parseInt(coordinate.substring(1)) - 1;
            return new int[]{row, col};
        }
        catch (NumberFormatException e){
            return null;
        }
    }

    public boolean checkForWinner(Pawn[][] fields, int player) {
        int counter = 0;
        for (Pawn[] row : fields) {
            for (Pawn col : row) {
                if (player == 1 && col.isWhite) {
                    counter++;
                } else if (player == 2 && !col.isWhite) {
                    counter++;
                }
            }
        }  //counter only checks for number of pieces, return true is there is a loser
        return counter == 0 || noMoreValidMoves(fields, player);
    }


    public boolean noMoreValidMoves(Pawn[][] fields, int player) {
        //TODO: loop over each player's pawns
        //check the 4 diagonal squares for each pawn
        //check for possible capture moves
        ArrayList<Boolean> booleanList;
        ArrayList<Boolean> whiteValid = new ArrayList<>();
        ArrayList<Boolean> blackValid = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] != null) { //TODO: IndexOutOfBounds error have to be eliminated!!!!! NO MERCYYYYYYYY!!!!!!!!!!!!
                    if (player == 1 && fields[i][j].isWhite) {
                        whiteValid.add((fields[i][j + 1] == null || fields[i][j - 1] == null || fields[i + 1][j] == null || fields[i - 1][j] == null));
                    } else if (player == 2 && !fields[i][j].isWhite) {
                        blackValid.add((fields[i][j + 1] == null || fields[i][j - 1] == null || fields[i + 1][j] == null || fields[i - 1][j] == null));
                    }
                }
            }
        }
        if (player == 1) {
            booleanList = whiteValid;
        } else {
            booleanList = blackValid;
        }
        for (boolean permutation : booleanList){
            if (!permutation) {
                return false;
            }
        } return true;
    }


    public static void main(String[] args) {

        Game game = new Game();
        game.start();
        System.out.println(game.noMoreValidMoves(game.getBoard().fields, game.player));
//        while (!checkForWinner(fields, game.player)) {
//            game.playRound();
//
    }

}
