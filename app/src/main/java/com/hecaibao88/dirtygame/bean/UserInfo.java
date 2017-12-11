package com.hecaibao88.dirtygame.bean;

/**
 * @author WangGuoWei
 * @time 2017/11/24 14:29
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
public class UserInfo {
    private String state;
    private String id;
    private String name;
    private String mobile;
    private String caidou;
    private String coinCount;
    private boolean isBusinesses;
    private String userHead;
    private String integral;
    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }

    public void setCaidou(String caidou) {
        this.caidou = caidou;
    }
    public String getCaidou() {
        return caidou;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }
    public String getCoinCount() {
        return coinCount;
    }

    public void setIsBusinesses(boolean isBusinesses) {
        this.isBusinesses = isBusinesses;
    }
    public boolean getIsBusinesses() {
        return isBusinesses;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }
    public String getUserHead() {
        return userHead;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
    public String getIntegral() {
        return integral;
    }
}
