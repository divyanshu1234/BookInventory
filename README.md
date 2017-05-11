# BookInventory
## Android database tutorial for IIT Madras Webops Club Summer School 2017 ##

Branches named from **step1** to **step8** contain the code for the various steps involved in adding the database and integrating it with the Android framework.

The **master** branch contains the final code.

You can compare any two consecutive step branches to find the code difference and hence find what is done in that step.

Instructions:

**step1:** 
  * Create MainActivity and change the layout and add a menu to the toolbar for this activity

**step2:** 
  * Create AddBookActivity which can be used to add data to the database
  * Add functionality to the menu buttons to connect the two activities
       
**step3:** 
  * Contract class is created to store the constants related to the database and its tables
  * DatabaseHelper class is also made to create the database
  * Insert dummy data functionality is added and the database is queried to to show the data
       
**step4:** 
  * AddBookActivity is modified to store custom data to the database table

**step5:** 
  * Create content provider for the app
  * Define the provider in the Manifest.xml
  * Modify the contracts class
       
**step6:**
  * Modify the functions of the provider class

**step7:** 
  * Replace old CRUD code form the two activities and use content resolver to query and insert data
  * Add a delete all button to the MainActivity menu file and use content resolver to delete the data in the table
       
**step8:** 
  * Implement CursorLoader in the MainActivity
  * Add NotificationUri to the code so that CursorLoader is automatically called when data in the table is changed
  * Modify the displayData() function code in the MainActivity
