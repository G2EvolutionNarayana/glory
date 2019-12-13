package com.android.glory.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.glory.Adapter.PlayerViewAdapter;
import com.android.glory.Pojo.PlayerDetails;
import com.android.glory.R;

import java.util.ArrayList;
import java.util.List;


public class Team_1_Fragment extends Fragment {
    private List<PlayerDetails> playerDetailsList;
    private PlayerViewAdapter playerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  root= inflater.inflate(R.layout.fragment_team_1, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv_players);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        playerDetailsList = new ArrayList<>();
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerDetailsList.add(new PlayerDetails(R.drawable.user,"M.S. Dhoni","158","10"));
        playerViewAdapter = new PlayerViewAdapter(getContext(),playerDetailsList);
        recyclerView.setAdapter(playerViewAdapter);

        return root;
    }





}
