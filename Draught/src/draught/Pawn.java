package draught;

public class Pawn {
    int positionX;
    int positionY;
    public boolean isWhite;
    Board board;

    public Pawn(Board board, int positionX, int positionY, boolean isWhite) {
        this.board = board;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isWhite = isWhite;
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
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean tryToMakeMove(int targetX, int targetY){
        return ((targetY + targetX) % 2 != 0 &&
                this.getPositionX() != targetX && this.getPositionY() != targetY &&
                targetIsEmpty(targetX, targetY) &&
                (targetIsOneStepAway(targetX, targetY) || twoStepsAwayAndEnemyIsBetweenSteps(targetX, targetY)));
    }
    private boolean targetIsEmpty(int targetX, int targetY){
        return (this.board.fields[targetX][targetY] == null);
    }
    private boolean targetIsOneStepAway(int targetX, int targetY){
        return (Math.abs(targetY - this.getPositionY()) == 1 && Math.abs(targetX - this.getPositionX()) == 1);
    }
    private boolean twoStepsAwayAndEnemyIsBetweenSteps(int targetX, int targetY){
        return (targetIsTwoStepsAway(targetX, targetY) && enemyIsBetweenSteps(targetX, targetY));
    }
    private boolean targetIsTwoStepsAway(int positionX, int positionY){
        return (Math.abs(positionY - this.getPositionY()) == 2 && Math.abs(positionX - this.getPositionX()) == 2);
    }
    private boolean enemyIsBetweenSteps(int positionX, int positionY){
        int enemyPositionX = -1;
        int enemyPositionY = -1;
        if (positionX > this.positionX){
            enemyPositionX = positionX - 1;
        }
        else if(positionX < this.positionX){
            enemyPositionX = positionX + 1;
        }
        if (positionY > this.positionY){
            enemyPositionY = positionY - 1;
        }
        else if(positionY < this.positionY){
            enemyPositionY = positionY + 1;
        }
        if ((board.fields[enemyPositionX][enemyPositionY].isWhite() && !this.isWhite())
            || (!board.fields[enemyPositionX][enemyPositionY].isWhite() && this.isWhite())){
            board.fields[enemyPositionX][enemyPositionY] = null;
            return true;
        }
        else return false;
    }
}