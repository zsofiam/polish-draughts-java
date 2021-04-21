package draught;

import java.util.Scanner;

public class Game {
    public Scanner input;
    public int player;
    private Board board;
    private int[] lastMoveCoordinates;
    private final String alphabet = "ABCDEFGHIJKLMONPQRSTUVWXYZ";

    public Game() {
        this.player = 1;
        input = new Scanner(System.in);
    }

    public Board getBoard(){
        return this.board;
    }
    public void start() {
        this.board = new Board();
        while (true) {
            playRound();
        }
    }

    public void playRound() {
        this.board.printBoard();
        System.out.println("\u001b[1;31m");
        System.out.println("\u001b[4;35m");
        System.out.println("Player " + this.player + "'s move");
        boolean moveHappened = false;
        while(!moveHappened){
            int[] startPosition = null;
            while(startPosition == null){
                startPosition = getPosition("pawn");
            }
            int[] endPosition = null;
            while (endPosition == null){
                endPosition = getPosition("target");
            }
            Pawn pawn = this.getBoard().getFields()[startPosition[0]][startPosition[1]];
            if (pawn.tryToMakeMove(endPosition[0],endPosition[1])){
                board.movePawn(startPosition[0], startPosition[1], endPosition[0],endPosition[1]);
                lastMoveCoordinates = endPosition;
                moveHappened = true;
            }
        }
        checkForWinner();
        swapPlayer();
    }
    private int[] getPosition (String place){
        System.out.println("Enter " + place + " coordinates: ");
        String pawnCoordinates = input.nextLine();
        int[] position = convertInputToArray(pawnCoordinates);
        return position;
    }

    private void swapPlayer() {
        if (this.player == 1){
            this.player = 2;
        }
        else {
            this.player = 1;
        }
    }

    public int[] convertInputToArray(String coordinates) {
        try {
            String letter = coordinates.substring(0, 1).toUpperCase();
            int row = alphabet.indexOf(letter);
            int col = Integer.parseInt(coordinates.substring(1)) - 1;
            return new int[]{row, col};
        }
        catch (NumberFormatException e){
            return null;
        }
    }

    public void checkForWinner() {
        checkIfPlayerWon();
        /*int counter = 0;
        for (Pawn[] row : fields) {
            for (Pawn col : row) {
                if (player == 1 && col.isWhite) {
                    counter++;
                } else if (player == 2 && !col.isWhite) {
                    counter++;
                }
            }
        }*/
        //counter only checks for number of pieces, return true is there is none
//        return counter == 0 || validMoves(fields, player).size() == 0; //validMove's size indicates the number of possible steps
        checkForValidMovesForOtherPlayer();
    }
    private void checkIfPlayerWon(){
        Pawn[][] fields = this.getBoard().getFields();
        boolean pawnIsWhite = fields[lastMoveCoordinates[0]][lastMoveCoordinates[1]].isWhite();
        if( checkDiagonal1(pawnIsWhite) || checkDiagonal2(pawnIsWhite)
                || checkDiagonal3(pawnIsWhite) || checkDiagonal4(pawnIsWhite) ){

        }

    }
    private boolean checkDiagonal1(boolean pawnIsWhite){
        if (lastMoveCoordinates[0] -4 >= 0 && lastMoveCoordinates[1]-4 >= 0){
            int count = 0;
            for (int i = lastMoveCoordinates[0]; i >= lastMoveCoordinates[0]-4; i--) {
                for (int j = lastMoveCoordinates[1]; j >= lastMoveCoordinates[1]-4; j--) {
                    if (this.getBoard().getFields()[i][j] != null && this.getBoard().getFields()[i][j].isWhite() == pawnIsWhite){
                        count ++;
                    }
                }
            }
            if (count == 5){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonal2(boolean pawnIsWhite){
        if (lastMoveCoordinates[0] -4 >= 0 && lastMoveCoordinates[1]+4 < this.getBoard().getFields()[0].length){
            int count = 0;
            for (int i = lastMoveCoordinates[0]; i >= lastMoveCoordinates[0]-4; i--) {
                for (int j = lastMoveCoordinates[1]; j <= lastMoveCoordinates[1]+4; j++) {
                    if (this.getBoard().getFields()[i][j] != null && this.getBoard().getFields()[i][j].isWhite() == pawnIsWhite){
                        count ++;
                    }
                }
            }
            if (count == 5){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonal3(boolean pawnIsWhite){
        if (lastMoveCoordinates[0] + 4 < this.getBoard().getFields().length && lastMoveCoordinates[1]+4 < this.getBoard().getFields()[0].length){
            int count = 0;
            for (int i = lastMoveCoordinates[0]; i <= lastMoveCoordinates[0] + 4; i++) {
                for (int j = lastMoveCoordinates[1]; j <= lastMoveCoordinates[1] + 4; j++) {
                    if (this.getBoard().getFields()[i][j] != null && this.getBoard().getFields()[i][j].isWhite() == pawnIsWhite){
                        count ++;
                    }
                }
            }
            if (count == 5){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonal4(boolean pawnIsWhite){
        if (lastMoveCoordinates[0] + 4 < this.getBoard().getFields().length && lastMoveCoordinates[1] - 4 >= 0){
            int count = 0;
            for (int i = lastMoveCoordinates[0]; i <= lastMoveCoordinates[0] + 4; i++) {
                for (int j = lastMoveCoordinates[1]; j >= lastMoveCoordinates[1] - 4; j--) {
                    if (this.getBoard().getFields()[i][j] != null && this.getBoard().getFields()[i][j].isWhite() == pawnIsWhite){
                        count ++;
                    }
                }
            }
            if (count == 5){
                return true;
            }
        }
        return false;
    }

    private void checkForValidMovesForOtherPlayer() {

    }

    /*public int[] isItOnBoard(int x, int y) {
        int[] res = new int[2];
        try {
            if (board.fields[x][y] == null) {
                res[0] = x;
                res[1] = y;
                return res;}
        } catch (IndexOutOfBoundsException e) {
            return null;
        }  return null;
    }

    public ArrayList validMoves(int player) {
        Pawn[][] fields = this.getBoard().getFields();
        //TODO: loop over each player's pawns
        //check the 4 diagonal squares for each pawn
        //check for possible capture moves
        ArrayList<int[]> valid = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {

                if (fields[i][j] != null) {
                   if (isItOnBoard(i-1,j-1) != null) {
                       valid.add(isItOnBoard(i-1,j-1));
                    } if (isItOnBoard(i-1,j+1) != null) {
                        valid.add(isItOnBoard(i-1,j+1));
                    } if (isItOnBoard(i+1,j-1) != null) {
                        valid.add(isItOnBoard(i+1,j-1));
                    } if (isItOnBoard(i+1,j+1) != null) {
                        valid.add(isItOnBoard(i+1,j+1));
                    }
                }
            }
        }
        return valid;
    }*/


    public static void main(String[] args) {

        Game game = new Game();
        game.start();
        game.playRound();
    }

}
