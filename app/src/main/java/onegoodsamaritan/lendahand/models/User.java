package onegoodsamaritan.lendahand.models;

import java.util.List;

public class User {
    public String fullName;
    public int birthYear;
    public String location;
    public List<Task> acquiredTasks;
    public List<Task> issuedTasks;
    public int karma;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fullName, int birthYear, String location, List<Task> acquiredTasks, List<Task> issuedTasks, int karma) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.location = location;
        this.acquiredTasks = acquiredTasks;
        this.issuedTasks = issuedTasks;
        this.karma = karma;
    }
}