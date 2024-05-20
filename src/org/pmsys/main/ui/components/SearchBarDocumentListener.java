package org.pmsys.main.ui.components;

import org.pmsys.main.entities.Project;
import org.pmsys.main.managers.IndexingManager;
import org.pmsys.main.managers.ProjectManager;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class SearchBarDocumentListener implements DocumentListener {

    private SearchBar searchBar;

    public SearchBarDocumentListener(SearchBar searchBar) {
        this.searchBar = searchBar;
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
        if(query.isEmpty()) {
            ProjectManager.INSTANCE.reloadProjectList();
        } else {
            List<Project> results = IndexingManager.INSTANCE.searchProjects(query);
            ProjectManager.INSTANCE.reloadSearchedProjects(results);
        }
    }

}
