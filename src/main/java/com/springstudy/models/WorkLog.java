package com.springstudy.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springstudy.enums.WorkLogAction;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "worklog")
@Entity(name = "WorkLog")
public class WorkLog {
    @Id
    @SequenceGenerator(
            name = "worklog_sequence",
            sequenceName = "worklog_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "worklog_sequence"
    )
    @Column(
            name = "worklog_id",
            updatable = false
    )
    private Integer Id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Column(
            name = "worklog_time",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime workLogTime;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "worklog_action"
    )
    private WorkLogAction workLogAction;

    @Column(
            name = "details",
            columnDefinition = "TEXT"
    )
    private String details;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    public WorkLog() {

    }

    public WorkLog(LocalDateTime workLogTime, WorkLogAction workLogAction, String details, User user) {
        this.workLogTime = workLogTime;
        this.workLogAction = workLogAction;
        this.details = details;
        this.user = user;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public LocalDateTime getWorkLogTime() {
        return workLogTime;
    }

    public void setWorkLogTime(LocalDateTime workLogTime) {
        this.workLogTime = workLogTime;
    }

    public WorkLogAction getWorkLogAction() {
        return workLogAction;
    }

    public void setWorkLogAction(WorkLogAction workLogAction) {
        this.workLogAction = workLogAction;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WorkLog{" +
                "Id=" + Id +
                ", workLogTime=" + workLogTime +
                ", workLogAction=" + workLogAction +
                ", details='" + details + '\'' +
                ", user=" + user +
                '}';
    }
}
