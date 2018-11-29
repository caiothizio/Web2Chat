/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caiot
 */
public class BancoMsg {
    
    public static boolean newMsg(String from, String msg, String to, Timestamp date) throws SQLException {
        String sql = "INSERT INTO public.\"mensagem\" values (?, ?, ?, ?)";

        Connection con = Conexao.getConnection();
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, from);
        psmt.setString(2, msg);
        psmt.setString(3, to);
        psmt.setTimestamp(4, date);

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
    
    public static List<MsgObject> getMsgs(String user1, String user2) throws SQLException, IOException {
        if (user1 == null || user2 == null) {
            return null;
        }
  
        String sql = "select * from public.\"mensagem\" where (remetente = ? or remetente = ?) and (destinatario = ? or destinatario = ?) order by data asc";
        Connection con = Conexao.getConnection();
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, user1);
        psmt.setString(2, user2);
        psmt.setString(3, user1);
        psmt.setString(4, user2);

        ResultSet rs = psmt.executeQuery();
        List<MsgObject> lista = new ArrayList<>();

        if (rs.next()) {
            do {
                MsgObject msg = new MsgObject();

                msg.setDate(rs.getTimestamp("data"));
                msg.setFrom(rs.getString("remetente"));
                msg.setMessage(rs.getString("texto"));
                msg.setTo(rs.getString("destinatario"));
               
                lista.add(msg);
            } while (rs.next());
            
            psmt.close();
            con.close();
            
            return lista;
        } else {
            psmt.close();
            con.close();

            return null;
        }
    }
}
