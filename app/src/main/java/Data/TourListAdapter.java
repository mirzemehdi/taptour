package Data;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmk.taptour.Interface.TourClickListener;
import com.mmk.taptour.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Tour;

public class TourListAdapter extends RecyclerView.Adapter<TourListAdapter.ViewHolder> {
    private Activity activity;
    private List<Tour>tourList;
    private TourClickListener tourClickListener;

    public TourListAdapter(Activity activity, List<Tour> tourList, TourClickListener tourClickListener) {
        this.activity = activity;
        this.tourList = tourList;
        this.tourClickListener = tourClickListener;
    }

    @NonNull
    @Override
    public TourListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(activity).inflate(R.layout.row_tour,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourListAdapter.ViewHolder viewHolder, int i) {
        Tour tour=tourList.get(i);
        viewHolder.tourName.setText(tour.getName());
        viewHolder.tourPrice.setText(tour.getPrice()+"$");
        Picasso.get().load(tour.getImageLink()).placeholder(R.drawable.taptour_logo)
                .into(viewHolder.tourImg);
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView tourImg,tourFavIcon;
        private TextView tourName,tourPrice;
        private RelativeLayout tourRowContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tourImg=(ImageView)itemView.findViewById(R.id.tourRowImage);
            tourFavIcon=(ImageView)itemView.findViewById(R.id.tourRowFav);
            tourName=(TextView)itemView.findViewById(R.id.tourRowName);
            tourPrice=(TextView)itemView.findViewById(R.id.tourRowPrice);
            tourRowContainer=(RelativeLayout)itemView.findViewById(R.id.tourRowContainer);




            //Tour Clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tourClickListener.onClick(v,getAdapterPosition(),tourList.get(getAdapterPosition()));
                }
            });

            tourFavIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tourFavIcon.getBackground().getConstantState()==
                            activity.getResources().getDrawable(R.drawable.ic_favorite_dislike).getConstantState())
                        tourFavIcon.setBackgroundResource(R.drawable.ic_favorite_like);
                    else
                        tourFavIcon.setBackgroundResource(R.drawable.ic_favorite_dislike);



                }
            });



        }
    }
}
