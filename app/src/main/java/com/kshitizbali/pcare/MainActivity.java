package com.kshitizbali.pcare;

import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.kshitizbali.pcare.adapters.AdapterAppointmentsList;
import com.kshitizbali.pcare.api.RetrofitClientInstance;
import com.kshitizbali.pcare.database.AppDatabase;
import com.kshitizbali.pcare.database.AppExecutors;
import com.kshitizbali.pcare.database.AppointmentsEntry;
import com.kshitizbali.pcare.utilities.ConstantUtils;
import com.kshitizbali.pcare.utilities.Utils;
import com.kshitizbali.pcare.viewmodel.MainAppointmentsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterAppointmentsList.DocAvatarClickListenerInterface, AdapterAppointmentsList.BookAppointmentListenerInterface {

    @BindView(R.id.rv_appointments)
    RecyclerView rv_appointments;


    AdapterAppointmentsList adapterAppointmentsList;
    private AppDatabase database;
    private CalendarView calView;
    private String currentDate;
    //private ImageView ivLoading;
    private LottieAnimationView animation_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        instantiateRecyclerView();


        if (Utils.isInternetAvailable(MainActivity.this)) {

            currentDate = Utils.getTodaysDate();
            OnLaunchTask(currentDate);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpAppointmentsModel();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);

        return true;
        /*return super.onCreateOptionsMenu(menu);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cal) {

            // calView.setVisibility(View.VISIBLE);
            // Toast.makeText(MainActivity.this, "Menu 1", Toast.LENGTH_LONG).show();
            setCalVisibility();


        }
        return true;
        /*return super.onOptionsItemSelected(item);*/
    }

    private void OnLaunchTask(final String date) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                RetrofitClientInstance.getAppointments(MainActivity.this, date);
            }
        });

    }

    private void setUpAppointmentsModel() {
        MainAppointmentsViewModel mainAppointmentsViewModel = ViewModelProviders.of(this).get(MainAppointmentsViewModel.class);
        mainAppointmentsViewModel.getAppointments().removeObservers(this);
        mainAppointmentsViewModel.getAppointments().observe(this, new Observer<List<AppointmentsEntry>>() {
            @Override
            public void onChanged(List<AppointmentsEntry> appointmentsEntries) {
                adapterAppointmentsList.setmAppointmentsList(appointmentsEntries);
            }
        });
    }

    private void instantiateRecyclerView() {
        animation_view = (LottieAnimationView) findViewById(R.id.animation_view);
        database = AppDatabase.getInstance(MainActivity.this);
        //ivLoading = (ImageView) findViewById(R.id.ivLoading);
        adapterAppointmentsList = new AdapterAppointmentsList(MainActivity.this, this, this);
        adapterAppointmentsList.setHasStableIds(true);
        /*rv_appointments.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));*/
        rv_appointments.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        DividerItemDecoration decoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL);
        rv_appointments.addItemDecoration(decoration);
        rv_appointments.setAdapter(adapterAppointmentsList);
        animateLayoutSlideUp(R.id.rv_appointments, 300);
        calView = (CalendarView) findViewById(R.id.calView);
        setCalVisibility();
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //loadingVisi();
                currentDate = Utils.getDateForAPI("" + i + "-" + (i1 + 1) + "-" + i2);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        database.appointmentsDao().delete();
                    }
                });
                OnLaunchTask(currentDate);
                //loadingVisi();
            }
        });

    }

    @Override
    public void onDocAvatarClickListener(String docName, String rating, int exp, String photo, String resid_prog, String bio) {


        showCustomDialog(photo, docName, rating, exp, resid_prog, bio);

    }

    private void showCustomDialog(String image, String name, String rating, int exp, String resident, String bio) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, viewGroup, false);
        ImageView ivDisplay = (ImageView) dialogView.findViewById(R.id.imageView);
        TextView tvName = (TextView) dialogView.findViewById(R.id.tvName);
        TextView tv_years_prac = (TextView) dialogView.findViewById(R.id.tv_years_prac);
        TextView tvResidency = (TextView) dialogView.findViewById(R.id.tvResidency);
        TextView tvBio = (TextView) dialogView.findViewById(R.id.tvBio);
        RatingBar ratingBar = (RatingBar) dialogView.findViewById(R.id.ratingBar);
        ImageView iv_close = (ImageView) dialogView.findViewById(R.id.iv_close);
        TextView tvRatingNumber = (TextView) dialogView.findViewById(R.id.tvRatingNumber);

        Utils.setTextStyle(tvResidency);
        Utils.setTextStyle(tv_years_prac);

        Utils.setAvatar(MainActivity.this, image, ivDisplay);
        tvName.setText(String.format("Dr. %s", name));

        tv_years_prac.setText(String.format("Years Practiced: %s %s", String.valueOf(exp), ConstantUtils.YEARS));
        tvResidency.setText(String.format("Residency: %s", resident));
        tvBio.setText(bio);
        ratingBar.setRating(Float.parseFloat(rating));
        tvRatingNumber.setText(rating);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }


    private void showCustomDialog(final int id, String docName, String time, String date) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirm, viewGroup, false);

        TextView tvInfo = (TextView) dialogView.findViewById(R.id.tvInfo);
        TextView tvDocName = (TextView) dialogView.findViewById(R.id.tvDocName);
        TextView tvDateTime = (TextView) dialogView.findViewById(R.id.tvDateTime);
        Button buttonOk = (Button) dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = (Button) dialogView.findViewById(R.id.buttonCancel);

        tvDocName.setText(String.format("Dr. %s", docName));
        tvDateTime.setText(String.format("Appointment: %s at %s", date, time));
        tvInfo.setText(String.format("Confirm your appointment with: Dr. %s", docName));

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        database.appointmentsDao().bookAppointment(id, ConstantUtils.NOT_AVAILABLE);
                        alertDialog.dismiss();

                    }
                });
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


    private void animateLayoutSlideUp(int resource, int slideDuration) {
        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(resource);
        slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator
                .linear_out_slow_in));
        slide.setDuration(slideDuration);
        getWindow().setEnterTransition(slide);
    }

    @Override
    public void onBookAppointmentListener(final int id, String docName, String time, String date, final String bookStatus) {
        showCustomDialog(id, docName, time, date);

    }

    private void setCalVisibility() {
        if (calView.getVisibility() == View.GONE) {
            calView.setVisibility(View.VISIBLE);
        } else {
            calView.setVisibility(View.GONE);
        }
    }

    private void loadingVisi() {
        if (animation_view.getVisibility() == View.VISIBLE) {
            animation_view.setVisibility(View.GONE);
        } else {
            animation_view.setVisibility(View.VISIBLE);
        }
    }




}
