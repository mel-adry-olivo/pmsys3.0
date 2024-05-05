package org.pmsys.main.ui.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.pmsys.constants.AppColors;
import org.pmsys.main.entity.Task;
import org.pmsys.main.ui.components.base.FlatLabel;
import org.pmsys.main.ui.components.base.FlatLabelFactory;
import org.pmsys.main.ui.components.base.FlatPanel;

import javax.swing.*;

public class TaskCardUI extends FlatPanel {

    private final Task task;

    public TaskCardUI(Task task) {
        this.task = task;
        setupTaskCard();
        setupComponents();
    }

    private void setupTaskCard() {
        setConstraints("insets 12", "", "[]12[]12[]12[]12[]");
        setLineBorder(1,1,1,1, 8);
    }

    private void setupComponents() {

        String title = task.getTitle();
        String priority = task.getPriority();
        String dueDate = task.getDueDate();
        String description = task.getDescription();

        FlatLabel taskTitle = FlatLabelFactory.createDefaultLabel(title, AppColors.BLACK);

//        FlatLabel taskTitle = new FlatLabel(title)
//                .setFontWeight(FlatLabel.MEDIUM)
//                .setColor(AppColors.BLACK)
//                .setFontSize(18);
//
//        TaskTag taskPriority = new TaskTag(Tag.NORMAL);
//        TaskTag taskDue = new TaskTag(Tag.IS_DATE)
//                .setDate("15 March 2024");
//
//        FlatLabel taskDescription = new FlatLabel("Description")
//                .setFontWeight(FlatLabel.MEDIUM)
//                .setColor(AppColors.DARK_GREY)
//                .setFontSize(12);
//
//        JProgressBar taskProgressBar = new JProgressBar();
//        taskProgressBar.setValue(31);
//
//        FlatLabel taskProgress = new FlatLabel("31%")
//                .setFontWeight(FlatLabel.MEDIUM)
//                .setColor(AppColors.DARK_GREY)
//                .setFontSize(11);
//
//
//        JButton optionButton = ComponentFactory.Buttons.createIconButton();
//        optionButton.setIcon(new FlatSVGIcon("org/pmsys/resources/icons/kebab.svg", 18,18));
//
//        add(taskTitle, "pushx");
//        add(optionButton, "wrap");
//        add(taskPriority, "split 2, gapright 4");
//        add(taskDue, "wrap");
//        add(taskDescription, "wrap");
//        add(taskProgressBar, "wrap, grow, span 2 1");
//        add(taskProgress, "");
//    }
//
//    private class TaskTag extends FlatPanel {
//
//
//
//        private FlatLabel tagLabel;
//
//        public TaskTag(Tag tag) {
//            setupTag(tag);
//            setConstraints("insets 1 8 1 8, center");
//        }
//
//        private void setupTag(Tag tag) {
//            tagLabel = new FlatLabel(tag.name())
//                    .setFontSize(11)
//                    .setFontWeight(FlatLabel.MEDIUM)
//                    .setColor(tag.color);
//
//            enableLineBorder(true, tag.color, 4);
//            setBackgroundColor(tag.backgroundColor);
//            setPanelSize(tagLabel.getPreferredSize());
//            add(tagLabel, "gaptop 3");
//        }
//
//        public TaskTag setDate(String date) { // Use 'public' here if you intend to expose this
//            tagLabel.setText(date);
//            return this;
//        }
//    }
//
//    private enum Tag {
//        LOW("#5190B2", "#CEECFD"),
//        NORMAL("#277153", "#D0FBE9"),
//        HIGH("#912018", "#F8D6D3"),
//        OVERDUE("#912018", "#FFFFFF"),
//        IS_DATE("#602093", "#FFFFFF");
//
//        private final String color;
//        private final String backgroundColor;
//
//        Tag(String color, String backgroundColor) {
//            this.color = color;
//            this.backgroundColor = backgroundColor;
//        }
    }
}
