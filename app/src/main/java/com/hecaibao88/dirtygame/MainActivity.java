package com.hecaibao88.dirtygame;

import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golive.aidl.UserHelper;
import com.golive.aidl.UserInfoInterface;
import com.google.gson.Gson;
import com.hecaibao88.dirtygame.audio.AndroidAudio;
import com.hecaibao88.dirtygame.audio.Music;
import com.hecaibao88.dirtygame.audio.Sound;
import com.hecaibao88.dirtygame.bean.DataBean;
import com.hecaibao88.dirtygame.bean.QuestionsData;
import com.hecaibao88.dirtygame.bean.QuestionsGroup;
import com.hecaibao88.dirtygame.bean.QuestionsType;
import com.hecaibao88.dirtygame.bean.UserInfo;
import com.hecaibao88.dirtygame.http.QHttpClient;
import com.hecaibao88.dirtygame.http.QResponse;
import com.hecaibao88.dirtygame.http.QResult;
import com.hecaibao88.dirtygame.task.NetTask;
import com.hecaibao88.dirtygame.utils.C;
import com.hecaibao88.dirtygame.utils.DeviceUtil;
import com.hecaibao88.dirtygame.utils.L;
import com.hecaibao88.dirtygame.utils.SharedPreferencesUtils;
import com.hecaibao88.dirtygame.utils.Utils;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;
import net.lemonsoft.lemonbubble.interfaces.LemonBubbleLifeCycleDelegate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 主页面
public class MainActivity extends FragmentActivity implements QHttpClient.RequestHandler,
        UserInfoInterface {


    @Bind(R.id.detail_container)
    FrameLayout mDetailContainer;
    @Bind(R.id.tx_baiwan)
    TextView mTxBaiwan;
    @Bind(R.id.tx_qianwan)
    TextView mTxQianwan;
    @Bind(R.id.tx_yiwan)
    TextView mTxYiwan;
    @Bind(R.id.rl_home)
    LinearLayout mRlHome;
    @Bind(R.id.bt_star)
    Button mBtStart;
    @Bind(R.id.ll_game_des)
    RelativeLayout mLlGameDes;
    @Bind(R.id.bt_punishment)
    Button mBtPunishment;
    @Bind(R.id.bt_rewards)
    Button mBtRewards;
    @Bind(R.id.ll_game_questions)
    RelativeLayout mLlGameQuestions;
    @Bind(R.id.iv_home)
    ImageView mHome;
    @Bind(R.id.tx_punishment_rewards_title)
    TextView mTxPunishmentRewardsTitle;
    @Bind(R.id.ll_punishment_rewards)
    RelativeLayout mLlPunishmentRewards;
    @Bind(R.id.bt_more)
    Button mBtMore;
    @Bind(R.id.bt_agin)
    Button mBtAgin;
    @Bind(R.id.ll_game_end)
    RelativeLayout mLlGameEnd;
    @Bind(R.id.bt_begin)
    Button mBtBegin;
    @Bind(R.id.rl_welcome)
    RelativeLayout mRlWelcome;
    @Bind(R.id.bt_gogogo)
    Button mBtGogogo;
    @Bind(R.id.ll_game_help)
    RelativeLayout mLlGameHelp;
    @Bind(R.id.bt_star1)
    Button mBtStar1;
    @Bind(R.id.ll_game_des1)
    RelativeLayout mLlGameDes1;
    @Bind(R.id.item_questions_num)
    TextView mItemQuestionsNum;
    @Bind(R.id.item_questions)
    TextView mItemQuestions;
    @Bind(R.id.bt_look_answer)
    Button mBtLookAnswer;
    @Bind(R.id.item_questions_answer)
    TextView mItemQuestionsAnswer;
    @Bind(R.id.ll_game_questions_answer)
    RelativeLayout mLlGameQuestionsAnswer;
    @Bind(R.id.tx_punishment1)
    TextView mTxPunishment1;
    @Bind(R.id.tx_punishment2)
    TextView mTxPunishment2;
    @Bind(R.id.tx_punishment3)
    TextView mTxPunishment3;
    @Bind(R.id.ll_punishment)
    RelativeLayout mLlPunishment;
    @Bind(R.id.ll_rewards)
    LinearLayout mLlRewards;
    @Bind(R.id.bt_next)
    Button mBtNext;
    @Bind(R.id.iv_yiwan)
    ImageView mIvYiwan;
    @Bind(R.id.iv_baiyi)
    ImageView mIvBaiyi;
    @Bind(R.id.iv_qianyi)
    ImageView mIvQianyi;
    private QuestionsData questionsData;
    private int groupId, questionsId, typeId;
    private boolean isAnswer = false;
    private AndroidAudio androidAudio;
    private Music mMusic;
    private Sound mSound1, mSound2, mSound3;
    private List<DataBean> questionsList;
    private UserHelper userHelper;
    private UserInfo userInfo;
    private int intentNum;
    private String glodsCount;
    private int typeRMB;
    private boolean goPay = false;
    private boolean isPaying = false;
    private int pay_glods;
    private Random random;
    private String out_trade_no;
    private boolean isDuiHuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userHelper = UserHelper.initialize(this, this);
        userHelper.getUserInfo(this);


        androidAudio = new AndroidAudio(this);
        mMusic = androidAudio.newMusic("m1.mp3");
        mMusic.setLooping(true);
        mMusic.play();

//        mSound2 = androidAudio.newSound("s2.mp3");
//        mSound3 = androidAudio.newSound("s3.mp3");

        random = new Random();
        /*ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment).commit();*/


        LemonBubbleView.defaultBubbleView().setLifeCycleDelegate(new LemonBubbleLifeCycleDelegate
                .Adapter() {
            @Override
            public void willShow(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {
                super.willShow(bubbleView, bubbleInfo);
                System.out.println("BUBBLE WILL SHOW~");
            }

            @Override
            public void alreadyShow(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {
                super.alreadyShow(bubbleView, bubbleInfo);
                System.out.println("BUBBLE ALREADY SHOW!");
            }

            @Override
            public void willHide(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {
                super.willHide(bubbleView, bubbleInfo);
                System.out.println("BUBBLE WILL HIDE~");
            }

            @Override
            public void alreadyHide(LemonBubbleView bubbleView, LemonBubbleInfo bubbleInfo) {
                super.alreadyHide(bubbleView, bubbleInfo);
                System.out.println("BUBBLE ALREADY HIDE!");
                if(isDuiHuan){
                    SharedPreferencesUtils.init(MainActivity.this).putBoolean("isPay",true);
                    SharedPreferencesUtils.init(MainActivity.this).putBoolean("isAnswer",false);
                    SharedPreferencesUtils.init(MainActivity.this).putInt("typeId",typeId);
                    SharedPreferencesUtils.init(MainActivity.this).putInt("groupId",groupId);
                    changeGame();//收到彩票页面
                }
            }
        });


        if(SharedPreferencesUtils.init(this).getInt("typeId")!=0){
            typeId = SharedPreferencesUtils.init(this).getInt("typeId");
        }
        if(SharedPreferencesUtils.init(this).getInt("groupId")!=0){
            groupId = SharedPreferencesUtils.init(this).getInt("groupId");
            questionsId = SharedPreferencesUtils.init(this).getInt("questionsId");
        }

        if(typeId==0 && groupId==0){
            changeHome();
        }

        loadOneData();
    }


    void playSound1(){
        mSound1 = androidAudio.newSound("s1.mp3");
        androidAudio.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                mSound1.play(15.0f);
            }
        });
    }
    void playSound2(){
        mSound2 = androidAudio.newSound("s2.mp3");
        androidAudio.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                mSound2.play(15.0f);
            }
        });
    }
    void playSound3(){
        mSound3 = androidAudio.newSound("s3.mp3");
        androidAudio.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                mSound3.play(15.0f);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMusic != null)
            mMusic.dispose();
        userHelper.destroy(MainActivity.this);
        L.debug("nettask","onDestroy===============");
    }

    @OnClick({R.id.iv_yiwan, R.id.iv_baiyi, R.id.iv_qianyi, R.id.bt_punishment, R.id
            .bt_rewards, R.id.bt_star, R.id.iv_home, R.id.bt_more, R.id.bt_agin, R.id.bt_begin, R
            .id.bt_gogogo, R.id.bt_star1, R.id.bt_look_answer, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_baiyi:
                if(!isPaying){
                    typeRMB = 888;
                    typeId = 2;
                    groupId = random.nextInt(2)+1;
                    intentNum = 15;
                    L.debug("nettask",groupId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
                //                changeGame();
                break;
            case R.id.iv_qianyi:
                if(!isPaying) {
                    typeRMB = 1088;
                    typeId = 3;
                    groupId = random.nextInt(2)+1;
                    intentNum = 20;
                    L.debug("nettask",groupId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
                //                changeGame();
                break;
            case R.id.iv_yiwan:
                if(!isPaying) {
                    typeRMB = 688;
                    typeId = 1;
                    groupId = random.nextInt(2)+1;
                    intentNum = 10;
                    L.debug("nettask",groupId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
                //                changeGame();
                break;
            case R.id.bt_punishment:
                playSound1();
                questionsId++;
                changePunishmentRewards(0);
                mTxPunishmentRewardsTitle.setText("你懂的~");
                break;
            case R.id.bt_rewards:
                playSound3();
                questionsId++;
                changePunishmentRewards(1);
                mTxPunishmentRewardsTitle.setText("运气真棒！");
                break;
            case R.id.bt_star://点击已收到
                playSound2();
                mLlGameDes1.setVisibility(View.VISIBLE);//等不及了页面
                break;
            case R.id.iv_home:
                playSound2();
                mLlGameHelp.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_more:
                playSound2();
                changeHome();
                break;
            case R.id.bt_agin:
                playSound2();
                changeQuestions();
                break;
            case R.id.bt_begin://欢迎页点击
                playSound2();
                mRlWelcome.setVisibility(View.GONE);//欢迎页
                mLlGameHelp.setVisibility(View.VISIBLE);//二维码页面
                break;
            case R.id.bt_gogogo://二维码页面点击
                playSound2();
                mLlGameHelp.setVisibility(View.GONE);//二维码页面消失进入选择页面
                isAnswer = true;
                break;
            case R.id.bt_star1:
                playSound2();
                mLlGameDes1.setVisibility(View.GONE);//等不及页面消失
                changeQuestions();//答题页面
                break;
            case R.id.bt_look_answer:
                playSound2();
                changeLookAnswer();//公布答案页面
                break;
            case R.id.bt_next:
                playSound2();
                changeQuestions();//回到答题页面
                break;
        }
    }

    private void showPay() {
        if (isAnswer) {

            isPaying = true;
            intent_query();
//            intent_order();

            /*LemonBubble.showRoundProgress(MainActivity.this, "请求中...");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LemonBubble.showRight(MainActivity.this, "支付成功", 2000);
                }
            }, 2000);*/
        }
    }


    public void changeGame() {
        mRlHome.setVisibility(View.GONE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.VISIBLE);
        mLlGameQuestions.setVisibility(View.GONE);
        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameQuestionsAnswer.setVisibility(View.GONE);

    }

    public void changeMDetailContainer() {
        mRlHome.setVisibility(View.GONE);
        mDetailContainer.setVisibility(View.VISIBLE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.GONE);
        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameEnd.setVisibility(View.GONE);
    }

    public void changeHome() {
        questionsId = 0;
        mRlHome.setVisibility(View.VISIBLE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.GONE);
        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameEnd.setVisibility(View.GONE);
        mLlGameQuestionsAnswer.setVisibility(View.GONE);
    }

    public void changeQuestions() {
        mRlHome.setVisibility(View.GONE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.VISIBLE);
        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameEnd.setVisibility(View.GONE);
        mLlGameQuestionsAnswer.setVisibility(View.GONE);


        questionsList = mQuestionsDataTypeMap.get(typeId).getgroupData().get(groupId).getData();

        if (questionsList != null && questionsId <= questionsList.size() - 1) {
            mItemQuestions.setText(questionsList.get(questionsId).getContent());
            mItemQuestionsNum.setText("第 " + (questionsId + 1) + " 题");
            mItemQuestionsAnswer.setText(questionsList.get(questionsId).getAnswer());
            SharedPreferencesUtils.init(this).putInt("typeId",typeId);
            SharedPreferencesUtils.init(this).putInt("groupId",groupId);
            SharedPreferencesUtils.init(this).putInt("questionsId",questionsId);
            SharedPreferencesUtils.init(MainActivity.this).putBoolean("isAnswer",true);
        } else if (questionsId >= questionsList.size()) {
            SharedPreferencesUtils.init(this).putInt("typeId",0);
            SharedPreferencesUtils.init(this).putInt("groupId",0);
            SharedPreferencesUtils.init(this).putInt("questionsId",0);
            SharedPreferencesUtils.init(MainActivity.this).putBoolean("isPay",false);
            SharedPreferencesUtils.init(MainActivity.this).putBoolean("isAnswer",false);
            questionsId = 0;
            mLlGameQuestions.setVisibility(View.GONE);
            mLlGameEnd.setVisibility(View.VISIBLE);
        }
    }

    public void changePunishmentRewards(int type) {
        mRlHome.setVisibility(View.GONE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.GONE);

        mLlGameQuestionsAnswer.setVisibility(View.GONE);
        mLlPunishmentRewards.setVisibility(View.VISIBLE);
        mLlPunishment.setVisibility(View.GONE);
        mLlRewards.setVisibility(View.GONE);
        if (type == 0) {
            mLlPunishment.setVisibility(View.VISIBLE);//惩罚
        } else {
            mLlRewards.setVisibility(View.VISIBLE);//奖励
        }

        mLlGameEnd.setVisibility(View.GONE);
    }


    public void changeLookAnswer() {
        mRlHome.setVisibility(View.GONE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.GONE);


        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameQuestionsAnswer.setVisibility(View.VISIBLE);

        mLlGameEnd.setVisibility(View.GONE);
    }


    /**
     * 获取题目
     */
    public void loadOneData() {
        Map<String, String> map = new HashMap<String, String>();
        NetTask.executeRequestByGet(NetTask.TELGAME_LIST, null, this);
    }

    /**
     * 查询库存
     */
    public void intent_query() {

        L.debug("nettask","查询库存");

        Map<String, String> map = new HashMap<String, String>();

        map.put("deviceNo", DeviceUtil.getDeviceId2Ipad(this));
        map.put("price", "30");

        NetTask.executeRequestByGet(NetTask.INTENT_QUERY, map, this);
    }

    /**   支付
     * {
     ""uid" : "7",
     "deviceNo" : "28BE03829C59451",
     "gold" : 1088000 ,
     "price": 30,
     "num":20
     }
     */
    public void intent_order() {

        L.debug("nettask","兑换成功  去支付");

        Map<String, String> map = new HashMap<String, String>();

        map.put("uid", userInfo.getId());
        map.put("deviceNo", DeviceUtil.getDeviceId2Ipad(this));
        map.put("gold", (typeRMB*1000)+"");
        map.put("price", "30");
        map.put("num", intentNum+"");
        map.put("out_trade_no", out_trade_no);

        NetTask.executeRequestByPost(NetTask.INTENT_ORDER, map, this);
    }


    /**
     * spServiceNo
     * APPID String 必填
     * <p>
     * userNo 用户id String 必填
     * <p>
     * golds 金币 String 必填
     * <p>
     * timeStamp 时间戳(毫秒) String 必填
     * <p>
     * nonceStr 随机数 String 必填
     * <p>
     * signType 加密类型 String 必填 默认MD5
     * <p>
     * sign 签名 String 必填
     * <p>
     * out_trade_no 兑换业务号 String 必填
     * <p>
     * deviceNo 设备号 String 必填
     * <p>
     * remark 备注 String 选填
     * <p>
     * dirtyGame  测试
     * appid:9BD28316AC664591BA34F7431E4CC486
     * appkey:E4D7043C6087414FAF5EC938547D78FD
     *
     * 正式环境
     dirtyGame
     appid:6E0A4B9EEF60488AB3B80687F6789F7E
     appkey:B834CEE2B6124BA1B7C2DD8E5C963F01
     */

    public void postPay() {

        L.debug("nettask","兑换金币");

        String appid = "";
        String appkey = "";
        if(L.debug){
             appid = C.TEST_APPID;
             appkey = C.TEST_APPKEY;
        }else{
            appid = C.API_APPID;
            appkey = C.API_APPKEY;
        }

        /*appid = C.API_APPID;
        appkey = C.API_APPKEY;*/


        Map<String, String> map = new HashMap<String, String>();

        map.put("spServiceNo", appid);
        map.put("userNo", userInfo.getId());
        map.put("golds", (typeRMB*1000)+"");
        map.put("timeStamp", System.currentTimeMillis() + "");
        UUID uuid = UUID.randomUUID();
        map.put("nonceStr", uuid.toString().replace("-", ""));
        map.put("signType", "MD5");


        out_trade_no = uuid.toString().replace("-", "");
        map.put("out_trade_no", out_trade_no);
        map.put("deviceNo", DeviceUtil.getDeviceId2Ipad(this));


        String sign = Utils.makeSign(map, appkey);

        map.put("sign", sign);


        //URL http://golive-xess.dev.haokefuns.com/lotteryGoldsExchangeRecord/lottery/exchangeGoldsFromSystemToSP


        NetTask.executeRequestByPostGlods(NetTask.EXCHANGE_GOLDS, map, this);
    }

    @Override
    public void onFinish(QResult resut, String reqId) {
        if (reqId.equals(NetTask.TELGAME_LIST)) {

            questionsData = new Gson().fromJson(resut.getBody(), QuestionsData.class);
            preseData(questionsData.getData());
//            SharedPreferencesUtils.init(MainActivity.this).putBoolean("isPay",false);

            isAnswer = SharedPreferencesUtils.init(MainActivity.this).getBoolean("isAnswer");

            if(typeId!=0 && groupId!=0 && SharedPreferencesUtils.init(MainActivity.this).getBoolean("isPay")&&isAnswer){
                mRlWelcome.setVisibility(View.GONE);
                changeQuestions();
            }else if(SharedPreferencesUtils.init(MainActivity.this).getBoolean("isPay")&&!isAnswer){
                mRlWelcome.setVisibility(View.GONE);
                isAnswer = true;
                changeGame();//收到彩票页面
            }


            L.debug("preseData", mQuestionsDataTypeMap.toString());
        } else if (reqId.equals(NetTask.EXCHANGE_GOLDS)) {
            if (resut.getResult().equals("10310")){//兑换金币成功
                isDuiHuan = true;
                L.debug("nettask","兑换金币成功");
                intent_order();//去支付

            }else{
                isDuiHuan = false;
                isPaying = false;
                Utils.showToastCenter(MainActivity.this,"兑换金币失败");
                LemonBubble.showRight(MainActivity.this, "兑换金币失败", 2000);
            }
        }else if(reqId.equals(NetTask.INTENT_QUERY)){

            try {
                JSONObject object = new JSONObject(resut.getData());
                int total = object.getInt("total");

                if(total < intentNum){
                    L.debug("nettask","商家库存不足");
                    isPaying = false;
                    Utils.showToastCenter(MainActivity.this,"商家库存不足！！");
                }
                else{
                    L.debug("nettask","检查金币 不足去充值");
                    checkGlods();//检查金币 不足去充值
                }

                L.debug("nettask","  total:"+total+"  intentNum:"+intentNum);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if(reqId.equals(NetTask.INTENT_ORDER)){
            playSound2();
            L.debug("nettask","支付成功");
            isPaying = false;
            LemonBubble.showRight(MainActivity.this, "支付成功", 2000);
            goPay = false;
        }

    }

    private void checkGlods() {
        pay_glods = typeRMB*1000;
        if(pay_glods>(int)Float.parseFloat(glodsCount)){
            L.debug("nettask","金币不足-->充值页面");
            Utils.showToastCenter(MainActivity.this,"金币不足！！");
            userHelper.payGold(MainActivity.this);//充值页面
            goPay = true;
        }else{
            postPay();//兑换金币
            LemonBubble.showRoundProgress(MainActivity.this, "请求中...");
        }
        L.debug("nettask","  pay_glods："+pay_glods+"  glodsCount:"+glodsCount);
    }

    private HashMap<Integer, QuestionsType> mQuestionsDataTypeMap = new HashMap<>();


    private void preseData(List<DataBean> data) {

        QuestionsType mQuestionsType = null;
        QuestionsGroup mQuestionsGroup = null;

        for (DataBean dataBean : data) {

            if (mQuestionsDataTypeMap.get(dataBean.getType()) == null) {//存储题目类型的List数据为0
                mQuestionsType = new QuestionsType();//新建一个题目类型实体
                mQuestionsType.setType(dataBean.getType());
                mQuestionsDataTypeMap.put(dataBean.getType(), mQuestionsType);


                mQuestionsGroup = new QuestionsGroup();//新建题目组实体
                mQuestionsGroup.setGroupId(dataBean.getGroupId());
                mQuestionsType.getgroupData().put(dataBean.getGroupId(), mQuestionsGroup);
                //将题目组实体添加到题目组List

                mQuestionsGroup.getData().add(dataBean);


            } else {
                mQuestionsType = mQuestionsDataTypeMap.get(dataBean.getType());
                if (mQuestionsType.getgroupData().get(dataBean.getGroupId()) == null) {//不同组
                    mQuestionsGroup = new QuestionsGroup();//新建题目组实体
                    mQuestionsGroup.setGroupId(dataBean.getGroupId());
                    mQuestionsType.getgroupData().put(dataBean.getGroupId(), mQuestionsGroup);
                    mQuestionsGroup.getData().add(dataBean);
                } else {//同组不同题
                    mQuestionsGroup = mQuestionsType.getgroupData().get(dataBean.getGroupId());
                    mQuestionsGroup.getData().add(dataBean);
                }

            }

        }

    }

    @Override
    public void onFail(QResponse response, String reqId) {
        if (reqId.equals(NetTask.EXCHANGE_GOLDS)) {
            isDuiHuan = false;
            isPaying = false;
//            Utils.showToastCenter(MainActivity.this,"兑换金币失败");
            LemonBubble.showError(MainActivity.this, "兑换金币失败", 2000);
        }
    }

    @OnClick(R.id.bt_star1)
    public void onClick() {
    }

    @Override
    public void userInfo(String s) {
        userInfo = new Gson().fromJson(s, UserInfo.class);
        glodsCount = userInfo.getCoinCount();
        L.debug("nettask","充值金币返回  userInfo");
        L.debug("nettask", userInfo.getCoinCount());
        if(goPay){//充值金币返回
            L.debug("nettask","充值金币返回-->二次检查金币");
            checkGlods();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.debug("nettask","充值金币返回  onResume");
        if(goPay && userInfo!=null){
            isPaying = false;
            Utils.showToastCenter(MainActivity.this,"取消支付");
            glodsCount = userInfo.getCoinCount();
            L.debug("nettask", userInfo.getCoinCount());
        }
    }
}
