package com.example.partybuilding.partybuilding.disembark.bean;

public class SuccessBean {


    /**     MissBean
     * msg : 登录成功
     * org : 支部委员会
     * success : true
     * pid : 430111197811111111
     * sid : DDD41FD02F17B65C2D4391D9B6949BD7
     * username : 演示
     */

    private String msg;
    private String org;
    private boolean success;
    private String pid;
    private String sid;
    private String username;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
