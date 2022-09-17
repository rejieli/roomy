package com.example.roomy.mongodb.models;

import org.bson.types.ObjectId;

import java.util.Objects;
import java.time.LocalDate;

public class Task {
    private ObjectId _id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private UserProfile assignee;
    private Boolean completion;

    public Task(
            ObjectId id,
            String name,
            String description,
            LocalDate dueDate,
            UserProfile assignee,
            Boolean completion
    ) {
        setId(id);
        setName(name);
        setDescription(description);
        setDueDate(dueDate);
        setAssignee(assignee);
        setCompletion(completion);
    }
    public ObjectId getId() {
        return _id;
    }

    public Task setId(ObjectId id) {
        this._id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }
    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    public Task setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    public UserProfile getAssignee() {
        return assignee;
    }

    public Task setAssignee(UserProfile assignee) {
        this.assignee = assignee;
        return this;
    }
    public Boolean getCompletion() {
        return completion;
    }

    public Task setCompletion(Boolean completion) {
        this.completion = completion;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Task{");
        sb.append("id=").append(_id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", assignee=").append(assignee);
        sb.append(", completion=").append(completion);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return (
                Objects.equals(_id, task._id)
                        && Objects.equals(name, task.name)
                        && Objects.equals(description, task.description)
                        && Objects.equals(dueDate, task.dueDate)
                        && Objects.equals(assignee, task.assignee)
                        && Objects.equals(completion, task.completion)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, description, dueDate, assignee, completion);
    }
}

