package com.example.vollleyarray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vollleyarray.databinding.CustomBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ApplistAdp extends RecyclerView.Adapter<ApplistAdp.MyViewHolder> {
    ArrayList<String> adpidArray;
    ArrayList<String> adptitleArray;
    ArrayList<String> adpurlArray;
    Context context;
    CustomBinding binding;

    public ApplistAdp(MainActivity mainActivity,
                      ArrayList<String> idArray,
                      ArrayList<String> titleArray, ArrayList<String> urlArray) {
        context=mainActivity;
        adpidArray=idArray;
        adptitleArray=titleArray;
        adpurlArray=urlArray;
    }

    @NonNull
    @Override
    public ApplistAdp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding= DataBindingUtil.inflate(inflater,R.layout.custom,null,false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ApplistAdp.MyViewHolder holder, int position) {
        binding.textView.setText(adpidArray.get(position));
        binding.textView2.setText(adptitleArray.get(position));
        binding.textView3.setText(adpurlArray.get(position));

    }

    @Override
    public int getItemCount() {
        return adpidArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DataBindingUtil.getBinding(itemView);
        }
    }
}
