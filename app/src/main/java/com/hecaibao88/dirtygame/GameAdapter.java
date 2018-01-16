/*
package com.hecaibao88.dirtygame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hecaibao88.dirtygame.dialog.ImProgressDialog;

import java.util.List;

*/
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
 *//*

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private ImProgressDialog loadingdialog;
    private List<GameData.DataBean> mGameList;
    private OnItemClickListener mItemClickListener;

    public GameAdapter(ImProgressDialog loadingdialog) {
        this.loadingdialog = loadingdialog;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_game;

        public ViewHolder(final View itemView) {
            super(itemView);
            iv_game = (ImageView) itemView.findViewById(R.id.iv_game);

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
        void onItemClick(View view, int position, GameData.DataBean gameData);
    }

    public GameAdapter() {
    }

    */
/**
     * 用于把要展示的数据源传进来，并赋值给一个全局变量mFruitList，我们后续的操作都将在这个数据源的基础上进行。
     *
     *//*

    public void setGameAdapterData(List<GameData.DataBean> mFruitList) {
        this.mGameList = mFruitList;
        this.notifyDataSetChanged();
    }

    */
/**
     * 用于创建ViewHolder实例的，并把加载出来的布局传入到构造函数当中，最后将viewholder的实例返回
     * @param parent
     * @param viewType
     * @return
     *//*

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    */
/**
     * 用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行，这里我们通过
     * position参数得到当前项的Fruit实例，然后再将数据设置到ViewHolder的Imageview和textview当中即可，
     * @param holder
     * @param position
     *//*

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GameData.DataBean game = mGameList.get(position);
       */
/* Glide.with(MyAppliction.getContext())
                .load(game.getImageUrl())
//                .placeholder(R.drawable.progress_medium_holo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(mRequestListener)
                .override(489, 763)
                .crossFade()
                .into(holder.iv_game);
*//*


    }





   */
/* private int picCount = 0;
    RequestListener mRequestListener = new RequestListener() {

        @Override
        public boolean onException(Exception e, Object model, Target target, boolean
                isFirstResource) {
            Log.d("nettask", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean
                isFromMemoryCache, boolean isFirstResource) {
            Log.e("nettask",  "model:"+model+" isFirstResource: "+isFirstResource);
            picCount++;
            if(picCount >= 3)
                loadingdialog.dismiss();
            return false;
        }
    };*//*





        @Override
        public int getItemCount () {
            if (mGameList != null)
                return mGameList.size();
            else
                return 0;
        }
}
*/
