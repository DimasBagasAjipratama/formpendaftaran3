package com.example.formpendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog picker;
    EditText eText;
    Button btnGet;

    RadioGroup radioGroupJK;
    RadioButton radioButtonJK;
    RadioButton radioButtonAgama;

    EditText firstName;
    EditText lastName;
    EditText tempatL;
    EditText tglL;
    EditText alamat;
    EditText telp;
    EditText email;
    EditText pass;
    EditText rePass;

    boolean emailPass;
    boolean passPass;

    private String vlFirstname;
    private String vlLastName;
    private String vlTempatL;
    private String vlTGL;
    private String vlAlamat;
    private String vlJK;
    private String vlAgama;
    private String vlTelp;
    private String vlEmail;
    private String vlPass;
    private String vlRePass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tglL=(EditText) findViewById(R.id.inputtanggallahir);
        tglL.setInputType(InputType.TYPE_NULL);
        tglL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tglL.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        Button back = (Button) findViewById(R.id.btnk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                System.exit(0);
            }
        });



        firstName = (EditText) findViewById(R.id.inputnamadepan);
        lastName = (EditText) findViewById(R.id.inputnamabelakang);
        tempatL = (EditText) findViewById(R.id.inputtempatlahir);
        tglL = (EditText) findViewById(R.id.inputtanggallahir);
        alamat = (EditText) findViewById(R.id.editalamat);
        telp = (EditText) findViewById(R.id.edittelepon);
        email = (EditText) findViewById(R.id.editemail);
        pass = (EditText) findViewById(R.id.editpassword);
        rePass = (EditText) findViewById(R.id.enteragainpassword);

        Button buttonDaftar = (Button) findViewById(R.id.btns);
        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //JK
                int selectedIdJK = radioGroupJK.getCheckedRadioButtonId();
                radioButtonJK = (RadioButton) findViewById(selectedIdJK);
                if (radioButtonJK == null) {
                    Toast.makeText(getApplicationContext(), "Jenis Kelamin harus diisi", Toast.LENGTH_LONG).show();
                }



                //validate
                if (firstName.getText().toString().length() == 0) {
                    firstName.setError("Nama depan Harus diisi");
                } else {

                }
                if (lastName.getText().toString().length() == 0) {
                    lastName.setError("Nama belakang Harus diisi");
                } else {

                }
                if (tempatL.getText().toString().length() == 0) {
                    tempatL.setError("Tempat Lahir Harus diisi");
                } else {

                }
                if (tglL.getText().toString().length() == 0) {
                    tglL.setError("Tanggal Harus diisi");
                    System.out.println(" "+tglL.getText().toString());
                } else {

                    //System.out.println("nyaa");
                    System.out.println("" + tglL.getText().toString());
                }
                if (alamat.getText().toString().length() == 0) {
                    alamat.setError("Alamat Harus diisi");
                } else {

                }

                if (telp.getText().toString().length() == 0) {
                    telp.setError("No Telp Harus diisi");
                } else {

                }

                if(!isValidEmail(email.getText().toString())) {
                    email.setError("Error");
                    emailPass = false;
                } else {
                    emailPass = true;
                }
                if (pass.getText().toString().length()<8) {
                    pass.setError("Password minimal 8 digit");
                    passPass = false;
                } else if (passwordCharValidation(pass.getText().toString())) {
                    pass.setError("Password minimal memiliki Uppercase, Lowercase, Number, Symbol");
                    passPass = false;
                } else {
                    passPass = true;
                }
                String passT =   pass.getText().toString();
                String rePassT =  rePass.getText().toString();
                if (!passT.equals(rePassT)) {
                    rePass.setError("Password tidak sesuai");

                } else {

                }

                //validateAll
                System.out.println("tes: "+ firstName.getText().toString().isEmpty());
                if (!firstName.getText().toString().isEmpty() &&
                        !lastName.getText().toString().isEmpty() &&
                        !tempatL.getText().toString().isEmpty() &&
                        !tglL.getText().toString().isEmpty() &&
                        !alamat.getText().toString().isEmpty() &&
                        !telp.getText().toString().isEmpty() &&
                        emailPass &&
                        passPass &&
                        pass.getText().toString().equals(rePass.getText().toString()) &&
                        radioButtonJK != null

                ){
                    //empty
                    System.out.println("nyaa");
                    vlFirstname = firstName.getText().toString();
                    vlLastName = lastName.getText().toString();
                    vlTempatL = tempatL.getText().toString();
                    vlTGL = tglL.getText().toString();
                    vlAlamat = alamat.getText().toString();
                    vlJK = radioButtonJK.getText().toString();
                    vlTelp = telp.getText().toString();
                    vlEmail = email.getText().toString();
                    vlPass = pass.getText().toString();
                    vlRePass = rePass.getText().toString();
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Konfirmasi");
                    alertDialog.setMessage("Apakah data yang anda masukkan sudah benar?");
                    alertDialog.setIcon(R.mipmap.ic_launcher_round);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "TIDAK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YA",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setIcon(R.mipmap.ic_launcher_round);
                                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);

                                    //declare
                                    TextView tFirstName = customLayout.findViewById(R.id.inputnamadepan);
                                    TextView tLastName = customLayout.findViewById(R.id.inputnamabelakang);
                                    TextView tTtl = customLayout.findViewById(R.id.inputtanggallahir);
                                    TextView tAlamat = customLayout.findViewById(R.id.editalamat);
                                    TextView tJk = customLayout.findViewById(R.id.jensklmn);
                                    TextView TTelp = customLayout.findViewById(R.id.edittelepon);
                                    TextView tEmail = customLayout.findViewById(R.id.editemail);

                                    Button tOK = customLayout.findViewById(R.id.btns);
                                    Button tExit = customLayout.findViewById(R.id.btnk);

                                    //addData
                                    tFirstName.setText(vlFirstname);
                                    tLastName.setText(vlLastName);
                                    tTtl.setText(vlTempatL + ", "+ vlTGL);
                                    tAlamat.setText(vlAlamat);
                                    tJk.setText(vlJK);
                                    TTelp.setText(vlTelp);
                                    tEmail.setText(vlEmail);

                                    //buttonFuction
                                    tOK.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alertDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    tExit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            System.exit(0);
                                        }
                                    });

                                    alertDialog.setView(customLayout);

                                    alertDialog.show();
                                }
                            });
                    alertDialog.show();
                } else {
                    System.out.println("eee.. yada..");

                }
            }
        });
    }

    public static boolean passwordCharValidation(String passwordEd) {
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordEd);
        if (!passwordEd.matches(".*\\d.*") || !matcher.matches()) {
            return true;
        }
        return false;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}

