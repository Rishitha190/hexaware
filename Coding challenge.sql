create database CrimeManagement;
use CrimeManagement;
CREATE TABLE Crime (
 CrimeID INT PRIMARY KEY,
 IncidentType VARCHAR(255),
 IncidentDate DATE,
 Location VARCHAR(255),
 Description TEXT,
 Status VARCHAR(20));
 
INSERT INTO Crime VALUES
(1, 'Robbery', '2023-09-15', '123 Main St, Cityville', 'Armed robbery at a convenience store', 'Open'),
(2, 'Homicide', '2023-09-20', '456 Elm St, Townsville', 'Investigation into a murder case', 'UnderInvestigation'),
(3, 'Theft', '2023-09-10', '789 Oak St, Villagetown', 'Shoplifting incident at a mall', 'Closed');

select * from crime;

CREATE TABLE Victim (
 VictimID INT PRIMARY KEY,
 CrimeID INT,
 Name VARCHAR(255),
 ContactInfo VARCHAR(255),
 Injuries VARCHAR(255),
 FOREIGN KEY (CrimeID) REFERENCES Crime(CrimeID));
 
INSERT INTO Victim VALUES
(1, 1, 'John Doe', 'johndoe@example.com', 'Minor injuries'),
(2, 2, 'Jane Smith', 'janesmith@example.com', 'Deceased'),
(3, 3, 'Alice Johnson', 'alicejohnson@example.com', 'None');
 
 select * from Victim;
 
CREATE TABLE Suspect (
 SuspectID INT PRIMARY KEY,
 CrimeID INT,
 Name VARCHAR(255),
 Description TEXT,
 CriminalHistory TEXT,
 FOREIGN KEY (CrimeID) REFERENCES Crime(CrimeID));

INSERT INTO Suspect VALUES
(1, 1, 'Robber 1', 'Armed and masked robber', 'Previous robbery convictions'),
(2, 2, 'Unknown', 'Investigation ongoing', NULL),
(3, 3, 'Suspect 1', 'Shoplifting suspect', 'Prior shoplifting arrests');

select * from suspect;

-- 1. Select all open incidents.
select * from crime where status='open';

-- 2.Find the total number of incidents.
select count(*) from Crime as totalIncidents;

-- 3. List all unique incident types.
select distinct IncidentType from crime;

-- 4.Retrieve incidents that occurred between '2023-09-01' and '2023-09-10'.
select * from Crime where IncidentDate between '2023-09-01' and '2023-09-10';

-- 5. List persons involved in incidents in descending order of age.
Alter table Victim add Column age int;
update Victim set age=30 where VictimId=1;
Update Victim set age=35 where VictimId=2;
Update Victim set age=40 where VictimId =3;

Alter table Suspect add Column age int;
Update Suspect set age=24 where SuspectId=1;
Update Suspect set age=27 where SuspectId=2;
Update Suspect set age=30 where SuspectId=3;

select * from Victim union all select * from Suspect order by age desc; 


-- 6. Find the average age of persons involved in incidents.
select avg(age) as AverageAge from(select Age from Victim union all select Age from Suspect) as allpersons;

-- 7.List incident types and their counts, only for open cases.
select IncidentType,count(*) from crime where status='open' group by IncidentType;

-- 8. Find persons with names containing 'Doe'.
select Name from Victim where Name like '%Doe%';

-- 9. Retrieve the names of persons involved in open cases and closed cases.
select Name from Victim where CrimeID IN (select CrimeID from Crime where Status = 'Open')
UNION
select Name from Suspect where CrimeID IN (select CrimeID from Crime where Status = 'Open');

select Name from Victim where CrimeID in (select CrimeID from Crime where Status = 'Closed')
UNION
select Name from Suspect where CrimeID in (select CrimeID from Crime where status = 'Closed');


-- 10. List incident types where there are persons aged 30 or 35 involved.
select distinct Crime.IncidentType from Crime join Victim on Crime.CrimeID=Victim.CrimeId where Victim.Age in (30,35)
union
select distinct Crime.IncidentType from Crime join Suspect on Crime.CrimeID=Suspect.CrimeId where Suspect.Age in (30,35);

-- 11. Find persons involved in incidents of the same type as 'Robbery'.
select Name,'Victim' as personType,'Robbery' as IncidentType from Victim JOIN Crime on Victim.CrimeId=Crime.CrimeId where Crime.IncidentTYpe="Robbery"
UNION ALL
select Name,'Suspect' as personType,'Robbery' as IncidentType from Suspect JOIN Crime on Suspect.CrimeId=Crime.CrimeId where Crime.IncidentTYpe="Robbery";

-- 12.List incident types with more than one open case.
SELECT IncidentType FROM Crime WHERE Status = 'Open' GROUP BY IncidentType HAVING COUNT(*) > 1;

-- 13.List all incidents with suspects whose names also appear as victims in other incidents.
Select distinct Crime.* from Crime
JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID
JOIN Victim ON Suspect.Name = Victim.Name AND Suspect.CrimeID <> Victim.CrimeID;

-- 14. Retrieve all incidents along with victim and suspect details.
select C.*, V.Name AS VictimName, V.ContactInfo, V.Injuries, S.Name AS SuspectName, S.Description AS SuspectDescription, S.CriminalHistory
FROM Crime C
left join Victim V on C.CrimeID = V.CrimeID
left join Suspect S on C.CrimeID = S.CrimeID;

-- 15. Find incidents where the suspect is older than any victim.
select distinct Crime.* from Crime join Suspect ON Crime.CrimeID = Suspect.CrimeID
where Suspect.Age > (select MAX(Age) from Victim where Victim.CrimeID = Suspect.CrimeID);

-- 16.Find suspects involved in multiple incidents:
SELECT Name, COUNT(*) AS Incident_Count FROM Suspect
INNER JOIN Crime ON Suspect.CrimeID = Crime.CrimeID GROUP BY Name HAVING COUNT(*) > 1;

-- 17. List incidents with no suspects involved.
SELECT * FROM Crime
LEFT JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID WHERE Suspect.SuspectID IS NULL;

-- 18.List all cases where at least one incident is of type 'Homicide' and all other incidents are of type 'Robbery'.
select c.CrimeID, c.IncidentType, c.IncidentDate, c.Location, c.Description, c.Status
from Crime c where IncidentType = 'Homicide'
or (IncidentType = 'Robbery' and not exists (select 1 from Crime c2 where c2.CrimeID = c.CrimeID and c2.IncidentType != 'Robbery'));

-- 19. Retrieve a list of all incidents and the associated suspects, showing suspects for each incident, or 'No Suspect' if there are none.
SELECT c.CrimeID, c.IncidentType, c.IncidentDate, c.Location, c.Description, c.Status,
COALESCE(s.Name, 'No Suspect') AS SuspectName FROM Crime c LEFT JOIN Suspect s ON c.CrimeID = s.CrimeID;  

-- 20.List all suspects who have been involved in incidents with incident types 'Robbery' or 'Assault'
SELECT DISTINCT s.SuspectID, s.Name
FROM Suspect s JOIN Crime c ON s.CrimeID = c.CrimeID WHERE c.IncidentType IN ('Robbery', 'Assault');