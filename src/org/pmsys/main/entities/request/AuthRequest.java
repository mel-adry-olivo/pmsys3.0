package org.pmsys.main.entities.request;

public record AuthRequest(String username, String password) implements Request { }
