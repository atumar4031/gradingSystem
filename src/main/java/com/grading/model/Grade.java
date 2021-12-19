package com.grading.model;

import java.util.Objects;

public class Grade {
    private long id;
    private long ca_score;
    private long exam_score;
    private long student_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCa_score() {
        return ca_score;
    }

    public void setCa_score(long ca_score) {
        this.ca_score = ca_score;
    }

    public long getExam_score() {
        return exam_score;
    }

    public void setExam_score(long exam_score) {
        this.exam_score = exam_score;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return id == grade.id && ca_score == grade.ca_score && exam_score == grade.exam_score && student_id == grade.student_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ca_score, exam_score, student_id);
    }

    @Override
    public String toString() {
        return + id +", "+ ca_score +", "+ exam_score +", "+ student_id;
    }
}
