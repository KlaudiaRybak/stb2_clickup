package pl.akademiaqa.dto.task.requests;

import lombok.Data;

@Data
public class UpdateTaskRequestDto {

    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String assignees;
    private boolean archived;
}
