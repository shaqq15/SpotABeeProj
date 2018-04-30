//package com.example.c1618639.myapplication;
//
///**
// * Created by c1618639 on 24/04/2018.
// */
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Comparator;
//
//
//public class PhotoInfo {
//        /**
//         * Name of Photo
//         * Time of Photo
//         * The x coordinates of the photo
//         * The y coordinates of the photo
//         * The number of bees in the photo
//         * Description of the photo
//         */
//
//        private String name;
//        private String time;
//        private int x;
//        private int y;
//        private int number_bees;
//        private String description;
//        private String api_out;
//
//        /**
//         * Instantiating of an Event
//         *
//         * @param name              Name
//         * @param time              Time
//         * @param x                 x coordinate
//         * @param y                 y coordinate
//         * @param number_bees       number of  bees
//         * @param description       description of  image
//         * @param api_out           api results of the api
//         */
//
//        public info_photo(String name, String time, int x, int y, int number_bees, String description, String api_out) {
//            this.name = name;
//            this.time = time;
//            this.x = x;
//            this.y = y;
//            this.number_bees = number_bees;
//            this.description = description;
//            this.api_out = api_out;
//        }
//
//        /**
//         * Constructor for Testing Purposes
//         */
//
//        public info_photo() {
//        }
//
//        /**
//         * Constructor for Testing Purposes
//         *
//         * @param name Name
//         * @param time Time
//         */
//
//        public Event(String name, String time) {
//            this.name = name;
//            this.time = time;
//        }
//
//        /**
//         * Sets the Name
//         *
//         * @param name Name
//         */
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        /**
//         * Sets the Time
//         *
//         * @param time Time
//         */
//
//        public void setTime(String time) {
//            this.time = time;
//        }
//
//        /**
//         * Gets the Time
//         *
//         * @return Time
//         */
//
//        public String getTime() {
//            return time;
//        }
//
//        /**
//         * Gets the Year Groups
//         *
//         * @return Year Groups
//         */
//
//        public YearGroup getYearGroups() {
//            return yearGroups;
//        }
//
//        /**
//         * Gets the Name
//         *
//         * @return Name
//         */
//
//        public String getName() {
//            return name;
//        }
//
//
//    /**
//     * Event Comparator
//     */
//}