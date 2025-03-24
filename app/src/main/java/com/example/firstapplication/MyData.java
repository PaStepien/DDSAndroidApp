package com.example.firstapplication;
public class MyData {
        private static MyData instance;

        String myName;

        private MyData(){
            myName = "Welcome";
        }

        public static MyData getInstance(){
            if (instance == null){
                instance = new MyData();
            }
            return instance;
        }
}
