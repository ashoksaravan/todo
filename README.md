This project is to manage internal tasks with in team.

To import the project into Eclipse, follow the steps below

    1. Ensure Maven is installed
    2. Open a command window (Windows) or a terminal (Linux/Mac)
    3. Run the following command:
        
        mvn eclipse:eclipse -Dwtpversion=2.0

Preparing the data source

    1. To Install and Run MongoDB (see http://docs.mongodb.org/manual/tutorial/install-mongodb-on-windows/)
    2. There's no need to create any collections because Spring will create them automatically
    3. There's no need to populate the database with sample data because our InitMongoService will insert our sample data automatically


Building and Running Application

    1. Open a command window (Windows) or a terminal (Linux/Mac)
    2. Run the following command:

        mvn tomcat:run
    
    3. Open a browser
    4. Enter the following URL (8080 is the default port for Tomcat):

        http://localhost:8080/todo/
        
