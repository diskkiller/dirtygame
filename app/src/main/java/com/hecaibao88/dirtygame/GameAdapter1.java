package com.hecaibao88.dirtygame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hecaibao88.dirtygame.dialog.ImProgressDialog;
import com.sunfusheng.glideimageview.progress.CircleProgressView;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @author WangGuoWei
 * @time 2017/12/26 13:53
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
public class GameAdapter1 extends RecyclerView.Adapter<GameAdapter1.ViewHolder> {
    private List<GameData> mGameList;
    private OnItemClickListener mItemClickListener;

    public GameAdapter1(ImProgressDialog loadingdialog) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView glideImageView;
        CircleProgressView progressView;

        public ViewHolder(final View itemView) {
            super(itemView);
            glideImageView = (ImageView) itemView.findViewById(R.id.glideImageView);
            progressView = (CircleProgressView) itemView.findViewById(R.id.progressView1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), mGameList.get(getAdapterPosition()));

                }
            });

        }
    }


    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, GameData gameData);
    }

    public GameAdapter1() {
    }

    /**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     */
    public void setGameAdapterData(List<GameData> mFruitList) {
        this.mGameList = mFruitList;
        this.notifyDataSetChanged();
    }

    /**
     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item1,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过
     * position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的Imageview和textview当中即可，
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GameData game = mGameList.get(position);

        Glide.with(MyAppliction.getContext())
                .load(game.getImageUrl())
                .transition(withCrossFade())
                .into(holder.glideImageView);





/*
        int cat_thumbnail = R.mipmap.tu11;
        if (position == 0)
            cat_thumbnail = R.mipmap.tu11;
        else if (position == 1)
            cat_thumbnail = R.mipmap.tu21;
        else if (position == 2)
            cat_thumbnail = R.mipmap.tu31;
        else if (position == 3)
            cat_thumbnail = R.mipmap.tu41;
        else if (position == 4)
            cat_thumbnail = R.mipmap.tu51;
        RequestBuilder<Drawable> thumbnailRequest = Glide
                .with( MyAppliction.getContext() )
                .load( cat_thumbnail );




        RequestOptions requestOptions = holder.glideImageView.requestOptions(R.color.black)
                .override(489, 763)
                .centerCrop()

                .skipMemoryCache(true) // 跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 不缓存到SDCard中



        GlideImageLoader imageLoader = holder.glideImageView.getImageLoader();

        imageLoader.setOnGlideImageViewListener(game.getImageUrl(), new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(MyAppliction.getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                holder.progressView.setProgress(percent);
                holder.progressView.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });

        imageLoader.requestBuilder(game.getImageUrl(), requestOptions)
                .thumbnail(Glide.with(MyAppliction.getContext())
                    .load(cat_thumbnail)
                    .apply(requestOptions))
//                .error(thumbnailRequest)
                    .into(holder.glideImageView);
        */

    }







        @Override
        public int getItemCount () {
            if (mGameList != null)
                return mGameList.size();
            else
                return 0;
        }
}
