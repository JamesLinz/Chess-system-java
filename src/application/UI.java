package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	  // https://stackoverflow.com/questions/2979383/java-clear-the-console
	  public static void clearScreen() { 
		  
		  System.out.flush();
	  }

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} 
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo posição de xadrez. Valores válidos são de a1 até h8");
		}
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCaptured(captured);
		System.out.println();
		System.out.println("Turn :" + chessMatch.getTurn());
		System.out.println("Esperando jogador: " + chessMatch.getCurrentPlayer());
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++)
				printPiece(pieces[i][j], false);

			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++)
				printPiece(pieces[i][j], possibleMoves[i][j]);

			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			
		}
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
	
	private static void printCaptured(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Peças capturadas:");
		System.out.print("Brancas: ");
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print("Pretas: ");
		System.out.println(Arrays.toString(black.toArray()));
	}
}
