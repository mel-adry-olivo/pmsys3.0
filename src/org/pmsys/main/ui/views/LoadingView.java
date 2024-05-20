package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.base.CLoadingIcon;
import org.pmsys.main.ui.components.base.CPanel;

public class LoadingView extends CPanel {

    public LoadingView() {
        setConstraints("fill, center, center", "center");
        add(new CLoadingIcon());
    }
}
