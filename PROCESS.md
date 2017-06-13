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
I commented the making of this list out for the moment. The app ran fine and I was able to make an ASCII representation of the board. 
![ASCII representation](doc/ASCII_representation_board.png)
<span style="color: #000; font: monaco; font-size: 2em;">r	n	b	q	k	b	n	r  
p	p	p	p	p	p	p	p  
/-	-	-	-	-	-	-	-  
/-	-	-	-	-	-	-	-  
/-	-	-	-	-	-	-	-  
/-	-	-	-	-	-	-	-  
P	P	P	P	P	P	P	P  
R	N	B	Q	K	B	N	R</span>



  