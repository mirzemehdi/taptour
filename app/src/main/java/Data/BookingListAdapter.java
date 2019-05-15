package Data;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mmk.taptour.R;

import java.util.List;

import Model.Booking;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.ViewHolder> {

    private List<Booking>bookings;
    private Activity context;

    public BookingListAdapter(List<Booking> bookings, Activity context) {
        this.bookings = bookings;
        this.context = context;
    }

    @Override
    public BookingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bookins_row,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingListAdapter.ViewHolder holder, int position) {

        Booking booking=bookings.get(position);
        holder.tourName.setText(booking.getName());
        holder.tourPrice.setText("Price: "+booking.getPrice()+"$");
        holder.userId.setText("By userId: "+ booking.getUserId());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tourName;
        private TextView tourPrice;
        private TextView userId;

        public ViewHolder(View itemView) {
            super(itemView);

            tourName=(TextView)itemView.findViewById(R.id.booking_tour_name);
            tourPrice=(TextView)itemView.findViewById(R.id.booking_tour_price);
            userId=(TextView)itemView.findViewById(R.id.booking_tour_userId);

        }
    }
}
