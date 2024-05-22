package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CComboBox<E> extends JComboBox<E> implements FlatStyleable<CComboBox<E>>, CComponent {

    private FlatStyler styler;

    public CComboBox(E[] items) {
        super(items);

        setPreferredSize(new Dimension(30, getHeight()));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ((JComponent) getUI().getAccessibleChild(this, 0)).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public Object getSelectedItem() {
        return (Object) super.getSelectedItem();
    }


    @Override
    public CComboBox<E> getComponent() {
        return this;
    }

    @Override
    public FlatStyler getStyler() {
        if(styler == null) {
            styler = new FlatStyler();
        }
        return styler;
    }

}
