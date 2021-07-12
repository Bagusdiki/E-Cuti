package com.cuti.online.panasonic.presenter;


import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.cuti.online.panasonic.interfaces.ProfileView;
import com.cuti.online.panasonic.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ProfilePresenter {
    ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    public void getProfile(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Users/" + uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                view.onSuccess(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(String.valueOf(error.getMessage()));
            }
        });
    }

    public void updateProfile(Bitmap bitmap, String uid, User user) {
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
                        user.setFoto(foto);
                        pushToDatabase(uid, user);
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

    private void pushToDatabase(String uid, User user) {
        FirebaseDatabase.getInstance().getReference().child("Users" + "/" + uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                view.onEditSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onEditFailed(String.valueOf(e.getMessage()));
            }
        });
    }

}
