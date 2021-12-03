/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Acer
 */
public class WatchListDAO extends DBContext {

    public SQLException insert(int UserID, int MovieID) {
        String sql = "insert into WatchList values (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, UserID);
            st.setInt(2, MovieID);
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public int delete(int MovieID,int UserID) {
        String sql = "delete from WatchList where MovieID=? and UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MovieID);
            st.setInt(2, UserID);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
}
