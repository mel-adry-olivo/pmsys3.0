package org.pmsys.main.managers;

import org.pmsys.main.services.*;

import java.util.EnumMap;
import java.util.Map;

public enum ServiceManager {

    INSTANCE;

    private final Map<Services, Object> services = new EnumMap<>(Services.class);

    public void clearServices() {
        services.clear();
    }

    private void registerService(Services service, Object obj) {
        services.put(service, obj);
    }

    public static Object getService(Services service) {
        if (!INSTANCE.services.containsKey(service)) {
            throw new IllegalArgumentException("Service not registered: " + service.name());
        }
        return INSTANCE.services.get(service);
    }

    public void loadServices() {
        registerService(Services.USER, new UserService());
        registerService(Services.AUTHENTICATION, new AuthService());
        registerService(Services.PROJECT, new ProjectService());
        registerService(Services.TASK, new TaskService());
    }
}
