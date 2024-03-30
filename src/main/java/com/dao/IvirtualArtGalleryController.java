package com.dao;
import java.sql.SQLException;

import com.exceptions.ArtWorkNotFoundException;
import com.exceptions.UserNotFoundException;
public interface IvirtualArtGalleryController {
	 // Artwork Management

    /**
     * Adds a new artwork to the virtual art gallery.
     */
    public void addArtwork();

    /**
     * Updates an existing artwork in the virtual art gallery.
     */
    public void updateArtwork();

    /**
     * Removes an artwork from the virtual art gallery.
     */
    public void removeArtwork();

    /**
     * Retrieves an artwork by its unique identifier.
     */
    public void getArtworkById();

    /**
     * Searches for artworks in the virtual art gallery.
     * @throws SQLException if a database access error occurs
     * @throws ArtWorkNotFoundException if the artwork is not found
     */
    public void searchArtworks() throws SQLException, ArtWorkNotFoundException;
    
    // User favorites
    
    /**
     * Adds an artwork to the favorites of a user.
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    public void addArtworkToFavorite() throws SQLException, UserNotFoundException;

    /**
     * Removes an artwork from the favorites of a user.
     */
    public void removeArtworkFromFavorite();

    /**
     * Retrieves the favorite artworks of a user.
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    public void getUserFavoriteArtworks() throws SQLException, UserNotFoundException;
}
