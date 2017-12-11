package com.hecaibao88.dirtygame.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wangguowei
 * @ClassName: C
 * @Description: 常量类
 * @date 15/10/31 下午9:00
 */
public class C {

    //访问2.0服务器
//    public static final String TEST_URL = "http://192.168.1.106:3000";

    /**
     * http://golive-xess.dev.haokefuns.com/lotteryGoldsExchangeRecord/lottery/exchangeGoldsFromSystemToSP
     * http://api.lottery.haokefuns.com:80/login/qrRedirect?deviceNo=abc123456hoperu&r=1512555941821&qrId=3b7079b4a92a0cd6aa00a83065e65b00
     */

    public static final String TEST_URL = "http://api.hecaibao88.com";

    public static final String AGREE_URL = "http://api.youjuu.com/doc/YouJooxy.html";
    public static final String TEST_EXCHANGE_GOLDS_URL = "http://golive-xess.dev.haokefuns.com";

    public static final String API_EXCHANGE_GOLDS_URL = "http://api.lottery.haokefuns.com:80";


    public static final String TEST_APPID = "9BD28316AC664591BA34F7431E4CC486";
    public static final String TEST_APPKEY = "E4D7043C6087414FAF5EC938547D78FD";



    public static final String API_APPID = "6E0A4B9EEF60488AB3B80687F6789F7E";
    public static final String API_APPKEY = "B834CEE2B6124BA1B7C2DD8E5C963F01";





    //    public static final String TEST_URL = "http://101.201.198.19/myhome/index.php?s=/api/";

    //public static final String API_URL = "http://101.201.145.243/home/service/index
    // .php?s=/api/Gate/index/";
    public static final String API_URL = "https://api.hecaibao88.com";

    public static final String BDKEY = "uxyeDqFWHCz6800hGsXuTNf9";

    public static final String key = "Youju123";
    public static String USER_NAME = "";

    public static String SOFT_VERSION = "2.3.1";

    public static final String SYS_VERSION = "2";  //1:ios 2:android

    public static final String SEX_FEMALE = "2";
    public static final String SEX_MALE = "1";

    public static final String UPYUN_USERNAME = "youju";
    public static final String UPYUN_PASSWORD = "Youju123";

    public static String SOCKET_DEFAULT_IP = "123.56.89.54";
    public static int SOCKET_DEFAULT_PORT = 8081;

    /**
     * 用来标识请求照相功能的activity
     */
    public static final int CAMERA_WITH_DATA = 3023;
    /**
     * 用来标识请求gallery的activity
     */
    public static final int PHOTO_PICKED_WITH_DATA = 3021;
    /**
     * 用来标识裁剪的activity
     */
    public static final int PICKED_WITH_DATA = 3020;
    /**
     * 图片来源（1相册）
     */
    public static final int IMAGE_SOURCE_ALBUM = 1;
    /**
     * 图片来源（2拍摄）
     */
    public static final int IMAGE_SOURCE_SHOOT = 2;

    /**
     * socket超时时间
     */
    public static final int SOCKET_TIME_OUT = 60 * 1000; // 1分钟

    /**
     * 头像存放目录
     */
    public static final String HEAD_IMAGE_PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/jaaaja/head/";


    /**
     * 微信
     */
    //youju
    //    public static final String WEIXIN_APP_ID = "wx8f161a077f403377";
    //    public static final String WEIXIN_APP_SECRET = "3983395a33719ca0711d5283d500b919";
    //jaaaja
    public static final String WEIXIN_APP_ID = "wxdab055dec2a6f77f";
    public static final String WEIXIN_APP_SECRET = "090104eba27903cfe2029062949bad12";


    public static final int PAGE_ZUJU = 2, PAGE_HOME = 0, PAGE_MSG = 1, PAGE_FRIEND = 3, PAGE_MY
            = 4;

    public static final String[] sex_male = {"1", "2", "3", "4", "5", "6", "8", "10", "13", "15",
            "17", "19", "22", "24", "26", "28", "30", "31", "32", "33", "34", "36", "38", "40",
            "42", "45", "47", "49", "51"};

    public static final String[] sex_female = {"7", "9", "11", "12", "14", "16", "18", "20", "21",
            "23", "25", "27", "29", "35", "37", "39", "41", "43", "44", "46", "48", "50", "52"};


    public static final String[] myhome_man_guanxi = {"妻子", "儿子", "女儿", "孙子", "孙女", "外孙", "外孙女"};
    public static final String[] myhome_girl_guanxi = {"丈夫", "儿子", "女儿", "孙子", "孙女", "外孙", "外孙女"};

    //爸妈家
    //父亲 母亲 妻子（丈夫）兄弟 姐妹 儿子 女儿 侄儿 侄女 外甥儿 外甥女

    public static final String[] fatherhome_man_guanxi = {"父亲", "母亲", "妻子", "兄弟", "姐妹", "儿子",
            "女儿", "侄儿", "侄女", "外甥", "外甥女"};
    public static final String[] fatherhome_girl_guanxi = {"父亲", "母亲", "丈夫", "兄弟", "姐妹", "儿子",
            "女儿", "侄儿", "侄女", "外甥", "外甥女"};

    //岳父母家
    //岳父 岳母 妻子 妻兄弟 妻姐妹 儿子 女儿 妻侄儿 妻侄女 妻外甥儿 妻外甥女

    public static final String[] yue_fatherhome_man_guanxi = {"岳父", "岳母", "妻子", "妻兄弟", "妻姐妹",
            "儿子", "女儿", "妻侄儿", "妻侄女", "妻外甥", "妻外甥女"};
    public static final String[] yue_fatherhome_girl_guanxi = {"公公", "婆婆", "丈夫", "夫兄弟", "夫姐妹",
            "儿子", "女儿", "夫侄儿", "夫侄女", "夫外甥", "夫外甥女"};

    //祖父母家
    //祖父 祖母 父亲 母亲 叔伯父 姑母 妻子（丈夫） 兄弟 姐妹 堂兄弟 堂姐妹 姑表兄弟 姑表姐妹

    public static final String[] zhu_fatherhome_man_guanxi = {"祖父", "祖母", "父亲", "母亲", "叔伯父",
            "姑母", "妻子", "兄弟", "姐妹", "堂兄弟", "堂姐妹", "姑表兄弟", "姑表姐妹"};
    public static final String[] zhu_fatherhome_girl_guanxi = {"祖父", "祖母", "父亲", "母亲", "叔伯父",
            "姑母", "丈夫", "兄弟", "姐妹", "堂兄弟", "堂姐妹", "姑表兄弟", "姑表姐妹"};

    //夫祖父母家
    //夫祖父 夫祖母 公公 婆婆 夫叔伯父 夫姑母 丈夫 夫兄弟 夫姐妹 夫堂兄弟 夫堂姐妹 夫姑表兄弟 夫姑表姐妹
    public static final String[] fu_zhu_fatherhome_guanxi = {"夫祖父", "夫祖母", "公公", "婆婆", "夫叔伯父",
            "夫姑母", "丈夫", "夫兄弟", "夫姐妹", "夫堂兄弟", "夫堂姐妹", "夫姑表兄弟", "夫姑表姐妹"};

    //岳祖父母家
    //岳祖父 岳祖母 岳父 岳母 岳叔伯父 岳姑母 妻子 妻兄弟 妻姐妹 妻堂兄弟 妻堂姐妹 妻姑表兄弟 妻姑表姐妹
    public static final String[] yue_zhu_fatherhome_guanxi = {"岳祖父", "岳祖母", "岳父", "岳母", "岳叔伯父",
            "岳姑母", "妻子", "妻兄弟", "妻姐妹", "妻堂兄弟", "妻堂姐妹", "妻姑表兄弟", "妻姑表姐妹"};

    //外祖父母家
    //外祖父 外祖母 父亲 母亲 舅父 姨母 丈夫（妻子） 兄弟 姐妹 舅表兄弟 舅表姐妹 姨表兄弟 姨表姐妹
    public static final String[] wai_zhu_fatherhome_man_guanxi = {"外祖父", "外祖母", "父亲", "母亲", "舅父",
            "姨母", "妻子", "兄弟", "姐妹", "舅表兄弟", "舅表姐妹", "姨表兄弟", "姨表姐妹"};
    public static final String[] wai_zhu_fatherhome_girl_guanxi = {"外祖父", "外祖母", "父亲", "母亲",
            "舅父", "姨母", "丈夫", "兄弟", "姐妹", "舅表兄弟", "舅表姐妹", "姨表兄弟", "姨表姐妹"};


    //夫外祖父母家
    //夫外祖父 夫外祖母 公公 婆婆 夫舅父 夫姨母 丈夫 夫兄弟 夫姐妹 夫舅表兄弟 夫舅表姐妹 夫姨表兄弟 夫姨表姐妹
    public static final String[] fu_wai_zhu_fatherhome_girl_guanxi = {"夫外祖父", "夫外祖母", "公公", "婆婆",
            "夫舅父", "夫姨母", "丈夫", "夫兄弟", "夫姐妹", "夫舅表兄弟", "夫舅表姐妹", "夫姨表兄弟", "夫姨表姐妹"};

    //岳外祖父母家
    //岳外祖父 岳外祖母 岳父 岳母 妻舅父 妻姨母 妻子 妻兄弟 妻姐妹 妻舅表兄弟 妻舅表姐妹 妻姨表兄弟 妻姨表姐妹
    public static final String[] yue_wai_zhu_fatherhome_girl_guanxi = {"岳外祖父", "岳外祖母", "岳父",
            "岳母", "妻舅父", "妻姨母", "妻子", "妻兄弟", "妻姐妹", "妻舅表兄弟", "妻舅表姐妹", "妻姨表兄弟", "妻姨表姐妹"};

    public static final String teshu_guanxi = "母亲" + "岳母" + "婆婆" + "祖母" + "夫祖母" + "岳祖母" + "外祖母" +
            "夫外祖母" + "岳外祖母";

    public static final List<Long> group_id = new ArrayList<Long>();

    /*
    当前数据是否改变
     */
    public static boolean isMyDataChanged = false;
    public static boolean isFamilyDataChanged = false;
    public static boolean isShareDataChanged = false;

    public static final int STORY = 0X100;
    public static final int FRIEND = 0X200;
    public static final int SHARE = 0X300;

    public static int FULL_VIDEO_TYPE = FRIEND;

    /**
     * AddInfoActivity 点击完成、删除按钮发送广播action
     */
    public static final String ACTION_ADDINFOACTIVITY = "AddInfoActivity";

    /**
     * NewMemberAdapter 点击同意添加按钮发送广播action
     */
    public static final String ACTION_NEWMEMBERADAPTER = "cn.com.jaaaja.jia.adapter" +
            ".NewMemberAdapter";

    /**
     * NewMemberAdapter 点击同意添加按钮发送广播action
     */
    public static final String ACTION_NEWMEMBER_ADD_ME = "cn.com.jaaaja.jia.newmemberaddme" +
            ".NewMemberAdapter";

    public static final String ACTION_NEWMEMBER_WHO_ADD_ME = "cn.com.jaaaja.whoaddme";

    public static final String ACTION_SELECT_FRIEND = "cn.com.jaaaja.select_friend";

    public static final String ACTION_SEND_FRIEND_COMPLETE = "cn.com.jaaaja.select_complete";

    /**
     * 1 男性
     */
    public static final String gender_male = "1";

    /**
     * 2 女性
     */
    public static final String gender_female = "2";

    /**
     * 点击添加按钮
     */
    public static final String ACTION_CLICK_ADD = "cn.com.jaaaja.jia.clickAdd";


    /**
     * 家谱表收到添加界面发送的广播之后请求数据，Onfinish之后发送的广播
     */
    public static final String ACTION_JIAPUBIAO_ONFINISH = "cn.com.jaaaja.jia.act" +
            ".JiaPuBiaoActivity";

    /**
     * 管家 我的资料，点击保存按钮 发送广播
     */
    public static final String ACTION_SAVE_MYDATA = "cn.com.jaaaja.ui.activity" +
            ".MyDataActivity";

    /**
     * 快速添加界面
     */
    public static final String ACTION_FAST_ADD = "cn.com.jaaaja.jia.act" +
            ".FastAddActivity";


    /**
     * 关闭家谱表界面，发送广播，通知JiaFragment刷新数据
     */
    public static final String ACTION_FINISH = "cn.com.jaaaja.jia.act" +
            ".JiaPuBiaoActivity_Finish";

    /**
     * 家谱录界面,滑动删除
     */
    public static final String ACTION_SLIP_DELETE = "cn.com.jaaaja.jia.delete";


    /**
     * 家谱表解析完推荐亲人后，发送广播
     */
    public static final String ACTION_JIAPUBIAO_ONFINISH_RECOMMEND = "cn.com.jaaaja.jia.act" +
            ".JiaPuBiaoActivity.Recommend";


    /**
     * 添加完亲人之后，重新获取数据，解析完之后，发送广播
     */
    public static final String ACTION_JIAFRAGMENT_PARSE_FAMILY = "cn.com.jaaaja.ui.activity.parse" +
            ".family";


    /**
     * DetailsActivity 点击添加到家谱录 onfinish 之后发送广播action
     */
    public static final String ACTION_DETAILSACTIVITY = "DetailsActivity";

    /**
     * 被删除后，接收到服务器通知，发出的广播
     */
    public static final String ACTION_DELETED = "cn.com.jaaaja.deleted";


    /**
     * JiaFragment 第一次 MergeList 之后 发送广播
     */
    public static final String ACTION_JIAFRAGMENT_MERGE_FIRST = "cn.com.jaaaja.ui.activity.merge" +
            ".first";


    /**
     * 被删除后，接收到服务器通知，发出的广播
     */
    public static final String ACTION_BROADCAST = "cn.com.jaaaja.msg.Broadcast";
    public static final String ACTION_BROADCAST_SYS_MSG = "cn.com.jaaaja.msg.Broadcast.sys.msg";
    public static final String ACTION_BROADCAST_VIDEO = "cn.com.jaaaja.msg.Broadcast.video";


    /**
     * 被删除后，接收到服务器通知，发出的广播
     */
    public static final String ACTION_FRIENDSCRICLE = "cn.com.jaaaja.friendscricle";

    /**
     * 有新评论的通知，发出广播
     */
    public static final String ACTION_FRIENDSCIRCLE_NEW_COMMENT = "friendscircle.new.comment";

    /**
     * 删除的时候，发送广播
     */
    public static final String ACTION_DELETE_MEM = "cn.com.jaaaja.jia.delete_mem";

    /**
     * 当故事界面再次可见的情况
     */
    public static final String ACTION_MY_STORY = "cn.com.jaaaja.jia.my_story";

    /**
     * 通知我的故事界面刷新
     */
    public static final String ACTION_UPDATE_MYSTORY = "cn.com.jaaaja.jia.my_story_update";

    /**
     * JiaFragment 里面 读取了 listview 部分 数据库 数据
     */
    public static final String ACTION_READ_LISTVIEWDATA = "cn.com.jaaaja.jia.jiaFragment" +
            ".readListviewData";

    /**
     *
     */
    public static final String ACTION_WHEELVIEW = "cn.com.jaaaja.jia.wheelview";

    /**
     * 家谱图 界面 点击 button 之后 刷新数据
     */
    public static final String ACTION_RADIO_BUTTON = "cn.com.jaaaja.jia.radiobutton";


    public static boolean isPlayerFinish = true;
    public static int CUR_PAGE;
    public static boolean isTuching = false;

    public static String jpushRegistratoinId = "";


    public static final int DEFULT_SHARE = 0x100;
    public static final int QUICK_SHARE = 0x200;


    public static final Float ZOOM_SCALE = 2f;

    public static int JIA_REN_RESUME = 0;

    public static int ADD_NEW_HOME = 0;
    /**
     * 我家是否存在
     */
    public static boolean EXIST_MYHOME = false;

    /**
     * 判断时候接收到消息广播
     */
    public static boolean IS_RECEIVE_BROAST = false;


    /**
     * 读取通讯录 每 ** 个 发送一次请求
     */
    public static final int MIN_NUMBER = 100;

    /**
     * 读取通讯录 每 ** 个 发送一次请求,子线程休眠时长
     */
    public static final int SLEEP_TIME = 1000;

    /**
     * 判断用户是否首次登录注册
     */
    public static boolean IS_FIRST_REGISTER = false;

    /**
     * 家谱中 已连接的 人数 (已把自己排除在外)
     */
    public static int CONTECTED_NUM = 1;

    /**
     * 家谱中 的总人数 (已把自己排除在外)
     */
    public static int TOTAL_NUM = 1;

    /**
     * 是否已经进入设置界面
     */
    public static boolean ALREADY_GO_SETTING = false;

    /**
     * 是否在推荐列表界面
     */
    public static boolean IS_RECOMMEND_ACT = false;

    /**
     * sortType  1：发布时间 2：发生时间。默认发布时间
     */
    public static int ORDER = 1;

    /**
     * 由于中兴手机 setActivityForResult 接收不到  用个标示
     */
    public static boolean EDIT_STORY_SUCCESS = false;


    /**
     * 部分手机无法判断 是否允许读取通讯录, true 表示 读取到通讯录了，false 代表没有读取到通讯录
     */
    public static boolean JUDGE_CUSOR = true;

    public static HashMap<Long, Bitmap> groupMap = new HashMap<>();
    public static HashMap<Long, String> groupMapUrl = new HashMap<>();

    public static String jaaaja_video_path;

    //CircleAdapter 中 是否显示
    public static boolean showHeader = false;
    public static boolean showCommentHeader = false;
    public static boolean bind_wx = false;
    public static boolean isAll = false;
    public static boolean memberGridviewAdapter_isDel = false;
    public static boolean bind_wx_secucess = false;
}
