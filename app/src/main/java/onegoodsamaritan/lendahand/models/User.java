package onegoodsamaritan.lendahand.models;

import java.util.List;

public class User {
    private String fullName;
    private int birthYear;
    private String location;
    private List<Task> acquiredTasks;
    private List<Task> issuedTasks;
    private int karma;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Task> getAcquiredTasks() {
        return acquiredTasks;
    }

    public void setAcquiredTasks(List<Task> acquiredTasks) {
        this.acquiredTasks = acquiredTasks;
    }

    public List<Task> getIssuedTasks() {
        return issuedTasks;
    }

    public void setIssuedTasks(List<Task> issuedTasks) {
        this.issuedTasks = issuedTasks;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
}