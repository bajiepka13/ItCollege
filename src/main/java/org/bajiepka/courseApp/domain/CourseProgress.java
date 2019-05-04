package org.bajiepka.courseApp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "progress")
public class CourseProgress {

    @ElementCollection
    @CollectionTable(name = "marks", joinColumns = @JoinColumn(name = "progress_id"))
    @Column(name = "mark")
    private final List<Integer> marks = new ArrayList<Integer>();
    private @Id
    @GeneratedValue
    Long id;
    private @Version
    int version;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Course course;
    @Column(name = "average", columnDefinition = "Decimal(6,1)")
    private double average = 0.00;
    private int finalMark;

    public CourseProgress() {
    }

    public void addMark(Integer mark) {
        marks.add(mark);
        refreshAverage();
    }

    public void addMark(List<Integer> fewMarks) {
        marks.addAll(fewMarks);
        refreshAverage();
    }

    public void refreshAverage() {

        List onlyMarks = marks.stream().collect(Collectors.toList());
        IntSummaryStatistics stats = onlyMarks.stream().mapToInt((mark) -> (int) mark).summaryStatistics();
        average = stats.getAverage();

    }

    @Override
    public String toString() {
        return course.toString();
    }

}
