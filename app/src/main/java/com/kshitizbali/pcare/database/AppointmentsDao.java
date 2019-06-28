package com.kshitizbali.pcare.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentsDao {

    @Query("SELECT * FROM appointments ORDER BY datetime(appointment_time)")
    LiveData<List<AppointmentsEntry>> loadAllAppointments();

    @Query("SELECT * FROM appointments WHERE id = (:id) ORDER BY datetime(appointment_time)")
    LiveData<List<AppointmentsEntry>>  getDoctorSpecificAppointments(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAppointments(AppointmentsEntry... appointmentsEntries);

    @Query("UPDATE appointments SET is_booked = (:book) WHERE id = (:id)")
    void bookAppointment(int id, String book);

    @Query("SELECT COUNT(id) FROM appointments")
    int getNumberOfRows();

    @Query("DELETE FROM appointments")
    void delete();
}
