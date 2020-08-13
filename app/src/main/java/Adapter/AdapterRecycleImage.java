package Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.volley.R;


import java.util.List;

import Model.UploadImages;

public class AdapterRecycleImage extends RecyclerView.Adapter<ImageViewHolder> {

    Context context;
    List<UploadImages> imageList;
    LayoutInflater inflater;

    public AdapterRecycleImage(Context context, List<UploadImages> imageList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom1, parent, false);
        ImageViewHolder h = new ImageViewHolder(v,context);
        return h;
        //or
//        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UploadImages ob = imageList.get(position);
        holder.textView.setText(ob.getId());
        Glide.with(context).load(imageList.get(position).getImages()).into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return imageList.size();
    }
}

 class ImageViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView textView;
    Context context;

    public ImageViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;

        imageView = itemView.findViewById(R.id.imagecontainer);
//        textView= itemView.findViewById(R.id.textView);
    }
}