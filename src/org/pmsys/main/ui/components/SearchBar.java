package org.pmsys.main.ui.components;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.ui.components.constants.IconConstants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchBar extends CPanel {

    private final CTextField myTextField;
    private final CButton mySearchButton;

    public SearchBar(String placeholder) {
        this.myTextField = CFieldFactory.createBorderlessTextField(placeholder);
        this.mySearchButton = CButtonFactory.createIconButton(IconConstants.SEARCH_ICON_SMALL);

        setupComponent();
    }

    @Override
    public boolean requestFocusInWindow() {
        return myTextField.requestFocusInWindow();
    }

    public void setupComponent() {
        setConstraints("insets 4 8 4 8")
                .setLineBorder(1,1,1,1, 8)
                .applyStyles();
        setPreferredSize(new Dimension(260, 35));

        myTextField.setTextMargin(10,1,10,0)
                .setBackgroundColor(ColorConstants.WHITE)
                .applyStyles();

        myTextField.getDocument().addDocumentListener(new SearchEvent(this));

        add(mySearchButton, "w 0%");
        add(myTextField, "w 100%");
    }

    public void setText(String text) {
        myTextField.setText(text);
    }

    public String getText() {
        return myTextField.getText();
    }

    public static class SearchPopup extends JPopupMenu {

        private final SearchBar searchBar;

        public SearchPopup(SearchBar searchBar) {
            this.searchBar = searchBar;
        }

        private JMenuItem createSearchItem(Project project) {
            JMenuItem item = new JMenuItem(project.getTitle());
            item.setName(project.getId());
            item.setMargin(new Insets(14,8,14,8));
            item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            item.addActionListener(e -> ActionManager.executeAction(Actions.SEARCH_ITEM_CLICK, item, searchBar));
            return item;
        }

        public void loadResults(List<Project> results) {
            this.removeAll();
            int height = 0;

            for (Project project : results) {
                JMenuItem item = createSearchItem(project);
                this.add(item);
                height += item.getPreferredSize().height;
            }

            this.setPreferredSize(new Dimension(searchBar.getWidth(), height));
        }
    }
}
