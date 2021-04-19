package draught;

public class Game {

    private int n;
    private Pawn[][] fields;
    public Game(int n) {
        this.n = n;
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
