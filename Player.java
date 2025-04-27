import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String symbol;
    private List<Point> moves;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.moves = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void addMove(int x, int y) {
        moves.add(new Point(x, y));
    }

    public void resetMoves() {
        moves.clear();
    }

    public List<Point> getMoves() {
        return moves;
    }
}
