package com.hecaibao88.dirtygame.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author WangGuoWei
 * @time 2017/12/7 15:17
 * @des ${TODO}
 * <p>
 * ┽
 * ┽                            _ooOoo_
 * ┽                           o8888888o
 * ┽                           88" . "88
 * ┽                           (| -_- |)
 * ┽                           O\  =  /O
 * ┽                        ____/`---'\____
 * ┽                      .'  \\|     |//  `.
 * ┽                     /  \\|||  :  |||//  \
 * ┽                    /  _||||| -:- |||||-  \
 * ┽                    |   | \\\  -  /// |   |
 * ┽                    | \_|  ''\---/''  |   |
 * ┽                    \  .-\__  `-`  ___/-. /
 * ┽                  ___`. .'  /--.--\  `. . __
 * ┽               ."" '<  `.___\_<|>_/___.'  >'"".
 * ┽              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * ┽              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ┽         ======`-.____`-.___\_____/___.-`____.-'======
 * ┽                            `=---='
 * ┽         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * ┽                      佛祖保佑       永无BUG
 * ┽
 * ┽
 * ┽
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
@Entity
public class DataBean {
    /**
     * _id : 5a28bbbcea3ab21540b5862d
     * type : 1
     * content : 问题
     * answer : 答案
     * problemgroup : 5a28bb71ea3ab21540b5862c
     * groupId : 1
     * __v : 0
     * createdAt : 2017-12-07T03:55:40.400Z
     * status : true
     */
    @Id(autoincrement = true)
    private Long mId;


    private int type;
    private String content;
    private String answer;
    private String problemgroup;
    private int groupId;
    private int __v;
    private String createdAt;
    private boolean status;

    @Generated(hash = 1490551254)
    public DataBean(Long mId, int type, String content, String answer,
            String problemgroup, int groupId, int __v, String createdAt,
            boolean status) {
        this.mId = mId;
        this.type = type;
        this.content = content;
        this.answer = answer;
        this.problemgroup = problemgroup;
        this.groupId = groupId;
        this.__v = __v;
        this.createdAt = createdAt;
        this.status = status;
    }

    @Generated(hash = 908697775)
    public DataBean() {
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProblemgroup() {
        return problemgroup;
    }

    public void setProblemgroup(String problemgroup) {
        this.problemgroup = problemgroup;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                ", problemgroup='" + problemgroup + '\'' +
                ", groupId=" + groupId +
                ", __v=" + __v +
                ", createdAt='" + createdAt + '\'' +
                ", status=" + status +
                '}';
    }

    public Long getMId() {
        return this.mId;
    }

    public void setMId(Long mId) {
        this.mId = mId;
    }

    public boolean getStatus() {
        return this.status;
    }
}
