Our GUI view uses the JTable to display the contents of the spreadsheet. We used ModelToTable, a wrapper class, so that the contents of our model's hash table can be displayed in the JTable. 

The JTable has built in functionality for column headers, but we had to find a way to create row headers. We created RowHeaderRenderer, a class that renders the row headers and RowListModel, the class that gets the actual row names. This was finally implemented into the JTable in our SpreadsheetGraphicsView class when constructing the view.

Overall, our design of the view interface is quite clear and flexible.

Fortunately, we did not have to make any design changes from our previous assignment, besides being able to handle a 'recursive' spreadsheet.