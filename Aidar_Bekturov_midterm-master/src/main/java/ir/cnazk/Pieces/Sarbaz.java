package ir.cnazk.Pieces;

import ir.cnazk.Board;
import javafx.scene.image.Image;

import java.util.Map;

public class Sarbaz implements Mohre{

    Rang rang;
    String pos;
    Board board;
    Image whiteImage;
    String firstPos;
    boolean hasMoved = false;

    public Sarbaz(Rang rang, String pos) {
        this.rang = rang;
        this.pos = pos;
        firstPos = pos;
        whiteImage = new Image("sarbaz_sefid.png");
    }

    @Override
    public boolean canMove(String square) {
        Board board = getBoard();
        int step = 2;
        if (!hasMoved) step = 3;
        if (rang == Rang.BLACK) {
            if (pos.charAt(0) == square.charAt(0)
                    && pos.charAt(1) - square.charAt(1) < step
                    && board.get(String.format("%c%c", pos.charAt(0), pos.charAt(1) - 1)) == null)
                return true;
            if (board.get(square) != null)
                if (Math.abs(square.charAt(0) - pos.charAt(0)) == 1
                        && pos.charAt(1) - square.charAt(1) == 1
                        && board.get(square).getRang() == getEnemyRang())
                    return true;
        } else {
            if (pos.charAt(0) == square.charAt(0)
                    && square.charAt(1) - pos.charAt(1) < step
                    && board.get(String.format("%c%c", pos.charAt(0), pos.charAt(1) + 1)) == null)
                return true;
            if (board.get(square) != null)
                if (Math.abs(square.charAt(0) - pos.charAt(0)) == 1
                        && square.charAt(1) - pos.charAt(1) == 1
                        && board.get(square).getRang() == getEnemyRang())
                    return true;
        }
        return false;
    }

    @Override
    public boolean moveTo(String square) {
        if (canMove(square)) {
            String lastPos = pos;
            pos = square;
            if (Math.abs(pos.charAt(1) - firstPos.charAt(1)) == 6)
                getBoard().createVazir(rang, pos, lastPos);
            return true;
        }
        return false;
    }

    @Override
    public Rang getRang() {
        return rang;
    }

    @Override
    public Rang getEnemyRang() {
        if (rang == Rang.BLACK)
            return Rang.WHITE;
        return Rang.BLACK;
    }

    @Override
    public Image getImage() {
        return whiteImage;
    }

    @Override
    public String toString() {
        return "S";
    }

    @Override
    public String getPos() {
        return pos;
    }
}
