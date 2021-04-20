package draught;

public class Pawn {
    int positionX;
    int positionY;
    public boolean isWhite;
    Board board;

    public Pawn(int positionX, int positionY, boolean isWhite, Board board) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.isWhite = isWhite;
        this.board = board;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    private boolean validateMove(int targetX, int targetY) {
        return ((targetY + targetX) % 2 != 0 && //not on white tile AND
                (this.getPositionX() != targetX && this.getPositionY() != targetY) && //not the same coordinates AND
                Math.abs(targetY - this.getPositionY()) == 1 && Math.abs(targetX - this.getPositionX()) == 1); //diagonal move indicates 1 tile difference from X AND Y
    }

}