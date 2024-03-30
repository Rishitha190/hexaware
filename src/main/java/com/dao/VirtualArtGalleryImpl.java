package com.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.entity.Artwork;
import com.exception.ArtWorkNotFoundException;
import com.exception.UserNotFoundException;
import com.util.DBConnection;
public class VirtualArtGalleryImpl implements IVirtualArtGallery {
	Connection conn;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	
//	int artworkId, String title, String description, LocalDate creationDate,String medium, String imageUrl
	
	@Override
	public boolean addArtwork(Artwork artwork) {
		boolean done = false;
		int n = 0;
			try{
				conn = DBConnection.getConnection();
				ps = conn.prepareStatement("insert into artwork values (?,?,?,?,?,?,?);");
				
				ps.setInt(1, artwork.getArtworkId());
				ps.setNString(2, artwork.getTitle());
				ps.setNString(3, artwork.getDescription());
				ps.setDate(4, Date.valueOf(artwork.getCreationDate()));
				ps.setNString(5, artwork.getMedium());
				ps.setNString(6, artwork.getImageUrl());
				ps.setInt(7, artwork.getArtistId());
				
				try{
					n = ps.executeUpdate();
				}
				catch(SQLIntegrityConstraintViolationException c) {
					c.printStackTrace();
				}
				if(n==1) {
					done=true;
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
		return done;
	}

	@Override
	public boolean updateArtwork(Artwork artwork) {
		boolean done = false;
		try{
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("update artwork set "
					+ "title=?,"
					+ "description=?,"
					+ "creationdate=?,"
					+ "medium=?,"
					+ "imageurl=?,"
					+ "artistid=?"
					+ " where artworkid = ? ;");
			
			
			ps.setNString(1, artwork.getTitle());
			ps.setNString(2, artwork.getDescription());
			ps.setDate(3, Date.valueOf(artwork.getCreationDate()));
			ps.setNString(4, artwork.getMedium());
			ps.setNString(5, artwork.getImageUrl());
			ps.setInt(6, artwork.getArtistId());
			ps.setInt(7, artwork.getArtworkId());
			int n = 0;
			try{
				n = ps.executeUpdate();
			}
			catch(SQLIntegrityConstraintViolationException c) {
				c.printStackTrace();
			}
			if(n==1) {
				done=true;
			}
			return done;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return done;
	}

	@Override
	public boolean removeArtwork(int artworkId) {
		boolean done = false;
		
		conn = DBConnection.getConnection();
		try {
			ps = conn.prepareStatement("delete from artwork where artworkid = ?");
			ps.setInt(1, artworkId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		int n = 0; 
		try {
			n = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(n>0) {
			done = true;
		}
		return done;
	}

	@Override
	public Artwork getArtworkById(int artworkId) {
		Artwork artwork = new Artwork();
		conn = DBConnection.getConnection();
		try {
			ps = conn.prepareStatement("select * from artwork where artworkid = ?");
			ps.setInt(1, artworkId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				artwork.setArtworkId(artworkId);
				artwork.setTitle(rs.getNString(2));
				artwork.setDescription(rs.getNString(3));
				artwork.setCreationDate(LocalDate.parse(rs.getDate(4).toString()));
				artwork.setMedium(rs.getNString(5));
				artwork.setImageUrl(rs.getNString(6));
				artwork.setArtistId(rs.getInt(7));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return artwork;
	}

	@Override
	public List<Artwork> searchArtworks(String keyword) throws SQLException, ArtWorkNotFoundException {
		Set<Artwork> searchSet = new HashSet<>();
		conn = DBConnection.getConnection();
		ps = conn.prepareStatement("select * from artwork where title = ?");
		ps.setNString(1, keyword);
		rs = ps.executeQuery();
		if(rs.next()) {
			do {
				Artwork artwork = new Artwork();
				artwork.setArtworkId(rs.getInt(1));
				artwork.setTitle(rs.getNString(2));
				artwork.setDescription(rs.getNString(3));
				artwork.setCreationDate(LocalDate.parse(rs.getDate(4).toString()));
				artwork.setMedium(rs.getNString(5));
				artwork.setImageUrl(rs.getNString(6));
				searchSet.add(artwork);
			}while(rs.next());
		}
		ps = conn.prepareStatement("select * from artwork where description = ?");
		ps.setNString(1, keyword);
		rs = ps.executeQuery();
		if(rs.next()) {
			do {
				Artwork artwork = new Artwork();
				artwork.setArtworkId(rs.getInt(1));
				artwork.setTitle(rs.getNString(2));
				artwork.setDescription(rs.getNString(3));
				artwork.setCreationDate(LocalDate.parse(rs.getDate(4).toString()));
				artwork.setMedium(rs.getNString(5));
				artwork.setImageUrl(rs.getNString(6));
				searchSet.add(artwork);
			}while(rs.next());
		}
		ps = conn.prepareStatement("select * from artwork where medium = ?");
		ps.setNString(1, keyword);
		rs = ps.executeQuery();
		if(rs.next()) {
			do {
				Artwork artwork = new Artwork();
				artwork.setArtworkId(rs.getInt(1));
				artwork.setTitle(rs.getNString(2));
				artwork.setDescription(rs.getNString(3));
				artwork.setCreationDate(LocalDate.parse(rs.getDate(4).toString()));
				artwork.setMedium(rs.getNString(5));
				artwork.setImageUrl(rs.getNString(6));
				searchSet.add(artwork);
			}while(rs.next());
		}
		List<Artwork> searchResults = new ArrayList<>(searchSet);
		if(searchResults.isEmpty())
			throw new ArtWorkNotFoundException();
		return searchResults;
	}

	@Override
	public boolean addArtworkToFavorite(int userId, int artworkId) throws SQLException, UserNotFoundException {
		conn = DBConnection.getConnection();
		
		ps = conn.prepareStatement("insert into user_favorite_artwork values(?,?);");
		ps.setInt(1, userId);
		ps.setInt(2, artworkId);
		ps.executeUpdate();
		
		
		ps = conn.prepareStatement("update user set favoriteArtworks = ? where userId=?;");
		ps.setInt(1, userId);
		ps.setInt(2, artworkId);
		
		int n = ps.executeUpdate();
		
		if(n>0) 		return true;
		if(n==0)		throw new UserNotFoundException("User or Artwork not found");
		return false;
	}

	@Override
	public boolean removeArtworkFromFavorite(int userId, int artworkId) throws SQLException, UserNotFoundException {
		ps = conn.prepareStatement("delete from user_favorite_artwork where userId=? and artworkId=?;");
		ps.setInt(1, userId);
		ps.setInt(2, artworkId);
		int n = ps.executeUpdate();
		if(n>0) return true;
		if(n==0) throw new UserNotFoundException("User or Artwork not found");
		return false;
	}

	@Override
	public boolean getUserFavoriteArtworks(int userId) throws SQLException, UserNotFoundException {
		conn = DBConnection.getConnection();
		ps = conn.prepareStatement("select * from artwork where artworkId in ("
				+ "select artworkid from user_favorite_artwork where userId=?);");
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		if(rs.next()) {
			do {
				Artwork artwork = new Artwork(rs.getInt(1),rs.getNString(2),
						rs.getNString(3),LocalDate.parse(rs.getDate(4).toString()),
						rs.getNString(5),rs.getNString(6),rs.getInt(7));
				System.out.println(artwork.toString());
			}while(rs.next());
			return true;
		}
		else {
			throw new UserNotFoundException();
			
		}
//		return false;
	}

}
