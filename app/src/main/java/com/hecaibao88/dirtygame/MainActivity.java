package com.hecaibao88.dirtygame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hecaibao88.dirtygame.bean.DataBeanDao;
import com.hecaibao88.dirtygame.bean.GreenDaoManager;
import com.hecaibao88.dirtygame.bean.QuestionsData;
import com.hecaibao88.dirtygame.bean.QuestionsGroup;
import com.hecaibao88.dirtygame.bean.QuestionsType;
import com.hecaibao88.dirtygame.bean.UserInfo;
import com.hecaibao88.dirtygame.bean.cfData;
import com.hecaibao88.dirtygame.dialog.ImProgressDialog;
import com.hecaibao88.dirtygame.http.QHttpClient;
import com.hecaibao88.dirtygame.http.QResponse;
import com.hecaibao88.dirtygame.http.QResult;
import com.hecaibao88.dirtygame.task.NetTask;
import com.hecaibao88.dirtygame.utils.C;
import com.hecaibao88.dirtygame.utils.DeviceUtil;
import com.hecaibao88.dirtygame.utils.L;
import com.hecaibao88.dirtygame.utils.SharedPreferencesUtils;
import com.hecaibao88.dirtygame.utils.Utils;
import com.hecaibao88.dirtygame.utils.dodo.FileUtil;
import com.hecaibao88.dirtygame.utils.dodo.NetStatus;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.LemonBubbleView;
import net.lemonsoft.lemonbubble.interfaces.LemonBubbleLifeCycleDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hecaibao88.dirtygame.R.id.tx_punishment1;
import static com.hecaibao88.dirtygame.R.id.tx_punishment2;
import static com.hecaibao88.dirtygame.R.id.tx_punishment3;

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
    ImageView mTxPunishmentRewardsTitle;
    @Bind(R.id.im_shanhua)
    ImageView mIvShanHua;
    @Bind(R.id.ll_punishment_rewards)
    RelativeLayout mLlPunishmentRewards;
    @Bind(R.id.bt_more)
    Button mBtMore;
    @Bind(R.id.bt_agin)
    Button mBtAgin;
    @Bind(R.id.ll_game_end)
    RelativeLayout mLlGameEnd;
//    @Bind(R.id.bt_begin)
//    Button mBtBegin;
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
    @Bind(tx_punishment1)
    TextView mTxPunishment1;
    @Bind(tx_punishment2)
    TextView mTxPunishment2;
    @Bind(tx_punishment3)
    TextView mTxPunishment3;
    @Bind(R.id.rewards_intent_num)
    TextView mRewardsNum;
    @Bind(R.id.tv_setting)
    TextView mSetting;
    @Bind(R.id.ll_punishment)
    RelativeLayout mLlPunishment;
    @Bind(R.id.network_state)
    RelativeLayout mNetworkState;
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
    @Bind(R.id.iv_caideng)
    ImageView mIvCaiDeng;
    @Bind(R.id.iv_caideng1)
    ImageView mIvCaiDeng1;
    @Bind(R.id.iv_caideng2)
    ImageView mIvCaiDeng2;
    @Bind(R.id.iv_caideng3)
    ImageView mIvCaiDeng3;
    @Bind(R.id.iv_caideng4)
    ImageView mIvCaiDeng4;
    @Bind(R.id.iv_caideng5)
    ImageView mIvCaiDeng5;
    @Bind(R.id.iv_caideng6)
    ImageView mIvCaiDeng6;
    @Bind(R.id.iv_caideng7)
    ImageView mIvCaiDeng7;
    private QuestionsData questionsData;
    private int groupId, questionsId, typeId;
    private boolean isAnswer = false;
    private AndroidAudio androidAudio;
    private Music mMusic;
    private Sound mSound1, mSound2, mSound3;
    private List<DataBean> questionsList;
    private UserHelper userHelper;
    private UserInfo userInfo;
    private int intentNum,price;
    private String glodsCount;
    private int typeRMB;
    private boolean goPay = false;
    private boolean isPaying = false;
    private int pay_glods;
    private Random random;
    private String out_trade_no;
    private boolean isDuiHuan;
    private AnimationDrawable rocketAnimation,rocketAnimation1,rocketAnimation2
            ,rocketAnimation3,rocketAnimation4,rocketAnimation5,rocketAnimation6,rocketAnimation7;
    private int mPunishmentId;
    private cfData questionsCFData;
    private List<List<String>> cfList;
    private FileUtil fileUtil;
    private List gameDataList =  new ArrayList<GameData.DataBean>();
    private GameAdapter1 adapter;
    private RecyclerView recyclerView;
    private boolean isLoad = false;
    private ImProgressDialog loadingdialog;
    private DataBeanDao dataBeanDao;
    private boolean isOnCreat = true;
    private boolean mRlWelcome_show = false;

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

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);

        loadingdialog = new ImProgressDialog.Builder(this).create();
        userHelper = UserHelper.initialize(this, this);
        userHelper.getUserInfo(this);
        fileUtil = new FileUtil();

        dataBeanDao = GreenDaoManager.getInstance().getSession().getDataBeanDao();

//        Glide.with(this).load(R.mipmap.caideng).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvCaiDeng);
//        Glide.with(this).load(R.mipmap.caideng).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvCaiDeng1);

        startRocketAnima(mIvCaiDeng);



        androidAudio = new AndroidAudio(this);
        mMusic = androidAudio.newMusic("m1.mp3");
        mMusic.setLooping(true);
        mMusic.play();

        random = new Random();
        /*ListFragment fragment = new ListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment).commit();*/



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        /**
         * 创建一个linearlayoutmaneger对象，并将他设置到recyclerview当中。layoutmanager用于指定
         * recyclerview的布局方式，这里是线性布局的意思。可以实现和listview类似的效果。
         *
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //        recyclerView.addItemDecoration(new SpaceItemDecoration(50));
        adapter = new GameAdapter1(loadingdialog);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new GameAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GameData.DataBean gameData) {

                if(!NetStatus.getNetStatus(MainActivity.this)){
                    Utils.showToastCenter(MainActivity.this,"网络断开,请检查网络哦");
                    return;
                }

                if(!isPaying){


                    if(reNetIsAvailable == 1)
                        isAnswer = true;

                    /**
                     * 万亿-1988（刺激）-20张50元面值即开彩-每次刮2张；
                     千亿-1188（激情）-30张20元面值即开彩-每次刮3张；
                     百亿-666（调情）-60张5元面值即开彩-每次刮6张；*/

                    typeRMB = gameData.getTypeRMB();
                    typeId = gameData.getTypeId();
                    if(mQuestionsDataTypeMap.get(typeId)!=null)
                        groupId = random.nextInt(mQuestionsDataTypeMap.get(typeId).getgroupData().size()-1)+1;
                    else{
                        Utils.showToastCenter(MainActivity.this,"暂未开放");
                        return;
                    }
                    intentNum = gameData.getIntentNum();
                    price = gameData.getPrice();
                    L.debug("nettask","groupId    "+groupId+"");
                    L.debug("nettask","typeId    "+typeId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
            }
        });



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
                    if(userInfo!=null&&userInfo.getId()!=null){
                        SharedPreferencesUtils.init(MainActivity.this,userInfo.getId()).putBoolean("isPay",true)
                                .putBoolean("isAnswer",false)
                                .putInt("typeId",typeId)
                                .putInt("groupId",groupId)
                                .putInt("intentNum",intentNum);

                    }
                    changeGame();//收到彩票页面
                }
            }
        });

        loadingdialog.show();

    }

    private void initData() {
        if(userInfo!=null&&userInfo.getId()!=null){

            if(SharedPreferencesUtils.init(this,userInfo.getId()).getInt("typeId")!=0){
                typeId = SharedPreferencesUtils.init(this,userInfo.getId()).getInt("typeId");
            }
            if(SharedPreferencesUtils.init(this,userInfo.getId()).getInt("groupId")!=0){
                groupId = SharedPreferencesUtils.init(this,userInfo.getId()).getInt("groupId");
                questionsId = SharedPreferencesUtils.init(this,userInfo.getId()).getInt("questionsId");
            }
            if(SharedPreferencesUtils.init(this,userInfo.getId()).getInt("intentNum")!=0){
                intentNum = SharedPreferencesUtils.init(this,userInfo.getId()).getInt("intentNum");
            }
        }


        if(typeId==0 && groupId==0 && questionsId == 0){
            changeHome();
            if(reNetIsAvailable == 1 && mLlGameHelp.getVisibility() == View.GONE){
                if(gameDataList!=null)
            }
        }


        /*if(dataBeanDao.queryBuilder().list()!=null&&dataBeanDao.queryBuilder().list().size()>0)
            preseData2View(dataBeanDao.queryBuilder().list());
        else*/
        loadOneData();
        loadCFData();


        loadGameData();
    }

    private void startRocketAnima(View view) {

        rocketAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.wel_anim);
        //获得bg的drawable对象，强制转换为AnimationDrawable
        view.setBackgroundDrawable(rocketAnimation);
        //播放动画
        rocketAnimation.stop();
        rocketAnimation.start();
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
            .bt_rewards, R.id.bt_star, R.id.iv_home, R.id.bt_more,R.id.ll_game_end, R.id.bt_agin, R.id.rl_welcome, R
            .id.bt_gogogo, R.id.bt_star1, R.id.bt_look_answer, R.id.bt_next,R.id.ll_game_des,R.id.ll_game_des1,R.id.tv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.iv_baiyi:
                if(!isPaying){
                    typeRMB = 666;//送60张5元
                    typeId = 2;
                    groupId = random.nextInt(2)+1;
                    intentNum = 60;
                    price = 5;
                    L.debug("nettask",groupId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
                *//*typeId = 2;
                groupId = random.nextInt(2)+1;
                intentNum = 30;
                changeGame();*//*
                break;
            case R.id.iv_qianyi:
                if(!isPaying) {
                    typeRMB = 1188;//送30张20元
                    typeId = 3;
                    groupId = random.nextInt(2)+1;
                    intentNum = 30;
                    price = 20;
                    L.debug("nettask",groupId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
                *//*typeId = 3;
                groupId = random.nextInt(2)+1;
                intentNum = 20;
                changeGame();*//*
                break;
            case R.id.iv_yiwan:
                if(!isPaying) {
                    typeRMB = 1988;//送60张5元
                    typeId = 1;
                    intentNum = 20;
                    price = 50;
                    groupId = random.nextInt(2)+1;
                    L.debug("nettask",groupId+"");
                    showPay();
                }else{
                    Utils.showToastCenter(MainActivity.this,"正在支付中...请稍后...");
                }
                *//*typeId = 1;
                groupId = random.nextInt(2)+1;
                intentNum = 60;
                changeGame();*//*
                break;*/
            case R.id.bt_punishment:
                playSound1();
                questionsId++;
                changePunishmentRewards(0);
                mIvShanHua.setVisibility(View.GONE);
                mTxPunishmentRewardsTitle.setImageResource(R.mipmap.nidongde);
                break;
            case R.id.bt_rewards:
                playSound3();
                questionsId++;
                changePunishmentRewards(1);
                mIvShanHua.setVisibility(View.VISIBLE);
                mRewardsNum.setText("恭喜你！答对了，喝杯酒庆祝一下，请刮"+(intentNum/10)+"张即开彩");
                mTxPunishmentRewardsTitle.setImageResource(R.mipmap.zhenbang);
                break;
            case R.id.ll_game_des://R.id.bt_star://点击已收到
                startRocketAnima(mIvCaiDeng5);
                playSound2();
                mLlGameDes.setVisibility(View.GONE);
                mLlGameDes1.setVisibility(View.VISIBLE);//等不及了页面
                break;
            case R.id.iv_home:
                if(mLlGameHelp.getVisibility() == View.VISIBLE)
                    return;

                playSound2();
                startRocketAnima(mIvCaiDeng1);
                mLlGameHelp.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_game_end://R.id.bt_more:
//                rocketAnimation7.stop();
                isDuiHuan = false;
                playSound2();
                changeHome();
                if(gameDataList!=null)
                    adapter.setGameAdapterData(gameDataList);
                break;
            case R.id.bt_agin:
                playSound2();
                changeQuestions();
                break;
            case R.id.rl_welcome://欢迎页点击
//                rocketAnimation.stop();
//                rocketAnimation1.start();
                startRocketAnima(mIvCaiDeng1);
                playSound2();
                mRlWelcome.setVisibility(View.GONE);//欢迎页
                mLlGameHelp.setVisibility(View.VISIBLE);//二维码页面
                break;
            case R.id.bt_gogogo://二维码页面点击
//                rocketAnimation1.stop();
                playSound2();
                mLlGameHelp.setVisibility(View.GONE);//二维码页面消失进入选择页面
                isAnswer = true;
                if(!isLoad){
//                    loadingdialog.show();
                    adapter.setGameAdapterData(gameDataList);
                    isLoad = true;
                }
                break;
            case R.id.ll_game_des1://R.id.bt_star1:
//                rocketAnimation5.stop();
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
            case R.id.tv_setting:
                if (Build.VERSION.SDK_INT > 10) {
                    // 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
                    startActivity(new Intent(
                            Settings.ACTION_WIFI_SETTINGS));
                    overridePendingTransition(R.anim.left_in,
                            R.anim.left_out);
                } else {
                    startActivity(new Intent(
                            android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    overridePendingTransition(R.anim.left_in,
                            R.anim.left_out);
                }
                break;
        }
    }


    private ConnectivityManager connectivityManager;
    private NetworkInfo info;
    private int reNetIsAvailable = 0;
    /**
     * 监听网络变化广播 做出相应的提示
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) getApplication()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    initData();
                    mNetworkState.setVisibility(View.GONE);
                } else {
                    reNetIsAvailable = 1;
                    changeNetworkState();
                }
            }
        }
    };



    private void showPay() {
        if (isAnswer) {

            isPaying = true;
//            intent_query();
            LemonBubble.showRoundProgress(MainActivity.this, "正在支付...");
            //先去支付
            intent_order();

        }
    }


    public void changeGame() {
        startRocketAnima(mIvCaiDeng4);
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
        if(reNetIsAvailable == 0)
            questionsId = 0;
        mRlHome.setVisibility(View.VISIBLE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.GONE);
        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameEnd.setVisibility(View.GONE);
        mLlGameQuestionsAnswer.setVisibility(View.GONE);
    }
    public void changeNetworkState() {
        mNetworkState.setVisibility(View.VISIBLE);
        mRlWelcome.setVisibility(View.GONE);
        mRlHome.setVisibility(View.GONE);
        mDetailContainer.setVisibility(View.GONE);
        mLlGameDes.setVisibility(View.GONE);
        mLlGameQuestions.setVisibility(View.GONE);
        mLlPunishmentRewards.setVisibility(View.GONE);
        mLlGameEnd.setVisibility(View.GONE);
        mLlGameQuestionsAnswer.setVisibility(View.GONE);
    }

    public void changeQuestions() {
        startRocketAnima(mIvCaiDeng2);
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
            if(userInfo!=null&&userInfo.getId()!=null){
                SharedPreferencesUtils.init(this,userInfo.getId()).putInt("typeId",typeId)
                        .putInt("groupId",groupId)
                        .putInt("questionsId",questionsId)
                        .putBoolean("isAnswer",true);
            }
        } else if (questionsId >= questionsList.size()) {
            if(userInfo!=null&&userInfo.getId()!=null){
                SharedPreferencesUtils.init(this,userInfo.getId()).putInt("typeId",0)
                        .putInt("typeId",0)
                        .putInt("groupId",0)
                        .putInt("questionsId",0)
                        .putInt("intentNum",0)
                        .putBoolean("isPay",false)
                        .putBoolean("isAnswer",false);
            }
            typeId = 0;
            groupId = 0;
            intentNum = 0;
            questionsId = 0;
            mLlGameQuestions.setVisibility(View.GONE);
            startRocketAnima(mIvCaiDeng7);
            mLlGameEnd.setVisibility(View.VISIBLE);
        }
    }

    public void changePunishmentRewards(int type) {
        startRocketAnima(mIvCaiDeng6);
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

            if(cfList!=null){
                mPunishmentId = random.nextInt(cfList.size()-1);

                mTxPunishment1.setText(cfList.get(mPunishmentId).get(0));
                mTxPunishment2.setText(cfList.get(mPunishmentId).get(1));
                mTxPunishment3.setText(cfList.get(mPunishmentId).get(2));

            }
        } else {
            mLlRewards.setVisibility(View.VISIBLE);//奖励
        }

        mLlGameEnd.setVisibility(View.GONE);
    }


    public void changeLookAnswer() {
        startRocketAnima(mIvCaiDeng3);
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
        L.debug("nettask", "读网络题目数据");
        Map<String, String> map = new HashMap<String, String>();
        NetTask.executeRequestByGet(NetTask.TELGAME_LIST, null, this);
    }


    public void loadCFData() {
        L.debug("nettask", "读网络惩罚数据");
        NetTask.executeRequestByGet(NetTask.GAME_CF, null, this);

    }
    public void loadGameData() {
        L.debug("nettask", "读游戏数据");
        NetTask.executeRequestByGet(NetTask.GAME_DIRTY, null, this);

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
        map.put("price", price+"");
        map.put("num", intentNum+"");

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

        if (reqId.equals(NetTask.GAME_DIRTY)) {

            if(resut!=null){
                GameData gameData = new Gson().fromJson(resut.getBody(),GameData.class);
                if(gameData != null)
                    gameDataList = gameData.getData();
            }

            L.debug("nettask", resut.getBody());
        }else if (reqId.equals(NetTask.TELGAME_LIST)) {

            if(resut!=null){
                mPreseData(resut);
            }

            L.debug("preseData", mQuestionsDataTypeMap.toString());
        }else if(reqId.equals(NetTask.GAME_CF)){

            if(resut!=null){
                mPreseQuestionaCFData(resut);
            }

        }else if (reqId.equals(NetTask.EXCHANGE_GOLDS)) {
            if (resut.getResult().equals("10310")){//兑换金币成功
                isDuiHuan = true;
                L.debug("nettask","兑换金币成功");
//                intent_order();//去支付
                isPaying = false;
                LemonBubble.showRight(MainActivity.this, "支付成功", 2000);
                goPay = false;
            }else{
                isDuiHuan = false;
                isPaying = false;
                Utils.showToastCenter(MainActivity.this,"兑换金币失败");
                LemonBubble.showError(MainActivity.this, "兑换金币失败", 2000);
            }
        }
        /*else if(reqId.equals(NetTask.INTENT_QUERY)){

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

        }*/
        else if(reqId.equals(NetTask.INTENT_ORDER)){
            /**
             * //返回
             {
             status:10000,//10000成功 10101库存不足 10102余额不足
             data:"1235466"//成功为订单号，不成功则为错误信息
             }
             */
            if (resut.getResult().equals("10000")){

                playSound2();
                L.debug("nettask","支付成功");
                postPay();//兑换金币
            }else if(resut.getResult().equals("10101")){
                LemonBubble.hide();
                Utils.showToastCenter(MainActivity.this,resut.getData());
                isPaying = false;
            }else if(resut.getResult().equals("10102")){
                LemonBubble.hide();
                Utils.showToastCenter(MainActivity.this,resut.getData());
//                userHelper.payGold(MainActivity.this);//充值页面
                userHelper.payGoldCode (MainActivity.this,typeRMB*1000.0);
                goPay = true;
                isPaying = false;
            }

        }

    }

    private void mPreseQuestionaCFData(QResult resut) {
        questionsCFData = new Gson().fromJson(resut.getBody(), cfData.class);
        if(questionsCFData!=null)
            cfList = questionsCFData.getData();
        L.debug("nettask","cfList  "+cfList.size()+"");
    }

    private void mPreseData(QResult resut) {
        questionsData = new Gson().fromJson(resut.getBody(), QuestionsData.class);
        L.debug("nettask","questionsData   "+questionsData.getData().size()+"");
        /*if(questionsData!=null){
            int size = questionsData.getData().size();
            for (int i = 0; i < size; i++) {
                dataBeanDao.save(questionsData.getData().get(i));
            }
        }*/
        preseData2View(questionsData.getData());
        loadingdialog.dismiss();
    }

    private void preseData2View(List<DataBean> data) {
        preseData(data);

        if(mLlGameQuestions.getVisibility() == View.VISIBLE || mLlGameDes.getVisibility() == View.VISIBLE) return;
        if(userInfo!=null&&userInfo.getId()!=null){
            isAnswer = SharedPreferencesUtils.init(MainActivity.this,userInfo.getId()).getBoolean("isAnswer");

            if(typeId!=0 && groupId!=0 && SharedPreferencesUtils.init(MainActivity.this,userInfo.getId()).getBoolean("isPay")&&isAnswer){
                mRlWelcome.setVisibility(View.GONE);
                changeQuestions();
            }else if(SharedPreferencesUtils.init(MainActivity.this,userInfo.getId()).getBoolean("isPay")&&!isAnswer){
                mRlWelcome.setVisibility(View.GONE);
                isAnswer = true;
                changeGame();//收到彩票页面
            }
        }
    }

    private void checkGlods() {
        pay_glods = typeRMB*1000;
        if(pay_glods>(int)Float.parseFloat(glodsCount)){
            L.debug("nettask","金币不足-->充值页面");
            Utils.showToastCenter(MainActivity.this,"金币不足！！");
//            userHelper.payGold(MainActivity.this);//充值页面
            goPay = true;
        }else{

            //先去支付
            intent_order();

//            postPay();//兑换金币
            LemonBubble.showRoundProgress(MainActivity.this, "正在支付...");
        }
        L.debug("nettask","  pay_glods："+pay_glods+"  glodsCount:"+glodsCount);
    }

    private HashMap<Integer, QuestionsType> mQuestionsDataTypeMap = new HashMap<>();


    private void preseData(List<DataBean> data) {
        mQuestionsDataTypeMap.clear();
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

        L.debug("nettask","mQuestionsDataTypeMap   "+mQuestionsDataTypeMap.size()+"");
        L.debug("nettask","mQuestionsDataTypeMap----  "+mQuestionsDataTypeMap.get(2).getgroupData().get(2).getData().size()+"");
    }

    @Override
    public void onFail(QResponse response, String reqId) {
        if(!NetStatus.getNetStatus(MainActivity.this)){
            Utils.showToastCenter(MainActivity.this,"网络断开,请检查网络哦");
            loadingdialog.dismiss();
            return;
        }
        if (reqId.equals(NetTask.EXCHANGE_GOLDS)) {
            isDuiHuan = false;
            isPaying = false;
//            Utils.showToastCenter(MainActivity.this,"兑换金币失败");
            LemonBubble.showError(MainActivity.this, "兑换金币失败", 2000);
        }
    }


    @Override
    public void userInfo(String s) {
        userInfo = new Gson().fromJson(s, UserInfo.class);
        if(userInfo!=null){

            if(isOnCreat){
                initData();
                isOnCreat = false;
            }


            if(userInfo.getState().equals("0"))
                finish();

            glodsCount = userInfo.getCoinCount();
            L.debug("nettask","充值金币返回  userInfo");
            L.debug("nettask", userInfo.getCoinCount());
            if(goPay){//充值金币返回
                L.debug("nettask","充值金币返回-->二次检查金币");
                checkGlods();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.debug("nettask","充值金币返回  onResume");
        if(goPay && userInfo!=null){
            isPaying = false;
            userHelper.getUserInfo(this);
            /*Utils.showToastCenter(MainActivity.this,"取消支付");
            glodsCount = userInfo.getCoinCount();
            L.debug("nettask", userInfo.getCoinCount());*/
        }
    }
}
