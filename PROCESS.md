# day 1
Since I thought that making a prototype was going to be tough, I already started on the board in the weekend. This days deadline was for a proposal however so I began to write that. Now with it finished, I am going to focus on the prototype again.
# day 2
The visual representation of the board is finished, but I am having a hard time actually placing pieces on it. It might be better to have actual individual tiles placed in a gridlayout that handle their own colour and occupation rather than having a custom gridview with tiles drawn on it which nevertheless returns proper coordinates.
What I really want to do is retain my custom gridview and have an imageview of a piece inflate into it. It is a challenge, because I haven't started on the engine yet and that will be where piece coordinates come from. I have created a position converter in case I want to implement a one-dimensional board on engine level and a two-dimensional board on GUI level. I have created a Piece class and a Knight class that extends Piece. After all I want to place some pieces on the board. I do think that the current state of the prototype is sufficient. 
# day 3
Tried to build a board made of individual tiles today. Not a success. Yet. The board doesn't display. Should be at this point all be black tiles, but is empty gridlayout.
I think that I am going to focus on the engine from now on, no point in making a GUI for a facade. It might be best to give pieces two-dimensional coordinates.
# day 4
Started work on the engine. Started by building pieces and movetypes. So far I have started work on the knight, the easiest piece, since it can jump over other pieces. This means no checking for pieces in its path. I took a very literal approach. By this I mean that I visualized a game of chess in my head and on the whiteboard, worked out some coordinate-offsets and put them in an two-dimensional array called POSSIBLE_MOVES.
![](doc/KnightMoves.jpeg)
Add the offset to the piecePosition and check if there is another piece. At this point I needed two different moves: A 'Normal' move, and an 'Attack' move.
# day 6
Today I started the Move classes and the rest of the Pieces. The Bishop needed a three-dimensional array because of its four directions and its two coordinates. For the rest it is similar to the knight. The Rook is just a copy of the Bishop, though with a different moveset and the Queen is probably best described as the culmination of the Bishop and the Rook (moveset wise). The Pawn though, has a very unique moveset as it can jump two squares in its first move and attacks diagonally. It would also be able to take pieces en passant, so it needs to check at its sides too. Opposing Pawns also move in different directions. So I think the Knight, Bishop, Rook, and Queen are finished, but the Pawn needs some work and the King too. 
# day 7
Today I created the board in engine, which is just a set of lists of tiles and pieces. However it is now still riddled with bugs and invoked virtual methods on null object references. As I am not that good at debugging, I'm going to need some 'expert' help on this. The only thing it should do is Map tiles of different coordinates to a Tilenumber. Do the same for the pieces and their movesets. I'll look into it tomorrow.
# day 8
Today I started bug-fixing. Using the debugging console, I found that my board was drawing black pawns on the first row, while this is where the higher pieces should be. It also drew an additional line of empty tiles. It took me way longer than I would like to admit to realize that when creating the pawns I set the yCoordinate for the black pawns to 0 instead of 1, and thus overlaying the higher pieces. Now using the debugger I ventured to find out why the app was crashing. It turns out that when I try to make a list of all the possible moves (which I need later to verify with the board that the selected move is legal) there seems to be a problem with the legalMove method in Piece. This leads to no Moves being added and so the trackMoves method in Board doesn't get any Move objects.
I commented the making of this list out for the moment. The app ran fine and I was able to make an ASCII representation of the board. This was achieved by overriding the toString methods of all pieces and empty tiles.
![ASCII representation](doc/ASCII_representation_board.png)

``r n b q k b n r``  
``p p p p p p p p``  
``0 0 0 0 0 0 0 0``  
``0 0 0 0 0 0 0 0``  
``0 0 0 0 0 0 0 0``  
``0 0 0 0 0 0 0 0``  
``P P P P P P P P``  
``R N B Q K B N R``

This evening though, I found the culprit. My legalMoves methods inside the pieces was using a getTile method from Board, that I had return null... had it return a tile and now everything is fine. The next step is maybe connect this to the GUI and finish the alpha build.

In the BoardGridView, I have implemented a method that returns a string key to a resourceID in a List. This resourceID is for each individual piece. The method takes coordinates from the nested for-loops in the onDraw method and recovers the belonging piece. The resulting resource is made into a Bitmap and if it isn't null, it is drawn in the onDraw method (if on black, after black is drawn). I switched the x and y coordinates by accident though and even though the pieces were in the wrong spot, all pieces were drawn. So I switched the x and y values and now some pieces are missing (but what pieces there are, are in the right place). I don't know why. Guess I'll find out tomorrow.  
<img src="doc/all_pieces_wrong_place.jpeg" width="200"/>
<img src="doc/all_butsome_pieces_right_place.jpeg" width="200"/>
# day 9
So I noticed a little speck on my screen. When I dragged the emulator it moved with it. It was right on the border with the black King's tile. It must be part of the queen that was drawn outside her tile! I realized that for some tiles the black tile was being drawn over the pieces. I split the drawing of the tiles and the pieces into two nested for-loops to make sure the tiles were drawn first and this fixed it. It also made me think that the bitmaps of the pieces were not scaling. Which is true.  
The below images have titles, hover to get a sense of their dimensions.  
<img src="doc/1_2.7_240x320.png" width="200" title="2.7, 240x320"/>
<img src="doc/2_3.2_320x480.png" width="200" title="3.2, 320x480"/>
<img src="doc/3_3.7_480x800.png" width="200" title="3.7, 480x800"/>
<img src="doc/4_4.65_720x1280.png" width="200" title="4.65, 720x1280"/>
<img src="doc/5_5.5_1080x1920.png" width="200" title="5.5, 1080x1920"/>
<img src="doc/6_5.5_1440x2560.png" width="200" title="5.5, 1440x2560"/>

The chesspieces are inside the tiles from 720x1280 and up and resolutions lower than 320x480 are unplayable (buttons fall off the screen). I don't think this is necessary to fix, since the app targets API 24 and no phone that runs this has a resolution lower than that.  
The next step would of course be to make the pieces movable. Since we don't want to be able to move the pieces of the opponent, I think it best to start with making a player class that keeps track of who's turn it is. I have an onTouchEvent in BoardGridView. This should send coordinates to some class (Probably Move or Board) and get a piece on that tile (may be null). Then when we tab again we should move this piece to the new tile. This will involve making a new board that has the piece moved. Then I will call invalidate() in the onTouch() method and the View should redraw itself based on the new board.

So it makes sense to have the players make the moves... so that is the plan now. We probably want to know who is next, so we ask the current player who he is playing against. This means two classes that are nearly identical (the two players), so I'm going to make an abstract player class that has a makeMove function. I recently made a singleton out of the board... so I am probably undoing that. If I am going to make a new board, then board can't be a singleton right? 

But Move holds the move! So I should get the move that corresponds to the coordinates of the onTouch method, validate the move and then execute the move. This should be done inside the Move class?
![](doc/Execute_move.jpg)
Since this photo was taken the PlayerMove Object has been refactored to AlternateBoard.

The player is making the moves, but the moving will be done by the move itself. The player can validate a move and return the same board if the move is illegal (meaning it isn't in the legalMoves list). Otherwise a new board with the piece moved should be returned. 