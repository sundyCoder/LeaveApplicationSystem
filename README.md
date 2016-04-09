# LeaveApplicationSystem 

(COMP5134 IS Development with OO Methods, Individual Project) @copyright

A leave application system for a Human Resource Department.


1.How to run the demo?

	Download the project.jar into your disk, and then double click it to run.
	About how to manipulate it, you can look at the next part.
	
2.How to run the program?

   (1). Right click the project.
        Run As -> Java Application -> MainFrame      
        
   (2). Then you can try the following function.
      After you run the program, you will see a main GUI in front of you.
      You should double click the left tree list item to see want you want.
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
         c.Supervisor:
            Double click Supervisor item
               ->You can edit the Endorse, Marked it if the supervisor endoreed. Ignored it if the supervisor decline.
         d.Director:
            Double click Director item:
               ->You can edit the Endorse, Marked it if director endorsed. Ignored it if the director declined
               (The director in this sytem is Eric, so you can only see  Eric's leave notice information )
                     
  
2.How to read the source code?
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
							©¤src
							  ©À©¤com
							  ©¦  ©À©¤data                                 //Data processing
							  ©¦  ©¦      DataSave.java
							  ©¦  ©¦      HSSFReadWrite.java
							  ©¦  ©¦      LeaveInfo.java
							  ©¦  ©¦      StaffInfo.java
							  ©¦  ©¦
							  ©¦  ©À©¤date                               //Date module
							  ©¦  ©¦      DateLabelFormatter.java
							  ©¦  ©¦      JDatePickerCalendar.java
							  ©¦  ©¦
							  ©¦  ©À©¤test                               //Function unit test
							  ©¦  ©¦      RetrieveLeaveInfoTest.java
							  ©¦  ©¦      SupervisorTableTest.java
							  ©¦  ©¦      TableModelTest.java
							  ©¦  ©¦
							  ©¦  ©À©¤util                               //Test
							  ©¦  ©¦      AddAccountItemDialog.java
							  ©¦  ©¦      HRDataPanel.java
							  ©¦  ©¦      LeaveApplicationForm.java
							  ©¦  ©¦      StaffDataPanel.java
							  ©¦  ©¦      SupervisorDataPanel.java
							  ©¦  ©¦
							  ©¦  ©¸©¤view
							  ©¦          MainFrame.java                 //Main entry point, you can read from here.
							  ©¦          MTable.java
							  ©¦          SupervisorLogin.java
							  
	 h. From the above, you can have a rough knowledge of my project.
	    You can read from MainFrame.java file, which starting from main() function.


3. What kind of design patterns are used in this project?

  (1). MVC
  (2). Observer
  (3). Simple Factory.
  (4). Strategy    
  (5). Chain of Responsibility.


4. Acknowledge
   Please fell free to contact with me if you have any  question.