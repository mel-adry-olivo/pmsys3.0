package org.pmsys.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;
import org.pmsys.main.managers.*;
import org.pmsys.main.ui.auth.AuthWindow;
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


    private Application() {
        ViewManager.INSTANCE.loadViews();
        FormManager.INSTANCE.loadForms();
        ServiceManager.INSTANCE.loadServices();
        ActionManager.INSTANCE.loadActions();
        new AuthWindow(); // authenticate user
    }

    public void launchApplication() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        ProjectManager.INSTANCE.reloadProjectList();
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
