<!-- TODO -->

### Seminar 1

#### Task

<!-- * Domain model (DM) -->
 <!-- * basic and alternative flows -->
 <!-- * business rules and clarifications -->
 <!-- * no class called program or system -->
<!-- * System sequence diagram (SSD) -->
 <!-- * Illustrating basic and alternative flows -->

#### Report

* Explain and discuss the result and explain the method
* <b>Method</b>
    * Explain how we used a category list and noun identification to identify classes
    * Explain how we decided on attributes and associations
    * Explain how we worked when developing the SSD
* <b>Result</b>
    * Show and briefly explain the domain model
    * Show and briefly explain the SSD
* <b>Discussion</b>
    * Evaluate your domain model using applicable assessment criteria from <i>assessment-criteria-seminar1.pdf</i>
    * Evaluate your SSD using applicable assessment criteria from <i>assessment-criteria-seminar1.pdf</i>

### Seminar 2

<!-- #### Task -->

<!-- * Use cohesion, coupling and encapsulation to design an object-oriented program -->
<!-- * Design a program with requirements: -->
 <!-- * Can handle all parts of the flow -->
 <!-- * Include startup -->
 <!-- * High cohesion -->
 <!-- * Low coupling -->
 <!-- * Good encapsulation -->
 <!-- * Well-designed public interface -->
 <!-- * Divided into layers, specified by MVC and Layer patterns -->
 <!-- * Not required to follow the DM and SSD -->
 <!-- * Not required to design the view, replace with a class called View -->
 <!-- * Not required to design the data layer -->

#### Report

* Motivate how cohesion, coupling and encapsulation are used when designing an object-oriented program
* <b>Method</b>
    * Explain how you worked and give examples how you reasoned when creating the design
* <b>Result</b>
    * Briefly explain interaction diagrams (communication or SSD) describing all functionality of the process sale scenario both basic and alternative flows, also the main method
    * Briefly explain a class diagram showing all classes in the interaction diagrams
* <b>Discussion</b>
    * Evaluate design using applicable assessment criteria from <i>assessment-criteria-seminar2.pdf</i>

### Seminar 3

<!-- #### Task -->

<!-- * Apply several best practices of object-oriented programming -->
<!-- * Write a program implementing the basic flow, the startup scenario, and the alternative flow 3-4b which was designed in seminar 2 (class diagram and communication diagram) -->
<!-- * Not required to program any other alternative flow apart from 3-4b -->
<!-- * Not required to add other functionality -->
<!-- * Not required to code the view -->
<!-- * No requirements on databases or external systems -->
<!-- * Compilable and executable -->
<!-- * Program in Java -->
<!-- * No exceptions -->
<!-- * Follow all guidelines in chapter six in the textbook -->
<!-- * Try to follow the design from seminar two -->
<!-- * High cohesion -->
<!-- * Low coupling -->
<!-- * Good encapsulation -->
<!-- * Well-defined public interface -->
<!-- * Unit tests for all classes in layers controller, model and integration that do not only contain getters, constructors, values or only print -->

#### Report

* Satisfactory explain and motivate the use of best practices of object-oriented programming
* Prove that you understand how to write and use unit tests
* <b>Method</b>
    * Explain how you worked and reasoned with the program
    * Explain how you worked and reasoned with unit tests
    * Explain how you chose which tests to write
* <b>Result</b>
    * Briefly explain the program
    * Include link to github
    * Include printout of sample run
    * Briefly explain the tests
* <b>Discussion</b>
    * Evaluate program using applicable assessment criteria from <i>assessment-criteria-seminar3.pdf</i>

### Seminar 4

<!-- #### Task -->

<!-- * When passed a list of all bought items, it tells a sum to be reduced from the total cost of the entire sale. The sum is zero if there’s no discount -->
<!-- * Use exceptions to handle alternative flow 3-4a -->
 <!-- * Exceptions shall be thrown to indicate that a search as been made for an identifier that did not exist in the inventory catalog -->
<!-- * Use exceptions to indicate that the database can not be called -->
 <!-- * Simulate this situation by always throwing a database failure when a search is made for a particular, hardcoded item identifier -->
<!-- * Choose between checked and unchecked exceptions -->
<!-- * Use the correct abstraction level for exceptions -->
<!-- * Name the exception after the error condition -->
<!-- * Include information about the error condition -->
<!-- * Use functionality provided in java.lang.Exception -->
<!-- * Write javadoc comments for all exceptions -->
<!-- * An object shall not change state if an exception is thrown -->
<!-- * Notify users -->
<!-- * Notify developers -->
<!-- * Write unit tests for the exception handling -->
<!-- * The program shall produce the following output: -->
 <!-- * User interface shall show an informative message when an exception is caught in the view -->
 <!-- * An error report shall be written to a log (file) when an exception is caught -->
<!-- * Use observer pattern to implement observers: -->
 <!-- * TotalRevenueView -->
 <!-- * TotalRevenueFileOutput -->
 <!-- * Shall never call the controller or any other class, update instead using the Observer pattern -->
<!-- * Use two more GoF patterns apart from Observer and Template method -->
 <!-- * Suggestion is to turn some registry/database into singleton or use Strategy (maybe also Composite) for discount calculation -->
 <!-- * Not allowed to copy entire files or classes from code samples written at the lectures -->

#### Report

* Apply design patterns and explain and motivate their use
* Prove that you understand how to write and use exceptions for error handling
* <b>Method</b>
    * Explain how you worked and reasoned when implementing exception handling
    * Explain how you worked and reasoned when implementing the observer pattern
    * Explain how you worked and reasoned when implementing the two more GoF patterns
* <b>Result</b>
    * Briefly explain the program
    * Include link to github
    * Include printout of a sample run
    * Briefly explain source code for all classes you changed when implementing the GoF patterns (including Observer)
* <b>Discussion</b>
    * Evaluate your program using applicable assessment criteria from the document <i>assessment-criteria-seminar4.pdf</i>

### Task 1, Inheritance

#### Task

* Use the Template Method design pattern for new observers
* The template class you create must have the structure of the pseudocode

#### Report

* <b>Method</b>
    * Explain how you worked and how you reasoned when implementing the template method pattern
* <b>Result</b>
    * Briefly explain source code
    * Link to repo
    * Sample run
* <b>Discussion</b>
    * Thoroughly motivate that your code is a correct implementation of the template method pattern
    * Thoroughly explain the benefits of using template method in your application

### Task 2, Inheritance vs Composition

<!-- IDEA: https://www.youtube.com/watch?v=hxGOiiR9ZKg -->

<!-- #### Task -->

<!-- * Adapt any class in the java libraries from Oracle -->
<!-- * Write one new class that adapts using inheritance, and another new class that adapts using composition -->
<!-- * Write a main method which instantiates your new classes and executes the adaptions -->
<!-- * Must include printouts illustrating how your classes work -->

#### Report

* <b>Method</b>
    * Explain how you worked and how you reasoned when writing the adapting classes
* <b>Result</b>
    * Briefly explain the source code
    * Link to repo
    * Sample run
* <b>Discussion</b>
    * Thoroughly compare adaption using inheritance vs composition
    * There are comparison criteria in section 9.3 in the course book
    * Draw a conclusion which is best: inheritance, composition or neither

### Task 3, Testing Output

#### Task

* Write unit tests for all methods that print to System.out
    * Must include at least the main method, the View class and the receipt printout
    * If you have additional methods printing to System.out, also those must be tested
    * The tests of the View class must test all printouts in that class, but you only have to test printout containing information
    * It’s not required to test printouts which exist only to make the view more readable

#### Report

* <b>Method</b>
    * Explain how you worked and how you reasoned when writing the unit tests
* <b>Result</b>
    * Briefly explain the source code
    * Link to repo
    * Sample run
* <b>Discussion</b>
    * Thoroughly evaluate your unit tests using assessment criteria related to testing from the document <i>assessment-criteria-seminar3.pdf</i>

<!-- ### History -->

<!-- #### Seminar 4 Feedback -->

<!-- * Tests that test exceptions actually work -->
<!-- * Check all requirements -->
<!-- * Check all javadoc -->
<!-- * Do not catch errors to throw another error -->
<!-- * No empty catch -->
<!-- * Do not print the error message to the user -->
<!-- * The @throws comments in the javadoc doesn't explain when the exception is thrown. -->
<!-- * The call to Sale on line 59 in Controller shall be made inside the try block. As it is now, null will be passed to sale.addItem if inventoryDB throws an exception. -->
<!-- * It's not a good idea to print the message from the exception (e.getMessage()) to the user. This means that the user interface is created in the layer where the exception was created, even though the printing takes place in the view. It's not at all certain that the developer writing the code where the exception is created knows that the message will be displayed to the user. -->
<!-- * I can't see a reason to place TotalRevenueFileOutput in the model. It's either a part of the user interface (view layer) or an external system (integration layer). -->
<!-- * TotalRevenueFileOutput prints to the view. The method writeFile is never called. -->
<!-- * It's wrong to instantiate the classes implementing the observer interface in the observed class, since that makes the observed class depend on the implementations. That way, the observed class needs to be changed if other observer implementations shall be used. -->
<!-- * Logging -->
<!-- * AccountingDB does not do anything -->
<!-- * Manually add item if ID not found - not needed?? -->
<!-- * Read the entire flow -->

<!-- #### Questions -->

<!-- * Does seminar 3 require anything special or does seminar 4 cover it? - hand in seminar 4 to cover both -->
<!-- * Do we just update the seminar 1 and 2 to fit to seminar 4? - update seminar 1 and 2 according to feedback -->
<!-- * Did I understand Task 1? - yes -->
<!-- * Is there to many system.out in receipt? - no, should be a printer though -->
<!-- * Are observers allowed to break the layers? (The integration and view observers interact with model abstract/interface) - yeah kinda -->
<!-- * Instantiate the classes implementing the observer interface in the observed class - create observers in view -->
<!-- * Task 1 abstract or interface - needed interface in abstract -->
<!-- * What are checked exceptions - checked under compilation, custom exceptions (unlike dividing with 0 that are low level) -->
<!-- * What are abstraction levels for exceptions - where are exceptions thrown and catched in the program, our instance: everything catched in view -->
<!-- * What is high cohesion - classes and functions are specialized and do one thing great unlike multitasking -->
<!-- * What is low coupling - low amount of connections to other components, a change in one class does not mean all other classes need change -->
<!-- * What is good encapsulation - hide the unimportant tech stuff for the more important solution and greater readability -->

<!-- #### Notes from TA -->

<!-- * FileOutput should be in integration -->
<!-- * Remove e.getMessage -->
<!-- * Two errors in additem -->
<!-- * Add specific exceptions -->
<!-- * All exceptions handled in view -->
<!-- * No business logic in controller -->
<!-- * No printouts in controller -->
<!-- * Printouts only in folder view or receipt -->
<!-- * Printer in physical layer -->
<!-- * Record for DTO -->
<!-- * Controller requests information and packages to DTOs -->
<!-- * View only has reference to controller and DTO -->
<!-- * No triangle reference -->
<!-- * Create observers in view -->
<!-- * General exception in observer abstract -->
<!-- * Move catch to handleErrors -->