/*
package com.hecaibao88.dirtygame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

*/
/**
 * 分类页面
 * A simple {@link Fragment} subclass.
 *//*

public class ListFragment extends BaseFragment {
    private ListView lv_left;


    private TypeLeftAdapter leftAdapter;
    private boolean isFirst = true;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        lv_left = (ListView) view.findViewById(R.id.lv_left);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        if (isFirst) {
            leftAdapter = new TypeLeftAdapter(mContext);
            lv_left.setAdapter(leftAdapter);
        }


        */
/*//*
/解析右边数据
        TypeRightAdapter rightAdapter = new TypeRightAdapter(mContext, titles);
        rv_right.setAdapter(rightAdapter);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });

        rv_right.setLayoutManager(manager);*//*



        Bundle arguments = new Bundle();
        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, titles[0]);
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(arguments);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragment).commit();



        initListener(leftAdapter);


    }

    private String[] titles = new String[]{"入门", "调情", "浪漫", "前戏", "性爱"};

    private void initListener(final TypeLeftAdapter adapter) {

        //点击监听
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.changeSelected(position);//刷新

                if (position != 0) {
                    isFirst = false;
                }

                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, titles[position]);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment).commit();

                leftAdapter.notifyDataSetChanged();
            }
        });

        //选中监听
        lv_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.changeSelected(position);//刷新
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}*/
