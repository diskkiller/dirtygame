package com.hecaibao88.dirtygame.bean;

import java.util.List;

/**
 * @author WangGuoWei
 * @time 2017/12/5 22:08
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
public class QuestionsData {


    /**
     * status : 10000
     * data : [{"_id":"5a28bbbcea3ab21540b5862d","type":1,"content":"问题","answer":"答案",
     * "problemgroup":"5a28bb71ea3ab21540b5862c","groupId":1,"__v":0,
     * "createdAt":"2017-12-07T03:55:40.400Z","status":true},
     * {"_id":"5a28c56e494a5e1128af550e","type":3,"content":"都是鸡巴惹的祸（猜一杂志名）","answer":"求是",
     * "problemgroup":"5a28bb71ea3ab21540b5862c","groupId":1,"__v":0,
     * "createdAt":"2017-12-07T04:37:02.459Z","status":true}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

}
