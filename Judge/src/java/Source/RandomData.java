/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import dal.MovieDAO;
import dal.RateDAO;
import dal.UserDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import model.Rate;
import model.User;

/**
 *
 * @author Acer
 */
public class RandomData {

    Random rad = new Random();

    protected User randomUser() {
        String data = "qwertyuiopasdfghjklzxcvbnm";
        data = data + "1234567890" + data.toUpperCase();
        String name = "";
        String pass = "";
        for (int i = 0; i < 5 + rad.nextInt(10); i++) {
            name = name + data.charAt(rad.nextInt(data.length()));
        }
        for (int i = 0; i < 5 + rad.nextInt(5); i++) {
            pass = pass + data.charAt(rad.nextInt(data.length()));
        }
        User user = new User(name, pass);
        return user;
    }

    protected int insertRandomUser(int num) {
        UserDAO u = new UserDAO();
        RandomData data = new RandomData();
        int success = 0;
        for (int i = 0; i < num; i++) {
            SQLException insert = u.insert(data.randomUser());
            if (insert == null) {
                success++;
            }
        }
        return success;
    }

    private int randomScore(int n) {
        LuckyNumber lucky = new LuckyNumber(n);
        return lucky.numberRan();
    }

    private String randomComment(int score) {
        String[] bad = {"", "Bad", "Waste of time", "Give me my money back",
            "This is a joke", "Boycott", "This is the worst movie I've ever seen",
            "Haizzz", "This movie is a disaster", "Disappointed", "Lmao", "Bruh",
            "No comment", "Bad script", "Stupid actor", "asdhaidjwi......"};
        if (score < 3) {
            return bad[rad.nextInt(bad.length)];
        }
        String[] notbad = {"", "Not Bad", "I only watch for the actors",
            "It would have been better with a little tweaking", "Improving",
            "Really sleepy", "I don't want to see it anymore", "It's good for insomniacs",
            "It makes me constipated"};
        if (score < 7) {
            return notbad[rad.nextInt(notbad.length)];
        }
        String[] good = {"", "Good", "Very Good", "Can't wait for the next part",
            "Excellent expressive actor", "The script is so unique", "The situation is so unexpected",
            "I laughed a lot", "So touching", "It's been a long time since I've had such a good movie",
            "I watched it 10 times", "It stole my heart", "Wonderful", "Excellent"};
        if (score < 9) {
            return good[rad.nextInt(good.length)];
        }
        String[] verygood = {"", "Take my money", "King of movies", "Goosebumps",
            "Worthy of an oscar", "I love this film", "Too short for me", "I want more",
            "Good", "Very Good", "Can't wait for the next part",
            "Excellent expressive actor", "The script is so unique", "The situation is so unexpected",
            "I laughed a lot", "So touching", "It's been a long time since I've had such a good movie",
            "I watched it 10 times", "It stole my heart", "Wonderful", "Excellent"};
        return verygood[rad.nextInt(verygood.length)];
    }

    protected Date randomDate(int year) {
        long millis = System.currentTimeMillis();
        Date current = new Date(millis);
        Date date = startDate(year);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        long millisBetween = current.getTime() - date.getTime();
        int time = (int) (millisBetween / 1000 / 60 / 60 / 24);
        return new Date(date.getTime() + ((long) rad.nextInt(time + 1)) * 24 * 60 * 60 * 1000);
    }

    private Date startDate(int year) {
        String stringDate = "01/01/" + year;
        try {
            java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
            java.sql.Date date1 = new java.sql.Date(date.getTime());
            return date1;
        } catch (ParseException e) {
        }
        return null;
    }

    protected void insertRandomRate(int MovieID) {
        int luckyScore = rad.nextInt(10) + 3;
        if (luckyScore>10){
            luckyScore = 21 - luckyScore;
        }
        for (int i = 0; i < 700 + rad.nextInt(10000) + rad.nextInt(10000); i++) {
            try {
                Rate rate = new Rate();
                UserDAO u = new UserDAO();
                rate.setUserID(rad.nextInt(u.maxID() + 1));
                MovieDAO m = new MovieDAO();
                rate.setMovieID(MovieID);
                int score = randomScore(luckyScore);
                rate.setRate(score);
                rate.setComment(randomComment(score));
                rate.setTime(randomDate(m.get(MovieID).getYear()));
                RateDAO r = new RateDAO();
                System.out.println("SQLException: " + r.post(rate));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        RandomData data = new RandomData();
        data.insertRandomUser(10000);
        MovieDAO m = new MovieDAO();
        for (int i = 1; i <= m.maxID(); i++) {
            data.insertRandomRate(i);
        }
    }
}

class LuckyNumber {

    int LuckyNumber;
    static final int luckyNumberRate = 60;
    static final int totalNumberRate = 100;
    static final int totalNumber = 10;
    Random rad = new Random();

    public LuckyNumber(int LuckyNumber) {
        this.LuckyNumber = LuckyNumber;
    }

    int numberRan() {
        int randomNumber = rad.nextInt(100) % totalNumberRate;
        if (randomNumber <= luckyNumberRate) {
            return LuckyNumber;
        } else {
            return rad.nextInt(totalNumber) + 1;
        }
    }
}
