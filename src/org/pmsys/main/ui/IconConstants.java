package org.pmsys.main.ui;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.util.HashMap;
import java.util.Map;

public class IconConstants {

    private static final int SMALL = 18;
    private static final int MEDIUM = 22;
    private static final int LARGE = 36;

    private static final Map<String, FlatSVGIcon> iconCache = new HashMap<>();

    private static FlatSVGIcon getIcon(String path, int size) {
        String cacheKey = path + "_" + size;
        return iconCache.computeIfAbsent(cacheKey, key -> new FlatSVGIcon(path, size, size));
    }

    public static final FlatSVGIcon USER_ICON = getIcon("org/pmsys/resources/icons/user.svg", 200); // to maintain resolution
    public static final FlatSVGIcon LOGO = getIcon("org/pmsys/resources/icons/logo.svg", LARGE);

    public static final FlatSVGIcon DASHBOARD_ICON_SMALL = getIcon("org/pmsys/resources/icons/dashboard.svg", SMALL);
    public static final FlatSVGIcon DASHBOARD_ICON_MEDIUM = getIcon("org/pmsys/resources/icons/dashboard.svg", MEDIUM);

    public static final FlatSVGIcon PROJECT_LIST_ICON_SMALL = getIcon("org/pmsys/resources/icons/folder.svg", SMALL);
    public static final FlatSVGIcon PROJECT_LIST_ICON_MEDIUM = getIcon("org/pmsys/resources/icons/folder.svg", MEDIUM);

    public static final FlatSVGIcon ADD_ICON_SMALL = getIcon("org/pmsys/resources/icons/add.svg", SMALL);

    public static final FlatSVGIcon EXPORT_ICON_SMALL = getIcon("org/pmsys/resources/icons/export.svg", SMALL);

    public static final FlatSVGIcon KEBAB_ICON_SMALL = getIcon("org/pmsys/resources/icons/kebab.svg", SMALL);

    public static final FlatSVGIcon SEARCH_ICON_SMALL = getIcon("org/pmsys/resources/icons/search.svg", SMALL);

    public static final FlatSVGIcon CLOSE_ICON_SMALL = getIcon("org/pmsys/resources/icons/close2.svg", SMALL);
}
