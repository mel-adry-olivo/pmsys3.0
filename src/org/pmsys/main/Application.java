package org.pmsys.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import org.pmsys.main.managers.*;
import org.pmsys.main.ui.auth.AuthWindow;
import org.pmsys.main.ui.MainWindow;
import org.pmsys.main.ui.utils.Benchmark;
import org.pmsys.main.ui.utils.WarmUp;


import javax.swing.SwingUtilities;

public class Application {

    private static Application instance;
    private MainWindow mainWindow;

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public void relaunch() {
        if (mainWindow != null) {
            mainWindow.dispose();
            mainWindow = null;
        }
        clearManagers();
        loadManagers();
        new AuthWindow(); // show login window again
    }

    private Application() {
        loadManagers();
        new AuthWindow(); // authenticate user
    }

    private void loadManagers() {
        ViewManager.INSTANCE.loadViews();
        FormManager.INSTANCE.loadForms();
        ServiceManager.INSTANCE.loadServices();
        ActionManager.INSTANCE.loadActions();
    }

    private void clearManagers() {
        ProjectManager.INSTANCE.clearProjects();
        ViewManager.INSTANCE.clearViews();
        FormManager.INSTANCE.clearForms();
        ServiceManager.INSTANCE.clearServices();
        ActionManager.INSTANCE.clearActions();
    }

    public void launchApplication() {
        mainWindow = new MainWindow();
        ViewManager.INSTANCE.setViewWindow(mainWindow);
        ProjectManager.INSTANCE.loadProjectList();
        WarmUp.DO_THE_WARMUP();
        mainWindow.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setupLookAndFeel();
            Application.getInstance();
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
