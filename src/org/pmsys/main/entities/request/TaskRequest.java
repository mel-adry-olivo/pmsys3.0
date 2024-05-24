package org.pmsys.main.entities.request;

public record TaskRequest(String id, String title, String description, String dueDate, String priority,
                          String status) implements Request {

}
