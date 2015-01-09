# Currency Converter 

PROJECT SPECIFICATIONS:

Develop a three-tier Web-based application in Java for on-line currency conversion that converts a specified amount from one currency to another, e.g. from SEK to EUR.
Your converter should be able to convert between at least 4 different currencies.
The converter should provide a Web-based client with a GUI (e.g., an HTML-form or an applet), which allows the user to choose a currency to convert from, and a currency to convert to; to enter an amount to be converted, and to submit the conversion request to the converter that runs on the server side of the application. The converter processes the request and sends the result to the client to be displayed in the user's Web-browser on the client side.
You must use JSF, EJB and JPA technologies to develop the converter. The client submits a request to the converter web tier (JSF) that uses an EJB to process the conversion request and to generate the result of conversion that is displayed in the user's Web-browser.
The conversion must be done by a session EJB. Conversion rates must be persistent entities (stored in a database). Java Persistence API must be used to represent currency rates stored in the database.
You are not required to provide any additional web interface for changing conversion rates but you should be able to store currency rates in the database, e.g. using the database administrator interface.

NOTE: to run the project in NetBeans a proper JavaDB has to be created in order to store the currency conversion values
