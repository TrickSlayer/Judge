package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Actor;

public class ActorDAO extends DBContext {

    public List<Actor> getAll() {
        List<Actor> list = new ArrayList<>();
        String sql = "select *from Actor ORDER BY name";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Actor g = new Actor();
                g.setActorID(rs.getInt("ActorID"));
                g.setName(rs.getNString("Name"));
                g.setDOB(rs.getDate("DOB"));
                g.setStatus(rs.getNString("Status"));
                list.add(g);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public List<Actor> getPage(int page) {
        List<Actor> list = new ArrayList<>();
        String sql = "SELECT *FROM (select ROW_NUMBER() OVER (ORDER BY Name) AS 'Num',*from Actor) AS a WHERE a.Num BETWEEN ? AND ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 25 * (page - 1) +1);
            st.setInt(2, 25 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Actor g = new Actor();
                g.setActorID(rs.getInt("ActorID"));
                g.setName(rs.getNString("Name"));
                g.setDOB(rs.getDate("DOB"));
                g.setStatus(rs.getNString("Status"));
                list.add(g);
            }
        } catch (SQLException e) {

        }
        return list;
    }
    
    public int size(){
        String sql = "SELECT COUNT(*) AS 'size' FROM Actor";
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

    public Actor get(int i) {
        String sql = "select *from Actor where ActorID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Actor(i,
                        rs.getNString("Name"),
                        rs.getDate("DOB"),
                        rs.getNString("Status"));
            }

        } catch (SQLException e) {

        }

        return null;
    }

    public int getID(String name) {
        name = name.trim();
        String sql = "select *from Actor where Name like ? ORDER BY Name";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("ActorID");
            }

        } catch (SQLException e) {

        }
        return 0;
    }

    public boolean exits(String name) {
        int id = getID(name);
        if (id > 0) {
            if (get(id).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public SQLException insert(Actor m) {
        String sql = "insert into Actor values (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, m.getName());
            st.setDate(2, m.getDOB());
            st.setNString(3, m.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public int deleteActorRole(int id) {
        String sql = "delete from ActorRole where ActorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int deleteActor(int id) {
        String sql = "delete from Actor where ActorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int UpdateActor(int id, Actor actor) {
        String sql = "update Actor set DOB=?,Status=?  where ActorID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, actor.getDOB());
            st.setNString(2, actor.getStatus());
            st.setInt(3, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public static void main(String[] args) {
        ActorDAO a = new ActorDAO();
        System.out.println(a.getPage(1).size());
    }
}
