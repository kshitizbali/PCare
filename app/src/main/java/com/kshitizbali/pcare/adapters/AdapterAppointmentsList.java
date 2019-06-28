package com.kshitizbali.pcare.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kshitizbali.pcare.R;
import com.kshitizbali.pcare.database.AppointmentsEntry;
import com.kshitizbali.pcare.utilities.ConstantUtils;
import com.kshitizbali.pcare.utilities.Utils;

import java.util.List;

public class AdapterAppointmentsList extends RecyclerView.Adapter<AdapterAppointmentsList.ViewHolder> {

    private Context mContext;
    private List<AppointmentsEntry> mAppointmentsList;
    private DocAvatarClickListenerInterface docAvatarClickListenerInterface;
    private BookAppointmentListenerInterface bookAppointmentListenerInterface;

    public AdapterAppointmentsList(Context context, DocAvatarClickListenerInterface docAvatarClickListenerInterface, BookAppointmentListenerInterface bookAppointmentListenerInterface) {
        this.mContext = context;
        this.docAvatarClickListenerInterface = docAvatarClickListenerInterface;
        this.bookAppointmentListenerInterface = bookAppointmentListenerInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_appointment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Utils.setAvatar(mContext, getmAppointmentsList().get(position).getDoctor_photo(), holder.ivAvatar);
        holder.tvName.setText(String.format("Dr. %s", getmAppointmentsList().get(position).getDoctor_name()));
        holder.tvTime.setText(Utils.getCorrectTime(getmAppointmentsList().get(position).getAppointment_time()));
        holder.ratingBar.setRating(Float.parseFloat(getmAppointmentsList().get(position).getDoctor_rating()));
        holder.tvRatingNum.setText(getmAppointmentsList().get(position).getDoctor_rating());
        if (getmAppointmentsList().get(position).getIs_booked().equalsIgnoreCase(ConstantUtils.NOT_AVAILABLE)) {
            holder.btAvailability.setText(ConstantUtils.BOOKED);
            holder.btAvailability.setBackgroundColor(mContext.getResources().getColor(R.color.dark_yellow, null));
        } else {
            holder.btAvailability.setText(ConstantUtils.AVAILABLE);
            holder.btAvailability.setBackgroundColor(mContext.getResources().getColor(R.color.green, null));
        }

        Log.i("Count", "" + getItemCount());
    }

    @Override
    public int getItemCount() {
        if (getmAppointmentsList() != null) {
            return getmAppointmentsList().size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivAvatar;
        public TextView tvName, tvTime;
        public RatingBar ratingBar;
        public Button btAvailability;
        public TextView tvRatingNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            btAvailability = (Button) itemView.findViewById(R.id.btAvailability);
            tvRatingNum = (TextView) itemView.findViewById(R.id.tvRatingNum);

            ivAvatar.setOnClickListener(this);
            btAvailability.setOnClickListener(this);
            Utils.setTextStyle(tvName);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ivAvatar) {
                //Show Doc Profile
                docAvatarClickListenerInterface.onDocAvatarClickListener(getmAppointmentsList().get(getAdapterPosition()).getDoctor_name(),
                        getmAppointmentsList().get(getAdapterPosition()).getDoctor_rating(),
                        getmAppointmentsList().get(getAdapterPosition()).getDoctor_exp(),
                        getmAppointmentsList().get(getAdapterPosition()).getDoctor_photo(),
                        getmAppointmentsList().get(getAdapterPosition()).getDoctor_res_prog(),
                        getmAppointmentsList().get(getAdapterPosition()).getDoctor_bio());
            } else if (view.getId() == R.id.btAvailability) {
                if (getmAppointmentsList().get(getAdapterPosition()).getIs_booked().equals(ConstantUtils.AVAILABLE)) {
                    bookAppointmentListenerInterface.onBookAppointmentListener(getmAppointmentsList().get(getAdapterPosition()).getId(), getmAppointmentsList().get(getAdapterPosition()).getDoctor_name(),
                            getmAppointmentsList().get(getAdapterPosition()).getAppointment_time(),
                            getmAppointmentsList().get(getAdapterPosition()).getAppointment_date(), getmAppointmentsList().get(getAdapterPosition()).getIs_booked());
                }

            }

        }


    }

    public List<AppointmentsEntry> getmAppointmentsList() {
        return mAppointmentsList;
    }

    public void setmAppointmentsList(List<AppointmentsEntry> mAppointmentsList) {
        this.mAppointmentsList = mAppointmentsList;
        notifyDataSetChanged();
    }

    public interface DocAvatarClickListenerInterface {
        void onDocAvatarClickListener(String docName, String rating, int exp, String photo, String resid_prog, String bio);
    }

    public interface BookAppointmentListenerInterface {
        void onBookAppointmentListener(int id, String docName, String time, String date, String bookStatus);
    }

}
