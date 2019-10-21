package com.android.glory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.glory.Adapter.Adapter_Navigation;
import com.android.glory.Pojo.Pojo_navigation;
import com.android.glory.R;

import java.util.ArrayList;

public class Activity_Navigation extends AppCompatActivity implements  Adapter_Navigation.OnItemClick{

    String []Id;
    String []Title;
    Integer []Image;




    Pojo_navigation feedInfo;
    Adapter_Navigation mAdapterFeeds;
    private RecyclerView mFeedRecyler;
    private ArrayList<Pojo_navigation> mListFeederInfo;
    Adapter_Navigation.OnItemClick mCallback;
    float intrating;

    String streditrateuscomment;
    String streditrateusrating = "0";
    ImageView imgdialogfiltercancel;
    Dialog dialograteus;
    LinearLayout lineardialog;


    static String registeruserid, registermobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

     /*   SharedPreferences prefuserdata = getSharedPreferences("registeruser", 0);
        registeruserid = prefuserdata.getString("registeruserid", "");
        registermobileno = prefuserdata.getString("registermobileno", "");



        if (registeruserid == null || registeruserid.equals("0") || registeruserid.length() == 0){
            Id = new String[]{"0","1","2","3"};
            Title = new String[]{"Home","About Us","Refer a Friend", "Login"};
            Image = new Integer[]{R.drawable.home,  R.drawable.aboutus, R.drawable.refer, R.drawable.logout};
        }else{
            Id = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11"};
            Title = new String[]{"Home","Profile","Notification", "Wishlist","Transaction","Reminder","Rate Us","Support","Contact Us","About Us","Refer a Friend","Logout"};
            Image = new Integer[]{R.drawable.home, R.drawable.myaccount, R.drawable.notificationicon,R.drawable.whishlistselected, R.drawable.transaction,R.drawable.transaction, R.drawable.star, R.drawable.support,R.drawable.contactus,R.drawable.aboutus, R.drawable.refer, R.drawable.logout};
        }*/
        Id = new String[]{"0","1","2","3","4","5","6","7"};

        Title = new String[]{"Invite Friends","Fantasy Point System","How to Play","Helpdesk","About Us","Contact Us","Legality","Terms & Conditions"};
        Image = new Integer[]{R.drawable.invite,R.drawable.oneicon, R.drawable.questionmark, R.drawable.help,R.drawable.aboutus,R.drawable.contactus, R.drawable.legality, R.drawable.terms};
        mFeedRecyler = (RecyclerView) findViewById(R.id.recycler_viewcart);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Activity_Navigation.this);
        mFeedRecyler.setLayoutManager(mLayoutManager);

        //  mFeedRecyler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mFeedRecyler.setHasFixedSize(true);
        mCallback = this;

        setUpRecyler();

    }
    private void setUpRecyler() {
        mListFeederInfo =new ArrayList<Pojo_navigation>();

        for(int i=0;i<Id.length;i++){
            Pojo_navigation feedInfo = new Pojo_navigation();
            feedInfo.setId(Id[i]);
            feedInfo.setName(Title[i]);
            feedInfo.setImage(Image[i]);
            mListFeederInfo.add(feedInfo);
        }
        mAdapterFeeds = new Adapter_Navigation(Activity_Navigation.this,mListFeederInfo, mCallback);
        mFeedRecyler.setAdapter(mAdapterFeeds);




    }

    @Override
    public void onClickedItem(int pos, String qty, int status) {
        Pojo_navigation feederInfo = mListFeederInfo.get(pos);


        if (feederInfo.getName().equals("Invite Friends")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_InviteFriends.class);
            startActivity(intent);

        }else if (feederInfo.getName().equals("Fantasy Point System")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_FantasyPointSystem.class);
            startActivity(intent);

        }else if (feederInfo.getName().equals("How to Play")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_Howtoplay.class);
            startActivity(intent);


        }else if (feederInfo.getName().equals("Helpdesk")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_HelpDesk.class);
            startActivity(intent);

        }else if (feederInfo.getName().equals("About Us")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_AboutUs.class);
            startActivity(intent);


        }else if (feederInfo.getName().equals("Contact Us")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_ContactUs.class);
            startActivity(intent);

        }else if (feederInfo.getName().equals("Legality")){
            Intent intent = new Intent(Activity_Navigation.this, Activity_Legality.class);
            startActivity(intent);
        }else if (feederInfo.getName().equals("Terms & Conditions")){

            Intent intent = new Intent(Activity_Navigation.this, Activity_TC.class);
            startActivity(intent);

        }


    }


}
