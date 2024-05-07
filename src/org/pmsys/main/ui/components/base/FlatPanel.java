package org.pmsys.main.ui.components.base;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import net.miginfocom.swing.MigLayout;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class FlatPanel extends JPanel implements MigLayoutCompatible<FlatPanel>, MouseListener {

    private final MigLayout layout;
    private final FlatStyler styler;

    private String layoutConstraints = "";
    private String columnConstraints = "";
    private String rowConstraints = "";

    private boolean isHoverable = false;

    public FlatPanel() {
        this("");
    }
    public FlatPanel(String... constraints) {
        layout = new MigLayout();
        styler = new FlatStyler();

        setConstraints(constraints);
        setLayout(layout);
        setPreferredSize(new Dimension(200, 200)); // default
        setOpaque(true);
        setVisible(true);
    }

    @Override
    public FlatPanel setConstraints(String... constraints) {
        layoutConstraints = constraints[0];
        layout.setLayoutConstraints(layoutConstraints);
        if (constraints.length > 1) {
            columnConstraints = constraints[1];
            layout.setColumnConstraints(columnConstraints);
        }
        if (constraints.length > 2) {
            rowConstraints = constraints[2];
            layout.setRowConstraints(rowConstraints);
        }
        return this;
    }
    @Override
    public FlatPanel setRowConstraints(String rowConstraints) {
        this.rowConstraints = rowConstraints;
        layout.setRowConstraints(rowConstraints);
        return this;
    }
    @Override
    public FlatPanel setColumnConstraints(String columnConstraints) {
        this.columnConstraints = columnConstraints;
        layout.setColumnConstraints(columnConstraints);
        return this;
    }
    @Override
    public FlatPanel setLayoutConstraints(String layoutConstraints) {
        this.layoutConstraints = layoutConstraints;
        layout.setLayoutConstraints(layoutConstraints);
        return this;
    }

    public FlatPanel setLineBorder(int top, int right, int bottom, int left, int radius) {
        setLineBorder(top,right,bottom,left, "#8F8F8F", radius);
        return this;
    }
    public void setLineBorder(int top, int right, int bottom, int left, String color, int radius) {
        if(getBorder() != null) {
            throw new IllegalArgumentException("Border already set");
        }
        styler.getStyleMap().put("border",
                top + ", " +
                right + ", " +
                bottom + ", " +
                left + ", "   +
                color +", 1," +
                String.valueOf(radius * 2));
    }
    public FlatPanel setMatteBorder(int top, int right, int bottom, int left) {
        if (styler.getStyleMap().containsKey("border")) {
            throw new IllegalArgumentException("Border already set");
        }
        setBorder(BorderFactory.createMatteBorder(top, right, bottom, left, Color.decode("#8F8F8F")));
        return this;
    }
    public FlatPanel setBackgroundColor(String colorCode) {
        styler.getStyleMap().put("background", colorCode);
        return this;
    }

    public FlatPanel setHoverable(boolean isHoverable) {
        this.isHoverable = isHoverable;
        if (isHoverable) {
            addMouseListener(this);
        } else {
            removeMouseListener(this);
        }
        return this;
    }

    public FlatPanel setArc(int arc) {
        if (styler.getStyleMap().containsKey("border") || getBorder() != null) {
            throw new IllegalArgumentException("Cannot set arc if border already set");
        }
        styler.getStyleMap().put("arc", String.valueOf(arc));
        return this;
    }

    public FlatPanel applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isHoverable) {
            putClientProperty("FlatLaf.style", "background: darken(#ffffff, 2%);");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (isHoverable) {
            putClientProperty("FlatLaf.style", "background: #ffffff;");
        }
    }

    public String getLayoutConstraints() {
        return layoutConstraints;
    }

    public String getColumnConstraints() {
        return columnConstraints;
    }

    public String getRowConstraints() {
        return rowConstraints;
    }
}
