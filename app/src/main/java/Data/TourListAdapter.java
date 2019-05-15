package Data;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mmk.taptour.Interface.FavoriteResultListener;
import com.mmk.taptour.Interface.LikeClickListener;
import com.mmk.taptour.Interface.TourClickListener;
import com.mmk.taptour.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Constans.Constants;
import Model.Tour;
import Model.User;

public class TourListAdapter extends RecyclerView.Adapter<TourListAdapter.ViewHolder> {
    private Activity activity;
    private List<Tour>tourList;
    private TourClickListener tourClickListener;
    private LikeClickListener likeClickListener;

    public TourListAdapter(Activity activity, List<Tour> tourList, TourClickListener tourClickListener,LikeClickListener likeClickListener) {
        this.activity = activity;
        this.tourList = tourList;
        this.tourClickListener = tourClickListener;
        this.likeClickListener=likeClickListener;
    }

    @NonNull
    @Override
    public TourListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(activity).inflate(R.layout.row_tour,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TourListAdapter.ViewHolder viewHolder, int i) {

        Tour tour=tourList.get(i);
        viewHolder.tourName.setText(tour.getName());
        viewHolder.tourPrice.setText(tour.getPrice()+"$");
        Picasso.get().load(tour.getImageLink()).placeholder(R.drawable.taptour_logo)
                .into(viewHolder.tourImg);
        if (Constants.currentUser!=null){
            isFavorite(tour.getId(), Constants.currentUser.getId(), new FavoriteResultListener() {
                @Override
                public void onResult(boolean isFavorite) {
                    if (isFavorite){
                        viewHolder.tourFavIcon.setBackgroundResource(R.drawable.ic_favorite_like);
                    }
                    else {
                        viewHolder.tourFavIcon.setBackgroundResource(R.drawable.ic_favorite_dislike);

                    }
                }
            });
        }
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
                            activity.getResources().getDrawable(R.drawable.ic_favorite_dislike).getConstantState()) {
                        if(likeClickListener.onClick(tourList.get(getAdapterPosition()), true))
                            tourFavIcon.setBackgroundResource(R.drawable.ic_favorite_like);

                    }
                    else {

                       if( likeClickListener.onClick(tourList.get(getAdapterPosition()), true))
                           tourFavIcon.setBackgroundResource(R.drawable.ic_favorite_dislike);
                    }



                }
            });



        }
    }


    public void isFavorite(final String tourId, final String userId, final FavoriteResultListener listener){

        RequestQueue queue= Volley.newRequestQueue(activity);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.ISFAVORITE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data=new JSONObject(response);
                            String result=data.getString(Constants.RESULT_TEXT);
                            if (result.equals(Constants.RESULT_SUCCESS)){
                                listener.onResult(true);
                            }
                            else {
                                listener.onResult(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResult(false);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put(Constants.KEY_FAVORITES_USERID,userId);
                params.put(Constants.KEY_FAVORITES_TOURID,tourId);
                return params;
            }
        };
        queue.add(stringRequest);
    }


}
