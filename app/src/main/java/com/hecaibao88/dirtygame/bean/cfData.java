package com.hecaibao88.dirtygame.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author WangGuoWei
 * @time 2017/12/25 15:19
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
public class cfData {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * 0 : 男伴双手抱住女伴的腰部，嘴里叼着一小块水果，两个人用30秒内吃完
         * 1 : 女伴用一只手抱住男伴的腰部，另外一只手用滑动的方式从胸部一直滑到胯部，时间不能少于30秒。
         * 2 : 两个人每人各自喝一瓶酒，左右各选四个人友情赞助同样喝一瓶酒
         */

        @SerializedName("0")
        private String _$0;
        @SerializedName("1")
        private String _$1;
        @SerializedName("2")
        private String _$2;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }
    }
}
