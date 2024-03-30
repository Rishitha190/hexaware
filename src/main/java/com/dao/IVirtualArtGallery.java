package com.dao;
import java.sql.SQLException;
import java.util.List;
import com.entity.Artwork;
import com.exception.ArtWorkNotFoundException;
import com.exception.UserNotFoundException;

public interface IVirtualArtGallery {
	  boolean addArtwork(Artwork artwork);
	    
	  boolean updateArtwork(Artwork artwork);
	   
      boolean removeArtwork(int artworkId);
	    
	 
	  Artwork getArtworkById(int artworkId);
	    
	    List<Artwork> searchArtworks(String keyword) throws SQLException, ArtWorkNotFoundException;
	    
	    boolean addArtworkToFavorite(int userId, int artworkId) throws SQLException, UserNotFoundException;
	 
	    boolean removeArtworkFromFavorite(int userId, int artworkId) throws SQLException, UserNotFoundException;
	   
	    boolean getUserFavoriteArtworks(int userId) throws SQLException, UserNotFoundException;

}
