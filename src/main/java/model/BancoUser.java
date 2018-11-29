/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caiot
 */
public class BancoUser {

    public static boolean newUser(String user, String email, String senha) throws SQLException {
        String sql = "INSERT INTO public.\"user\" VALUES (?, ?, ?)";

        Connection con = Conexao.getConnection();
        PreparedStatement psmt;

        psmt = con.prepareStatement(sql);
        psmt.setString(1, user);
        psmt.setString(2, email);
        psmt.setString(3, senha);

        int i = psmt.executeUpdate();
        if (i != 0) {
            psmt.close();
            con.close();

            return true;
        } else {
            psmt.close();
            con.close();

            return false;
        }

    }

    public static boolean loginUser(String user, String senha) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM public.\"user\" WHERE usuario=? AND senha=?";

        Connection con = Conexao.getConnection();
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, user);
        psmt.setString(2, senha);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            psmt.close();
            rs.close();
            con.close();

            return true;
        } else {
            psmt.close();
            rs.close();
            con.close();

            return false;
        }

    }

    public static ArrayList<String> getAllUsers(String user) throws SQLException {
        if (user == null) {
            return null;
        }
        ArrayList<String> contatos = new ArrayList<>();
        String sql = "SELECT usuario FROM public.\"user\" where usuario != ?";
        Connection con = Conexao.getConnection();
        PreparedStatement psmt = con.prepareStatement(sql);
        
        psmt.setString(1, user);
        
        ResultSet rs = psmt.executeQuery();
        
        if(rs.next()){
            do{
                contatos.add(rs.getString("usuario"));
            }while(rs.next());
        }else{
            con.close();
            psmt.close();
            
            return null;
        }
        con.close();
        psmt.close();
        return contatos;
    }
    
    public static boolean deleteUser(String user) throws SQLException {
        if (user == null) {
            return false;
        }

        Connection con = Conexao.getConnection();
        String deleteMsgs = "delete from public.\"mensagem\" where remetente = ? or destinatario = ?";
        PreparedStatement psmt = con.prepareStatement(deleteMsgs);
        psmt.setString(1, user);
        psmt.setString(2, user);

        psmt.executeUpdate();

        String deleteUser = "delete from public.\"user\" where usuario = ?";
        psmt = con.prepareStatement(deleteUser);
        psmt.setString(1, user);

        int i = psmt.executeUpdate();

        if (i == 0) {
            con.close();
            psmt.close();

            return false;
        } else {
            con.close();
            psmt.close();

            return true;
        }

    }
}
