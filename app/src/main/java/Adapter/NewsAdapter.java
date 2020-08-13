package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.volley.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.TechCrunch;

public class NewsAdapter extends ArrayAdapter<TechCrunch> {
    Context context;
    int resource;
    ArrayList<TechCrunch> objects;

    public NewsAdapter(Context context, int resource, ArrayList<TechCrunch> objects) {
        super(context,resource,objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;}


        public View getView(int position, View convertView, ViewGroup parent) {

            //1.  Create the View
            // view represent Object in Java File for list_item
            View view = LayoutInflater.from(context).inflate(resource, parent, false);

            ImageView image = view.findViewById(R.id.imageView);
            TextView txtTitle = view.findViewById(R.id.textViewTitle);
            TextView txtAuthor = view.findViewById(R.id.textViewAuthor);
            TextView txtPublishedAt = view.findViewById(R.id.textViewPublishedAt);

            //2. Fetch the Object from ArrayList
            TechCrunch news =objects.get(position);

            //3. Set Data on View

            Picasso.get().load(news.urlToImage).into(image);

            txtTitle.setText(news.title);
            txtAuthor.setText(news.author);
            txtPublishedAt.setText(news.publishedAt);

            //4. Return the View
            return view;
        }


    }
