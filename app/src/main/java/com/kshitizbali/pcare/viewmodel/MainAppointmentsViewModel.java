package com.kshitizbali.pcare.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kshitizbali.pcare.database.AppDatabase;
import com.kshitizbali.pcare.database.AppointmentsEntry;

import java.util.List;

public class MainAppointmentsViewModel extends AndroidViewModel {

    private LiveData<List<AppointmentsEntry>> appointments;

    public MainAppointmentsViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        appointments = database.appointmentsDao().loadAllAppointments();
    }

    public LiveData<List<AppointmentsEntry>> getAppointments() {
        return appointments;
    }
}
