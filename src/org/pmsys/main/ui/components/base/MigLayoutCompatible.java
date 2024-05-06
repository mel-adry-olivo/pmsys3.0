package org.pmsys.main.ui.components.base;

public interface MigLayoutCompatible<T> {

    T setConstraints(String... params);
    T setLayoutConstraints(String layoutConstraints);
    T setRowConstraints(String rowConstraints);
    T setColumnConstraints(String columnConstraints);
}
