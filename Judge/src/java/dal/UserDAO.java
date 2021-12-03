package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO extends DBContext {

    public User check(String user, String pass) {
        String sql = "select *from [User] where UserName = ? and Password =?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setUserName(rs.getString("UserName"));
                u.setPassword(rs.getString("Password"));
                u.setAvata(rs.getString("Avata"));
                u.setRole(rs.getString("Role"));
                u.setPower(Power(rs.getString("Role")));
                return u;
            }

        } catch (SQLException e) {

        }
        return null;
    }
    
    public User get(int id){
        String sql = "select *from [User] where UserID = ? ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setUserName(rs.getString("UserName"));
                u.setPassword(rs.getString("Password"));
                u.setAvata(rs.getString("Avata"));
                u.setRole(rs.getString("Role"));
                u.setPower(Power(rs.getString("Role")));
                return u;
            }

        } catch (SQLException e) {

        }
        return null;
    }
    
    public User getLower(int id,int power){
        String sql = 
                "select *from [User] a INNER JOIN dbo.PowerRole b\n" +
                "ON b.Role = a.Role\n" +
                "WHERE UserID = ? AND b.Power < ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, power);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setUserName(rs.getString("UserName"));
                u.setPassword(rs.getString("Password"));
                u.setAvata(rs.getString("Avata"));
                u.setRole(rs.getString("Role"));
                u.setPower(Power(rs.getString("Role")));
                return u;
            }

        } catch (SQLException e) {

        }
        return null;
    }
    
    public User getByUserName(String name){
        String sql = 
                "select *from [User]\n" +
                "WHERE UserName = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setGmail(rs.getString("Gmail"));
                return u;
            }

        } catch (SQLException e) {

        }
        return null;
    }
    
    public List<User> getLowerPage(int power,int page) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT *FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.UserName) AS 'Num',a.UserID,a.UserName,b.Role,b.Power FROM dbo.[User] a,dbo.PowerRole b WHERE a.Role=b.Role AND b.Power< ?) AS a WHERE a.Num BETWEEN ? AND ? ";        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, power);
            st.setInt(2, 25 * (page - 1) +1);
            st.setInt(3, 25 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("UserID"));
                u.setUserName(rs.getString("UserName"));
                u.setRole(rs.getString("Role"));
                u.setPower(rs.getInt("Power"));
                list.add(u);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public int sizeLower(int power){
        String sql = "SELECT COUNT(*) AS 'size' FROM dbo.[User] a,dbo.PowerRole b WHERE a.Role=b.Role AND b.Power< ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, power);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }
    
    public int size(){
        String sql = "SELECT COUNT(*) AS 'size' FROM dbo.[User] ";
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
    
    public int maxID(){
        String sql = "SELECT MAX(UserID) as max FROM dbo.[User]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException e) {

        }

        return 0;
    }
    
    public User exits(String user){
        String sql = "select *from [User] where UserName = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return get(rs.getInt("UserID"));
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public int Power(String Role) {
        String sql = "select *from PowerRole where Role =?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Role);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("Power");
            }

        } catch (SQLException e) {

        }
        return 0;
    }
    
    public SQLException insert(User u){
        String sql = "insert into [User] values (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getPassword());
            if (u.getAvata() != null) {
                st.setString(3, "images/" + u.getAvata());
            } else {
                st.setString(3, "images/admin.jpg");
            }
            st.setString(4, "user");
            st.setString(5, u.getGmail());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }
    
    public int updatePassword(String pass,int id){
        if (pass.length()<5) return -1;
        String sql = "update [User] set Password=?  where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setInt(2, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public int updateAvatar(String avata,int id){
        String sql = "update [User] set Avata=?  where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "images/"+avata);
            st.setInt(2, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public int updateRole(String Role,int id){
        String sql = "update [User] set Role=?  where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Role);
            st.setInt(2, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
    }
}
