package com.example.codeswitch;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JobItem {
    private int jobImageResource;
    private String jobTitleText;
    private String jobSkillsRequiredText;

    public JobItem(int ImageResource, String TitleText, String SkillsRequiredText){
        jobImageResource = ImageResource;
        jobTitleText = TitleText;
        jobSkillsRequiredText = SkillsRequiredText;
    }

    public int getJobImageResource(){
        return jobImageResource;
    }

    public String getJobTitleText(){
        return this.jobTitleText;
    }

    public String getJobSkillsText(){
        return this.jobSkillsRequiredText;
    }
}
