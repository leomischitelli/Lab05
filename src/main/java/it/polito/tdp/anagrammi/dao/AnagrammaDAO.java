package it.polito.tdp.anagrammi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.model.Anagramma;


public class AnagrammaDAO {
	
	public Set<Anagramma> getTutteLeParole(){ //inutile
		final String sql = "SELECT * FROM parola";
		
		Set<Anagramma> parole = new HashSet<Anagramma>();
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Anagramma a = new Anagramma(rs.getString("nome"), true);
				parole.add(a);
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return parole;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean isCorrect(String anagramma) {
		final String sql = "SELECT COUNT (*) as n FROM parola WHERE nome = ? ";
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet rs = st.executeQuery();
			
			boolean esito = false;
			
			while(rs.next()) {
				if(rs.getInt("n") == 1)
					esito = true;
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return esito;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
		
	}

}
