#![](http://i.imgur.com/S7xBFja.png)Sundy#

##![](http://i.imgur.com/S7xBFja.png)Leave Application System##
A leave application system for a Human Resource Department.
##Introduction##
###**1.How to run the demo?**###

	Download the project.jar into your disk, and then double click it to run.
	About how to manipulate it, you can look at the next part.
	Or you can see it in my Youtube Tutorial:
	    https://www.youtube.com/watch?v=TakfxiknC3c&feature=youtu.be
	
###2.How to run the program?###

   (1). Right click the project.
        Run As -> Java Application -> Login,
           Username:   staff
           Password: 123456     
        
   (2). Then you can try the following function.
      After you run the program, you will see a main GUI in front of you.
      You should double click the left tree list item to see want you want to do.
         a.HRStaff: 
            Double click HRStaff item
              ->Show All:  you can see the information of all staff.
              ->Add Staff: you can add the staff, including name, age, Title and the current staff's supervisor.
                    (The current system has three default supervisor, James, Eric and Petter, and Eric is director.)
              ->Delete Staff: HR can delete a  staff.
              
         b.Staff:
            Double click Staff item
               ->Leave Application:  apply for a leave from Date X to Date Y, and input who is your supervisor.
               ->Modify info: you also can Modify your leave information
               ->Show Progress: staff can enquiry his/her leave applicaiton progress at here.
               
         c.Supervisor:
            Double click Supervisor item
               ->Deal Leave: Supervisor and Director can edit the Endorse, Marked it if the supervisor endoreed. Ignored it if the supervisor decline.
               Only if all the direct or indirect supervisor endorsed this leave applicaiton, the staff can get a endorse of his/her leave.
               
         d.Director: (Here, Eric is a default director)
            Double click Director item:
               ->You can edit the Endorse, Marked it if director endorsed. Ignored it if the director declined
               (The director in this sytem is Eric, so you can only see  Eric's leave notice information here )
                     
  
###2.How to read the source code?###

	(1). Download the source code from github.
	    git clone https://github.com/sundyCoder/LeaveApplicationSystem
	    
	(2). Open your eclipse and import above source code.
	    Notice: Your eclipse must be support Maven(eclipse Mars2.0 support marven)
	            If you eclipse do not have Maven, you can do it as following.
	             http://www.tutorialspoint.com/maven/maven_eclispe_ide.htm
	    a. Open Eclipse.
	    b. Select File > Import > option
	    c. Select Maven Projects Option. Click on Next Button.
	    d. Select Project location, where a project was stored on your disk.
	    e. Click Finish Button.
	    f. Now, you can see the project in eclipse.
	    g. Souce code architecture:		
	    	   
						    
					├─src
					│  ├─com 
					│  │  ├─data                                        //Data processing
					│  │  │      CacheFile.java 
					│  │  │      DataSave.java
					│  │  │      HSSFReadWrite.java
					│  │  │      LeaveInfo.java
					│  │  │      StaffInfo.java
					│  │  │      StaffRelation.java
					│  │  │
					│  │  ├─date                                        //Date module
					│  │  │      DateLabelFormatter.java
					│  │  │      JDatePickerCalendar.java
					│  │  │
					│  │  ├─test                                        //Function unit test
					│  │  │      ProcessTableTest.java
					│  │  │      RetrieveLeaveInfoTest.java
					│  │  │      SupervisorTableTest.java
					│  │  │      TableModelTest.java
					│  │  │
					│  │  ├─util                                       //function module
					│  │  │      AddAccountItemDialog.java
					│  │  │      HRDataPanel.java
					│  │  │      LeaveApplicationForm.java
					│  │  │      LeaveProcessPanel.java
					│  │  │      StaffDataPanel.java
					│  │  │      SupervisorDataPanel.java
					│  │  │
					│  │  └─view
					│  │          Login.java                          //Login entry point
					│  │          MainFrame.java
					│  │          MTable.java
					│  │          TableModel.java
					│  │
					│  └─imgs                                         //GUI images
					│          back.JPG
					│          front.JPG
					│          leftpanel0.JPG
					│          leftpanel1.JPG
					│          leftpanel2.JPG
					│          logo.JPG
						
							  
	 h. From the above, you can have a rough knowledge of my project.
	    You can read from Login.java file, which starting from main() function.


###3. What kind of design patterns are used in this project?###
1. MVC
2. Observer
3. Simple Factory
4. Strategy
5. Chain of Responsibility

###4. Acknowledge###
   Please fell free to contact with me if you have any  question.

**(COMP5134 IS Development with OO Methods, Individual Project) copyright@Sundy**
