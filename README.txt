=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: jlopez26
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D array. I used a 2d array to store and represent the 10x10 grid of buttons of the minesweeper board. The grid had
  the Cell type. Cell was an abstract class with two implementations that represented the two different types of
  cells, one was an empty cell without a mine and the other was a cell with a mine. This was an appropriate use of the
  concept because a 2d array makes it easy to identify the rows and columns that each button was part of, and I was able
  to easily access the necessary row and column for any buttons that I needed.

  2. Inheritance and subtyping. I created an abstract class called Cell that contained a few complete methods and a few
  abstract methods that the classes that extended it had to implement. I chose to use an abstract class instead of an
  interface because I wanted to extend the use of a JButton onto the Cell class. I then extended this abstract class
  twice, once in the Empty class and once in the Mine class. Both of these classes had their own implementation of the
  reveal and isMine methods and in my code I use dynamic dispatch on both of these methods because my 2d array that I
  used was of type Cell so, it depended on what was the dynamic class of the button to know which reveal or isMine
  method to use. This was an appropriate use of the concept because I needed to way to differentiate between the
  cells that had a mine and those that didn't and I also needed to store them in the same 2D array so this was one way
  I could achieve both of those goals.

  3. Recursion. I used recursion when implementing the revealZeros method because in the Minesweeper Game when you click
  on a cell that has 0 mines surrounding it, the game should reveal any of the surrounding cells that also have
  0 mines around it. It serves as a kickstart for the player in the game. I implemented recursion here by checking if
  a cell has 0 mines around it and it is not a mine and if so then you reveal that mine but also reveal the zeros around
  it. So its a recursive process of checking if surrounding cells have zero mines and are not mines themselves. This
  is an appropriate implementation because it has a base case of if the cell is a mine or does not have 0 surrounding
  mines. This is also most efficiently done with recursion because you only recursively call the method on cells that
  have 0 surrounding mines.

  4. JUnit testing. I implemented the JUnit testing in my BoardTest class. In this class I tested a few things but one
  of the things I tested was if the surrounding cells of a mine accurately displayed the number of mines. This test was
  particularly helpful in debugging my addMines method. I believe this was an appropriate feature of the concept
  because a lot of my game was very testable and easy to test because it was a game mostly made up of integer values.
  I was able to test a wide variety of things like if the number of mines were correct after creating new games and
  also if the positions of the mines were different when creating a new game.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  I have a runMinesweeper class that implements runnable and that serves as the base of the game with the main frame
  and it creates the toolbar at the bottom fo the game where a user can quit the game or create a new game. I then
  have a board class that serves as the functional part of the grid of buttons. This maintains the game state as it
  relates to the 2d array of Jbuttons. I then created an abstract class called Cell that contained a few complete
  methods and a few abstract methods that the classes that extended it had to implement. I chose to use an abstract
  class instead of an interface because I wanted to extend the use of a JButton onto the Cell class. I then
  extended this abstract class twice, once in the Empty class and once in the Mine class.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  Yes, when I was trying to figure out how to implement the inheritance and subtyping of the cell class I
  was trying to update the board by simply creating a mine object but this did not work because I was trying
  to change the grid after I had already added it to the JPanel. So it took me a while that to add the mine object
  to the board and accurately update it I needed to clear add the buttons after adding the mines or else
  the mines were unresponsive because they were updated after the buttons were added to the panel. This was difficult
  for me to solve because it was not clear what was causing the error and I was only able to solve the problem
  when I went through every line carefully with the debugger.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I like my design because I believe I make good use of the JButtons to implement the game and the actions. It also
  made the action events very easy to implement because all I really needed to do was reveal the cell and
  set the button's enabled field to false. I also believe that my game is very well encapsulated because all of
  the fields are private. If the checkstyle allowed me to, I would have like to make a few of the fields of my
  superclass Cell protected instead of private. If I had the chance to refactor my game I would add different modes
  so that a player could chose easy, medium, or hard and each of those modes would have a different sized grid and
  different number of mines.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  None
