package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.AboutUs.AboutExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.sharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_TC extends AppCompatActivity {
    private ProgressDialog wallatedialog;
    private TextView texttitle, textdesc2,xTvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__tc);
        texttitle = findViewById(R.id.texttitle);
        textdesc2 = findViewById(R.id.textdesc2);
        xTvtitle = findViewById(R.id.xTvtitle);

        ImageView imgcancel = (ImageView) findViewById(R.id.imgcancel);
        imgcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String strdesc = "\u2022 The GLORY-5 users are enforced to follow certain Terms & Conditions associated with the usage of GLORY-5. It is mandatory for the users to follow the terms and conditions of GLORY-5 while using the services provided by GLORY-5: \n" +
                "\n" +
                "\u2022 The GLORY-5 app is neither affiliated by nor associated to any sort of private or government Sports leagues and tournaments until and unless stated by the organization. In addition to this, the GLORY-5 app is not related and does not claim any official status with any of the official or non-official sports team or sportsperson. We do not promote, support or encourage betting or gambling in any form. \n" +
                "\n" +
                "\u2022 GLORY-5 used as a single entity anywhere in this document and all sections of T&C, should mean and constitutes of GLORY-5.com website and GLORY-5  app on both android and iOS store.\n \n" +
                "\n" +
                "\u2022 The GLORY-5 app comprises of a group of content solely developed by GLORY-5, the partners of GLORY-5, licensors, associates and/or Users of GLORY-5. The partners, licensors, associates of  GLORY-5 and/ or  GLORY-5 itself holds the intellectual property rights (“Intellectual Property Rights”) in all software underlying GLORY-5.com and entirely owns the Fun Features and material published on GLORY-5.com, including (but not limited to) games, Contests, software, advertisements, written content, photographs, graphics, images, illustrations, marks, logos, audio or video clippings and Flash animation. The users are not permitted to modify, publish, transmit, participate in the transfer or sale of, reproduce, create derivative works of, distribute, publicly perform, publicly display, or exploit any of the materials or content published on GLORY-5.com in any manner, either in whole or in part until and unless users have written license from GLORY-5  team or authorities.\n \n" +
                "\n" +
                "\u2022 The users can demand permission for modifying, publishing, transmitting, participating in the transfer of, reproducing, creating derivative works of, or publicly displaying any of the materials or content published on GLORY-5.com from info@GLORY-5.com\n \n" +
                "\n" +
                "\u2022 The users of  GLORY-5 hold the sole responsibility of all the materials (whether publicly posted or privately transmitted) that they upload, post, e-mail, transmit or otherwise make available on GLORY-5.com (\"Users' Content\"). The users of GLORY-5 assures that they hold and own all the Intellectual Property Rights in the User's Content and that none of the material uploaded or posted by the users as a part of the User's content does not hurt or harm any of the third party rights. The users also ensure not to use or display any of the names, logos, marks, labels, trademarks, copyrights or intellectual and proprietary rights of any third party associated with or present on GLORY-5.com. The users further accept and agree that GLORY-5, its directors, employees, affiliates and assigns holds harmless and unanswerable towards any and/or all sort of costs, damages, loss and harm including towards litigation costs and counsel fees, in case of any claims made by any third party following the display or use of the names, logos, marks, labels, trademarks, copyrights or intellectual and proprietary rights on GLORY-5.com, through the User's commissions or omissions\n \n" +
                "\n" +
                "\u2022 The users of GLORY-5 by accepting the terms and conditions permit    GLORY-5 and its affiliates, partners, licensors and associates a worldwide, irrevocable, royalty-free, non-exclusive, sub-licensable license to use, reproduce, create derivative works of, distribute, publicly perform, publicly display, transfer, transmit, and/or publish Users’ Content for any of the following purposes:\n" +
                "\n" +
                "displaying Users’ Content on GLORY-5.com\n" +
                "\n" +
                "distributing Users’ Content, either electronically or via other media, to other Users seeking to download or otherwise acquire it, and/or\n" +
                "\n" +
                "Storing Users’ Content in a remote database accessible by end users, for a charge.\n" +
                "\n" +
                "This license shall apply to the distribution and the storage of Users’ Content in any form, medium, or technology.\n \n" +
                "\n" +
                "\u2022 Users consent to receiving communications such as announcements, administrative messages, and advertisements from  GLORY-5 or any of its partners, licensors or associates.\n \n" +
                "\n" +
                "\u2022 An individual using the services offered by GLORY-5.com or the GLORY-5 App including the Fun Features offered, by participating in various contests and, games (including fantasy games), organized on the GLORY-5  platform (“Contest(s)”) are bound to follow these Terms and Conditions, and all other rules, regulations, and terms of use defined by the GLORY-5 authorities.\n \n" +
                "\n" +
                "\u2022 The GLORY-5 authorities have all the rights to modify or change the already defined Terms and Conditions, rules, regulations, and terms of use at any point in time, by posting the same on GLORY-5.com. GLORY-5 constitutes the acceptance and agreement of the individuals using  GLORY-5 services towards time to time amendments that can be made in the Terms and Conditions, rules, regulations, and terms of use already defined by       GLORY-5 The users can be informed about the amendments in these Terms and Conditions, rules, regulations, and terms of use by sending an email to the User's registered email address or posting notifications in the User accounts. In a circumstance wherein the user disagrees or does not accepts the amendments in the Terms and Conditions, rules, regulations, and terms of use can use the options provided in such an email or notification to indicate their non-acceptance within a defined time frame. If a user fails to indicate his/ her non-acceptance of the amendments within the defined time period the users are bound to accept the changes or modification in these Terms and Conditions, rules, regulations, and terms of use. In case the user disapproves/ rejects to accept the amendments in these Terms & Conditions, rules, regulations, and terms of use, then GLORY-5 at its sole & absolute discretion can refrain the user from using the services & Fun Features provided by GLORY-5.\n \n" +
                "\n" +
                "\u2022 Various services offered as a part of Fun Features by  GLORY-5 might be associated with additional rules and regulations formulated in that respect. If the Terms and Conditions are inconsistent with the additional conditions formulated, the additional conditions shall prevail.\n \n" +
                "\n" +
                "\u2022 GLORY-5 may, at its sole and absolute discretion:\n" +
                "\n" +
                "Restrict, suspend, or terminate any User’s access to all or any part of GLORY-5.com or GLORY-5.com Fun Features;\n" +
                "\n" +
                "Change, suspend, or discontinue all or any part of the Fun Features;\n" +
                "\n" +
                "Deactivate or delete a User’s account and all related information and files on the account;\n" +
                "\n" +
                "Reject, move, or remove any material that may be submitted by a User;\n" +
                "\n" +
                "Move or remove any content that is available on GLORY-5.com;\n" +
                "\n" +
                "Establish general practices and limits concerning use of GLORY-5.com.\n" +
                "\n" +
                "Revise or make additions to the roster of players available for selection in a Contest on account of revisions to the roster of players involved in the relevant Sports Event.\n" +
                "\n" +
                "Assign its rights and liabilities to all User accounts hereunder to any entity (post intimation of such assignment shall be sent to all Users to their registered email ids)\n \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to restrict, suspend or terminate a user’s access to all or any part of GLORY-5.com or the Fun Features provided by , GLORY-5 deactivate or delete user account and information, delete user content without any prior notice to the user if any user is found or believed to be breaching the Terms and Conditions defined by  GLORY-5 or using GLORY-5.com or the fun features provided by  GLORY-5 illegally or inappropriately. Further, GLORY-5  is authorized to take any technical or legal action against the user if necessary. \n" +
                "\n" +
                "\u2022 In the event of suspension or deletion of a user's account due to negligence on the part of GLORY-5, then, the company shall repay the fee charged for any of the Fun Features provided by GLORY-5v, without any delay. However, no refund would be made in case of suspension or removal due to:\n" +
                "\n" +
                "The breach of the Terms and Conditions defined by GLORY-5 or inappropriate use of GLORY-5.com or Fun Features provided by GLORY-5; or\n" +
                "\n" +
                "Any circumstances beyond the reasonable control of GLORY-5 .\n \n" +
                "\n" +
                "\u2022 The users hereby accepting the Terms and Conditions of GLORY-5 agree to receive communications including announcements, administrative messages, and advertisements from GLORY-5 or any of its partners, licensors or associates. \n" +
                "\n" +
                "\u2022 There may be various links to other Internet sites owned and operated by third parties on GLORY-5.com which may contain different conditions for users' use of the site. The GLORY-5  authorities do not hold any control or responsibilities for internet sites apart from GLORY-5.com, and thus, it shall not be held responsible for any content posted on any third party Internet site. Further, the involvement of GLORY-5 with any of the third-party content or links to third-party Internet sites is not related to any sort of endorsement by  GLORY-5 of such third-party Internet site.\n \n" +
                "\n" +
                "\u2022 GLORY-5 is neither associated with nor responsible for the correspondence, transactions, and all other related activities between the user and third parties including payment providers and verification service providers on GLORY-5.com. The users' involvement with third parties is entirely dependent on the terms and conditions, policies and services terms of these third parties and the users are solely responsible for reviewing the policies, terms and conditions, and the service terms of these third parties before transacting or availing any of the services. Accepting the Terms and Conditions of GLORY-5 the users agree that GLORY-5 is not responsible for any sort of damage or loss faced by the users due to any sort of transactions, correspondence and all related activities with any of the third parties. Any questions, complaints, or claims related to any third party product or service should be directed to the appropriate vendor. \n" +
                "\n" +
                "\u2022 GLORY-5.com comprises a group of content created and posted by either GLORY-5 as well as various third parties. GLORY-5  does not hold responsible for the accuracy, integrity or the quality of the content created and posted by any of the third parties and such content may not be GLORY-5 relied upon by the Users in terms of using the Fun Features provided by the  platform or taking part in any of the contests hosted on the GLORY-5  platform. \n" +
                "\n" +
                "\u2022 All sort of user information collected by GLORY-5 in terms of personal details and payment information at the time of registration is subjected to the Privacy Policy of GLORY-5 which can be reviewed by the users at Privacy Policy. \n" +
                "\n" +
                "\u2022 The Terms and Conditions, rules, regulations and terms of use defined by GLORY-5 for its users are mandatory to be followed by the users and in an event if the user fails to follow the Terms and Conditions, rules, regulations, and terms of use adequately the authorities of GLORY-5 holds all the rights to take action against such user, which might include (but not limited to):\n" +
                "\n" +
                "Restricting, suspending, or terminating any User’\ts access to all or any part of GLORY-5.com's Fun Features;\n" +
                "\n" +
                "Deactivating or deleting a User’s account and all related information and files on the account; or\n" +
                "\n" +
                "Refraining from awarding any prize(s) to such User.\n \n" +
                "\n" +
                "\u2022 By accepting the Terms and Conditions of GLORY-5, the individual’s using services provided by GLORY-5 agree to provide true, accurate, current and complete information while registration and at other times when demanded by GLORY-5. The individuals further provide consent to keeping their registration information updated. \n" +
                "\n" +
                "\u2022 A single user is permitted to register or operate only one user account with GLORY-5 (inclusive of both web & app).\n \n" +
                "\n" +
                "\u2022 The individuals using the services provided by GLORY-5 provide their consent for receiving all the communications from GLORY-5 by marking e-mails from GLORY-5.com as part of their \"safe senders\" list. In an event, if any of the emails sent by GLORY-5 remains unread by the user because of being sent to the Spam or Junk folders, GLORY-5 shall not be held responsible. \n" +
                "\n" +
                "\u2022 Any sort of password issued by GLORY-5 to any of the individual using the services provided by GLORY-5 are confidential and should not be shared by the users with anyone else. The users hold the sole responsibility of maintaining the confidentiality of their accounts and passwords. The users offer their consent by agreeing to the Terms and Conditions of GLORY-5 to inform GLORY-5 about any unauthorized use of their passwords or accounts or any other breach of security. \n" +
                "\n" +
                "\u2022 The user is solely responsible for the security and confidentiality of his/her payment accounts linked to his/her GLORY-5 account. In case of any misuse of the linked payment accounts of the user, GLORY-5 shall not be held liable; further, such misuse of the linked payment accounts of the user should be reported to GLORY-5 as soon as it comes to the notice of the user. \n" +
                "\n" +
                "\u2022 The users ensure to exit/log-out of their accounts after the completion of each session. In case of any sort of loss or damage faced by the user due to the failure of abiding these rules and regulations, GLORY-5 shall not be held responsible. \n" +
                "\n" +
                "\u2022 The individuals using services provided by GLORY-5 should neither conduct nor assist the usage of cheats, exploits, automation, software, bots, hacks or any unauthorized third-party software specifically formulated for modifying or intervening with GLORY-5.com Fun Features and/or GLORY-5 experience. \n" +
                "\n" +
                "\u2022 The individuals using services offered by GLORY-5 platform provides their consent of not copying, modifying the technology and software associated with GLORY-5 and Fun Features. Further, the users accept not to rent, loan, sell, assign, distribute, reverse engineer, grant a security interest in, or otherwise transfer any right to anyone of the associated technologies and software. \n" +
                "\n" +
                "\u2022 The Terms and Conditions of GLORY-5 bounds the users to neither modify nor become a cause of modification of any of the files, technology or software associated with GLORY-5. \n" +
                "\n" +
                "\u2022 The users provide their acceptance to not to disrupting, overburdening, or promoting any kind of disruption or overburdening to:\n" +
                "\n" +
                "The servers or computers offering or supporting GLORY-5 services or Fun Features.\n" +
                "\n" +
                "The enjoyment of GLORY-5.com's Fun Features by other Users or persons.\n \n" +
                "\n" +
                "\u2022 The Terms and Conditions of GLORY-5 do not permit the user to attain any sort of unauthorized access to the User accounts, Servers or networks associated to GLORY-5.com's Fun Features by any means except the user interface provided by GLORY-5. The user must not indulge in activities like circumventing or modifying, attempting to circumvent or modify, or encouraging or assisting any other person to circumvent or modify, any security, technology, device, or software associated with the Fun Features provided by GLORY-5. \n" +
                "\n" +
                "\u2022 The users provide their acceptance for not using the GLORY-5 platform for any sort of inadequate activities including (but not limited to): \n" +
                "\n" +
                "\u2022 To engage in any obscene, offensive, indecent, racial, communal, anti-national, objectionable, defamatory or abusive action or communication; \n" +
                "\n" +
                "\u2022 To harass, stalk, threaten, or otherwise violate any legal rights of other individuals; \n" +
                "\n" +
                "\u2022 To Transmit files that contain viruses, corrupted files, or any other similar software or programs that may damage or adversely affect the operation of another person's computer, GLORY-5.com, any software, hardware, or telecommunications equipment; \n" +
                "\n" +
                "\u2022 To advertise, offer or sell any goods or services for any commercial purpose on GLORY-5.com without the express written consent of GLORY-5; \n" +
                "\n" +
                "\u2022 To Transmit content regarding services, products, surveys, contests, pyramid schemes, spam, unsolicited advertising or promotional materials, or chain letters; \n" +
                "\n" +
                "\u2022 The users are bound to accept that they will neither support or conduct any sort of attack including (but not limited to) distribution of a virus, denial of service, or other attempts to interrupt the services provided by GLORY-5 or the use or enjoyment of other GLORY-5 users. \n" +
                "\n" +
                "\u2022 In order to register for the Contest(s), Participants are required to provide the following information accurately:\n" +
                "Full Name\n" +
                "Team Name(s)\n" +
                "E-mail address\n" +
                "Password\n" +
                "State of Residence\n" +
                "Gender\n" +
                "Date of birth \n" +
                "\n" +
                "\u2022 The “Full Name” and “Date of Birth” mentioned by the user for the purpose of verification should match with the Name and Date of Birth mentioned on all the documents required to be uploaded for verification purposes. \n" +
                "\n" +
                "\u2022 The people participating in activities, services or features provided by GLORY-5 are required to provide their approval stating they have carefully read all the terms and conditions and will comply with all the rules, regulations and terms of use of GLORY-5. \n" +
                "\n" +
                "\u2022 The participants entering any contest(s) organized by GLORY-5 as a resident of Sikkim, Assam, Odisha, Telangana, and Nagaland will not be permitted to sign up for any round in the paid version of the Contest as elaborated below. In case of audits from GLORY-5, if a user is found to be a resident of Sikkim, Assam, Odisha, Telangana or Nagaland but not stated at the time of registration then GLORY-5 at its sole and absolute discretion holds the rights to take action against such user including (not limited to) blocking of user account, deletion or deactivation of user account, and resetting the user account to initial level by deleting all the logs, refers & reducing the amount present in the user wallet to “zero.” \n" +
                "\n" +
                "\u2022 The participants who are above the age of 18 years and have successfully completed their registration process by entering valid and adequate information in the registration form are sent a confirmation email comprising their login details. \n" +
                "\n" +
                "\u2022 In case of PAN card verification, any scanned image of a photocopy of the users’ PAN card will not be acceptable. For PAN card verification it is mandatory to upload a full image of the original PAN card. \n" +
                "\n" +
                "\u2022 In case, you are uploading an E-PAN card for PAN card verification process then it is mandatory to upload a full scanned image of the E-PAN card (both front and back). \n" +
                "\n" +
                "\u2022 The GLORY-5 platform conducts various contest(s) as a part of Fun Features offered by GLORY-5. GLORY-5 offers fantasy cricket, fantasy football, fantasy kabaddi, fantasy volleyball and fantasy basketball as a part of its fun features. The contests conducted by GLORY-5 allows the users to participate in the Fun Features by formulating their own fantasy team(s) comprising of on-field players involved in the on-field match (as applicable), series or tournament to which the fantasy game relates. The teams of the individual users are rewarded with points on the basis of the performance of on-field players at the end of a designated match, round or tournament of the Contest(s). The user whose team attains the highest aggregate of points will be declared as the winner of the contest. GLORY-5 is authorized to declare more than one Winner and distribute prizes to such Winners in increasing order of their Team's aggregate score at the end of the designated round(s) of the Contests in the event of various pre-specified contest(s). The Contest(s) organized as a part of Fun Features would comply to the Terms and Conditions, rules and regulations mentioned below: \n" +
                "\n" +
                "\u2022 \"Fantasy Rules\" available at “How To Play” rules and regulations (including rules and regulation in relation to any payments made to participate in the Contest(s); and all Participants agree to abide by the same. \n" +
                "\n" +
                "\u2022 GLORY-5 presently offers paid versions of the contests wherein the users can participate by paying a pre-defined amount on the relevant Contest page as a part of contests entry fee. An individual with the highest aggregate of scores at the end of the pre-determined round would be eligible for winning a pre-designated prize, as stated on the relevant Contest(s) page. \n" +
                "\n" +
                "\u2022 An individual is authorized to make different teams for participating in Contest(s) conducted by the GLORY-5 platform as a part of its Fun Features. However, until and unless GLORY-5 specifies a contest as Multiple Entry contests, the users are bound to submit only one Team to participate in the contest organized by GLORY-5 as a part of its Fun Features. In the context of the Multiple Entry Contest, the participants can enter in the contest with more than one team, but the user will not be allowed to edit or revise the Teams so submitted for participation in such Multiple Entry Contest after the deadline. Further, GLORY-5 is authorized to limit the number of multiple teams allowed in a contest or the number of teams a single user account can create for entering a Multiple Entry Contest. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to charge a pre-defined fee for allowing the users to access the Fun Features in relation to the Contest(s). In a situation wherein a participant is found to be a resident of Sikkim, Assam, Odisha, Telangana, and Nagaland, by GLORY-5, the user will not be allowed to proceed to sign up for the round or league and may not participate in any paid version of the Contest(s). \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to abandon a contest or adjust or change the deadline of a particular match or in unavoidable circumstances that stand beyond the control of GLORY-5. The scenarios wherein the match will not be abandoned and the deadline can be adjusted include (but are not limited to) the ones mentioned below:\n" +
                "\n" +
                "The Official Match Start Time is before GLORY-5’s Deadline\n" +
                "\n" +
                "This scenario can differ for cricket and other sports as: \n" +
                "\n" +
                "\u2022 For Other Sports apart from Cricket:\n" +
                "\n" +
                "A difference of 10 minutes (maximum) between the official match start time and GLORY-5’s deadline can be adjusted by the platform. However, if the difference is of more than 10 minutes, i.e., the official match starts more than 10 minutes before GLORY-5’s deadline, then, the contest will be abandoned on GLORY-5 \n" +
                "\n" +
                "\u2022 For Cricket:\n" +
                "\n" +
                "GLORY-5, at its sole and absolute decision, can adjust the deadline of a contest on GLORY-5 by few minutes or few overs (whichever is less) before the beginning of the official match\n" +
                "\n" +
                "In case GLORY-5 fails to cross-check or verify the official match start time from any of its reliable third-party sources or from the official websites, then, GLORY-5’s deadline for the particular contest will be adjusted in such a way that a maximum of few overs is bowled in the adjusted time being. \n" +
                "\n" +
                "The Official Match Start Time is after GLORY-5’s Deadline\n" +
                "\n" +
                "GLORY-5 at its sole and absolute discretion can abandon, cancel or delay the start of a contest on GLORY-5 by extending the deadline in case of certain unavoidable circumstances such as (but not limited to) delay in the toss, weather conditions, technical glitches, non-appearance of the teams on the field, natural disasters, etc. \n" +
                "\n" +
                "\u2022 GLORY-5 conducts Contest(s) as a part of Fun Features as public and private leagues wherein the users can participate in a Contest with other Users without any restriction on participation. The public and private leagues comply to various Terms and Conditions including (but not limited to): \n" +
                "\n" +
                "\u2022 Public League\n" +
                "\n" +
                "GLORY-5 in public league format of the Contest(s) can offer any pre-defined numbers of participants that can take part in the contest.\n" +
                "\n" +
                "GLORY-5 is authorized to conduct the public league format of Contest(s) as a paid contest and might announce the winner at the end of the contest.\n" +
                "\n" +
                "GLORY-5 can pre-define the number of participants required to make the Contest(s) operational. In an event wherein the number of participants required to make the contest operation equalizes with the number of users participating in the contest, the Contest(s) organized as a public league by GLORY-5 will begin at the scheduled start of the Contest(s). However, in a situation wherein the number of participants is less than the number required at the time of beginning the Contest(s), the Contest(s) will be discarded, and the participation fee will be refunded to the participants without any charge or deduction. \n" +
                "\n" +
                "\u2022 Private Leagues\n" +
                "\n" +
                "GLORY-5 offers \"Private Leagues\" as another form of a contest on its platform. The Private Leagues can be defined as a contest wherein the users are enabled to create their own contest and invite other users (friends or other GLORY-5 users) to compete with them. The private leagues created by the users are a part of pay-to-play contests wherein the players are required to pay an entry fee for joining the contest.\n" +
                "\n" +
                "GLORY-5 enables users to create a Private League contest with a minimum of 2 players and a maximum of 49 players. The user can define the maximum Winning amount to be ₹10,000 whereas the minimum Winning amount for a private league can be defined as ₹17. The users after creating a private contest will be given a unique identification code, the “Contest Code” which can be shared by the users among their acquaintances for enabling others to join the contest.\n" +
                "\n" +
                "GLORY-5 neither allows nor encourages any change except the name of the contest once a user creates any private league. The Winning amount and number or participants once defined at the time of creating a private league cannot be changed further in any case.\n" +
                "\n" +
                "In order to join a private league, the invited user is required to fill in the contest code followed by the payment of the entry fee, and then only the invited user will be considered as a participant of the private contest. The private leagues are a part of first come first serve basis. In case, the private league meets the required number of participants; it shall be considered as operative, and no other user will be allowed to participate in the contest.\n" +
                "\n" +
                "In an event, a private contest created by any user fails to meet the pre-specified required number of the participant before the deadline which is 1 hour prior to the commencement of the real match, the contest will be automatically deleted, and the entry fee will be refunded to all the users who have joined the private league.\n" +
                "\n" +
                "The participation of any invited user in any of the private contest created by any user is entirely dependent on the pre-specified number of participants for the private league. GLORY-5 shall not be held responsible for the deletion or cancellation of any private league due to an inability of meeting the pre-specified required number of participants.\n" +
                "\n" +
                "GLORY-5 at its sole and absolute discretion holds all the rights to block a user account, refraining the user from further playing or availing the services offered by GLORY-5, if any user is found or suspected of carrying out or encouraging any suspicious activity, by the auditing team of GLORY-5. \n" +
                "\n" +
                "\u2022 Games of skill are legal, as they are excluded from the ambit of Indian gambling legislations including, the Public Gambling Act of 1867. The Indian Supreme Court in the cases of State of Andhra Pradesh v. K Satyanarayana (AIR 1968 SC 825) and KR Lakshmanan v. State of Tamil Nadu (AIR 1996 SC 1153) has held that a game in which success depends predominantly upon the superior knowledge, training, attention, experience, and adroitness of the player shall be classified as a game of skill. \n" +
                "\n" +
                "\u2022 The Contest (s) described above (across the Fun Features) are games of skill as success of Participants depends primarily on their superior knowledge of the games of cricket, football, kabaddi, volleyball, Basketball, statistics, knowledge of players’ relative form, players’ performance in a particular territory, conditions and/or format (such as ODIs, test cricket and Twenty20 in the cricket fantasy game), attention and dedication towards the Contest(s) and adroitness in playing the Contest(s). The Contest(s) also requires Participants to field well-balanced sides with limited resources and make substitutions at appropriate times to gain the maximum points. \n" +
                "\n" +
                "\u2022 By participating in this Contest(s), each Participant acknowledges and agrees that he/she is participating in a game of skill. \n" +
                "\n" +
                "\u2022 The Contest(s) are open only to persons above the age of 18 years. \n" +
                "\n" +
                "\u2022 The Contest(s) are open only to persons, currently residing in India. \n" +
                "\n" +
                "\u2022 GLORY-5 may, in accordance with the laws prevailing in certain Indian states, bar individuals residing in those states from participating in the Contest(s). Currently, individuals residing in the Indian states of Sikkim, Assam, Odisha, Telangana, and Nagaland may not participate in the paid version of the Contest as the laws of these states bar persons from participating in games of skill where participants are required to pay to enter. In case, amid detailed audits on the end of GLORY-5 any user is found to be a resident of any of the aforementioned states and is found to be hiding it from GLORY-5 at the time of registration then actions mentioned earlier including (but not limited to) block, account reset, account deletion or deactivation shall be initiated. In an event, the state laws of these states change and the residents of these states are allowed to participate in the paid versions of Game of Skills, the users (which were blocked earlier for being a resident of these states) shall be treated as new registrations and in no case, previous winnings shall be considered and any claim of such sort will not be entertained in any case. \n" +
                "\n" +
                "\u2022 Persons who wish to participate must have a valid email address. \n" +
                "\n" +
                "\u2022 Only those Participants who have successfully registered on the GLORY-5.com as well as registered prior to each round in accordance with the procedure outlined above shall be eligible to participate in the Contest and win prizes. \n" +
                "\n" +
                "\u2022 Selection of Winners\n" +
                "\n" +
                "GLORY-5 will select winners on the basis of computation and tabulation of scores in a designated round (which may last anywhere between one day and an entire tournament) of the Contest(s). An individual whose team will score the highest aggregate of points based on the on-field performance of their selected players will be declared as winners by GLORY-5. However, in an event wherein the number of pre-designated winners is defined to be more than one by GLORY-5, then, the winners will be chosen on the basis of increasing order of their aggregate scores at the end of the designated round of the Contest. GLORY-5 shall pre-define the number of winners to be announced and prizes to be distributed on the Contest page before the beginning of the game. Any individual identified by GLORY-5 as creating a team on behalf of other users will be disqualified from the Contest(s). In an event wherein there's a tie between the winners, then the winning users shall be declared as the winners, and the prize shall be equally divided among such Participants. If the users have not complied to the Terms and Conditions defined by GLORY-5, then, GLORY-5 shall not be liable to pay any prize in relation to the use of the GLORY-5.com, Contest, \"Fantasy Rules,\" etc. \n" +
                "\n" +
                "\u2022 Contacting Winners\n" +
                "\n" +
                "GLORY-5 or any other third party organizing Contest(s) as a part of Fun Features provided by GLORY-5 shall contact the winners of the contest(s) through email, mobile or both provided by the user at the time of registration. GLORY-5 shall detail the users about the documents required for the collection of the prize at this stage. As a general practice, winners will be required to provide the following documents:\n" +
                "\n" +
                "Original scanned copy of the User’s PAN card.\n" +
                "\n" +
                "User’s bank account details and proof of the same. \n" +
                "\n" +
                "\u2022 An individual shall not be allowed by GLORY-5 to withdraw his/her prize without the submission and verification of the above mentioned documents within the time-period defined by GLORY-5. By accepting these Terms and Conditions, the users ensure that the documents provided at the time of verification are the true copies of the original documents. \n" +
                "\n" +
                "\u2022 The users are bound to provide adequate and complete details at the time of registration. GLORY-5 shall not be held responsible for any sort of communication, commissions or omissions that might lead to a situation wherein the results may not be communicated to the Winner. \n" +
                "\n" +
                "\u2022 GLORY-5 shall publish the list of the participants on a separate web-page on GLORY-5.com alongside separately informing the winners through e-mail. If ever happens that a winner's name has been displayed on the separate webpage, but he/she has not received any communication e-mail from GLORY-5, then the participant should contact GLORY-5 within the time specified on the webpage. \n" +
                "\n" +
                "\u2022 Verification process \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized only to permit the participants who have successfully completed the verification process by providing the required documents within the time limit specified by GLORY-5 to withdraw/receive their accumulated winnings (or any part thereof). Any request for extending the time-limit for verification and document submission shall not be accepted by GLORY-5. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to disqualify any winner from withdrawing their accumulated winnings post scrutinizing all documents (or any part thereof) on the following grounds:\n" +
                "\n" +
                "GLORY-5 identifies that any of the document or information provided by the user at the time of verification is inadequate, incorrect, misleading, false, fabricated, incomplete or illegible; or\n" +
                "\n" +
                "Participant does not fulfill the Eligibility Criteria specified in Clause 10 above; or\n" +
                "\n" +
                "Any other ground. \n" +
                "\n" +
                "\u2022 Taxes Payable\n" +
                "\n" +
                "The prizes offered to the users declared as winners of the contest(s) organized by GLORY-5 comply with a deduction of tax (\"TDS\") under the Income Tax Act 1961. In an event wherein such tax deductions are made, the winners will be provided TDS certificates. The individuals declared as winners by GLORY-5 agree to pay other applicable tax, including (but not limited to) income tax, gift tax, etc. in respect of the prize money. \n" +
                "\n" +
                "\u2022 Miscellaneous\n" +
                "\n" +
                "Prize distribution and awarding related decisions undertaken by GLORY-5 shall be final, binding and non-contestable. The individuals participating in the paid formats of the Contest(s) ensure that they are not the residents of Sikkim, Assam, Odisha, Telangana, and Nagaland. GLORY-5 hold all the rights to disqualify any participant playing the paid formats of the Contest(s) if identified as a resident of the abovementioned Indian states and forfeit any prize won by such participants. Further GLORY-5 is authorized to suspend or terminate the accounts of such participants at its sole and absolute discretion.\n" +
                "\n" +
                "If in case of a head-to-head contest both the users tend to have same team with the same players as well as the captain & vice-captain, no winner will be declared in such cases and the entry fee of both the users will be refunded to their GLORY-5 wallets. \n" +
                "\n" +
                "\u2022 Free League\n" +
                "\n" +
                "GLORY-5 often organizes Contest(s) in Free League formats wherein it enables its users to win cash prize without paying any entry charges for the leagues. In an event wherein any user wins the free league organized by GLORY-5 the winning amount will get transferred to the user’s account which can be used only for joining further paid leagues organized by GLORY-5. The winning amount cannot be used or withdrawn for any other purpose except for joining other leagues. \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to disqualify a participant alongside forfeiting the prize of such participants at its sole and absolute discretion, in an event wherein an individual below the age of 18 years is identified playing the paid formats of the Contest(s) organized by GLORY-5. In addition to this, GLORY-5 can suspend or terminate the accounts of such participants. GLORY-5 shall not be held liable for the quality, suitability, and merchantability of the prizes as GLORY-5 makes no warranties about the same to the extent permitted by legal authorities. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to modify or change the prizes offered to the participants declared as the winners of Contest(s) organized by GLORY-5, and the participants not raise any claim or question the decision of GLORY-5 related to the prize modification. \n" +
                "\n" +
                "\u2022 GLORY-5 shall not be held responsible or liable for any sort of damage or loss to the prizes occurring at the time of transportation. \n" +
                "\n" +
                "\u2022 Any sort of transportation charges including shipping and courier are to be undertaken by the customers in respect of the prizes. \n" +
                "\n" +
                "\u2022 In case of the cash prizes, the transaction charges fall under the part of the customers. \n" +
                "\n" +
                "\u2022 The prizes given to the participants declared as the winners of Contest(s) organized by GLORY-5 are non-transferable and non-refundable. The winners can neither exchange/redeem those prizes for cash or kind. \n" +
                "\n" +
                "\u2022 In an event wherein an individual declared as the winner of Contest(s) accepts the prize offered by GLORY-5, then affiliates of GLORY-5 and the company itself is authorized to use the name, likeness, voice and comments of the winner for various purposes including (but not limited to) advertising, promotion of the company in any media worldwide, alongside trading without any further permissions or consents and/or additional compensation being given to the participant. By accepting the Terms and Conditions, the winners of the Contest(s) provide their consent for being available for promotional purposes as planned and desired by GLORY-5 without any charge. The promotional and advertising events can include (but not limited to) press events, internal meetings and ceremonies/functions wherein the dates for the promotional events will be solely decided by the team of GLORY-5. \n" +
                "\n" +
                "\u2022 In a rare event wherein GLORY-5 identifies the need of any governmental, statutory or regulatory compliances or approvals for organizing any Contest(s) or the company identifies any on-going Contest(s) breaching the legal guidelines or being prohibited by the legal authorities, then, GLORY-5 hold all the rights to cancel or withdraw such Contest(s) without any prior notice to the participants or the winners. The individuals using the services provided by GLORY-5 agree not to make any claims related to such cancellation or withdrawal in any manner. The employees, directors, affiliates, relatives and family members of GLORY-5, will not be eligible to participate in any Contest(s). \n" +
                "\n" +
                "\u2022 Any sort of disputes associated with Fun Features or services provided by GLORY-5 including the Contest(s), the construction, validity, interpretation and enforceability of these Terms and Conditions, or the rights and obligations of the User(s) (including Participants) or GLORY-5 shall be undertaken by the courts of competent jurisdiction at Jaipur. All sort of disputes, issues, and questions shall be addressed by the court competent jurisdiction in alignment with the laws of the Republic of India. \n" +
                "\n" +
                "\u2022 In a circumstance wherein a legal dispute arises (legal issue or question) between two parties, the party raising the dispute is bound to provide written notification (\"Notification\") to the other party involved in the dispute. The parties involved in the dispute should initially focus on resolving the issue post receiving the Notification. However, if both the parties fail to resolve the dispute through mutual discussion within 15 days of receiving the Notification, the dispute shall be resolved by arbitration. \n" +
                "\n" +
                "\u2022 The place of arbitration shall be Jaipur, India. All arbitration proceedings shall be conducted in English and in accordance with the provisions of the Arbitration and Conciliation Act, 1996, as amended from time to time. \n" +
                "\n" +
                "\u2022 During the arbitration process, the parties involved in the dispute will be complied to follow the arbitration award. Additionally, the parties involved in the disputes will have to bear costs of arbitration on their own and will have to equally share the fees of the arbitrator unless stated otherwise by the arbitral tribunal. The arbitrator is allowed to pass interim orders and awards, including the orders for specific performance and such orders would be enforceable in competent courts. The arbitrator is bound to provide a rational award. \n" +
                "\n" +
                "\u2022 The users agree to access and use the Fun Features provided by GLORY-5 voluntarily and at their sole risk. Further, accepting not to hold GLORY-5 responsible or liable on account of any loss or damage sustained including (but not limited to) any accident, injury, death, loss of property by Users or any other person or entity during the course of access to the Fun Features (including participation in the Contest(s)) or as a result of acceptance of any prize. \n" +
                "\n" +
                "\u2022 The individuals using the services or Fun Features provided by GLORY-5 reimburse GLORY-5, and/ or any of its directors, employees, partners, associates, and licensors, from and against all the responsibility, liability, cost, loss or expense faced by the user due to their access to the Fun Features including (but not limited to) personal injury and damage to property and whether direct, indirect, consequential, foreseeable, due to some negligent act or omission on their part, or otherwise. \n" +
                "\n" +
                "\u2022 GLORY-5 shall not be held responsible either jointly or individually, for any sort of errors or omissions, neither on behalf of itself nor on behalf of third parties in terms of the prizes. \n" +
                "\n" +
                "\u2022 By accepting these terms and conditions the user accepts that he/she shall be held solely responsible for any consequences arising due to an event wherein a user carries out any sort of illegal act due to the non-conformity with these Terms and Conditions and other rules and regulations in relation to Fun Features, including the provision of an incorrect address or other personal details. \n" +
                "\n" +
                "\u2022 The users provide their consent to defend, and hold GLORY-5 harmless from any third party/entity/organization claims arising due to, or associated with user’s involvement with GLORY-5.com or his/her participation in any Contest(s) organized on GLORY-5 platform. \n" +
                "\n" +
                "\u2022 The individuals using the Fun Features and services provided by GLORY-5, by accepting these Terms and Conditions agree that GLORY-5 shall not be held responsible alongside waiving and releasing each and every right or claim, all actions, causes of actions (present or future) each of them has or may have against GLORY-5, its respective agents, directors, officers, business associates, group companies, sponsors, employees, or representatives for all and any injuries, accidents, or mishaps (whether known or unknown) or (whether anticipated or unanticipated) faced due to the Fun Features or associated with the Contests or the prizes of the Contests. \n" +
                "\n" +
                "\u2022 To the extent permitted under law, neither GLORY-5 nor its parent/holding company, subsidiaries, affiliates, directors, officers, professional advisors, employees shall be responsible for the deletion, the failure to store, the misdelivery, or the untimely delivery of any information or material. \n" +
                "\n" +
                "\u2022 In alignment to the legal bodies, GLORY-5 shall not be held responsible for any harm faced due to downloading or accessing any information or material, the quality of servers, games, products, Fun Features or sites, cancellation of competition and prizes. GLORY-5 does not hold any responsibility if a User pays for access to one of GLORY-5's Fun Features and the user shall not be provided any refund as a result of, any inaccessibility that is caused by GLORY-5's maintenance on the servers or the technology that underlies our sites, failures of GLORY-5's service providers (including telecommunications, hosting, and power providers), computer viruses, natural disasters or other destruction or damage of our facilities, acts of nature, war, civil disturbance, or any other cause beyond our reasonable control. Further, GLORY-5 disclaims any sort of warranty for the content provided on GLORY-5.com(s). GLORY-5.com(s) content is distributed on an \"as is, as available\" basis. \n" +
                "\n" +
                "\u2022 The users are solely responsible for any sort of material accessed, downloaded or otherwise obtained through GLORY-5.com and holds the sole responsibility for any potential damage to their computer system or loss of data that occurs as a result of downloading or accessing any such material. \n" +
                "\n" +
                "\u2022 GLORY-5 shall make best endeavors to ensure that the GLORY-5.com(s) is error-free and secure, however, neither GLORY-5 nor any of its partners, licensors or associates makes any warranty that:\n" +
                "\n" +
                "the GLORY-5.com(s) will meet Users’ requirements,\n" +
                "\n" +
                "GLORY-5.com(s) will be uninterrupted, timely, secure, or error-free\n" +
                "\n" +
                "the results that may be obtained from the use of GLORY-5.com(s) will be accurate or reliable; and\n" +
                "\n" +
                "the quality of any products, Fun Features, information, or other material that Users purchase or obtain through GLORY-5com(s) will meet Users’ expectations.\n \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to rectify any errors identified in the determination of Winners or in the transfer of amounts to a User's account, using various method as it deems fit, including (but not limited to) through a set-off of the mistaken payment from amounts due to the User or deduction from the User's account of the amount of mistaken payment. In such events, GLORY-5 agrees to notify the user about the error and the rectification measure adopted to address the error. \n" +
                "\n" +
                "\u2022 Complying to the legal bodies and authorities neither GLORY-5 nor its partners, licensors or associates disclaims any sort of responsibility for any direct, indirect, incidental, special, or consequential damages arising out of the use of or inability to use our sites, even if we have been advised of the possibility of such damages. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to cancel any Fun Features, events or Contest(s) requiring specific permission or authority from any statutory authority or any state or the central government, or the board of directors in an event wherein such permission or authority is either not obtained or denied either before or after the organization of the relevant Fun Features, events or Contest(s). \n" +
                "\n" +
                "\u2022 By accepting these Terms and Conditions, the users agree not to make any demands, or claims in the event of suspension or closure of any Services, events or Contests organized/provided by GLORY-5. \n" +
                "\n" +
                "\u2022 In alignment with certain legislation, GLORY-5 is bound to notify the users regarding various events. In this regard, the users by accepting these Terms and Conditions provide their consent that such notification issued and posted by GLORY-5 on either GLORY-5.com or conveyed through the e-mail provided by the customer while registration, will be considered adequate and authorized, and will be effective upon the customers. Further, GLORY-5 shall not be held responsible for any loss of information or failing to notify the user, if the user fails to provide adequate and accurate information at the time of registration. \n" +
                "\n" +
                "\u2022 GLORY-5 shall not be held responsible for delay or failure in notifying the users, organizing/providing services, Fun Features and Contest(s) because of outside reasons considered to be out of the reach of GLORY-5, including (but not limited to) any failure to perform due to unforeseen circumstances or cause beyond GLORY-5's control such as acts of God, war, terrorism, riots, embargoes, acts of civil or military authorities, fire, floods, accidents, network infrastructure failures, strikes, or shortages of transportation facilities, fuel, energy, labor or materials or any cancellation of any match to which a Contest relates. If such an event ever happens then, GLORY-5 holds all the rights to cancel any related Contest(s) and to process an appropriate refund for all Participants. \n" +
                "\n" +
                "\u2022 GLORY-5’s failure to exercise or enforce any right or provision of these Terms and Conditions shall not constitute a waiver of such right or provision. \n" +
                "\n" +
                "\u2022 By accepting these Terms and Conditions, the users provide their consent that regardless of any statute or law to the contrary, any claim or cause of action faced due to or associated with the use of GLORY-5.com or these Terms must be filed within thirty (30) days of such claim or cause of action arising or be forever barred. \n" +
                "\n" +
                "\u2022 These Terms and Conditions, including all terms, conditions, and policies that are incorporated herein by reference, constitute the entire agreement between the User(s) and GLORY-5 Fantasy Sports Private Limited and govern your use of the GLORY-5.com, superseding any prior agreements that any User may have with GLORY-5 Gaming Private Limited. \n" +
                "\n" +
                "\u2022 In an event wherein any part of these Terms and Conditions defined by GLORY-5 is identified to be indefinite, invalid, or otherwise unenforceable, the rest of these Terms and Conditions shall continue in full force. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to moderate, restrict or ban the use of the GLORY-5.com, by any particular user or generally, in alignment to the GLORY-5’s policy/policies from time to time, at its sole and absolute discretion and without any notice. \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to permanently close or temporarily suspend any Fun Features (including any Contest(s)). \n" +
                "\n" +
                "\u2022 All your Transaction history (including but not limited to Amount Added, Winnings, League Joined, etc.) will only be available for the last 15 days. In case you’ve any query related to any of your transaction please reach our support team within 7 days of occurrence of the issue. \n" +
                "\n" +
                "\u2022 Any amount once added to the GLORY-5 account of user can't be refunded back to the user in any case, and the amount can be used to join the leagues, fun features and Contest(s) organized by GLORY-5. \n" +
                "\n" +
                "\u2022 The individuals using the services and Fun Features provided by GLORY-5 are not permitted to cancel any transaction which has been once confirmed. However, GLORY-5 is authorized to permit any user to cancel a transaction and refund the transaction amount paid by the user at its sole and absolute discretion, in an event :If the User sends a written request to GLORY-5 from the registered email Id to cancel such payment; or If the payment is made for participation in the paid version(s) of the Contest(s), the cancellation request must be received at least 2 days prior to the commencement of the round in respect of which the payment is made; GLORY-5 shall not be liable to refund any amount thereafter. \n" +
                "\n" +
                "\u2022 GLORY-5 reserves the rights to refund the transaction amount to the user in the abovementioned case at its sole and absolute discretion post deducting applicable cancellation charges and taxes. Further, amid the transaction process, the users should take a note of the additional terms and conditions which significantly govern the transaction. In an event wherein the clauses defined in additional terms and conditions conflict with the present Terms and Conditions defined by GLORY-5, the additional terms, and conditions associated with transactions shall prevail. The refund is only applicable to the amount used to join any league and shall be refunded to the GLORY-5 wallet source, as deducted. \n" +
                "\n" +
                "\u2022 The users are bound to comply with the Promotion Terms defined by GLORY-5 in respect of such Promotion (\"Promotion Terms\") and these Standard Terms while participating in any sort of promotions. Further, the users while participating in any Promotion accepts and agrees to comply with terms and conditions, and privacy policies of the GLORY-5. \n" +
                "\n" +
                "\u2022 The Promotions are only open to users in India. Participation in the Promotions by proxy is not permitted. \n" +
                "\n" +
                "\u2022 Participation in the Promotions is voluntary. \n" +
                "\n" +
                "\u2022 Participation in one Promotion does not guarantee that such user will be eligible to participate in another Promotion. \n" +
                "\n" +
                "\u2022 An individual using the services and Fun Features provided by GLORY-5 shall participate and avail the Promotions offered by GLORY-5 only through one account. A user already registered with GLORY-5 is not permitted to create a new account or use various accounts for participating in a Promotion. \n" +
                "\n" +
                "\u2022 The individuals using the services provided by GLORY-5, intending to participate in a Promotion may be required to verify their mobile number and other account details in alignment with the Promotion Terms for such Promotion. \n" +
                "\n" +
                "\u2022 The individuals willing to participate in the Promotion of GLORY-5 with their numbers registered on the National Do Not Call Registry (“NDNC Registry”), need to de-register their numbers from the NDNC Registry until the completion of such Promotion (including the delivery of Bonus Amount (if any) or the free-entry (if any) under such Promotion). GLORY-5 shall not be held responsible for any loss or damage in the event of notification failure to the users from GLORY-5 arising due to the participant’s association with NDNC list. The users intending to participate in the Promotion with their numbers registered on to the NDNC list shall not be authorized to make any claim or raise any complaints against GLORY-5 if they do or do not receive any call or SMS with respect to their participation and all other matters pertaining to a Promotion. \n" +
                "\n" +
                "\u2022 The verification process may require you to submit personal information about yourself. The users willing to participate in the Promotion provide their consent to receive communication from GLORY-5. Any sort of user information including identity and contact details collected by GLORY-5 amid the registration, verification or during the user usage of GLORY-5.com shall be subject to GLORY-5’s Privacy Policy. \n" +
                "\n" +
                "\u2022 GLORY-5 reserves rights to disqualify any user at its sole and absolute discretion from a Promotion in an event wherein the user is identified to be engaged in any inadequate, illegal, or unlawful conduct (with regard to any of the Promotions or otherwise). \n" +
                "\n" +
                "\u2022 The users can use the Bonus Amount (if any) deposited in their accounts as a part of GLORY-5 promotional offers for paying 5% of the entry fee to join various cash contests such as Hot contests, Contests for Champions & Head-To-Head (leagues up to 99 members)and 25% of the entry fee to join Mega contests with 100 or more members. However, the Bonus Amount (if any) present in the accounts of the users cannot be withdrawn or transferred to any other cash balance section of wallet held by the user with GLORY-5 or to any third party account or to any bank/payment instrument account. The bonus amount (if any) shall expire at the end of 14 days from the date of credit of the bonus amount (if any) w.e.f 8th August, 2019. \n" +
                "\n" +
                "\u2022 The deposit of the Bonus Amount (if applicable) or the grant of the free-entry (if any) shall be at the sole discretion of GLORY-5 and shall be subject to the user’s compliance with these Standard Terms and the applicable Promotion Terms. GLORY-5 is authorized to change or substitute the Bonus Amount (if any) or free-entry (if any) provided to the users under a Promotion at any time without any prior notice to the users. The users are not permitted to substitute Bonus Amount (if any) or free-entry (if any) for other items or exchange for cash. \n" +
                "\n" +
                "\u2022 In an event wherein GLORY-5 identifies or believes that a user participating in the Promotion has violated the Standard Terms, Promotion Terms or the terms and conditions of the GLORY-5 fantasy game(s), GLORY-5 is authorized to withhold or forfeit the benefits of a Promotion (including a free-entry or Bonus Amount due to a participant or any prizes/winnings earned by the participant by using such benefits). \n" +
                "\n" +
                "\u2022 Mere participation in a Promotion does not entitle the participant to receive any free-entry or Bonus Amount(s) indicated as a prize under such Promotion. \n" +
                "\n" +
                "\u2022 GLORY-5's decision regarding the winners and prizes in the context of the Promotion(s) will be final and binding. Further, GLORY-5 shall not entertain any sort of correspondence, objection, complaints, etc. in this regard. \n" +
                "\n" +
                "\u2022 Each Promotion cannot be clubbed with any of other contest/offer/promotion that is running simultaneously and organized or conducted by GLORY-5. \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to change/modify/or withdraw any of the Promotions and/or change these Standard Terms and/or the Promotion Terms without any prior notice to the users. \n" +
                "\n" +
                "\u2022 GLORY-5 does not make any commitment, express or implied, to respond to any feedback, suggestion and, or, queries of the participants of the Promotions. \n" +
                "\n" +
                "\u2022 Notwithstanding anything contained herein, the aggregate liability of GLORY-5 to a participating user in relation to any Promotion for any reason whatsoever shall not exceed ₹100 or the joining fee (whichever is lower). \n" +
                "\n" +
                "\u2022 Any sort of dispute or disagreement regarding the Promotion shall be addressed and resolved in the jurisdictions of the courts of Jaipur as all these Promotions shall be governed by the laws of the Republic of India. \n" +
                "\n" +
                "\u2022 GLORY-5 provides you an opportunity to compete with your friends by referring them for joining GLORY-5 through the Refer and Earn Program of GLORY-5. The refer and earn program of GLORY-5 enables you to Refer friends alongside offering you and your referred friends a Cash Bonus of up to Rs.100 in an event wherein you and your referred friend meet the criteria and complete all the steps specified in these terms. The Cash Bonus earned by you and your referred friend will be redeemable to join cash contests and contests through the GLORY-5’s mobile application for the iOS and/or Android mobile devices. The following Terms and Conditions are defined by GLORY-5 for participating in the Refer & Earn program. These Terms and Conditions should be followed by the users as they govern the user’s participation in the Program: \n" +
                "\n" +
                "\u2022 Eligibility\n" +
                "\n" +
                "The users that: (i) have an account registered and a verified gaming account with GLORY-5; (ii) are eligible to participate in the pay-to-play GLORY-5 fantasy Sports game according to the Terms and Conditions defined by GLORY-5; and (iii) have downloaded and installed the Application on their respective mobile devices, are considered as eligible for participating in the Program. GLORY-5 neither encourages nor permits participation in the program through a proxy. \n" +
                "\n" +
                "\u2022 GLORY-5 does not permit any user to register or operate more than one gaming account with GLORY-5. The participation in the Program is voluntary, and no user is allowed to participate in the Program with more than one user account. \n" +
                "\n" +
                "\u2022 The users while participating in the Program provide their consent to abide by these Terms and Conditions of GLORY-5. \n" +
                "\n" +
                "\u2022 The users in order to participate in the Refer & Earn Program need to download and install GLORY-5's mobile application on their mobile devices. The users post downloading and installing the mobile application on their mobile devices will be provided with a unique link or code, which the user can distribute among his/her friends inviting them to join GLORY-5 by creating and registering an account with GLORY-5, and downloading and installing the mobile application of GLORY-5 on their mobile devices. The referred friend (Referent) of the user (Referrer) already registered with GLORY-5, can use the received link or code by either: (i) Clicking on the link, following which the Referent would be landed on to the registration page of GLORY-5 wherein he can create and register an account with GLORY-5, and download and install the mobile application on his/her device; or (ii) the Referent can directly download and install the GLORY-5's mobile application on his/her device, create and register an account with GLORY-5, and enter the unique code shared by the Referrer. \n" +
                "\n" +
                "\u2022 The Cash Bonus provided to the Referent and the Referrer under the Refer & Earn program of GLORY-5 can only be earned by the user and its referred friend in an event they abide by other terms specified in these Terms, including: (i) The referred friend of the user should not be already registered with GLORY-5, (ii) both the user and its referred friend are eligible for participating in the pay-to-play Sports contest organized by GLORY-5 in alignment to the Terms and Conditions defined by GLORY-5, (iii) the referred friend of the user should successfully create, register and verify an account with GLORY-5 using the unique link or code provided by the Referrer; and (iv) the user and his/her referred friend should agree to the license agreement of GLORY-5's mobile app, and should download and install the GLORY-5's mobile application in accordance to their mobile device platforms. The already registered users of GLORY-5 as defined in these Terms and Conditions refers to an individual who presently operates or holds an account registered with GLORY-5 or a user who operated or had an account with GLORY-5 at some point of time. \n" +
                "\n" +
                "\u2022 GLORY-5 neither permits nor encourages an already registered user of GLORY-5 to register a new account under a new name in order to earn Cash Bonus. GLORY-5 is authorized to take adequate actions if a user registering with the Platform is identified to be an existing user of GLORY-5. \n" +
                "\n" +
                "\u2022 By accepting these terms and conditions, the users provide their consent to provide all the information required for the verification of a Referrer/Referent including personal information about the user (Referrer/Referent) and documents identifying the Referrer/Referent. Further, the Referrer provides his/her consent for receiving communication from GLORY-5 alongside permitting GLORY-5 to directly communicate with Referents referred by the already registered user of GLORY-5. Any sort of user information collected by MytTeam11 amid the course of user's use of the Website shall be subject to GLORY-5's Privacy Policy. \n" +
                "\n" +
                "\u2022 Any user won't be allowed to make any withdrawals from his gaming account registered with GLORY-5 until the completion of the verification process of a Referrer/Referent. An already registered user with GLORY-5 or his/her referred friend is permitted to seek voluntary verification by clicking on the 'Verify' tab. However, the Referent can also verify his/her personal and contact information at the time of registration of an account with GLORY-5 through the GLORY-5's mobile application. \n" +
                "\n" +
                "\u2022 Referent Bonus\n" +
                "\n" +
                "The Cash Bonus earned by the referred friend of already registered user with GLORY-5 is subjected to various Terms and Conditions, and the bonus amount earned will be provided to the Referent on the successful completion of the verification process which includes email verification, mobile verification, and PAN card verification. As soon as the Referent registers on GLORY-5 a cash bonus will be credited into the GLORY-5 wallet of the Referent. Further, as soon as the Referent verifies his/her PAN card details, a cash bonus amount will be further credited instantly to his/her GLORY-5 account. In a rare event, if the referred friend of the existing user of GLORY-5 fails to successfully verify his/her personal and contact details within the Verification Period or omits to provide documents requested for such verification, the Referent shall not be eligible for receiving the entitled Referent Bonus (as specified above). \n" +
                "\n" +
                "\u2022 Referrer Bonus\n" +
                "\n" +
                "The already registered user of GLORY-5 for receiving the entitled “Referrer Bonus” must have downloaded and installed the GLORY-5’s mobile application on his/her mobile devices. b) The Credit of the Referrer Bonus is contingent on the Referent's successful verification of the Referent’s personal and contact information: As soon as the Referent successfully verifies his/her personal and contact information which includes email verification, mobile verification and PAN card verification, a cash bonus amount will be credited instantly to the GLORY-5 wallet of the Referrer. However, if the referred friend fails to successfully verify his/her personal and contact details no Cash Bonus will be rewarded to the Referrer either. No cash bonus will be awarded to the referrer on referent registration. \n" +
                "\n" +
                "\u2022 In an event wherein more than 9 friends of the already registered user with GLORY-5 are identified to be unverified (email, mobile number, PAN card or Bank account details unverified), GLORY-5 holds all the rights to decline or reject the withdrawal request of the user and block the user from the Platforms of GLORY-5. \n" +
                "\n" +
                "\u2022 Neither the Referrer nor the Referent is permitted to use the Cash Bonus credited to their gaming accounts for joining any head-to-head contest. Additionally, the Cash Bonus amount credited to the gaming accounts of the Referrer and Referent cannot be withdrawn or transferred to any other cash balance account held by the Referrer/Referent with GLORY-5 or to any third party account or to any bank/payment instrument account. \n" +
                "\n" +
                "\u2022 GLORY-5 holds all the rights to change or substitute the Cash Bonus offered under the Refer & Earn Program without any prior notice to the user. The crediting of Cash Bonus to the gaming accounts of the Referrer and Referent shall be at the sole discretion of GLORY-5 and shall be subject to the Referrer’s/Referent’s alignment with these";

        String strdesc2 = "\u2022 GLORY-5 reserves the right to:\n" +
                "\n" +
                "withhold the deposit of the Bonus Amount; and/or\n" +
                "\n" +
                "forfeit any deposited Bonus Amount to a Referrer/Referent or any prizes/winnings earned by the participant by using such Bonus Amount; and/or\n" +
                "\n" +
                "suspend or deactivate the gaming accounts of the user already registered with GLORY-5 or his/her referred friend in an event GLORY-5 identifies or believes the user to be breaching the Terms and Conditions defined by GLORY-5. \n" +
                "\n" +
                "\u2022 The Referrer/Referent is not subjected to any winnings, prizes or bonus just because of participating in the Program. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to disqualify any Referrer/Referent at its sole and absolute discretion, in an event if GLORY-5 identifies any Referrer/Referent to be engaged in any illegal, inadequate or unlawful conduct. \n" +
                "\n" +
                "\u2022 No correspondence, objection or complaints will be entertained by GLORY-5 regarding the Program, and crediting of Cash Bonus and the decision of GLORY-5 in this regard would be considered as final and binding. \n" +
                "\n" +
                "\u2022 GLORY-5 can suspend or deactivate any user account in an event a user is identified to be involved in any sort of spamming on the website of GLORY-5 or conducting any suspicious on the Platforms of GLORY-5. \n" +
                "\n" +
                "\u2022 This Program cannot be clubbed with any other contests/promotions/programs that are running simultaneously and organized or conducted by GLORY-5. \n" +
                "\n" +
                "\u2022 GLORY-5 is authorized to change, modify, substitute or withdraw the Program and/or these terms and conditions without any prior notice to the users at its sole discretion. \n" +
                "\n" +
                "\u2022 The Terms and Conditions, as applicable to the GLORY-5's fantasy games and services, will apply to and govern the Program. \n" +
                "\n" +
                "\u2022 GLORY-5 does not make any commitment, express or implied, to respond to any feedback, suggestion and, or, queries of the participants (Referrer/Referent) of the Program. \n" +
                "\n" +
                "\u2022 The Terms and Conditions for Refer & Earn Program can be accessed at Refer and Earn T&C \n" +
                "\n" +
                "\u2022 In order to create, register and operate an account with GLORY-5, the users need to verify personal and contact information including (i) Mobile Number, (ii) E-Mail Address, (iii) Pan Card Details; and (iv) Bank Account Details such as, Name, Bank Name, DOB, Branch, IFSC Code, State. \n" +
                "\n" +
                "\u2022 The images provided by the individuals using the services and Fun Features provided by GLORY-5should be scanned copies of the original documents. Further, the image size of the scanned copies of original documents provided by the users for account verification should not exceed 2MB. \n" +
                "\n" +
                "\u2022 By accepting these Terms and Conditions, the users provide their consent to provide adequate and accurate information at the time of verification. GLORY-5 reserves all the rights to reject the verification application of a user in an event wherein the PAN card details and Account details of the user does not match with each other. \n" +
                "\n" +
                "\u2022 The users using the services provided by GLORY-5 should provide the documents demanded by GLORY-5, none of the document alternatives would be accepted for the verification process. Further, the PAN card of a user creating and registering an account with GLORY-5 born before the year 1968 will not be accepted. In an event wherein the user creating and registering an account with GLORY-5 is born before the year 1968, the user will be required to provide a verification prof wherein the user should email an image holding his/her PAN card near his/her face at support@GLORY-5.com \n" +
                "\n" +
                "\u2022 The verification process of GLORY-5 needs 1-3 days for completion. In this regard, all the verification requests can take a time of up to 1-3 working days for completion. The users will be updated by GLORY-5 regarding the completion of the verification process. \n" +
                "\n" +
                "\u2022 Any change in the information provided by the user while registration or after should only be entertained if received in writing with a valid document of change. Users are bound to provide the change in their information to GLORY-5 for updating their records in the system. If the user fails to provide the same, the user shall be held solely responsible for failure in receiving communication updates from GLORY-5. \n" +
                "\n" +
                "\u2022 PAN CARD\n" +
                "\n" +
                "A pan card once uploaded by the user and verified from our end can be deleted anytime if found or suspected to be a forged document.\n" +
                "\n" +
                "In case the documents are found or believed to be fake, the user account can be blocked under “fair play violation”.\n" +
                "\n" +
                "In case a user is found to be a resident of Sikkim, Assam, Odisha, Telangana or Nagaland after the pan card verification, his/her pan card can be deleted anytime even after the verification. \n" +
                "\n" +
                "\u2022 BANK ACCOUNT\n" +
                "\n" +
                "A bank account once uploaded by the user and verified from our end can be deleted anytime if found or suspected to be a forged document.\n" +
                "\n" +
                "In case the documents are found or believed to be fake, the user account can be blocked under “fair play violation”.\n" +
                "\n" +
                "The withdrawals can be withheld if the uploaded documents are found or believed to be fake amid or after the bank account verification.\n" +
                "\n" +
                "In case a user is found to be a resident of Sikkim, Assam, Odisha, Telangana or Nagaland after the bank account verification, his/her bank account can be deleted anytime even after the verification.\n" +
                "\n" +
                "All state bank accounts have been merged to SBI, thus, make sure the IFSC entered by you should be the new IFSC.\n" +
                "\n" +
                "It is mandatory that the bank account number on the verification image should match exactly with the account number entered in details. The “0s” mandatory to be mentioned in the details. \n" +
                "\n" +
                "\u2022 The winnings, cash bonuses and prizes are subjected to various taxes. In an event, the winning amount of a user crosses the mark of ₹10,000, a 31.20% taxation charge will be imposed on the winning of the user. \n" +
                "\n" +
                "\u2022 The withdrawal request of a user is processed within 2-3 days (herein the working hours refers to bank’s working hours) excluding the date the request is made. In case, your withdrawal request is not processed within 2-3 days after the date the request is made; please check your registered email address provided by you at the time of registration for any communications from GLORY-5. In an event, any GLORY-5 user is found to be violating the Fair Play Terms, or in general Terms & Conditions of the website then GLORY-5 at its sole and absolute discretion holds all the rights to take strict measures against the users including (but not limited to):\n" +
                "\n" +
                "In an event a user is found having more than 9 referred friends whose mobile number, email address or PAN card details are not verified, then the withdrawals requests of the user can be put on hold, or his/her user account can be blocked permanently.\n" +
                "\n" +
                "In an event, a user is found to have more than 9 friends whose details (mobile number, email address or PAN card details) are unverified and the user is asked to get the details (mobile number, email address, PAN card and Bank account details) of their referred friends verified by the executives of GLORY-5, then the withdrawal request put on hold shall not be refunded to the GLORY-5 wallet of the user & shall continue to be on hold.\n" +
                "\n" +
                "In an event a user is found to be having more than single id registered on GLORY-5 or multiple ids registered under the same name then the withdrawal request of such users can either be put on hold, or their user accounts can be blocked refraining them from playing further on GLORY-5.\n" +
                "\n" +
                "Any user if ever found to be violating the Fair Play Terms of GLORY-5 which can be visited on “Fair Play,” will be either blocked from playing further on GLORY-5 or his/her withdrawal requests will be put on hold for violating the Fair Play terms.\n" +
                "\n" +
                "The withdrawals of users found to be creating a fake refer chain by creating multiple ids under the same name to earn the Refer & Earn bonus will be blocked from using GLORY-5 services or Fun Features provided by the platform alongside putting their withdrawal requests on hold.\n" +
                "\n" +
                "In an event, any user is identified to be a resident of Sikkim, Assam, Odisha, Telangana or Nagaland amid the GLORY-5 audits and had hidden this fact while registering at the platform will be blocked, and his/her withdrawal requests will be put on hold.\n" +
                "\n" +
                "GLORY-5 could put the withdrawal requests of users violating the Fair Play terms of GLORY-5 or can block the user account at any stage amid its audits, no matter if the withdrawal requests of the user had been accepted previously.\n" +
                "\n" +
                "In case the withdrawal request of a user is kept on hold, and GLORY-5 at its sole & absolute discretion decides to process the withdrawal request of that user after he/she agrees to abide by the Fair Play Terms of GLORY-5 then such withdrawals shall take 5-7 working days to process.\n" +
                "\n" +
                "In case, we suspect any account to be violating our fair play or verification terms already stated in the Terms & Conditions of GLORY-5; we hold all the rights to verify the user at any time. GLORY-5 holds all the rights to video call any user at any point of time to validate & verify his/her identity. If the video call done as a part of the verification process is found to be negative, then the user account shall be blocked & his withdrawal shall be put on hold. GLORY-5 is free by all means to verify & validate the identity of a user at any point of time as per its will/wish.\n" +
                "\n" +
                "In case, a user is using a single device for creating multiple accounts on GLORY-5, it should be ensured by the user that all the mandatory verification formalities including mobile, email, PAN card & Bank Account details verification has been done by the user for all the accounts created. Abiding by the said process will help such users, who create multiple accounts on a single device, in terms of smooth withdrawals on GLORY-5. \n" +
                "\n" +
                "\u2022 A minimum amount of ₹150 can be withdrawn per day \n" +
                "\n" +
                "\u2022 A maximum amount of ₹10000 can be withdrawn per day \n" +
                "\n" +
                "\u2022 People who have added more than ₹5000 (in total since registration) to their GLORY-5 wallet will be considered eligible for the Instant Cash Withdrawal \n" +
                "\n" +
                "\u2022 The PAN Card and Bank Account of the user requesting for Instant Withdrawal should be verified with GLORY-5 \n" +
                "\n" +
                "\u2022 The user’s name mentioned on the PAN card uploaded on GLORY-5 for verification, and the Profile name of user should be same. \n" +
                "\n" +
                "\u2022 The Instant Withdrawal process will be done via IMPS method of transfer. Thus, the withdrawal might take 2-3 hours for the amount to reflect in your bank account \n" +
                "\n" +
                "\u2022 GLORY-5, at its sole and absolute discretion, can hold or deactivate the Instant Withdrawal service anytime without any prior information to the user \n" +
                "\n" +
                "\u2022 The user requesting an Instant Withdrawal should not have any Violation History on GLORY-5 \n" +
                "\n" +
                "\u2022 In case, you have violated the Fair Play Terms of GLORY-5, after a number of successful Instant Withdrawals, the next & forthcoming Instant Withdrawal request by you will be put on hold & will continue to be on hold until an appropriate explanation is received. Such cases shall be treated in compliance with the “Fair Play Violation.” \n" +
                "\n" +
                "\u2022 In case of a dispute, disagreement or concern between GLORY-5 and its payment partner, GLORY-5 holds all the right to put the withdrawal requests and amounts on hold until the resolution.";


//        TextView textdesc = (TextView) findViewById(R.id.textdesc);
//        textdesc.setText(strdesc);
//
//        TextView textdesc2 = (TextView) findViewById(R.id.textdesc2);
//        textdesc2.setText(strdesc2);
        Activity_Terms();
    }


    private void Activity_Terms() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");

        wallatedialog = new ProgressDialog(Activity_TC.this);
        wallatedialog.setMessage("Please Wait ...");
//        wallatedialog.setIndeterminate(false);
        wallatedialog.setCancelable(false);
        wallatedialog.show();


        Api api = ApiClient.getClient().create(Api.class);
        Call<com.glory.apk.Model.TermsModel.Example> login = api.TermsCall("terms_conditions");
        login.enqueue(new Callback<com.glory.apk.Model.TermsModel.Example>() {
            @Override
            public void onResponse(Call<com.glory.apk.Model.TermsModel.Example> call, Response<com.glory.apk.Model.TermsModel.Example> response) {
                wallatedialog.dismiss();

                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse() == null) {

                    } else if (response.body().getResponse().getType().equals("data_found")) {

                        textdesc2.setText(response.body().getData().get(0).getContentDescription());
                        texttitle.setText(response.body().getData().get(0).getContentTitle());
                        xTvtitle.setText(response.body().getData().get(0).getContentTitle());

                    } else {
                        Toast.makeText(Activity_TC.this, response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("testing", "error");
                    wallatedialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getType(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<com.glory.apk.Model.TermsModel.Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.valueOf(t.getLocalizedMessage()), Toast.LENGTH_SHORT).show();
                wallatedialog.dismiss();

            }
        });
    }


}
