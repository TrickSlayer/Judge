/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PowerRole;

/**
 *
 * @author Acer
 */
public class PowerRoleDAO extends DBContext{
    public List<PowerRole> getAll(){
        List<PowerRole> list = new ArrayList<>();
        String sql = "select *from PowerRole ORDER BY Power";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                PowerRole p = new PowerRole();
                p.setPower(rs.getInt("Power"));
                p.setRole(rs.getString("Role"));
                list.add(p);
            }
            
        }catch(SQLException e){
            
        }
        
        return list;
    }
    
    public List<PowerRole> getLimit(int Power){
        List<PowerRole> list = new ArrayList<>();
        String sql = "select *from PowerRole where Power < ? ORDER BY Power";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Power);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                PowerRole p = new PowerRole();
                p.setPower(rs.getInt("Power"));
                p.setRole(rs.getString("Role"));
                list.add(p);
            }
            
        }catch(SQLException e){
            
        }
        
        return list;
    }
    
    public int delete(int Power) {
        String sql = "delete from PowerRole where Power=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Power);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public int update(String Role,int Power){
        String sql = "update PowerRole set Role=? where Power=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Role);
            st.setInt(2, Power);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public SQLException insert(int Power, String Role ){
        String sql = "insert into PowerRole values (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Role);
            st.setInt(2, Power);
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }
    
    public String getRole(int Power){
        String sql = "select *from PowerRole where Power = ?";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Power);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return rs.getString("Role");
            }
            
        }catch(SQLException e){
            
        }
        
        return "";
    }
    
    public boolean exits(String Role){
        String sql = "select *from PowerRole where Role = ?";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Role);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return true;
            }
            
        }catch(SQLException e){
            
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        PowerRoleDAO p = new PowerRoleDAO();
    }
}
