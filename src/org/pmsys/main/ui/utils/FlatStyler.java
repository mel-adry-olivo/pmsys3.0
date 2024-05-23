package org.pmsys.main.ui.utils;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class FlatStyler {

    private final Map<String, String> styleMap;

    public FlatStyler() {
        styleMap = new HashMap<>();
    }

    public void applyFlatStyle(JComponent component) {

        if(styleMap.isEmpty()) {
            return;
        }

        StringBuilder styleBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : styleMap.entrySet()) {
            styleBuilder
                    .append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append(";");
        }

        String finalStyle = styleBuilder.toString();
        component.putClientProperty("FlatLaf.style", finalStyle);
    }

    public Map<String, String> getStyleMap() {
        return styleMap;
    }
}
