package com.cuti.online.karyawan.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.RegisterView;
import com.cuti.online.karyawan.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class RegisterPresenter {
    RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    public void register(String nama, String noTelpon, String email, String password, Bitmap foto) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    uploadFoto(foto, task.getResult().getUser().getUid(), nama, noTelpon, email, password);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailed(String.valueOf(e));

            }
        });
    }

    private void uploadFoto(Bitmap bitmap, String uid, String nama, String noTelpon, String email, String password) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference(uid);
        StorageReference imgReferences = storageReference.child("images/" + uid + ".jpg");
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] data = bao.toByteArray();
        UploadTask uploadTask = imgReferences.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgReferences.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String foto = String.valueOf(uri);
                        pushToDatabase(uid, nama, noTelpon, email, password, foto);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailed("Coba lagi\nGagal mengunggah foto");
            }
        });
    }

    private void pushToDatabase(String uid, String nama, String noTelpon, String email, String password, String foto) {
        User user = new User();
        user.setNama(nama);
        user.setNoTelp(noTelpon);
        user.setPassword(password);
        user.setEmail(email);
        user.setFoto(foto);
        FirebaseDatabase.getInstance().getReference().child("Users" + "/" + uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                view.onSuccess(task);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailed("Registrasi gagal\nSilahkan coba kembali");
            }
        });
    }
}
