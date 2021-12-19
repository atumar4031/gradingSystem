package com.grading.dao;

import com.grading.model.Grade;

import java.util.ArrayList;

public interface igrade {
    ArrayList<Grade> findGrade();
    boolean updateGrade(long id);
    boolean deleteGrade(long id);
}
