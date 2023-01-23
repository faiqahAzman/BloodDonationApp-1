package com.blooddonationapp.startactivity.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blooddonationapp.startactivity.R;
import com.blooddonationapp.startactivity.UserData.bloodBank;


import java.io.InputStream;
import java.util.ArrayList;

public class CardView_RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static int numberABMinus, numberAMinus, numberAPlus;
    private Context context;
    ArrayList<bloodBank> list = new ArrayList<>();

    public CardView_RVAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<bloodBank> bloodBanks){
        list.addAll(bloodBanks);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_cardlayout, parent, false);
        return new bloodBankViewHolder(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bloodBankViewHolder vh = (bloodBankViewHolder) holder;
        bloodBank bloodBankObj = list.get(position);
        vh.bloodBankName.setText(bloodBankObj.getName());
        vh.bloodBankDate.setText(bloodBankObj.getDate());
        vh.bloodBankTime.setText(bloodBankObj.getTime());

        switch (bloodBankObj.getBloodRequested()) {
            case "AB-":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_abminus);
                break;
            case "A-":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_aminus);
                break;
            case "A+":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_aplus);
                break;
            case "B-":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_bminus);
                break;
            case "B+":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_bplus);
                break;
            case "O-":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_ominus);
                break;
            case "O+":
                vh.bloodTypeImage.setBackgroundResource(R.drawable.blood_full_oplus);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
