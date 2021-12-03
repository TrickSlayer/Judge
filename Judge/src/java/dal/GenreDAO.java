package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Genre;

public class GenreDAO extends DBContext {

    public List<Genre> getAll() {
        List<Genre> list = new ArrayList<>();
        String sql = "select *from Genre ORDER BY Genre";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Genre g = new Genre();
                g.setGenreId(rs.getInt("GenreID"));
                g.setGenre(rs.getString("Genre"));
                g.setDescription(rs.getNString("Description"));
                list.add(g);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public List<Genre> getPage(int page) {
        List<Genre> list = new ArrayList<>();
        String sql = "SELECT *FROM (select ROW_NUMBER() OVER (ORDER BY Genre) AS 'Num',*from Genre) AS a WHERE a.Num BETWEEN ? AND ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 25 * (page - 1) +1);
            st.setInt(2, 25 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Genre g = new Genre();
                g.setGenreId(rs.getInt("GenreID"));
                g.setGenre(rs.getString("Genre"));
                g.setDescription(rs.getNString("Description"));
                list.add(g);
            }

        } catch (SQLException e) {

        }
        return list;
    }
    
    public int size(){
        String sql = "SELECT COUNT(*) AS 'size' FROM Genre";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public Genre get(int i) {
        String sql = "select *from Genre where GenreID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Genre g = new Genre();
                g.setGenreId(rs.getInt("GenreID"));
                g.setGenre(rs.getString("Genre"));
                g.setDescription(rs.getNString("Description"));
                return g;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public int getID(String genre) {
        String sql = "select *from Genre where Genre like ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + genre + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("GenreID");
            }

        } catch (SQLException e) {

        }
        return 0;
    }

    public boolean exits(String genre) {
        int id = getID(genre);
        if (id > 0) {
            if (get(id).getGenre().equals(genre)) {
                return true;
            }
        }
        return false;
    }

    public SQLException insert(Genre m) {
        String sql = "insert into Genre values (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, m.getGenre());
            st.setNString(2, m.getDescription());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public int deleteTable(int id, String table) {
        String sql = "delete from " + table + " where GenreId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int UpdateGenre(int id, Genre genre) {
        String sql = "update Genre set Description=?  where GenreID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, genre.getDescription());
            st.setInt(2, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
