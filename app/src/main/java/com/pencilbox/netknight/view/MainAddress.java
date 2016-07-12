package com.pencilbox.netknight.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.PopupWindow;

import com.pencilbox.netknight.R;
import com.pencilbox.netknight.model.BlockIp;
import com.pencilbox.netknight.model.BlockName;
import com.pencilbox.netknight.presentor.ListAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class MainAddress extends Fragment implements IBlockingIpView{
    private PopupWindow popupWindow;
    private ListView listView;
    private List<String> listAddress;
    private ListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.address_main, container, false);
        view.findViewById(R.id.btn_adtopleft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    return;
                } else {
                    initmPopupWindowViewleft();
                    popupWindow.showAsDropDown(v, 0, 5);
                }

            }
        });

        view.findViewById(R.id.btn_addressadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressInputDialog dialog = new AddressInputDialog();
                dialog.show(getFragmentManager(), "Dialog");


            }
        });
        /**
         * 将数据库中的已有记录加载进listview
         */
//        listView = (ListView) view.findViewById(R.id.list_address);
//        listAddress = new ArrayList<String>();
//        for (int i = 1; i<= DataSupport.count(BlockName.class); i++){
//            listAddress.add(DataSupport.find(BlockName.class,i).getcName());
//            listAddress.add(DataSupport.find(BlockName.class,i).getIp());
//        }
//        listAdapter = new ListAdapter(this.getContext(),listAddress);
//        listView.setAdapter(listAdapter);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initmPopupWindowViewleft() {
        View customView = getActivity().getLayoutInflater().inflate(R.layout.mainleft_top,
                null, false);
        popupWindow = new PopupWindow(customView, 500, 600);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow.setAnimationStyle(R.style.ways);
        popupWindow.setOutsideTouchable(true);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                return false;
            }
        });


        Button btn_dariy = (Button) customView.findViewById(R.id.btn_dariy);
        Button btn_bag = (Button) customView.findViewById(R.id.btn_bag);
        btn_dariy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DairyTabbed.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), GraspBag.class);
                startActivity(intent1);
                getActivity().finish();

            }
        });


    }

    /*
        @Override
        public void onDataInputListener(String start, String end) {
            listaddress = new ArrayList<String>();
            address_adapter = new ListAdapter(getActivity(), listaddress);
            listaddress.add(start);
            listaddress.add(end);
            listViewaddress.setAdapter(address_adapter);
        }
    */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onLoadBlockingList(BaseAdapter adapter) {

    }

    @Override
    public void onListRefresh() {

    }

    @Override
    public void onOptionFailed(int optionId, String msg) {

    }
}
