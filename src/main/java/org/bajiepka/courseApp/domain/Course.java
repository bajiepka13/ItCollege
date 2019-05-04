package org.bajiepka.courseApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "course", catalog = "course_application")
public class Course {

    private static final int MAX_COURSES_PER_STUDENT = 3;
    private static final int MODULES_PER_COURSE = 10;
    private @Id
    @GeneratedValue
    Long id;
    private @Version
    int version;
    @NotNull
    private String name;
    @NotNull
    private int number;
    private float cost;
    private int modules;

    @ManyToMany
    @JoinTable(name = "course_listeners",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "listener_id")
    )
    private List<Student> participators = new ArrayList<Student>();

    public Course() {
    }

    public Course(String name, int number, float cost) {
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public void addParticipator(Student student) {
        participators.add(student);
    }

    public void addParticipator(Collection<Student> students) {
        participators.addAll(students);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void fill(Course course) {
        this.version++;
        this.name = course.getName();
        this.modules = course.getModules();
        this.number = course.getNumber();
    }
}
