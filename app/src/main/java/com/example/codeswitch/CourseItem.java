package com.example.codeswitch;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CourseItem {
    private int courseImageResource;
    private String courseTitleText;
    private String courseSkillsText;

    public CourseItem(int ImageResource, String TitleText, String SkillsText){
        courseImageResource = ImageResource;
        courseTitleText = TitleText;
        courseSkillsText = SkillsText;
    }

    public int getCourseImageResource(){
        return courseImageResource;
    }

    public String getCourseTitleText(){
        return this.courseTitleText;
    }

    public String getCourseSkillsText(){
        return this.courseSkillsText;
    }
}
