package com.android.glory.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Activity.Activity_CricketList;
import com.android.glory.Adapter.Adapter_MyMatches;
import com.android.glory.Pojo.Entity_MyMatches;
import com.android.glory.R;

import java.util.ArrayList;

public class Fragment_MyMatchesUpcoming extends Fragment implements Adapter_MyMatches.OnItemClickcourses{

    RecyclerView recyclercricket;
    String [] strtitle = new String[]{"MARSH CUP MATCH 15", "MARSH CUP MATCH 15","MARSH CUP MATCH 15","MARSH CUP MATCH 15","MARSH CUP MATCH 15"};
    String [] strtitle1 = new String[]{"NSW", "SAU","IND","KAR","MRW"};
    String [] strtitle2 = new String[]{"TAS", "QUN","PAK","CHA","PSW"};
    Integer []courseImage1 = new Integer[]{R.drawable.icon1,R.drawable.icon1, R.drawable.icon1, R.drawable.icon1,R.drawable.icon1};
    Integer []courseImage2 = new Integer[]{R.drawable.icon1,R.drawable.icon1, R.drawable.icon1, R.drawable.icon1,R.drawable.icon1};
    Adapter_MyMatches cricket_Adapter;
    Adapter_MyMatches.OnItemClickcourses mCallback2;

    private ArrayList<Entity_MyMatches> allItemscricket = new ArrayList<Entity_MyMatches>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_mymatchesupcoming, container, false);
        recyclercricket = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclercricket.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclercricket.setLayoutManager(mLayoutManager2);
        mCallback2 = this;

        setUpRecyler2();

        return rootView;
    }
    private void setUpRecyler2() {
        allItemscricket =new ArrayList<Entity_MyMatches>();

        for(int i=0;i<strtitle.length;i++){
            Entity_MyMatches feedInfo = new Entity_MyMatches();
            feedInfo.setProductName(strtitle[i]);
            allItemscricket.add(feedInfo);
        }
        cricket_Adapter = new Adapter_MyMatches(getActivity(),allItemscricket, mCallback2);
        recyclercricket.setAdapter(cricket_Adapter);
    }

    @Override
    public void OnItemClickcourses(int pos, String qty, String sub_category_id, int status) {

        Intent intent = new Intent(getActivity(), Activity_CricketList.class);
        startActivity(intent);

    }


}
