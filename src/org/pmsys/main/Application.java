package org.pmsys.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import org.pmsys.main.controller.AuthController;
import org.pmsys.main.controller.ProjectController;
import org.pmsys.main.service.AuthService;
import org.pmsys.main.service.ProjectService;
import org.pmsys.main.service.UserService;
import org.pmsys.main.ui.views.ProjectView;
import org.pmsys.main.ui.windows.AuthWindow;
import org.pmsys.main.ui.views.DashboardView;
import org.pmsys.main.ui.views.ProjectListView;
import org.pmsys.main.ui.windows.MainWindow;


import javax.swing.*;

public class Application {

    private MainWindow mainWindow;

    public Application() { }

    public void loadApplication() {
        mainWindow = new MainWindow();

        ProjectListView projectListView = new ProjectListView();
        ProjectService projectService = new ProjectService();
        ProjectController projectController = new ProjectController(projectService, projectListView, mainWindow);
        projectController.loadProjectFromFile();

        DashboardView dashboardView = new DashboardView();

        ProjectView projectView = new ProjectView();

        mainWindow.addView(projectListView, "projectListView");
        mainWindow.addView(dashboardView, "dashboardView");
        mainWindow.addView(projectView, "projectView");

    }

    public boolean isApplicationLoaded() {
        return mainWindow != null;
    }

    public void showApplication() {
        mainWindow.setVisible(true);
    }

    public void authenticateUser() {
        AuthWindow authWindow = new AuthWindow();
        AuthService authService = new AuthService(new UserService());
        AuthController authController = new AuthController(authService, authWindow, this);
    }

    public static void main(String[] args) {

        System.out.println(System.getProperty("java.class.path"));

        SwingUtilities.invokeLater(() -> {

            FlatInspector.install("ctrl shift alt X");
            FlatLightLaf.registerCustomDefaultsSource("org/pmsys/resources/themes");

            FlatInterFont.install();

            FlatLightLaf.setPreferredFontFamily( FlatInterFont.FAMILY );
            FlatLightLaf.setPreferredLightFontFamily( FlatInterFont.FAMILY_LIGHT );
            FlatLightLaf.setPreferredSemiboldFontFamily( FlatInterFont.FAMILY_SEMIBOLD );

            FlatLightLaf.setup();

            Application application = new Application();
            application.authenticateUser();


            // for testing
//            Application application = new Application();
//            application.loadApplication();
//            application.showApplication();

        });
    }


}
