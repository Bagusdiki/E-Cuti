package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.PengajuanCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.model.JenisCuti;
import com.cuti.online.karyawan.model.User;
import com.cuti.online.karyawan.presenter.PengajuanCutiPresenter;
import com.cuti.online.karyawan.utils.Sharedpreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PengajuanCutiActivity extends AppCompatActivity implements PengajuanCutiView {
    Spinner spCuti;
    EditText noTelp, nama, tglMulai, tglSelesai;
    Button simpan;
    ArrayList<String> jenisCuti = new ArrayList();
    PengajuanCutiPresenter presenter;
    String cuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_cuti);
        initPresenter();
        spCuti = findViewById(R.id.spJenisCuti);
        noTelp = findViewById(R.id.etTelpCuti);
        nama = findViewById(R.id.etNamaLengkapCuti);
        tglMulai = findViewById(R.id.etTanggalMulaiCuti);
        tglSelesai = findViewById(R.id.etTanggalSelesaiCuti);
        simpan = findViewById(R.id.btSubmitCuti);
        tglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(tglMulai);
            }
        });
        tglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(tglSelesai);
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cuti == null) {
                    Toast.makeText(getApplicationContext(), "Silahkan pilih jenis cuti", Toast.LENGTH_SHORT).show();
                } else if (tglMulai.getText().toString().isEmpty()) {
                    tglMulai.setError("Tidak boleh kosong");
                } else if (tglSelesai.getText().toString().isEmpty()) {
                    tglSelesai.setError("Tidak boleh kosong");
                } else if (nama.getText().toString().isEmpty()) {
                    nama.setError("Tidak boleh kosong");
                } else if (noTelp.getText().toString().isEmpty()) {
                    noTelp.setError("Tidak boleh kosong");
                } else {
                    Cuti mCuti = new Cuti();
                    mCuti.setJenisCuti(cuti);
                    mCuti.setMulai(tglMulai.getText().toString());
                    mCuti.setSelesai(tglSelesai.getText().toString());
                    mCuti.setNama(nama.getText().toString());
                    mCuti.setNoTelpon(noTelp.getText().toString());
                    presenter.submitCuti(mCuti);
                }
            }
        });
    }

    private void initPresenter() {
        presenter = new PengajuanCutiPresenter(this);
        presenter.getProfile(Sharedpreferences.getString("uid"));
        presenter.getJenisCuti();
    }

    private void datePicker(EditText editText) {
        Locale locale = new Locale("in", "ID");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", locale);
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        editText.setText(dateFormat.format(newDate.getTime()));

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onGetJenisCutiSuccess(ArrayList<JenisCuti> listJenisCuti) {
        jenisCuti.clear();
        for (int i = 0; i < listJenisCuti.size(); i++) {
            jenisCuti.add(listJenisCuti.get(i).getNama());
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, jenisCuti);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCuti.setAdapter(adapter);
        spCuti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cuti = jenisCuti.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onGetJenisCutiFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGetProfileSuccess(User user) {
        nama.setText(user.getNama());
        noTelp.setText(user.getNoTelp());
    }

    @Override
    public void onGetProfileFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitSuccess() {
        Toast.makeText(getApplicationContext(),"Cuti berhasil diajukan dan dalam proses",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();

    }
}