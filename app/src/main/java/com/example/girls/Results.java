package com.example.girls;

/**
 * Created by 媚敏 on 2017/6/13.
 */

public class Results {

    public String url;

    public String _id;
    public String createAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;

    public String used;
    public String who;

    public Results(String desc, String url) {
        this.desc = desc;
        this.url = url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

 /* public String toString(){
        return "_id="+_id+",createdAt=" +createAt
                + ",desc="+desc+",publishedAt="+publishedAt
                +",source="+source+",type="+type
                +",url="+url+",used="+used
                +",who="+who;
    }*/

}







