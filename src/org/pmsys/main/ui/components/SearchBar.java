package org.pmsys.main.ui.components;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.AppIcons;
import org.pmsys.main.ui.components.base.*;

import java.awt.*;

public class SearchBar extends CPanel {

    private final CTextField myTextField;
    private final CButton mySearchButton;

    public SearchBar(String placeholder) {
        this.myTextField = CFieldFactory.createBorderlessTextField(placeholder);
        this.mySearchButton = CButtonFactory.createIconButton(AppIcons.SEARCH_ICON_SMALL);

        setupComponent();
    }

    public void setText(String text) {
        myTextField.setText(text);
    }

    public String getText() {
        return myTextField.getText();
    }

    private void setupComponent() {

        setConstraints("insets 4 8 4 8")
                .setLineBorder(1,1,1,1, 8)
                .applyFlatStyle();
        setPreferredSize(new Dimension(260, 35));

        myTextField.setTextMargin(10,1,10,0)
                .setBackgroundColor(AppColors.WHITE)
                .applyFlatStyle();

        myTextField.getDocument().addDocumentListener(new SearchBarDocumentListener(this));


        add(mySearchButton, "w 0%");
        add(myTextField, "w 100%");
    }
}
