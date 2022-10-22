package com.xxxx.cms.query;

import com.xxxx.cms.base.BaseQuery;

public class BugQuery extends BaseQuery {

    private Integer bugId;

    private String bugTitle;

    private String trueName;

    private Integer solve;

    public String getMan() {
        return trueName;
    }

    public void setMan(String man) {
        this.trueName = man;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getSolve() {
        return solve;
    }

    public void setSolve(Integer solve) {
        this.solve = solve;
    }
}
