package com.glory.apk.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.apk.Model.PancardUpload.PancardExample;
import com.glory.apk.R;
import com.glory.apk.Retrofit.Api;
import com.glory.apk.Retrofit.ApiClient;
import com.glory.apk.Utilites.StaticUtils;
import com.glory.apk.Utilites.sharedPrefs;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyPanCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText xEtUserName, xEtPanNumber, xEtDateOfBirth;
    Spinner xStatesSpinner;
    Button butsubmit, xBtnUploadPanImage;
    String[] country = {"Select State","Andhra Pradhesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};
    private int GALLERY = 1, CAMERA = 2;
    String strspinner,panid,viewuseremail;
    Bitmap bitmap;
    Boolean isPermissionGranted = false;
    Dialog stateSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_pan_card);
        stateSelection = new Dialog(VerifyPanCardActivity.this);
        stateSelection.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mInitWidgets();
    }

    private void mInitWidgets() {
        xEtUserName = findViewById(R.id.xEtUserName);
        xEtPanNumber = findViewById(R.id.xEtPanNumber);
        xEtDateOfBirth = findViewById(R.id.xEtDateOfBirth);
        xStatesSpinner = findViewById(R.id.xStatesSpinner);
        butsubmit = findViewById(R.id.butsubmit);
        xBtnUploadPanImage = findViewById(R.id.xBtnUploadPanImage);
        xStatesSpinner.setOnItemSelectedListener(this);
        xEtUserName.setText(getIntent().getStringExtra(StaticUtils.PANNAME));
        xEtPanNumber.setText(getIntent().getStringExtra(StaticUtils.PANNUMBER));
        xEtDateOfBirth.setText(getIntent().getStringExtra(StaticUtils.PANDOB));
        panid=getIntent().getStringExtra(StaticUtils.PANID);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        xStatesSpinner.setAdapter(aa);

        butsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
                strspinner = xStatesSpinner.getSelectedItem().toString();

                Matcher matcher = pattern.matcher(xEtPanNumber.getText().toString());
                if (bitmap == null) {
                    Toast.makeText(getApplicationContext(), "Please select Image", Toast.LENGTH_LONG).show();
                } else if (xEtUserName.getText().toString().length() == 0 || xEtUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();

                } else if (xEtPanNumber.getText().toString().length() == 0 || xEtPanNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter Pancard number", Toast.LENGTH_LONG).show();
                } else if (xEtDateOfBirth.getText().toString().length() == 0 || xEtDateOfBirth.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter date Of birth", Toast.LENGTH_LONG).show();
                } else if (strspinner.equals("Select State")) {
                    Toast.makeText(getApplicationContext(), "Please select the state", Toast.LENGTH_LONG).show();
                }  else {
                    matchesList();
                }


            }
        });

//         else if (matcher.matches()) {
//            Toast.makeText(getApplicationContext(), "Please enter correct pancard number", Toast.LENGTH_LONG).show();
//        }
//
        xBtnUploadPanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (requestMultiplePermissions()) {
                    showPictureDialog();

                }else {
                }

            }
        });


        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(current)) {
                    String clean = charSequence.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int j = 2; j <= cl && j < 6; j += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    xEtDateOfBirth.setText(current);
                    xEtDateOfBirth.setSelection(sel < current.length() ? sel : current.length());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        };

        xEtDateOfBirth.addTextChangedListener(tw);

    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    xBtnUploadPanImage.setBackgroundColor(getColor(R.color.greencolor));


                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            xBtnUploadPanImage.setBackgroundColor(getColor(R.color.greencolor));

        }
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void matchesList() {

        Log.e("testing", "strregisteredtoken = " + "matchesList");
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(VerifyPanCardActivity.this);
        pDialog.setMessage("Please Wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        String image = convertToString();
        Api api = ApiClient.getClient().create(Api.class);
        viewuseremail = sharedPrefs.getPreferences(getApplicationContext(), sharedPrefs.Pref_userId);

        Call<PancardExample> login = api.PancardImageUpload(image, xEtPanNumber.getText().toString(), xEtUserName.getText().toString(), xEtDateOfBirth.getText().toString(), viewuseremail, strspinner,panid);
        login.enqueue(new Callback<PancardExample>() {
            @Override
            public void onResponse(Call<PancardExample> call, Response<PancardExample> response) {
                pDialog.dismiss();
                Log.e("testing", "status = " + response.body());

                //       Log.e("testing", "status = " + response.body().getStatus());
                Log.e("testing", "response = " + response.body().getResponse().getMessage());
                //  Log.e("testing","response = "+response.body().getData().getPageContent());
                Log.e("testing", "response = " + response.body());
                if (response.body().getStatus() == null || response.body().getStatus().length() == 0) {

                } else if (response.body().getStatus().equals("success")) {
                    if (response.body().getResponse().getType().equals("verify_success")) {
//                        Log.e("testing", "dateumList.size = " + dateumList.size());
//                        dateumList=response.body().getData();
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                        pDialog.dismiss();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }

                } else {
                    Log.e("testing", "error");
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PancardExample> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("testing", "response = " + t.getLocalizedMessage());

                pDialog.dismiss();

            }
        });
    }

    private Boolean requestMultiplePermissions() {
        isPermissionGranted = false;
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted

                        if (report.areAllPermissionsGranted()) {

                            isPermissionGranted = true;
                            //  Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        } else {
                            isPermissionGranted = true;

                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
        return isPermissionGranted;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String state = xStatesSpinner.getSelectedItem().toString();
        if (state.equals("Assam")||state.equals("Odisha")||state.equals("Nagaland")||state.equals("Telangana")||state.equals("Sikkim")){
            stateSelection.setContentView(R.layout.select_state_dialog);
//        dialog!!.setCancelable(false)
            stateSelection.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView textOk = (TextView) stateSelection.findViewById(R.id.textOk);

            ImageView imgcancel = (ImageView) stateSelection.findViewById(R.id.xIvCancel);

            textOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stateSelection.cancel();
                    xStatesSpinner.setSelection(0);
                }
            });
            imgcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stateSelection.cancel();
                    xStatesSpinner.setSelection(0);

                }
            });

            Window window = stateSelection.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            stateSelection.setCancelable(false);
            stateSelection.show();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
