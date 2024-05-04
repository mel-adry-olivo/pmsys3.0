package org.pmsys.main.ui.windows;

import net.miginfocom.swing.MigLayout;
import org.pmsys.main.entity.User;
import org.pmsys.main.ui.components.HeaderUI;
import org.pmsys.main.ui.components.MenuUI;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private MenuUI menu;
    private HeaderUI header;

    private FlatPanel mainContentArea;

    public MainWindow( ) {
        setupWindow();
        setupComponents();
    }

    public void addView(FlatPanel panel, String name) {
        mainContentArea.add(panel, name);
    }

    private void setupComponents() {
        header = new HeaderUI();
        mainContentArea = new FlatPanel();
        mainContentArea.setLayout(new CardLayout());
        menu = new MenuUI(header, mainContentArea);

        add(menu, "cell 0 0 1 2, w 0%, grow");
        add(header, "cell 1 0, h 0%, grow");
        add(mainContentArea, "cell 1 1, grow");
    }

    private void setupWindow() {
        setLayout(new MigLayout("insets 0, fill", "[grow 0]0[]", "[]0[grow]"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("System");
        setSize(1280, 720);
        setLocationRelativeTo(null);
    }


    public FlatPanel getMainContentArea() {
        return mainContentArea;
    }

    public HeaderUI getHeader() {
        return header;
    }

}
