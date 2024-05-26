package org.pmsys.main.util;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.managers.SessionManager;

import java.util.List;

public class ReportFormatter {

    public static String formatOverall(List<Project> projects) {
        StringBuilder formatter = new StringBuilder();
        int done = 0;
        int overdue = 0;

        formatter.append("--").append("\n");
        formatter.append("Username: ").append(SessionManager.INSTANCE.getUser().getUsername()).append("\n");
        formatter.append("--").append("\n");

        if(projects.isEmpty()) {
            formatter.append("No projects found.");
            return formatter.toString();
        }

        formatter.append("Projects: \n");

        for (Project project : projects) {
            formatter.append("--").append("\n");
            formattedProject(project, formatter);
            if (project.getStatus().equalsIgnoreCase("done")) {
                done++;
            } else if (project.getStatus().equalsIgnoreCase("overdue")) {
                overdue++;
            }
        }
        formatter.append("--").append("\n");
        formatter.append("Total Projects: ").append(projects.size()).append("\n");
        formatter.append("Total Done Projects: ").append(done).append("\n");
        formatter.append("Total Overdue Projects: ").append(overdue);

        return formatter.toString();
    }

    public static String formatIndividual(Project project) {
        StringBuilder formatter = new StringBuilder();
        int done = 0;
        int inProgress = 0;
        int toReview = 0;
        int ready = 0;

        formatter.append("--").append("\n");
        formatter.append("Username: ").append(SessionManager.INSTANCE.getUser().getUsername()).append("\n");
        formatter.append("--").append("\n");
        formatter.append("Project Title: ").append(project.getTitle()).append("\n");
        formatter.append("Project Description: ").append(project.getDescription()).append("\n");
        formatter.append("Project Status: ").append(project.getStatus()).append("\n");
        formatter.append("Project Due Date: ").append(project.getDueDate()).append("\n");
        formatter.append("--").append("\n");

        if(project.getTasks().isEmpty()) {
            formatter.append("No tasks found.");
            return formatter.toString();
        }

        formatter.append("Tasks: \n");

        for (Task task : project.getTasks()) {
            formatter.append("--").append("\n");
            formattedTask(task, formatter);

            String status = task.getStatus().toLowerCase();
            switch (status) {
                case "done" -> done++;
                case "in progress" -> inProgress++;
                case "to review" -> toReview++;
                case "ready" -> ready++;
            }
        }

        formatter.append("--").append("\n");
        formatter.append("Total Tasks: ").append(project.getOverallTaskCount()).append("\n");
        formatter.append("Total Done Tasks: ").append(done).append("\n");
        formatter.append("Total In Progress Tasks: ").append(inProgress).append("\n");
        formatter.append("Total To Review Tasks: ").append(toReview).append("\n");
        formatter.append("Total Ready Tasks: ").append(ready);

        return formatter.toString();
    }

    private static void formattedTask(Task task, StringBuilder formatter) {
        formatter.append("Title: ").append(task.getTitle()).append("\n");
        formatter.append("Description: ").append(task.getDescription()).append("\n");
        formatter.append("Due Date: ").append(task.getDueDate()).append("\n");
        formatter.append("Priority: ").append(task.getPriority()).append("\n");
        formatter.append("Status: ").append(task.getStatus()).append("\n");
    }

    private static void formattedProject(Project project, StringBuilder formatter) {
        formatter.append("Title: ").append(project.getTitle()).append("\n");
        formatter.append("Description: ").append(project.getDescription()).append("\n");
        formatter.append("Status: ").append(project.getStatus()).append("\n");
        formatter.append("Due Date: ").append(project.getDueDate()).append("\n");
        formatter.append("Total Tasks: ").append(project.getOverallTaskCount()).append("\n");
        formatter.append("Done Tasks: ").append(project.getTaskDoneCount()).append("\n");
    }
}
