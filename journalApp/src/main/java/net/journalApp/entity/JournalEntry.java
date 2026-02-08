package net.journalApp.entity;

// POJO class (Plane Old Java Entry)
public class JournalEntry {
    private String Id;
    private String title;
    private String content;

    public String getId() {
        return Id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
