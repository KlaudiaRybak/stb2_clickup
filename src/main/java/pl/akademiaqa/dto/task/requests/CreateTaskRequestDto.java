package pl.akademiaqa.dto.task.requests;

import lombok.Data;
import org.json.JSONObject;

@Data                                               // biblioteka lombok - gettery, settery, konstruktor, metoda toString
public class CreateTaskRequestDto {

    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String assignees;
    private boolean archived;


    // kod zbędny, jeżeli stosujemy bibliotekę lombok
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getPriority() {
//        return priority;
//    }
//
//    public void setPriority(String priority) {
//        this.priority = priority;
//    }
//
//    public String getParent() {
//        return parent;
//    }
//
//    public void setParent(String parent) {
//        this.parent = parent;
//    }
//
//    public String getAssignees() {
//        return assignees;
//    }
//
//    public void setAssignees(String assignees) {
//        this.assignees = assignees;
//    }
//
//    public boolean isArchived() {
//        return archived;
//    }
//
//    public void setArchived(boolean archived) {
//        this.archived = archived;
//    }
//
//    @Override
//    public String toString() {                                  // jeżeli będziemy chcieli zalogować coś na konsolę
//        return "CreateTaskRequestDto{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", status='" + status + '\'' +
//                ", priority='" + priority + '\'' +
//                ", parent='" + parent + '\'' +
//                ", assignees='" + assignees + '\'' +
//                ", archived=" + archived +
//                '}';
//    }
}
