package org.bajiepka.courseApp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Advance {

    @Column(name = "COURSE_ADVANCE", length = 20)
    private String course;

}
