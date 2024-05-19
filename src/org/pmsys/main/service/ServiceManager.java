package org.pmsys.main.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private static ServiceManager instance;
    private final Map<String, Object> services = new HashMap<>();

    private static ServiceManager getInstance() {
        if (instance == null) {
            synchronized (ServiceManager.class) {
                if (instance == null) {
                    instance = new ServiceManager();
                }
            }
        }
        return instance;
    }

    public static void registerService(String name, Object service) {
        getInstance().services.put(name, service);
    }

    public static Object getService(String name) {
        if (!getInstance().services.containsKey(name)) {
            throw new IllegalArgumentException("Service not registered: " + name);
        }
        return getInstance().services.get(name);
    }
}
