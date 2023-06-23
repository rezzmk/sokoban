package pt.iscte.madco.poo.sokoban.abstractions;

import java.util.List;

import pt.iscte.madco.poo.sokoban.interfaces.BiDimGameEngine;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;

/**
 * Implements GraphPack logic
 */
public abstract class GameEngine implements BiDimGameEngine, Observer {
    @Override
    public void clearCanvas() {
        ImageMatrixGUI.getInstance().clearImages();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addObjectsToCanvas(Object object) {
        ImageMatrixGUI.getInstance().addImages((List<ImageTile>) object);
    }

    @Override
    public void updateSessionStatus(String status) {
        ImageMatrixGUI.getInstance().setName("Sokoban");
        ImageMatrixGUI.getInstance().setStatusMessage(status);
    }

    @Override
    public void setCanvasSize(int w, int h) {
        ImageMatrixGUI.setSize(w, h);
    }

    @Override
    public void start() {
        ImageMatrixGUI.getInstance().registerObserver(this);
        ImageMatrixGUI.getInstance().go();
    }

    @Override
    public void updateScreen() {
        ImageMatrixGUI.getInstance().update();
    }

    @Override
    public int getLastKeyPressed(Observed arg) {
        return ((ImageMatrixGUI) arg).keyPressed();
    }
}
