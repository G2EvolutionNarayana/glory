package com.glory.apk.Utilites;

import com.glory.apk.Model.HomePlayersSelected;
import com.glory.apk.Model.OpposPlayerSelected;

import java.util.ArrayList;

public class StaticUtils {

    public static final String MATCH_ID = "matchId";
    public static final String HOME_TEAM_SHORT_NAME = "homeTeamShortName";
    public static final String HOME_TEAM_FULL_NAME = "homeTeamFullName";
    public static final String OPPOS_TEAM_SHORT_NAME = "oppsShortName";
    public static final String OPPOS_TEAM_FULL_NAME = "oppsFullName";
    public static final String HOME_FLAG = "homeflag";
    public static final String OPPOS_FLAG = "oppositeFlag";
    public static final String PACKAGE = "PACKAGE";
    public static final String HOME_TEAM_COUNT = "HOMETEAMCOUNT";
    public static final String OPPOSITE_TEAM_COUNT = "OPPOSITETEAMCOUNT";
    public static final String PACKAGE_ID = "packageId";
    public static final String HITTERID = "HitterId";
    public static final String HITTER_IMAGE = "HitterImage";
    public static final String HITTER_NAME = "HitterName";

    public static final String NO_PLAYERS5 = "noofplayers5";
    public static final String NO_PLAYERS7 = "noofplayers7";
//    public static final String CREDITS5= "40";
//    public static final String CREDITS7 = "51";

    public static final String CONTEST_ID = "CONTESTID";
    public static final String CONTEST_USERID = "CONTEST_USERID";
    public static final String TOTAL_POINTS = "TOTAL_POINTS";

    public static final String PANNAME = "PANNAME";
    public static final String PANNUMBER = "PANNUMBER";
    public static final String PANDOB = "PANDOB";
    public static final String PANSTATE = "PANSTATE";
        public static final String PANID = "PANID";

    public static final String BRANCH_NAME = "BRANCH_NAME";
    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String IFSC_CODE = "IFSC_CODE";
    public static final String BANK_ID = "BANK_ID";

    public static int HomeTeamcount=0;
    public static int OppoTeamcount=0;
    public static int FINAL_COUNT=0;
    public static Double CREDITS5=40.0;
    public static Double CREDITS7=55.0;

    public static Double EditCREDITS5=40.0;
    public static Double EditCREDITS7=55.0;

    public static int Edit_HomeTeamcount=0;
    public static int Edit_OppoTeamcount=0;
    public static int Edit_FINAL_COUNT=0;
    public static ArrayList<HomePlayersSelected> homePlayers=new ArrayList<>();
    public static ArrayList<OpposPlayerSelected> opposePlayers=new ArrayList<>();


}
