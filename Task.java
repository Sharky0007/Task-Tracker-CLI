import java.util.Date;

public class Task {
    private int taskId;
    private String description;
    private String status;
    private Date createdOn;
    private Date updatedOn;
 
    public Task(int taskId, String description, String status, Date createdOn, Date updatedOn) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public String toJson(){
        return "{\n" +
                "  \"taskId\": " + taskId + ",\n" +
                "  \"description\": \"" + description + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"createdOn\": \"" + createdOn + "\",\n" +
                "  \"updatedOn\": \"" + updatedOn + "\"\n" +
                "}";
    }
}
