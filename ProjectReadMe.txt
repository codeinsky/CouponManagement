										CuponManagment System 

	- GitHub project point
		GitHub : https://github.com/codeinsky/CuponManagment.git
		GitHub Basic Commands : 
						echo "# CuponManagment" >> README.md
						git init
						git add README.md
						git commit -m "first commit"
						git remote add origin https://github.com/codeinsky/CuponManagment.git
						git push -u origin master
- Project Steps 
 New step
 
 
  								UBUNTO JDataBase connections steps:
 1. Server start 
 Go to jdbc:derby://10.13.1.1:1527/CuponSystemDB
 2. Start the server with ./NetworkServerControl start/shutdown 
 3. Open other terminal start IJ 
 4. Connect to local SQl server   : 'jdbc:derby:CuponSystemDB;'
 5. Show tables and so on .....
 
 								JAVA Derby DB Driver add to a project 
 1. Right click on the project name then go to build path 
 2. Build path 
 3. Add external archive  and then java client DB 
 
 									Project Progress 
1.Phase 1
	* connection pool - in progress  
		- full Threads test - done  
		- connection statement works - DONE 
		- get /return - DONE  
 		- Exceptions change to origin  - NEED  
 		- wait and notify when all 10 connections a used - done 
 		- close connection with check that all 10 connections are returned - need 
 	 * Data Base tables - done 
 	 * Beans - done 
 	 	- what Coupon collection to use 
 	 