package org.pmsys.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import org.pmsys.constants.View;
import org.pmsys.main.actions.ActionManager;
import org.pmsys.main.actions.auth.LoginAction;
import org.pmsys.main.actions.auth.RegisterAction;
import org.pmsys.main.actions.auth.SwitchFormAction;
import org.pmsys.main.controller.ProjectController;
import org.pmsys.main.controller.ProjectListController;
import org.pmsys.main.manager.FormManager;
import org.pmsys.main.service.*;
import org.pmsys.main.ui.views.*;
import org.pmsys.main.ui.MainWindow;


import javax.swing.SwingUtilities;

public class Application {

    private static Application instance;
    public static Application start() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    private MainWindow mainWindow;

    private ProjectListView projectListView;
    private DashboardView dashboardView;
    private ProjectView projectView;

    private ProjectService projectService;
    private TaskService taskService;

    private ProjectListController projectListController;
    private ProjectController projectController;

    private Application() {
        load();
        authenticateUser();
    }

    public void launchApplication() {
        mainWindow = new MainWindow();
        initializeViews();
        initializeServices();
        initializeControllers();
        mainWindow.setVisible(true);
    }

    private void load() {
        ServiceManager.registerService("user", new UserService());
        ServiceManager.registerService("authentication", new AuthService());

        ActionManager.registerAction("login", new LoginAction());
        ActionManager.registerAction("register", new RegisterAction());
        ActionManager.registerAction("switchAuthForm", new SwitchFormAction());
    }

    public void authenticateUser() {
        AuthWindow authWindow = new AuthWindow();
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
            Application.start();
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
