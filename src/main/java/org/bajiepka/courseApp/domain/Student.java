package org.bajiepka.courseApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "students", catalog = "course_application")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    @ManyToMany(targetEntity = CourseProgress.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "students_progress",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "progress_id")
    )
    private final List<CourseProgress> progressList = new ArrayList<CourseProgress>();
    private @Id
    @GeneratedValue
    @Column(name = "student_id")
    Long id;
    private @Version
    int version;
    @NotNull
    private String name;
    private String address;
    private String phone;
    private Integer gradeBook;
    private float averageProgress;
    @Embedded
    private Advance advance;
    @ManyToMany(mappedBy = "participators")
    private List<Course> courses = new ArrayList<Course>();

    public Student() {

    }

    public Student(String name, String address, String phone, Integer gradeBook) {

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gradeBook = gradeBook;

    }

    @PostConstruct
    private void fillGradebookNumber() {
        gradeBook = gradeBook + Math.toIntExact(id);
    }

    @Override
    public String toString() {
        return getName();
    }

    public void addCourseProgress(CourseProgress progress) {
        progressList.add(progress);
    }

    public void fill(Student copy) {
        this.version++;
        this.name = copy.getName();
        this.phone = copy.getPhone();
        this.address = copy.getAddress();
        this.averageProgress = copy.getAverageProgress();
        this.gradeBook = copy.getGradeBook();
    }
}
