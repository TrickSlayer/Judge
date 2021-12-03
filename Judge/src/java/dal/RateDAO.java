package dal;

import model.Statistics;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rate;

public class RateDAO extends DBContext {

    public List<Rate> getAll() {
        List<Rate> list = new ArrayList<>();
        String sql = "select *from Rate";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate r = new Rate();
                r.setUserID(rs.getInt("UserID"));
                r.setMovieID(rs.getInt("MovieID"));
                r.setTime(rs.getDate("Time"));
                r.setComment(rs.getNString("Comment"));
                r.setRate(rs.getInt("Rate"));
                list.add(r);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public float getScoreMovie(int i) {
        String sql = "SELECT ROUND(AVG(CAST(Rate AS FLOAT)),1) AS 'avg' FROM dbo.Rate WHERE MovieID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getFloat("avg");
            }

        } catch (SQLException e) {

        }

        return 0;
    }

    public String getNumScoreMovie(int i) {
        Long num = getNumIntScoreMovie(i);
        if (num < 1000) {
            return num + "";
        }
        if (num < 1000000) {
            return (float) Math.floor((float) num / 100) / 10 + "K";
        }
        if (num < 1000000000) {
            return (float) Math.floor((float) num / 100000) / 10 + "M";
        }
        return (float) Math.floor((float) num / 100000000) / 10 + "B";
    }

    public long getNumIntScoreMovie(int i) {
        String sql = "SELECT COUNT(*) 'num' FROM dbo.Rate WHERE MovieID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getLong("num");
            }

        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Rate> getRateByMovieIDNotIncludeUserPage(int MovieID, int UserID, int page) {
        List<Rate> list = new ArrayList<>();
        String sql = "SELECT *FROM (SELECT ROW_NUMBER() OVER (ORDER BY Time DESC) AS 'Num',*FROM dbo.Rate WHERE MovieID = ? AND NOT UserID = ?) AS a WHERE a.Num BETWEEN ? AND ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MovieID);
            st.setInt(2, UserID);
            st.setInt(3, (page - 1) * 10 + 1);
            st.setInt(4, page * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate r = new Rate();
                r.setUserID(rs.getInt("UserID"));
                r.setMovieID(rs.getInt("MovieID"));
                r.setTime(rs.getDate("Time"));
                r.setComment(rs.getNString("Comment"));
                r.setRate(rs.getInt("Rate"));
                list.add(r);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int getSizeRateByMovieIDNotIncludeUser(int MovieID, int UserID) {
        String sql = "SELECT COUNT(*) AS 'Num' FROM dbo.Rate WHERE MovieID = ? AND NOT UserID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MovieID);
            st.setInt(2, UserID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("Num");
            }

        } catch (SQLException e) {

        }

        return 0;
    }

    public Rate get(int UserID, int MovieID) {
        String sql = "select *from Rate where MovieID =? and UserID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MovieID);
            st.setInt(2, UserID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate r = new Rate();
                r.setUserID(rs.getInt("UserID"));
                r.setMovieID(rs.getInt("MovieID"));
                r.setTime(rs.getDate("Time"));
                r.setComment(rs.getNString("Comment"));
                r.setRate(rs.getInt("Rate"));
                return r;
            }

        } catch (SQLException e) {

        }

        return null;
    }

    public boolean exits(int UserID, int MovieID) {
        return get(UserID, MovieID) != null;
    }

    public SQLException insert(Rate r) {
        String sql = "  INSERT INTO dbo.Rate VALUES (?,?,GETDATE(),?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, r.getUserID());
            st.setInt(2, r.getMovieID());
            st.setNString(3, r.getComment());
            st.setInt(4, r.getRate());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public SQLException insertAll(Rate r) {
        String sql = "  INSERT INTO dbo.Rate VALUES (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, r.getUserID());
            st.setInt(2, r.getMovieID());
            st.setDate(3, r.getTime());
            st.setNString(4, r.getComment());
            st.setInt(5, r.getRate());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public SQLException update(Rate r) {
        String sql = "update Rate set Comment=?,Rate=?, Time = GETDATE()  where UserID=? and MovieID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, r.getComment());
            st.setInt(2, r.getRate());
            st.setInt(3, r.getUserID());
            st.setInt(4, r.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public SQLException updateAll(Rate r) {
        String sql = "update Rate set Comment=?,Rate=?, Time = ?  where UserID=? and MovieID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, r.getComment());
            st.setInt(2, r.getRate());
            st.setDate(3, r.getTime());
            st.setInt(4, r.getUserID());
            st.setInt(5, r.getMovieID());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public SQLException post(Rate r) {
        if (exits(r.getUserID(), r.getMovieID())) {
            if (r.getTime() != null) {
                return updateAll(r);
            }
            return update(r);
        } else {
            if (r.getTime() != null) {
                return insertAll(r);
            }
            return insert(r);
        }
    }

    public int delete(int UserID, int MovieID) {
        String sql = "DELETE dbo.Rate WHERE UserID=? AND MovieID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, UserID);
            st.setInt(2, MovieID);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public List<Statistics> statistics(int MovieID) {
        List<Statistics> list = new ArrayList<>();
        String sql =    "SELECT a.AVG, a.Count, DAY(a.time) AS 'Day',MONTH(a.time) AS 'Month',YEAR(a.time) AS 'Year' FROM (\n" +
                        "	SELECT a.*, ROUND(AVG(CAST(b.Rate AS FLOAT)),1) AS 'AVG' FROM (\n" +
                        "		SELECT MovieID, Time, COUNT(*) AS 'Count' FROM dbo.Rate\n" +
                        "		WHERE MovieID = ? AND YEAR(time) = YEAR(GETDATE())\n" +
                        "		GROUP BY MovieID,Time\n" +
                        "		) AS a, dbo.Rate AS b\n" +
                        "	WHERE a.Time >= b.Time AND a.MovieID = b.MovieID\n" +
                        "	GROUP BY a.MovieID, a.Time, a.Count\n" +
                        "	) AS a\n" +
                        "ORDER BY a.Time";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MovieID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Statistics s = new Statistics(
                        rs.getInt("Day"),
                        rs.getInt("Month"),
                        rs.getInt("Year"),
                        rs.getInt("Count"),
                        rs.getFloat("AVG"));
                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public static void main(String[] args) {
        RateDAO r = new RateDAO();
        List<Statistics> list = r.statistics(1);
        System.out.println(list.size());
    }
}
