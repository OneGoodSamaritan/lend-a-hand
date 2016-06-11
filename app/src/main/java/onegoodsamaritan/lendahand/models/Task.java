package onegoodsamaritan.lendahand.models;

public class Task {
    private String title;
    private String description;
    private String location;
    private int karma;
    private String requestor;
    private String date;

    // 0 : open, 1 : accepted, 2 : completed
    private int status;

    public Task() {
    }

    public Task(String title, String description, String location, int karma) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.karma = karma;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
}
