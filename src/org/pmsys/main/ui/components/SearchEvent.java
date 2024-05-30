package org.pmsys.main.ui.components;

import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.IndexingManager;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class SearchEvent implements DocumentListener {

    private final SearchBar searchBar;
    private final SearchBar.SearchPopup popup;

    public SearchEvent(SearchBar searchBar) {
        this.searchBar = searchBar;
        this.popup = new SearchBar.SearchPopup(searchBar);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String query = searchBar.getText();
        performSearch(query);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String query = searchBar.getText();
        performSearch(query);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public void performSearch(String query) {
        if (query.isEmpty()) {
            popup.setVisible(false);
            return;
        }

        List<Project> results = IndexingManager.INSTANCE.searchProjects(query);
        popup.loadResults(results);

        if (!results.isEmpty()) {
            popup.pack();
            popup.show(searchBar, 0, searchBar.getHeight());
            searchBar.requestFocusInWindow();
        } else {
            popup.setVisible(false);
        }
    }
}
