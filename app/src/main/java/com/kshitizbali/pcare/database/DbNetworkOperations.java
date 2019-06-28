package com.kshitizbali.pcare.database;

import android.content.Context;

import com.kshitizbali.pcare.model.DoctorModel;
import com.kshitizbali.pcare.model.RetroGetAppointments;
import com.kshitizbali.pcare.utilities.ConstantUtils;
import com.kshitizbali.pcare.utilities.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;

public class DbNetworkOperations {

    public static void insertAllAppointments(Context context, Response<RetroGetAppointments> response) {

        final AppDatabase appDatabase = AppDatabase.getInstance(context);

        List<RetroGetAppointments.AppointmentsBean> fetchedAppoinments = Objects.requireNonNull(response.body()).getAppointments();
        List<RetroGetAppointments.DoctorsBean> fetchedDocs = response.body().getDoctors();

        HashMap<Integer, DoctorModel> doctors = new HashMap<>();

        for (int i = 0; i < fetchedDocs.size(); i++) {
            DoctorModel docModal = new DoctorModel();

            docModal.setName(fetchedDocs.get(i).getFirst_name() + " " + fetchedDocs.get(i).getLast_name());
            docModal.setRating(fetchedDocs.get(i).getAverage().getRolling_average());
            docModal.setPhoto(fetchedDocs.get(i).getImage_url());
            docModal.setExp(fetchedDocs.get(i).getYears_practiced());
            docModal.setRes_prog(fetchedDocs.get(i).getResidency_program());
            docModal.setBio(fetchedDocs.get(i).getBio());
            doctors.put(fetchedDocs.get(i).getDoctor_id(), docModal);
        }

        for (int i = 0; i < fetchedAppoinments.size(); i++) {
            final AppointmentsEntry appointmentsEntry = new AppointmentsEntry(fetchedAppoinments.get(i).getId(),
                    Utils.getCorrectDate(fetchedAppoinments.get(i).getAppointment_date()),
                    fetchedAppoinments.get(i).getAppointment_time(),
                    Objects.requireNonNull(doctors.get(fetchedAppoinments.get(i).getDoctor())).getName(),
                    Objects.requireNonNull(doctors.get(fetchedAppoinments.get(i).getDoctor())).getRating(),
                    Objects.requireNonNull(doctors.get(fetchedAppoinments.get(i).getDoctor())).getPhoto(),
                    Objects.requireNonNull(doctors.get(fetchedAppoinments.get(i).getDoctor())).getExp(),
                    Objects.requireNonNull(doctors.get(fetchedAppoinments.get(i).getDoctor())).getRes_prog(),
                    Objects.requireNonNull(doctors.get(fetchedAppoinments.get(i).getDoctor())).getBio(), fetchedAppoinments.get(i).getAppointment_status());

            /*Utils.getCorrectTime(fetchedAppoinments.get(i).getAppointment_time())*/
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    appDatabase.appointmentsDao().insertAppointments(appointmentsEntry);
                }
            });
        }

    }



}
