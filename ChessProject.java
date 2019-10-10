import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
  int turnCount = 0;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
  private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}

  private Boolean checkBlackOponent(int newX, int newY) {
    Boolean opponent;
    Component c1 = chessBoard.findComponentAt(newX,newY);
    JLabel awaitingPiece = (JLabel)c1;
    String tmp1 = awaitingPiece.getIcon().toString();
    if ((tmp1.contains("White"))) {
      opponent = true;
    }
    else {
      opponent = false;
    }
    return opponent;
  }

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean success =false;
    Boolean progress = false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.

			So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/

    if(pieceName.contains("Queen")) {
      Boolean inTheWay = false;
      int landingX = e.getX()/75;
      int landingY = e.getY()/75;
      int distance = Math.abs(startX-landingX);
      if ((pieceName.contains("White"))&&((turnCount%2)==1)) {
        validMove = false;
      }
      else if((pieceName.contains("Black"))&&((turnCount%2)==0)) {
        validMove =false;
      }
      else {
        if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) { //if outisde of board
          validMove = false;
        }
        else {
          validMove = true;
        }
      }

    }

//code for Bishup Movement
    else if (pieceName.contains("Bishup")) {
      Boolean inTheWay = false;
      int landingX = e.getX()/75;
      int landingY = e.getY()/75;
      int distance = Math.abs(startX-landingX);
      if ((pieceName.contains("White"))&&((turnCount%2)==1)) {
        validMove = false;
      }
      else if((pieceName.contains("Black"))&&((turnCount%2)==0)) {
        validMove =false;
      }
      else {
        if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) { //if outisde of board
          validMove = false;
        }
        else {
          validMove = true;
          if (Math.abs(startX-landingX) == Math.abs(startY-landingY)) { //if vhange in X is same as chnage in Y
            if ((startX-landingX < 0)&&(startY - landingY < 0)) { //if change in X is negative, and change in y negative
              for (int i = 0;i < distance ;i++ ) {
                if (piecePresent((initialX+(i*75)), (initialY+(i*75)))) {
                  inTheWay = true;
                }
              }
            }
            else if ((startX-landingX < 0)&&(startY-landingY > 0)) { //delta X negative, deta Y positive,
              for (int i=0;i < distance ;i++) {
                if (piecePresent((initialX+(i*75)), (initialY-(i*75)))) {
                  inTheWay = true;
                }
              }
            }
            else if ((startX-landingX > 0)&&(startY-landingY > 0)) { //delta X positive, delta Y positive
              for (int i = 0;i < distance ;i++ ) {
                if (piecePresent((initialX-(i*75)), (initialY-(i*75)))) {
                  inTheWay = true;
                }
              }
            }
            else if ((startX-landingX>0)&&(startY-landingY<0)) { //delta X positive, delta Y negative
              for (int i = 0;i < distance ;i++) {
                if (piecePresent((initialX-(i*75)), (initialY+(i*75)))) {
                  inTheWay = true;
                }
              }
            }

            if (inTheWay) {
              validMove = false;
            }
            else {
              if (piecePresent(e.getX(), (e.getY()))) {
                if (pieceName.contains("White")) {
                  if (!checkBlackOponent(e.getX(),e.getY())) {
                    validMove = true;
                  }
                  else {
                    validMove = false;
                  }
                }
                else {
                  if (checkBlackOponent(e.getX(), e.getY())) {
                    validMove=true;
                  }
                  else {
                    validMove = true;
                  }
                }
              }
              else{
                validMove = true;
              }
            }
          }
          else {
            validMove = false;
          }
        }
      }
    }


//Code for Rook Movement
    else if(pieceName.contains("Rook")) {
      Boolean inTheWay = false;
      int landingX = e.getX()/75;
      int landingY = e.getY()/75;
      if ((pieceName.contains("White"))&&((turnCount%2)==1)) {
        validMove = false;
      }
      else if((pieceName.contains("Black"))&&((turnCount%2)==0)) {
        validMove =false;
      }
      else {
        if ((landingX < 0) || (landingX >7) || (landingY < 0) || (landingY > 7)) {
          validMove = false;
        }
        else{
          if (((Math.abs(startX-landingX)!=0) && (Math.abs(startY-landingY) ==0))
           || ((Math.abs(startX - landingX)==0)&&(Math.abs(landingY-startY)!=0))) {
            if (Math.abs(startX-landingX)!=0) {
              int xMovement = Math.abs(startX-landingX);
              if (startX-landingX > 0) {
                for (int i =0;i < xMovement ; i++) {
                  if (piecePresent(initialX-(i*75),e.getY())) {
                    inTheWay = true;
                    break;
                  }
                  else {
                    inTheWay = false;
                  }
                }
              }
              else {
                for (int i=0;i < xMovement ;i++ ) {
                  if (piecePresent(initialX+(i*75), e.getY())) {
                    inTheWay =true;
                    break;
                  }
                  else {
                    inTheWay = false;
                  }
                }
              }
            }
            else {
              int yMovement = Math.abs(startY - landingY);
              if (startY-landingY > 0) {
                for (int i=0;i < yMovement ;i++ ) {
                  if (piecePresent(e.getX(), initialY-(i*75))) {
                    inTheWay = true;
                    break;
                  }
                  else {
                    inTheWay = false;
                  }
                }
              }
              else {
                for (int i =0; i < yMovement;i++ ) {
                  if (piecePresent(e.getX(), initialY+(i*75))) {
                    inTheWay = true;
                    break;
                  }
                  else {
                    inTheWay = false;
                  }
                }
              }
            }

            if (inTheWay) {
              validMove = false;
            }
            else {
              if (piecePresent(e.getX(), (e.getY()))) {
                if (pieceName.contains("White")) {
                  if (!checkBlackOponent(e.getX(),e.getY())) {
                    validMove = true;
                  }
                  else {
                    validMove = false;
                  }
                }
                else {
                  if (checkBlackOponent(e.getX(), e.getY())) {
                    validMove = true;
                  }
                  else {
                    validMove = false;
                  }
                }
              }
              else {
                validMove = true;
              }
            }
          }
          else {
            validMove = false;
          }
        }
      }
    }



//Code for Knight Movement
    else if (pieceName.contains("Knight")) {
      int landingX = e.getX()/75;
      int landingY = e.getY()/75;

      if ((pieceName.contains("White"))&&((turnCount%2)==1)) {
        validMove = false;
      }
      else if((pieceName.contains("Black"))&&((turnCount%2)==0)) {
        validMove =false;
      }
      else {
        if (((landingX<0) || (landingX>7) || (landingY<0)||(landingY > 7))) {
          validMove = false;
        }
        else {
          if (((landingX == startX+1) && (landingY == startY+2))
          || ((landingX == startX-1) && (landingY == startY+2))
          || ((landingX == startX+2) && (landingY == startY+1))
          || ((landingX == startX-2) && (landingY == startY+1))
          || ((landingX == startX+1) && (landingY == startY-2))
          || ((landingX == startX-1) && (landingY == startY-2))
          || ((landingX == startX+2) && (landingY == startY-1))
          || ((landingX == startX-2) && (landingY == startY-1))  ) {

            if (piecePresent(e.getX(),(e.getY()))) {
              if (pieceName.contains("White")) {
                if ((!checkBlackOponent(e.getX(),e.getY()))) {
                  validMove = true;
                }
                else {
                  validMove = false;
                }
              }
              else{
                if (checkBlackOponent(e.getX(), e.getY())) {
                  validMove = true;
                }
                else {
                  validMove = false;
                }
              }
            }
            else {
              validMove = true;
              }
            }
            else {
            validMove = false;
          }
        }
      }
    }

    if (pieceName.equals("BlackPawn")) {
      //The pawn can move either two or one sqaures
      if ((turnCount%2) == 0) {
        validMove = false;
      }
      else {
        if (startY == 6) {
          //int landingY = e.getY()/75; //getting new Y position
          //int landingX = e.getX()/75;
          if ((startX == (e.getX()/75)) && (((startY-(e.getY()/75))== 1) || (startY-(e.getY()/75))==2)) {
            if(((startY-(e.getY()/75))==2)){ //if change in y is equal to 2
              if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){ //checks if there is ay peice on first square in front or second square
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
            else{
              if((!piecePresent(e.getX(), (e.getY())))) //else if no piece is present
              {
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
          }
          else if ( (piecePresent(e.getX(), e.getY())) && ((((e.getX()/75)-startX)==1)||((e.getX()/75)-startX)==-1) && (((e.getY()/75)-startY)==-1)) {
            validMove = true;
          }
          else {
            validMove = false;
          }
        }
        else {
          int landingY = e.getY()/75; //getting new Y position
          int landingX = e.getX()/75;
          if ((startX == landingX) && (((startY-landingY) == 1))) {
            if(!piecePresent(e.getX(),(e.getY()))) {
              validMove =true;
              if (landingY==0) {
                progress = true;
              }
            }
            else{
              validMove = false;
            }

          }
          else if ((checkBlackOponent(e.getX(),e.getY())) && (piecePresent(e.getX(), e.getY())) && ((((e.getX()/75)-startX)==1)||((e.getX()/75)-startX)==-1) && (((e.getY()/75)-startY)==-1)) {
            validMove =  true; //fix for white pawn taking a black piece when at starting position
            if (landingY==0) {
              progress = true;
            }
          }
           else {
            validMove = false;
          }
        }
      }

    }

		if(pieceName.equals("WhitePawn")){

      if ((turnCount%2) == 1) {
        validMove = false;
      }
      else {
        if(startY == 1) //if move is at start
        {
          if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2)) //if move is one square ahead or two
          {
            if((((e.getY()/75)-startY)==2)){ //if change in y is equal to 2
              if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()-75)))){ //checks if there is ay peice on first square in front or second square
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
            else{
              if((!piecePresent(e.getX(), (e.getY())))) //else if no piece is present
              {
                validMove = true;
              }
              else{
                validMove = false;
              }
            }
          }
          else if ((!checkBlackOponent(e.getX(), e.getY())) && (piecePresent(e.getX(), e.getY())) && (((((e.getX()/75) == (startX+1)&&(startX+1<=7)))||(((e.getX()/75) == (startX-1))&&(startX-1 >=0))))&&((e.getY()/75) == (startY+1))) {
            validMove =  true; //fix for white pawn taking a black piece when at starting position
          }
          else{
            validMove = false;
          }
        }
        else{ //if pawn is moving any other position than start
          int newY = e.getY()/75; //getting new Y position
          int newX = e.getX()/75;	//getting new X position
          if((startX-1 >=0)||(startX +1 <=7)) //if startX is between starting position between start row and end of table
          {
            if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0))))&&(newY == (startY+1))) //if new X,Y is has piece and x is +/- 1 , and y is +1
            {
              if(checkWhiteOponent(e.getX(), e.getY())){ //Check is peice is white opponent
                validMove = true;
                if(startY == 6){ //if start Y is equal to 6, then this is a valid move
                  success = true;
                }
              }
              else{
                validMove = false;
              }
            }
            else{
              if(!piecePresent(e.getX(), (e.getY()))){ //if newX and newY has no piece
                if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){ //if startX is same as newX, and if newY - startY is equal to its a valid move
                  if(startY == 6){
                    success = true; //if startY is 6 then its valid
                  }
                  validMove = true;
                }
                else{
                  validMove = false;
                }
              }
              else{
                validMove = false;
              }
            }
          }
          else{
            validMove = false;
          }
        }
      }


		}





		if(!validMove){		//if not valid move, send piece back to where it was
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;//sets peice to old co-ordinates
			}
			String pieceLocation = pieceName+".png"; //sets image to location
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
      if (progress) {
        int location = 0 + (e.getX()/75);
        if (c instanceof JLabel) {
          Container parent = c.getParent();
          parent.remove(0);
          pieces = new JLabel( new ImageIcon("BlackQueen.png"));
          parent = (JPanel)chessBoard.getComponent(location);
          parent.add(pieces);
        }
      }
			else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);
			}
      turnCount++;
		}


    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((e.getX()/75)-startX);
    int yMovement = Math.abs((e.getY()/75)-startY);
    System.out.println("-------------------------");
    System.out.println("The piece that is being moved is: "+pieceName);
    System.out.println("The starting co-ordinates are : "+"( "+startX+", "+startY+")");
    System.out.println("The xMovement is: "+xMovement);
    System.out.println("The yMovement is: "+yMovement);
    System.out.println("Valid Move: "+validMove);
    System.out.println("The landing co-ordinates are : "+"( "+landingX+" , "+landingY+")");
    System.out.println("Difference in Y is:"+(startY-(e.getY()/75)));
    System.out.println("Starting Y Co ordinate is: "+startY);
    System.out.println("turnCount: "+turnCount);
    System.out.println("-------------------------");

    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
