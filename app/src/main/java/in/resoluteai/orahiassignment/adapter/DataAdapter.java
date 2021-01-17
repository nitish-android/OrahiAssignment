package in.resoluteai.orahiassignment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.resoluteai.orahiassignment.R;
import in.resoluteai.orahiassignment.modal.Datum;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Datum> datumList;
    private Context context;
    int colerloop[] = {1, 2, 2, 2, 3, 3, 3, 3, 1, 1};
    int color;
    int count = 1;

    public DataAdapter(List<Datum> datumList, Context context) {
        this.datumList = datumList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.graph_items, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum datum = datumList.get(position);
        holder.textView.setText(datum.getMonth());
        int stat = datumList.get(position).getStat();

        if (color == 3) {
            color = Color.RED;
        } else if (color == 1) {
            color = Color.BLUE;
        } else if (color == 2) {
            color = Color.GREEN;
        }

        for (int k = 1; k <= count; k++) {
            View view = new View(context);
            view.setBackgroundColor(Color.RED);
            view.setLayoutParams(new LinearLayout.LayoutParams(25, stat));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                    .getLayoutParams();
            params.setMargins(1, 0, 0, 0); // substitute parameters for left,
            // top, right, bottom
            view.setLayoutParams(params);
            holder.layout.addView(view);
            holder.layout.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.linearChart1);
            textView = itemView.findViewById(R.id.txt_months1);

            RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.myanim);
            ranim.setFillAfter(false); //For the textview to remain at the same place after the rotation
            textView.setAnimation(ranim);

        }
    }
}
