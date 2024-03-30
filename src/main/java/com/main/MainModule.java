package com.main;
import java.sql.SQLException;
import java.util.Scanner;

import com.dao.*;
import com.exceptions.ArtWorkNotFoundException;
import com.exceptions.UserNotFoundException;
import com.entity.Artwork;
import java.util.List;
import com.exceptions.ArtWorkNotFoundException;
import com.exceptions.UserNotFoundException;
public class MainModule {
	public static void main(String ar[]) {
		VirtualArtGalleryControllerImpl vc = new VirtualArtGalleryControllerImpl();
		
		Scanner i = new Scanner(System.in);
		int choice;
		String ch = "y";
		
		
		do {
			System.out.println("----- MENU -----"
					+ "\n1. Add Artwork"
					+ "\n2. Update Artwork"
					+ "\n3. Remove Artwork"
					+ "\n4. Get Artwork by Id"
					+ "\n5. Search Artworks"
					+ "\n6. Add Artwork to favorite"
					+ "\n7. Remove Artworks from Favorite"
					+ "\n8. Get User favorite artworks"
					+ "\nEnter your choice: ");
			choice = i.nextInt();
			switch(choice) {
			case 1:
				vc.addArtwork();
				break;
			case 2:
				vc.updateArtwork();
				break;
			case 3:
				vc.removeArtwork();
				break;
			case 4:
				vc.getArtworkById();
				break;
			case 5:
				try {
					vc.searchArtworks();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ArtWorkNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				try {
					vc.addArtworkToFavorite();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (UserNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			case 7:
				vc.removeArtworkFromFavorite();
				break;
			case 8:
				try {
					vc.getUserFavoriteArtworks();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Enter valid choice: ");
			}
//			i.nextLine();
			System.out.println("Enter y to continue: ");
//			si.nextLine();
				ch = i.next();
		}
		while(ch.equalsIgnoreCase("y"));
		System.out.println("Thanks for using our system");
		i.close();
	}
    }

