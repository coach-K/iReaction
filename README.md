# Checkpoint Two (iReaction)

The long awaited java package **iReaction** has finally been released on January 21, 2016.
This release is optimized for speed and performance.

UML Class Diagram

![Alt text](https://github.com/andela-kogunde/iReaction/blob/master/assets/UML%20Class%20Diagram.png?raw=true "iReaction UML Class Diagram")

## What is iReaction?
An **ultimate tripod** java package, its goal is to standardize, improve and ease java development/programming, iReaction is referred to as an ultimate tripod because of its three (3) powerful packages (PDL) :

1. parser Package
2. database Package
3. logger Package

### P - parser Package
This package can parse content of `ANY` pair file and return next pair object. 
It uses delimiter to determine the key and value of the read content.

Lets say we have a `'simple.txt'` file containing information of people in an organization

```
-------------
NAME : JOHN DOE
GENDER : MALE
AGE : 24
POSITION : TRAINER
-------------
NAME : JANE DOE
GENDER : FEMALE
AGE : 19
POSITION : FELLOW
-------------
NAME : DAN DOE
GENDER : MALE
AGE : 27
POSITION : FACILITY MANAGER
-------------
```

> *parser package* can be used to parse this file and return the next pair object.

```Java
public class PairFromFile {

	public static void main(String[] args) throws Exception {
		
		PairFileParser parser = new PairFileParser(new File("simple.txt"));
		parser.setEndOfBlock('-', '-'); //notify the parser about end of block character
		pair pair;

		while ((pair = parser.readPair(':')) != null) { //reads the file with the specified delimiter
			if (!parser.isEndOfBlock)
				System.out.printf("%s : %s", pair.getKey(), pair.getValue());
		}
	}

}
```

### D - database Package
This package handles all database operations and executions such as:
- Creating a flexible connection to Server
- Create, Read, Update and Delete Operation (CRUD) 
- Managing the preparation of SQL statements thereby providing an helper methods to perform CRUD and more SQL executions in order to make database operations flexible.

>SIMPLE CRUD OPERATION

```Java
public class SimpleCRUD {

	public static void main(String[] args) throws Exception {
		
		//Connect With Server
		DatabaseConnector databaseConnector = new DatabaseConnector(URL, USERNAME, PASSWORD);
        DatabaseManager databaseManager = new DatabaseManager(databaseConnector.connect());
		
		//Create New database
		databaseManager.createNewDatabase(DATABASE).execute();

		//Connect to Created database
        databaseConnector.setDatabase(DATABASE);
        databaseManager.setConnection(databaseConnector.connect());

		//Create Table
		databaseManager.createNewTable(Config.TABLE.toString())
                .addPrimaryKey("id")
                .addColumn("NAME")
				.addColumn("GENDER")
				.addColumn("AGE", DatabaseManager.INTEGER)
                .addColumn("POSITION")
				.execute();

		//Insert
		databaseManager.insert(fields, values).executeUpdate();

		//Select
		databaseManager.select().executeQuery();

		//Update
		databaseManager.update(fields, values).where(field, value).executeUpdate();

		//Delete
		databaseManager.delete().where(field, value).executeUpdate();
	}

}
```


### L - logger Package
Its `Log` and `LogWriter` classes logs and write log object to file respectively at a fast speed.

>Simple logger Example

```Java
public class SimpleLogger {

	public static void main(String[] args) throws Exception {
		
		//Log message
		Log log = Log.p(SimpleLogger.class.getSimpleName(), "Logging a simple message.");
		
		//Writing Log message to file
		LogWriter logWriter = new LogWriter(new File("logging-2-1.log"));
		logWriter.write(log);
	}

}
```


## reaction Project
This project is included in the Checkpoint Two (iReaction) Packages. It is required to write a multi-threaded application, which reads in key value pair data from a text document, parses the document and saves the data into a mysql database while generating an action log at the same time. 

>**File Information**

>**File Name:** reactions.dat

>**Version:** 17.0

>**Date and time generated:** March 20, 2013, 2:44:14

>*The format of this file is defined at http://bioinformatics.ai.sri.com/ptools/flatfile-format.html.*
# 

**Checkpoint Two (iReaction)** package was used to implement this project in no time.

[VIEW IMPLEMENTATION](https://github.com/andela-kogunde/iReaction/tree/master/src/reaction "reaction Project Implementation")


#
## Get Checkpoint Two (iReaction)
#
```
Local Checkout 
git clone https://github.com/andela-kogunde/iReaction.git
```

#
## Projects using Checkpoint Two (iReaction)
There are few numbers of apps that uses __"Checkpoint One (Library)"__. Feel free to contact me or submit a pull request to add yours to this list.

* reaction Project

### Development

Want to contribute? Great!

1.  Fork [__Checkpoint Two (iReaction)__](https://github.com/andela-kogunde/Library.git) repository.
2.  Make the fix.
3.  Submit a pull request.

### Todos

 - Add more operations to parser
 - Add more parser (Multi-line parser)

License
----

MIT


**Free Package!**
#
#
