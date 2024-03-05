create database artgallery;
drop database artgallery;
create database artgallery;
use artgallery;
-- Create the Artists table
CREATE TABLE Artists (
 ArtistID INT PRIMARY KEY,
 Name VARCHAR(255) NOT NULL,
 Biography TEXT,
 Nationality VARCHAR(100));
 -- Insert sample data into the Artists table
INSERT INTO Artists (ArtistID, Name, Biography, Nationality) VALUES
 (1, 'Pablo Picasso', 'Renowned Spanish painter and sculptor.', 'Spanish'),
 (2, 'Vincent van Gogh', 'Dutch post-impressionist painter.', 'Dutch'),
 (3, 'Leonardo da Vinci', 'Italian polymath of the Renaissance.', 'Italian');
-- Create the Categories table
CREATE TABLE Categories (
 CategoryID INT PRIMARY KEY,
 Name VARCHAR(100) NOT NULL);
 -- Insert sample data into the Categories table
INSERT INTO Categories (CategoryID, Name) VALUES
 (1, 'Painting'),
 (2, 'Sculpture'),
 (3, 'Photography');

-- Create the Artworks table
CREATE TABLE Artworks (
 ArtworkID INT PRIMARY KEY,
 Title VARCHAR(255) NOT NULL,
 ArtistID INT,
 CategoryID INT,
 Year INT,
 Description TEXT,
 ImageURL VARCHAR(255),
 FOREIGN KEY (ArtistID) REFERENCES Artists (ArtistID),
 FOREIGN KEY (CategoryID) REFERENCES Categories (CategoryID));
 -- Insert sample data into the Artworks table
INSERT INTO Artworks (ArtworkID, Title, ArtistID, CategoryID, Year, Description, ImageURL) VALUES
 (1, 'Starry Night', 2, 1, 1889, 'A famous painting by Vincent van Gogh.', 'starry_night.jpg'),
 (2, 'Mona Lisa', 3, 1, 1503, 'The iconic portrait by Leonardo da Vinci.', 'mona_lisa.jpg'),
 (3, 'Guernica', 1, 1, 1937, 'Pablo Picassos powerful anti-war mural.', 'guernica.jpg');
 
-- Create the Exhibitions table
CREATE TABLE Exhibitions (
 ExhibitionID INT PRIMARY KEY,
 Title VARCHAR(255) NOT NULL,
 StartDate DATE,
 EndDate DATE,
 Description TEXT);
 -- Insert sample data into the Exhibitions table
INSERT INTO Exhibitions (ExhibitionID, Title, StartDate, EndDate, Description) VALUES
 (1, 'Modern Art Masterpieces', '2023-01-01', '2023-03-01', 'A collection of modern art masterpieces.'),
 (2, 'Renaissance Art', '2023-04-01', '2023-06-01', 'A showcase of Renaissance art treasures.');
-- Create a table to associate artworks with exhibitions
CREATE TABLE ExhibitionArtworks (
 ExhibitionID INT,
 ArtworkID INT,
 PRIMARY KEY (ExhibitionID, ArtworkID),
 FOREIGN KEY (ExhibitionID) REFERENCES Exhibitions (ExhibitionID),
 FOREIGN KEY (ArtworkID) REFERENCES Artworks (ArtworkID));
 -- Insert artworks into exhibitions
INSERT INTO ExhibitionArtworks (ExhibitionID, ArtworkID) VALUES
 (1, 1),
 (1, 2),
 (1, 3),
 (2, 2);
-- Q1 Retrieve the names of all artists along with the number of artworks they have in the gallery, and list them in descending order of the number of artworks
select ar.Name,count(aw.title) as artworkcount from Artists as ar left join Artworks as aw on ar.ArtistID=aw.ArtistID group by ar.name order by  artworkcount desc; 

-- Q2 List the titles of artworks created by artists from 'Spanish' and 'Dutch' nationalities, and order them by the year in ascending order
select Artworks.Title from Artworks left join Artists on Artworks.ArtistID=Artists.ArtistID where Artists.Nationality ='Spanish' or Artists.Nationality='Dutch' order by Artworks.Year;

-- Q3 Find the names of all artists who have artworks in the 'Painting' category, and the number of artworks they have in this category.
select Artists.Name,count(Artworks.title) as paintingcategory from Artists left join Artworks on Artworks.ArtistID=Artists.ArtistID
left join  Categories on Artworks.CategoryID=Categories.CategoryID where Categories.Name='Painting' group by Artists.Name;

-- Q4 List the names of artworks from the 'Modern Art Masterpieces' exhibition, along with their along with their artists and categories
select Artworks.Title,Artists.Name,Categories.Name from Artworks join Artists on Artworks.ArtistID=Artists.ArtistID 
join Categories on Artworks.CategoryID=Categories.CategoryID join ExhibitionArtworks on Artworks.ArtworkID=ExhibitionArtworks.ArtworkID
join Exhibitions on Exhibitions.ExhibitionID=ExhibitionArtworks.ExhibitionID where Exhibitions.Title='Modern Art Masterpieces';

-- Q5 Find the artists who have more than two artworks in the gallery.
select ar.Name from Artists as ar left join Artworks as aw on ar.ArtistID=aw.ArtistID group by ar.Name having count(aw.title)>2 ;

-- Q6 Find the titles of artworks that were exhibited in both 'Modern Art Masterpieces' and 'Renaissance Art' exhibitions
select Artworks.Title from Artworks 
join Categories on Artworks.CategoryID=Categories.CategoryID join ExhibitionArtworks on Artworks.ArtworkID=ExhibitionArtworks.ArtworkID
join Exhibitions on Exhibitions.ExhibitionID=ExhibitionArtworks.ExhibitionID where Exhibitions.Title='Modern Art Masterpieces' or Exhibitions.Title='Renaissance Art';

-- Q7 Find the total number of artworks in each category
select Categories.Name,count(Artworks.title) as countcategory from Categories
left join  Artworks on Artworks.CategoryID=Categories.CategoryID group by Categories.Name;

-- Q8 List artists who have more than 3 artworks in the gallery.
select ar.Name from Artists as ar left join Artworks as aw on ar.ArtistID=aw.ArtistID group by ar.Name having count(aw.title)>3 ;

-- Q9 Find the artworks created by artists from a specific nationality (e.g., Spanish).
select Artworks.Title from Artworks left join Artists on Artworks.ArtistID=Artists.ArtistID where Artists.Nationality ='Spanish' ;

-- Q10 List exhibitions that feature artwork by both Vincent van Gogh and Leonardo da Vinci.
select Exhibitions.Title from Exhibitions left join  ExhibitionArtworks on Exhibitions.ExhibitionID=ExhibitionArtworks.ExhibitionID 
left join Artworks on Artworks.ArtworkID=ExhibitionArtworks.ArtworkID 
left join Artists on Artworks.ArtistID=Artists.ArtistID where Artists.Name='Vincent van Gogh' or Artists.Name='Leonardo da Vinci';

-- Q11 Find all the artworks that have not been included in any exhibition.
 select * from Artworks where Artworks.Title not in(select distinct Artworks.Title from ExhibitionArtworks inner join Artworks on Artworks.ArtworkID =ExhibitionArtworks.ArtworkID);
 
 -- Q12 List artists who have created artworks in all available categories.
 select artists.Name,count(Categories.Name) as categorycount from Artists left join Artworks  on Artists.ArtistID=Artworks.ArtistID 
 left join  Categories on Artworks.CategoryID=Categories.CategoryID group by Artists.Name having count(Categories.Name)=(select count(*) from Categories);
 
 -- Q13 List the total number of artworks in each category.
 select Categories.Name,count(Artworks.title) as countcategory from Categories
left join  Artworks on Artworks.CategoryID=Categories.CategoryID group by Categories.Name;

-- Q14 Find the artists who have more than two artworks in the gallery.
select ar.Name from Artists as ar left join Artworks as aw on ar.ArtistID=aw.ArtistID group by ar.Name having count(aw.title)>2 ;

-- Q15 List the categories with the average year of artworks they contain, only for categories with more than 1 artwork
SELECT Categories.Name AS Category, AVG(Artworks.Year) AS AverageYear FROM Categories
JOIN Artworks ON Categories.CategoryID = Artworks.CategoryID GROUP BY Categories.Name HAVING COUNT(Artworks.ArtworkID) > 1;

-- Q16 Find the artworks that were exhibited in the 'Modern Art Masterpieces' exhibition.
SELECT Artworks.Title, Artists.Name AS Artist FROM ExhibitionArtworks
JOIN Artworks ON ExhibitionArtworks.ArtworkID = Artworks.ArtworkID
JOIN Artists ON Artworks.ArtistID = Artists.ArtistID WHERE ExhibitionArtworks.ExhibitionID = (SELECT ExhibitionID FROM Exhibitions WHERE Title = 'Modern Art Masterpieces');

-- Q17 Find the categories where the average year of artworks is greater than the average year of all ARTWORKS
SELECT Categories.Name AS Category, AVG(Artworks.Year) AS AvgCategoryYear FROM Categories
JOIN Artworks ON Categories.CategoryID = Artworks.CategoryID GROUP BY Categories.Name HAVING AVG(Artworks.Year) > (SELECT AVG(Year) FROM Artworks);

-- Q18 List the artworks that were not exhibited in any exhibition.
select * from Artworks where Artworks.Title not in(select distinct Artworks.Title from ExhibitionArtworks inner join Artworks on Artworks.ArtworkID =ExhibitionArtworks.ArtworkID);

-- Q19 Show artists who have artworks in the same category as "MonaLisa."
SELECT DISTINCT Artists.Name AS Artist FROM Artists
JOIN Artworks ON Artists.ArtistID = Artworks.ArtistID
JOIN Categories ON Artworks.CategoryID = Categories.CategoryID WHERE Categories.CategoryID = (SELECT CategoryID FROM Artworks WHERE Title = 'Mona Lisa');

-- Q20 List the names of artists and the number of artworks they have in the gallery.
select ar.Name,count(aw.title) as artworkcount from Artists as ar left join Artworks as aw on ar.ArtistID=aw.ArtistID group by ar.name;