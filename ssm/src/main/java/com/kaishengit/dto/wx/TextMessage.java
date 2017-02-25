package com.kaishengit.dto.wx;

public class TextMessage {


    /**
     * touser : UserID1|UserID2|UserID3
     * toparty :  PartyID1 | PartyID2
     * totag :  TagID1 | TagID2
     * msgtype : text
     * agentid : 1
     * text : {"content":"Holiday Request For Pony(http://xxxxx)"}
     * safe : 0
     */

    private String touser;
    private String toparty;
    private String msgtype = "text";
    private int agentid = 6;
    private TextBean text;
    private int safe = 0;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public int getAgentid() {
        return agentid;
    }

    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }

    public TextBean getText() {
        return text;
    }

    public void setText(TextBean text) {
        this.text = text;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public static class TextBean {
        /**
         * content : Holiday Request For Pony(http://xxxxx)
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
