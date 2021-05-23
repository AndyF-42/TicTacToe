/*
 * Standard game of Tic Tac Toe,
 * X goes first, and grid is
 * A, B, C, by 1, 2, 3
 * Author: Andy Fleischer
 * Date: 9/12/2019
 */

import java.util.Scanner;

public class TicTacToe {

	int[][] board = new int[3][3];
	final int BLANK = 0;
	final int X_MOVE = 1;
	final int O_MOVE = 2;
	final int X_TURN = 0;
	final int O_TURN = 1;
	int turn = 0;
	int xWins = 0;
	int oWins = 0;
	Scanner scanner;
	String input = "";
	
	//Constructor for TicTacToe
	public TicTacToe()
	{
		scanner = new Scanner(System.in);
		boolean playing = true;
		
		//Complete game loop. Once out, program is fully over
		while (playing)
		{
			printBoard();
			
			//One turn loop (once out of loop, single game has finished) 
			while (!checkWin(X_MOVE) && !checkWin(O_MOVE) && !checkTie())
			{
				input = scanner.nextLine().toUpperCase();
				//Makes sure coordinates are a, b, or c, followed by  1, 2, or 3
				if (input.length() != 2)
				{
					System.out.println("Enter a single letter followed by a single number");
				}
				else if (input.charAt(0) != 'A' &&
						 input.charAt(0) != 'B' &&
						 input.charAt(0) != 'C')
				{
					System.out.println("Row must be an A, B, or C");
				}
				else if (input.charAt(1) != '1' &&
						 input.charAt(1) != '2' &&
						 input.charAt(1) != '3')
				{
					System.out.println("Column must be a 1, 2, or 3");
				}
				//When the coordinates are good
				else
				{
					int row = input.charAt(0) - 'A';
					int col = input.charAt(1) - '1';
					
					//Inserts proper letter
					if (board[row][col] == BLANK)
					{
						if (turn == X_TURN)
						{
							board[row][col] = X_MOVE;
							turn = O_TURN;
						}
						else
						{
							board[row][col] = O_MOVE;
							turn = X_TURN;
						}
					}
					//If chosen space is already taken...
					else
					{
						System.out.println("Somebody already went there!");
					}
				}
				printBoard();
				
				//Check for win on either side or tie (runs checkWin method)
				if (checkWin(X_MOVE))
				{
					System.out.println("X wins!");
					xWins++;
				}
				else if (checkWin(O_MOVE))
				{
					System.out.println("O wins!");
					oWins++;
				}
				else if (checkTie())
				{
					System.out.println("Looks like it's a tie!");
				}
			}
			
			//Check for playing again
			System.out.println("X has won " + xWins + " times, and O has won " + oWins + " times.");
			System.out.println("Would you like to play again?");
			input = scanner.nextLine().toLowerCase();
			//Clear board if playing again
			if (input.equals("y") || input.equals("yes"))
			{
				for (int row = 0; row < board.length; row++)
				{
					for (int col = 0; col < board.length; col++)
					{
						board[row][col] = BLANK;
					}
				}
				//Reset turn to X
				turn = X_TURN;
			}
			//Leave program
			else
			{
				System.out.println("OK, goodbye!");
				playing = false;
			}
		}
	}
	
	//Prints out current board
	public void printBoard()
	{
		//Prints a blank, X, or O for each row
		System.out.println(" \t1\t2\t3");
		for (int row = 0; row < board.length; row++)
		{
			String output = (char)('A' + row) + "\t";
			for (int col = 0; col < board[0].length; col++)
			{
				if (board[row][col] == BLANK)
				{
					output += " \t";
				}
				else if (board[row][col] == X_MOVE)
				{
					output += "X\t";
				}
				else if (board[row][col] == O_MOVE)
				{
					output += "O\t";
				}
			}
			System.out.println(output);
		}
	}
	
	//Checks for a win, returns boolean true or false
	public boolean checkWin(int player)
	{
		// -- 3 in a row -- //
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player)
		{
			return true;
		}
		else if (board[1][0] == player && board[1][1] == player && board[1][2] == player)
		{
			return true;
		}
		else if (board[2][0] == player && board[2][1] == player && board[2][2] == player)
		{
			return true;
		}
		// -- 3 in a row -- //
		
		// -- 3 in a column -- //
		else if (board[0][0] == player && board[1][0] == player && board[2][0] == player)
		{
			return true;
		}
		else if (board[0][1] == player && board[1][1] == player && board[2][1] == player)
		{
			return true;
		}
		else if (board[0][2] == player && board[1][2] == player && board[2][2] == player)
		{
			return true;
		}
		// -- 3 in a column -- //
		
		// -- Diagonals -- //
		else if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
		{
			return true;
		}
		else if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
		{
			return true;
		}
		// -- Diagonals -- //
		
		return false;
	}
	
	//Checks for tie, returns boolean true or false
	public boolean checkTie()
	{
		//Looks through the whole board for a blank
		//If there is one, it was not a tie (all spaces must be taken for a tie)
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[0].length; col++)
			{
				if (board[row][col] == BLANK)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	//Main method to start program
	public static void main(String[] args)
	{
		new TicTacToe();

	}
}
