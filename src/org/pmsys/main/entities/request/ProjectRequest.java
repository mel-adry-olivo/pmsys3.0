package org.pmsys.main.entities.request;

public record ProjectRequest(String id, String title, String description, String dueDate) implements Request {}
