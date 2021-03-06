package alexandre.com.br.movies.presentation.gui.adapter;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import alexandre.com.br.movies.R;
import alexandre.com.br.movies.presentation.MoviesApp;
import alexandre.com.br.movies.presentation.gui.activity.ImageActivity;
import alexandre.com.br.movies.presentation.mvp.model.ImageModel;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private List<ImageModel> data = new ArrayList<>();
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ImageModel> newImages) {
        data = newImages;
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageModel imageModel = data.get(position);
        MoviesApp.PICASSO.load(imageModel.getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(ImageActivity.getCallingIntent(context, data.get(getPosition()).getOriginalURL()));
                }
            }, 200);
        }
    }

}
