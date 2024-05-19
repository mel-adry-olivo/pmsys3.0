package org.pmsys.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import org.pmsys.constants.View;
import org.pmsys.main.controller.AuthController;
import org.pmsys.main.controller.ProjectController;
import org.pmsys.main.controller.ProjectListController;
import org.pmsys.main.manager.FormManager;
import org.pmsys.main.service.AuthService;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.service.TaskService;
import org.pmsys.main.service.UserService;
import org.pmsys.main.ui.utils.Benchmark;
import org.pmsys.main.ui.views.*;
import org.pmsys.main.ui.MainWindow;


import javax.swing.SwingUtilities;

public class Application {

    private MainWindow mainWindow;

    private ProjectListView projectListView;
    private DashboardView dashboardView;
    private ProjectView projectView;

    private ProjectService projectService;
    private TaskService taskService;

    private ProjectListController projectListController;
    private ProjectController projectController;

    public Application() { }

    public void launchApplication() {
        mainWindow = new MainWindow();
        initializeViews();
        initializeServices();
        initializeControllers();
        mainWindow.setVisible(true);
    }

    public void authenticateUser() {
        AuthView authView = new AuthView();
        AuthService authService = new AuthService(new UserService());
        AuthController authController = new AuthController(authService, authView, this);
    }

    private void initializeViews() {
        projectListView = new ProjectListView();
        dashboardView = new DashboardView();
        projectView = new ProjectView();

        mainWindow.addView(View.PROJECT_LIST, projectListView);
        mainWindow.addView(View.DASHBOARD, new DashboardView());
        mainWindow.addView(View.PROJECT, projectView);
        mainWindow.addView(View.LOADING, new LoadingView());
    }

    private void initializeServices() {
        projectService = new ProjectService();
        taskService = new TaskService();
    }

    private void initializeControllers() {
        FormManager formManager = new FormManager();

        projectController = new ProjectController(projectService, taskService, projectView, formManager, mainWindow);
        projectListController = new ProjectListController(projectService, projectListView, projectView, projectController);
        projectListController.loadAllProjectsFromFile();

        projectController.setProjectListController(projectListController);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setupLookAndFeel();
            Application application = new Application();
            application.authenticateUser();
        });
    }

    private static void setupLookAndFeel() {
        FlatInspector.install("ctrl shift alt X"); // for debugging

        FlatLightLaf.registerCustomDefaultsSource("org/pmsys/resources/themes");
        FlatInterFont.install();
        FlatLightLaf.setPreferredFontFamily( FlatInterFont.FAMILY );
        FlatLightLaf.setPreferredLightFontFamily( FlatInterFont.FAMILY_LIGHT );
        FlatLightLaf.setPreferredSemiboldFontFamily( FlatInterFont.FAMILY_SEMIBOLD );

        FlatLightLaf.setup();
    }
}
