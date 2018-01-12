package com.hecaibao88.dirtygame;

import java.util.List;

/**
 * @author WangGuoWei
 * @time 2017/12/26 13:56
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
public class GameData {
    /**
     * status : 10000
     * data : [{"typeRMB":188,"typeId":1,"intentNum":10,"price":10,"imageUrl":"http://cdn.hcb66
     * .com/tu1.png"},{"typeRMB":388,"typeId":1,"intentNum":20,"price":10,"imageUrl":"http://cdn
     * .hcb66.com/tu2.png"},{"typeRMB":588,"typeId":1,"intentNum":30,"price":10,
     * "imageUrl":"http://cdn.hcb66.com/tu3.png"},{"typeRMB":888,"typeId":1,"intentNum":60,
     * "price":10,"imageUrl":"http://cdn.hcb66.com/tu4.png"},{"typeRMB":1188,"typeId":1,
     * "intentNum":20,"price":30,"imageUrl":"http://cdn.hcb66.com/tu5.png"}]
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

    public static class DataBean {
        /**
         * typeRMB : 188
         * typeId : 1
         * intentNum : 10
         * price : 10
         * imageUrl : http://cdn.hcb66.com/tu1.png
         */

        private int typeRMB;
        private int typeId;
        private int intentNum;
        private int price;
        private String imageUrl;

        public int getTypeRMB() {
            return typeRMB;
        }

        public void setTypeRMB(int typeRMB) {
            this.typeRMB = typeRMB;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getIntentNum() {
            return intentNum;
        }

        public void setIntentNum(int intentNum) {
            this.intentNum = intentNum;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

   /* public String imageUrl;

    int typeRMB = 1988;//金额
    int typeId = 1;//类型
    int intentNum = 60;//彩票数量
    int price = 5;//彩票面值



    public GameData(int typeRMB, int typeId, int intentNum, int price, String
            imageUrl) {
        this.imageUrl = imageUrl;
        this.typeRMB = typeRMB;
        this.typeId = typeId;
        this.intentNum = intentNum;
        this.price = price;
    }*/



}
