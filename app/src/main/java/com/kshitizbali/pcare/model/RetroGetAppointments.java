package com.kshitizbali.pcare.model;

import java.util.List;

public class RetroGetAppointments {


    private List<AppointmentsBean> appointments;
    private List<DoctorsBean> doctors;

    public List<AppointmentsBean> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsBean> appointments) {
        this.appointments = appointments;
    }

    public List<DoctorsBean> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorsBean> doctors) {
        this.doctors = doctors;
    }

    public static class AppointmentsBean {
        /**
         * appointment_start_time : 2019-06-04 05:00:00
         * appointment_date : 2019-06-04
         * doctor : 102
         * doctor_type_available : {}
         * appointment_status : Available
         * symptoms : null
         * appointment_type : null
         * appointment_end_time : 2019-06-04 05:15:00
         * appointment_utc_time : 2019-06-04T12:00:00Z
         * appointment_time : 05:00:00
         * appointment_id : 22a43914-1a44-44a3-9e38-f53f6e3a0164
         * opentok_session_id : null
         * id : 387348
         * doctor_type :
         */

        private String appointment_start_time;
        private String appointment_date;
        private int doctor;
        private DoctorTypeAvailableBean doctor_type_available;
        private String appointment_status;
        private Object symptoms;
        private Object appointment_type;
        private String appointment_end_time;
        private String appointment_utc_time;
        private String appointment_time;
        private String appointment_id;
        private Object opentok_session_id;
        private int id;
        private String doctor_type;

        public String getAppointment_start_time() {
            return appointment_start_time;
        }

        public void setAppointment_start_time(String appointment_start_time) {
            this.appointment_start_time = appointment_start_time;
        }

        public String getAppointment_date() {
            return appointment_date;
        }

        public void setAppointment_date(String appointment_date) {
            this.appointment_date = appointment_date;
        }

        public int getDoctor() {
            return doctor;
        }

        public void setDoctor(int doctor) {
            this.doctor = doctor;
        }

        public DoctorTypeAvailableBean getDoctor_type_available() {
            return doctor_type_available;
        }

        public void setDoctor_type_available(DoctorTypeAvailableBean doctor_type_available) {
            this.doctor_type_available = doctor_type_available;
        }

        public String getAppointment_status() {
            return appointment_status;
        }

        public void setAppointment_status(String appointment_status) {
            this.appointment_status = appointment_status;
        }

        public Object getSymptoms() {
            return symptoms;
        }

        public void setSymptoms(Object symptoms) {
            this.symptoms = symptoms;
        }

        public Object getAppointment_type() {
            return appointment_type;
        }

        public void setAppointment_type(Object appointment_type) {
            this.appointment_type = appointment_type;
        }

        public String getAppointment_end_time() {
            return appointment_end_time;
        }

        public void setAppointment_end_time(String appointment_end_time) {
            this.appointment_end_time = appointment_end_time;
        }

        public String getAppointment_utc_time() {
            return appointment_utc_time;
        }

        public void setAppointment_utc_time(String appointment_utc_time) {
            this.appointment_utc_time = appointment_utc_time;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

        public String getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(String appointment_id) {
            this.appointment_id = appointment_id;
        }

        public Object getOpentok_session_id() {
            return opentok_session_id;
        }

        public void setOpentok_session_id(Object opentok_session_id) {
            this.opentok_session_id = opentok_session_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDoctor_type() {
            return doctor_type;
        }

        public void setDoctor_type(String doctor_type) {
            this.doctor_type = doctor_type;
        }

        public static class DoctorTypeAvailableBean {
        }
    }

    public static class DoctorsBean {
        /**
         * years_practiced : 11
         * bio : Dr. Yeap is a graduate of McGill University Faculty of Medicine. She completed her Internal Medicine residency at Beth Israel Deaconess Medical Center, a Harvard teaching hospital. She is board certified in Internal Medicine. She has practiced as a hospitalist on the East coast and in California since completion of residency in 2007. She is also very familiar with the healthcare delivery system in the United States as she has served as medical director for a major IPA in California and also a local health plan. In her spare time, she enjoys running races, martial arts and traveling with family. She speaks fluent Mandarin and Cantonese in addition to English.
         * first_name : Wan Chen
         * last_name : Yeap
         * languages : ["English"]
         * average : {"rolling_average":"4.7","last_updated":"2019-06-01"}
         * residency_program : Harvard
         * reviews : [{"date":"2018-03-24","gender":"F","age":31,"id":624,"text":"Thank you for your help Dr. Yeap! I will be using this again! I had a wonderful experience. This was my first time using PlushCare and was amazed by how easy and convenient it was to contact a doctor to help me with a common illness that I did not have to wait over an hour to be seen."}]
         * image_url : https://s3-us-west-1.amazonaws.com/plushcare-static-bucket/doctors/Wan+Yeap+Square-min.jpg
         * doctor_id : 34
         */

        private int years_practiced;
        private String bio;
        private String first_name;
        private String last_name;
        private AverageBean average;
        private String residency_program;
        private String image_url;
        private int doctor_id;
        private List<String> languages;
        private List<ReviewsBean> reviews;

        public int getYears_practiced() {
            return years_practiced;
        }

        public void setYears_practiced(int years_practiced) {
            this.years_practiced = years_practiced;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public AverageBean getAverage() {
            return average;
        }

        public void setAverage(AverageBean average) {
            this.average = average;
        }

        public String getResidency_program() {
            return residency_program;
        }

        public void setResidency_program(String residency_program) {
            this.residency_program = residency_program;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class AverageBean {
            /**
             * rolling_average : 4.7
             * last_updated : 2019-06-01
             */

            private String rolling_average;
            private String last_updated;

            public String getRolling_average() {
                return rolling_average;
            }

            public void setRolling_average(String rolling_average) {
                this.rolling_average = rolling_average;
            }

            public String getLast_updated() {
                return last_updated;
            }

            public void setLast_updated(String last_updated) {
                this.last_updated = last_updated;
            }
        }

        public static class ReviewsBean {
            /**
             * date : 2018-03-24
             * gender : F
             * age : 31
             * id : 624
             * text : Thank you for your help Dr. Yeap! I will be using this again! I had a wonderful experience. This was my first time using PlushCare and was amazed by how easy and convenient it was to contact a doctor to help me with a common illness that I did not have to wait over an hour to be seen.
             */

            private String date;
            private String gender;
            private int age;
            private int id;
            private String text;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
