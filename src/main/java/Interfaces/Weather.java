package Interfaces;

public class Weather implements data {
    public void getDate() {

        System.out.println("date");
    }

    public void getTime() {

        System.out.println("time");
    }

    public int getLocation() {
        System.out.println("location");
        return 0;
    }
    public static void main(String args[]){
        Weather weather = new Weather();
        weather.getDate();
        weather.getTime();
        weather.getLocation();

    }
}
