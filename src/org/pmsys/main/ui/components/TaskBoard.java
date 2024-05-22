package org.pmsys.main.ui.components;

import org.pmsys.main.entities.Task;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TaskBoard extends CScrollPane {

    private Map<String, Section> sections; // map status to section
    private int maxSectionHeight;

    public TaskBoard() {
        initBoard();
        setupBoardLayout();
    }

    public void resetBoard() {
        view.removeAll();
        initBoard();
        setupBoardLayout();
        updateBoardSize();
    }

    public void addTask(TaskCard taskCard) {
        Section section = sections.get(taskCard.getTask().getStatus());
        if (section != null) {
            section.addTask(taskCard, Options.CARD_CONSTRAINTS);
            recalculateBoardSize(section);
        }
    }

    public void removeTask(TaskCard taskCard) {
        Section section = sections.get(taskCard.getTask().getStatus());
        section.removeTask(taskCard);
        recalculateBoardSize(section);
    }

    public void updateTask(TaskCard taskCard, String oldStatus) {

        Task task = taskCard.getTask();

        Section oldSection = sections.get(oldStatus);
        Section newSection = sections.get(task.getStatus());

        newSection.updateTask(taskCard);

        if (oldSection != newSection) {
            removeAndAdd(taskCard, oldSection, newSection);
        }
    }

    private void removeAndAdd(TaskCard card, Section oldSection, Section newSection) {
        oldSection.removeTask(card);
        recalculateBoardSize(oldSection);

        newSection.addTask(card, Options.CARD_CONSTRAINTS);
        recalculateBoardSize(newSection);
    }

    private void recalculateBoardSize(Section section) {
        int currentSectionHeight = section.getHeight();
        if(currentSectionHeight > maxSectionHeight) {
            maxSectionHeight = currentSectionHeight;
            updateBoardSize();
        }
    }

    private void updateBoardSize() {
        int width = view.getWidth();
        int newHeight = maxSectionHeight + Options.getTotalHeaderSpace();

        view.setPreferredSize(new Dimension(width, newHeight));
        view.repaint();
        view.revalidate();
    }

    private void initBoard() {
        sections = new HashMap<>();
        maxSectionHeight = 0;

        addSection("Ready", new Section("Ready"));
        addSection("In Progress", new Section("In Progress"));
        addSection("To Review", new Section("To Review"));
        addSection("Done", new Section("Done"));
    }

    private void addSection(String status, Section section) {
        sections.put(status, section);
        addToView(section.getHeader(), Options.HEADER_CONSTRAINTS);
        addToView(section.getSection(), Options.SECTION_CONSTRAINTS);
    }

    private void setupBoardLayout() {
        String layoutConstraints = "flowy, insets 16 24 0 14, fillx, wrap 2, nocache";
        String rowConstraints = "[]" + Options.CARD_GAP + "![]";
        String columnConstraints =
                "[]" + Options.SECTION_GAP
                + "![]" + Options.SECTION_GAP
                + "![]" + Options.SECTION_GAP
                + "![]";
        setConstraints(layoutConstraints, columnConstraints, rowConstraints);
    }

    // wrapper class for the task header and container
    private static class Section {

        private CPanel header;
        private CPanel container;
        private Map<Task, TaskCard> cards;

        private Section(String name) {
            initSection(name);
        }

        private void addTask(TaskCard taskCard, String constraint) {
            container.add(taskCard, constraint);
            cards.put(taskCard.getTask(), taskCard);
            updateLayout();
        }

        private void removeTask(TaskCard taskCard) {
            TaskCard cardToRemove = cards.get(taskCard.getTask());
            if (cardToRemove != null) {
                container.remove(cardToRemove);
                cards.remove(cardToRemove.getTask());
                updateLayout();
            }
        }

        private void updateTask(TaskCard taskCard) {
            TaskCard cardToUpdate = cards.get(taskCard.getTask());
            if(cardToUpdate != null) {
                cardToUpdate.updateTaskDetails(taskCard.getTask());
                updateLayout();
            }
        }

        private void updateLayout() {
            container.setRowConstraints("[]0".repeat(cards.size()));
            container.getParent().doLayout();
            container.repaint();
            container.revalidate();
        }

        private int getHeight() {
            return container.getSize().height;
        }

        private CPanel getSection() {
            return container;
        }

        private CPanel getHeader() {
            return header;
        }

        private void initSection(String name) {
            cards = new HashMap<>();
            header = new CPanel();
            container = new CPanel("insets 0, flowy", "", "");

            header.setConstraints("insets 10 12 10 12, filly", "[]push[]");
            header.setBackgroundColor(ColorConstants.WHITE).setLineBorder(1,1,1,1, 8);
            header.applyStyles();
            header.add(CLabelFactory.createMediumLabel(name, ColorConstants.BLACK));
        }
    }

    private static class Options {
        static String HEADER_HEIGHT = "46";
        static String CARD_HEIGHT = "0";
        static String CARD_GAP = "16";
        static String SECTION_GAP = "16";
        static String CARD_CONSTRAINTS = "w 100%, h " + CARD_HEIGHT + "%, gapbottom " + CARD_GAP + "!";
        static String HEADER_CONSTRAINTS = "w 25%, h " + HEADER_HEIGHT  + "!";
        static String SECTION_CONSTRAINTS = "w 25%, h 0%, growy";
        public static int getTotalHeaderSpace() {
            return Integer.parseInt(HEADER_HEIGHT) + (Integer.parseInt(CARD_GAP) * 2);
        }
    }
}
