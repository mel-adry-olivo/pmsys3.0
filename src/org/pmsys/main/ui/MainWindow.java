package org.pmsys.main.ui;

import net.miginfocom.swing.MigLayout;
import org.pmsys.main.ui.components.HeaderUI;
import org.pmsys.main.ui.components.MenuUI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private MenuUI menu;
    private HeaderUI header;

    private JLayeredPane mainContentArea;

    public MainWindow( ) {
        setupWindow();
        setupComponents();
    }

    public void addView(JComponent view, String name) {
        view.setPreferredSize(new Dimension(mainContentArea.getWidth(), mainContentArea.getHeight()));
        mainContentArea.add(view, name);
    }

    private void setupComponents() {
        mainContentArea = new JLayeredPane();
        mainContentArea.setLayout(new CardLayout());
        mainContentArea.setOpaque(true);
        mainContentArea.setVisible(true);

        header = new HeaderUI();
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

    public CardLayout getContentLayout() {
        return (CardLayout) mainContentArea.getLayout();
    }

    public JLayeredPane getMainContentArea() {
        return mainContentArea;
    }

    public HeaderUI getHeader() {
        return header;
    }

}
