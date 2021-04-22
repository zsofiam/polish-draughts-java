package draught;

import java.util.Scanner;

public class Game {
    public Scanner input;
    public int player;
    private Board board;
    private boolean gameIsOn;
    private final String alphabet = "ABCDEFGHIJKLMONPQRSTUVWXYZ";

    public Game() {
        this.player = 1;
        input = new Scanner(System.in);
    }

    public Board getBoard(){
        return this.board;
    }
    public void start() {
        this.gameIsOn = true;
        this.board = new Board();
        while (gameIsOn) {
            playRound();
        }
        this.board.printBoard();
        displayResult();
    }

    private void displayResult() {
        System.out.println("PLAYER " + player + " WON!!!! Game over.");
    }

    public void playRound() {
        System.out.println("\033[1;35m");
        this.board.printBoard();
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
//                board.setLastMove(endPosition);
                moveHappened = true;
            }
        }
        if (checkForWinner()){
            this.gameIsOn = false;}
        else{swapPlayer();}
    }
    private int[] getPosition (String place){
        System.out.println("Enter " + place + " coordinates: ");
        String pawnCoordinates = input.nextLine();
        int[] position = convertInputToArray(pawnCoordinates);
        if (position != null && notOnBoard(position)){
            position = null;
        }
        else if(position != null && place.equals("pawn")){
            boolean playerIsWhite = this.player == 1;
            if (this.getBoard().getFields()[position[0]][position[1]] == null ||
                    this.getBoard().getFields()[position[0]][position[1]].isWhite != playerIsWhite){
                position = null;
            }
        }
        return position;
    }

    private boolean notOnBoard(int[] position) {
        if (position[0] < 0 || position[0] >= this.getBoard().getFields().length ||
                position[1] < 0 || position[1] >= this.getBoard().getFields().length){
            return true;
        }
        return false;
    }

    private void swapPlayer() {
        this.player = this.player == 1 ? 2 : 1;
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
    public boolean checkForWinner() {
        if(NoMoreValidMovesForOtherPlayer()){
            return true;
        }
        return false;
    }

    private boolean NoMoreValidMovesForOtherPlayer() {
        boolean playerIsWhite = this.player == 1;
        for (Pawn[]row:this.board.getFields()) {
            for (Pawn cell: row) {
                if (cell != null && cell.isWhite() != playerIsWhite){
                    if (tryToMakeMoveInEveryDirection(cell)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean tryToMakeMoveInEveryDirection(Pawn pawn){
        if (pawn.tryToMakeMove(pawn.getPositionX()-1,pawn.getPositionY()-1)
                || pawn.tryToMakeMove(pawn.getPositionX()-1,pawn.getPositionY()+1)
                || pawn.tryToMakeMove(pawn.getPositionX()+1,pawn.getPositionY()+1)
                || pawn.tryToMakeMove(pawn.getPositionX()+1,pawn.getPositionY()-1)
        || pawn.tryToMakeMove(pawn.getPositionX()-2,pawn.getPositionY()-2)
                || pawn.tryToMakeMove(pawn.getPositionX()-2,pawn.getPositionY()+2)
                || pawn.tryToMakeMove(pawn.getPositionX()+2,pawn.getPositionY()+2)
                || pawn.tryToMakeMove(pawn.getPositionX()+2,pawn.getPositionY()-2)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Game game = new Game();
        game.start();
    }

}
