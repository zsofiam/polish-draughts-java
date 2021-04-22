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

    public boolean tryToMakeMove(int targetX, int targetY){

        return ( isOnBoard(targetX) && isOnBoard(targetY) &&
                ((targetY + targetX) % 2 != 0) &&
                        (this.getPositionX() != targetX && this.getPositionY() != targetY) &&
                targetIsEmpty(targetX, targetY) &&
                (targetIsOneStepAway(targetX, targetY) || twoStepsAwayAndEnemyIsBetweenSteps(targetX, targetY)));
    }

    private boolean isOnBoard(int targetX) {
        return (targetX >= 0 && targetX < this.board.getFields().length);
    }

    private boolean targetIsEmpty(int targetX, int targetY){
        return (this.board.getFields()[targetX][targetY] == null);
    }
    private boolean targetIsOneStepAway(int targetX, int targetY){
        return (Math.abs(targetY - this.getPositionY()) == 1 && Math.abs(targetX - this.getPositionX()) == 1);
    }
    private boolean twoStepsAwayAndEnemyIsBetweenSteps(int targetX, int targetY){
        return (targetIsTwoStepsAway(targetX, targetY) && enemyIsBetweenSteps(targetX, targetY));
    }
    private boolean targetIsTwoStepsAway(int targetX, int targetY){
        return (Math.abs(targetY - this.positionY) == 2 && Math.abs(targetX - this.positionX) == 2);
    }
    private boolean enemyIsBetweenSteps(int targetX, int targetY){
        int[]enemyPosition = findEnemyPosition(targetX, targetY);
        if (board.getFields()[enemyPosition[0]][enemyPosition[1]] != null &&
                ( (board.getFields()[enemyPosition[0]][enemyPosition[1]].isWhite() && !this.isWhite())
            || (!board.getFields()[enemyPosition[0]][enemyPosition[1]].isWhite() && this.isWhite())) ){
            return true;
        }
        else return false;
    }
    public int[] findEnemyPosition(int targetX, int targetY){
        int enemyPositionX = -1;
        int enemyPositionY = -1;
        if (targetX > this.positionX){
            enemyPositionX = targetX - 1;
        }
        else {
            enemyPositionX = targetX + 1;
        }
        if (targetY > this.positionY){
            enemyPositionY = targetY - 1;
        }
        else {
            enemyPositionY = targetY + 1;
        }
        return new int[]{enemyPositionX, enemyPositionY};
    }
}