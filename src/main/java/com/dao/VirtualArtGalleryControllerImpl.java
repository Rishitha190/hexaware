package com.dao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import com.entity.Artwork;
import com.exceptions.ArtWorkNotFoundException;
import com.exceptions.UserNotFoundException;
public class VirtualArtGalleryControllerImpl implements IVirtualArtGalleryController {
IVirtualArtGallery vag = new VirtualArtGalleryImpl();
	
	Scanner sc = new Scanner(System.in);
	
	public Map<Integer,Artwork> artworkList = new HashMap<>();
	public List<Integer> favoriteArtworksList = new ArrayList<>();
	
	@Override
	public void addArtwork() {
		System.out.println("Enter Artwork ID: ");
		int artworkId = sc.nextInt(); 
		System.out.println("Enter title: ");
		String title = sc.next();
		System.out.println("Enter description: ");
		sc.nextLine();
		String description = sc.nextLine();
		System.out.println("Enter creation date (YYYY-MM-DD): ");
		LocalDate creationDate = LocalDate.parse(sc.next());
		System.out.println("Enter medium: ");
		String medium = sc.next();
		System.out.println("Enter Image URL: ");
		String imageUrl = sc.next();
		System.out.println("Enter artist ID: ");
		int artistId = sc.nextInt();
		Artwork artwork = new Artwork(artworkId,title,description,creationDate,medium,imageUrl,artistId);
		
		if(vag.addArtwork(artwork)) {
			artworkList.put(artworkId,artwork);
			System.out.println("Artwork added successfully");
		}
		
	}

	@Override
	public void updateArtwork() {
		System.out.println("Enter artwork id to update: ");
		int artworkId = sc.nextInt();
		System.out.println("Enter updated title: ");
		String title = sc.next();
		System.out.println("Enter updated description: ");
		sc.nextLine();
		String description = sc.nextLine();
		System.out.println("Enter updated creation date (YYYY-MM-DD): ");
		LocalDate creationDate = LocalDate.parse(sc.next());
		System.out.println("Enter updated medium: ");
		String medium = sc.next();
		System.out.println("Enter updated Image URL: ");
		String imageUrl = sc.next();
		System.out.println("Enter updated artist ID: ");
		int artistId = sc.nextInt();
		
		Artwork artwork = new Artwork(artworkId,title,description,creationDate,medium,imageUrl,artistId);
		
		if(vag.updateArtwork(artwork)) {
			artworkList.put(artworkId, artwork);
			System.out.println("Artwork updated successfully");			
		}
	}

	@Override
	public void removeArtwork() {
		System.out.println("Enter artwork ID to remove: ");
		int artworkId = sc.nextInt();
		if(vag.removeArtwork(artworkId)) {
			artworkList.remove(artworkId);
			System.out.println("Artwork removed successfully");
		}
		
	}

	@Override
	public void getArtworkById() {
		System.out.println("Enter artwork ID to retrieve the artwork: ");
		int artworkId = sc.nextInt();
		Artwork artwork = vag.getArtworkById(artworkId);
		System.out.println("Artwork ID: "+ artwork.getArtworkId()
				+ "\nTitle: "+ artwork.getTitle()
				+ "\nDescription: "+ artwork.getDescription()
				+ "\nCreation Date: "+ artwork.getCreationDate()
				+ "\nMedium: "+artwork.getMedium()
				+ "\nImage URL: "+artwork.getImageUrl()
				+ "\nArtist Id: "+artwork.getArtistId());
	}

	@Override
	public void searchArtworks() throws SQLException, ArtWorkNotFoundException {
		System.out.println("Enter keyword to search: ");
		String keyword = sc.next();
		List<Artwork> searchResult = vag.searchArtworks(keyword);
		System.out.println("SEARCH RESULTS ------ ");
		for(Artwork artwork:searchResult) {
			System.out.println(artwork.toString());
		}
		
	}

	@Override
	public void addArtworkToFavorite() throws SQLException, UserNotFoundException {
		System.out.println("Enter user Id: ");
		int userId = sc.nextInt();
		System.out.println("Enter artwork Id: ");
		int artworkId = sc.nextInt();
		if(vag.addArtworkToFavorite(userId,artworkId)) {
			System.out.println("Added to favorite");
		}
		else {
			System.out.println("Could not add to favorites");
		}
		
	}

	@Override
	public void removeArtworkFromFavorite() {
		System.out.println("Enter artwork Id to remove from favorites: ");
		int artworkId = sc.nextInt();
		if(vag.removeArtwork(artworkId)) {
			System.out.println("Removed from favorites");
		}
		else {
			System.out.println("Could not remove artwork from favorites");
		}
		
	}

	@Override
	public void getUserFavoriteArtworks() throws SQLException, UserNotFoundException {
		System.out.println("Enter user Id to retrieve favorite artworks: ");
		int userId = sc.nextInt();
		if(vag.getUserFavoriteArtworks(userId)) {
			System.out.println("-----------------");
		}		
	}
}
