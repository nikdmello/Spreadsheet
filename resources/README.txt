Assignment 6:
Our GUI view uses the JTable to display the contents of the spreadsheet. We used ModelToTable, a wrapper class, so that the contents of our model's hash table can be displayed in the JTable.
The JTable has built in functionality for column headers, but we had to find a way to create row headers. We created RowHeaderRenderer, a class that renders the row headers and RowListModel, the class that gets the actual row names. This was finally implemented into the JTable in our SpreadsheetGraphicsView class when constructing the view.
Overall, our design of the view interface is quite clear and flexible.
Fortunately, we did not have to make any design changes from our previous assignment, besides being able to handle a 'recursive' spreadsheet.

Assignment 7:
For this assignment we had to restructure the view a little bit. We readjusted the view so that the table was a component of it to allow for the addition of other components. After that we needed a way to add rows in the spread sheet so we added that functionality.
The design from the previous assignment proved to be flexible as we had to change very little code to add the functionality for this assignment.
In terms of the controller, we made it handle all the event requests from the view by giving the view a wrapper that would request things from the controller. The controller would then go and make the necessary changes to the model and view.

Assignment 8:
We were able to get all the essential features working successfully: our JAR files supports all the required command-line arguments, including -providers. We were able to make our provider's editor view work with our code. We did not have to rewrite or edit our own view nor make changes in any of our code to achieve this. Therefore, the other command-line arguments still work with existing our code. In addition, we implemented the extra credit functionality that allows a user to load and save worksheets.