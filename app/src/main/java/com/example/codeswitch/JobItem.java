package com.example.codeswitch;

public class JobItem {
    private int jobImageResource;
    private String jobTitleText;
    private String jobCompanyText;
    private String jobDatePostedText;

    public JobItem(int ImageResource, String TitleText, String CompanyText, String JobDatePostedText){
        jobImageResource = ImageResource;
        jobTitleText = TitleText;
        jobCompanyText = CompanyText;
        jobDatePostedText = JobDatePostedText;
    }

    public int getJobImageResource(){
        return jobImageResource;
    }

    public String getJobTitleText(){
        return this.jobTitleText;
    }

    public String getJobCompanyText(){
        return this.jobCompanyText;
    }

    public String getJobDatePostedText() { return this.jobDatePostedText; }
}
