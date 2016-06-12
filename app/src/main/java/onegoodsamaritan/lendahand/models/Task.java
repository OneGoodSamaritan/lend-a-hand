package onegoodsamaritan.lendahand.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Task {
    public String title;
    public String description;
    public String location;
    public int karma;
    public String requestor;
    public String date;
    public String key;
    public String samaritan;

    // 0 : open, 1 : accepted, 2 : completed
    public int status;

    public Task() {
    }

    public Task(String title, String description, String location, int karma, String requestor, String date, int status, String samaritan) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.karma = karma;
        this.requestor = requestor;
        this.date = date;
        this.status = status;
        this.samaritan = samaritan;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("description", description);
        result.put("location", location);
        result.put("karma", karma);
        result.put("requestor", requestor);
        result.put("date", date);
        result.put("status", status);
        result.put("samaritan", samaritan);

        return result;
    }
}
