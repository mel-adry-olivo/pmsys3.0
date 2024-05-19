package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.base.FlatLoadingIcon;
import org.pmsys.main.ui.components.base.FlatPanel;

public class LoadingView extends FlatPanel {

    public LoadingView() {
        setConstraints("fill, center, center", "center");
        add(new FlatLoadingIcon());
    }
}
