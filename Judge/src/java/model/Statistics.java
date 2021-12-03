package model;

public class Statistics {
    int day;
    int month;
    int year;
    int count;
    float rate;

    public Statistics(int day, int month, int year, int count, float rate) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.count = count;
        this.rate = rate;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    
}
