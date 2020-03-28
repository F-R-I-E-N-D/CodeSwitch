package com.example.codeswitch;

import java.util.ArrayList;

public class JobItem {
    private int jobImageResource;
    private String jobReferenceNumberText;
    private ArrayList<String> jobRequiredSkillsList;
    private String jobTitleText;
    private String jobCompanyText;
    private String jobDatePostedText;

    public JobItem(int ImageResource, String ReferenceNumberText, ArrayList<String> RequiredSkillsList, String TitleText, String CompanyText, String JobDatePostedText){
        jobImageResource = ImageResource;
        jobRequiredSkillsList = RequiredSkillsList;
        jobReferenceNumberText = ReferenceNumberText;
        jobTitleText = TitleText;
        jobCompanyText = CompanyText;
        jobDatePostedText = JobDatePostedText;
    }

    public int getJobImageResource(){
        return jobImageResource;
    }

    public ArrayList<String> getJobRequiredSkillsList(){ return jobRequiredSkillsList; }

    public String getJobReferenceNumberText(){
        return jobReferenceNumberText;
    }

    public String getJobTitleText(){
        return this.jobTitleText;
    }

    public String getJobCompanyText(){
        return this.jobCompanyText;
    }

    public String getJobDatePostedText() { return this.jobDatePostedText; }
}
