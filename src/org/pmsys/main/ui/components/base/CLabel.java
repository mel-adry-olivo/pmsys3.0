package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.utils.FlatStyler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CLabel extends JLabel implements CComponent {

    private final FlatStyler styler;

    public CLabel(Icon icon) {
        this("");
        setIcon(icon);
    }

    public CLabel(ImageIcon icon, int alignment) {
        super(icon, alignment);
        styler = new FlatStyler();
    }

    public CLabel(String text) {
        super(text);
        styler = new FlatStyler();
    }

    // only wraps two lines
    public CLabel wrapOnIndex(int atIndex) {
        String text = getText();

        if (text.length() <= atIndex) {
            return this;
        }
        int breakIndex = text.lastIndexOf(' ', atIndex);

        if (breakIndex == -1) {
            breakIndex = atIndex;
        }

        String wrappedText = String.format("<html>%s<br>%s</html>", text.substring(0, breakIndex), text.substring(breakIndex + 1));
        setText(wrappedText);

        return this;
    }

    public CLabel setForegroundColor(String colorCode) {
        styler.getStyleMap().put("foreground", colorCode);
        return this;
    }

    public CLabel setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    public CLabel isLink() {
        String text = getText();
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setText("<html><u>" + text + "</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setText(text);
            }
        });

        return this;
    }

    public CLabel applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }

    public static final String H1_140 = "140% $h1.font";
    public static final String H1_120 = "120% $h1.font";
    public static final String H1 = "$h1.font";
    public static final String H2 = "$h2.font";
    public static final String H3 = "$h3.font";

    public static final String SEMIBOLD = "$semibold.font";
    public static final String LARGE_SEMIBOLD = "130% $semibold.font";
    //public static final String EXTRA_LARGE = " 120% $large.font";
    public static final String LARGE = "$large.font";
    public static final String DEFAULT = "$defaultFont";
    public static final String MEDIUM = "$medium.font";
    public static final String SMALL = "$small.font";
    public static final String MINI = "$mini.font";
}
