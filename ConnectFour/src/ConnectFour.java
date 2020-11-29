import java.util.Scanner;
import java.lang.Math.*;

public class ConnectFour {

    private static char token ='X';
    private static int turn = 1;
    private static char[][] board;
    private static int WIDTH, HEIGHT;
    private static final char BLANK = '.';
    private static boolean isWin;
    private static int col = 0;

    public static void main(String[] args){

        clearBoard();
        //System.out.println(isBoardFull());
        while(!isBoardFull() && !isWin){
            System.out.println("\n"+token+"'s turn");
            do{
                userInput();
            }while(!Drop());
            checkWin();
            displayBoard();
            switchUser();
        }
        if(isBoardFull() && !isWin){
            System.out.println("Draw");
        }
    }

    private static void clearBoard(){
        WIDTH = 7;
        HEIGHT = 6;
        isWin = false;
        board = new char[WIDTH][HEIGHT];
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<HEIGHT; j++){
                board[i][j] = BLANK;
            }
        }
    }

    private static boolean isBoardFull(){
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<HEIGHT; j++){
                if(board[i][j] == BLANK) return false;
            }
        }
        return true;
    }

    private static void userInput(){
        int response;
        Scanner input = new Scanner(System.in);
        boolean hasInput = false;
        System.out.println("Enter an integer between 1 and 7");
        while(!hasInput){
            while(!input.hasNextInt()){
                System.out.println("Please enter an integer between 1 and 7");
                input.nextLine();
            }
            response = input.nextInt();
            if(response >= 1 && response <= 7){
                col = response-1;
                hasInput = true;
            }else{
                System.out.println("Not valid integer, try 1-7");
                hasInput = false;
            }
        }

    }

    private static boolean Drop(){
        for(int i=0; i<HEIGHT; i++){
            if(board[col][HEIGHT-1] != BLANK){
                System.out.println("This column is full, please choose another column");
                return false;
            }else if(board[col][i] == BLANK){
                board[col][i] = token;
                return true;
            }
        }
        return false;
    }

    private static void checkWin(){
        if(checkVertical() || checkHorizontal() || checkDiagonal() || checkAntiDiagonal()){
            isWin=true;
        }else{
            isWin = false;
        }
    }

    private static boolean checkVertical(){
        for(int i=0; i<WIDTH-3; i++){
            for(int j=0; j<HEIGHT; j++){
                if(board[i][j] == token && board[i+1][j] == token &&
                board[i+2][j] == token && board[i+3][j] == token){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkHorizontal(){
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<HEIGHT-3; j++){
                if(board[i][j] == token && board[i][j+1] == token &&
                board[i][j+2] == token && board[i][j+3] == token){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonal(){
        for(int i=3; i<WIDTH; i++){
            for(int j=3; j<HEIGHT; j++){
                if(board[i][j] == token && board[i-1][j-1] == token &&
                board[i-2][j-2] == token && board[i-3][j-3] == token){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkAntiDiagonal(){
        for(int i=3; i<WIDTH; i++){
            for(int j=0; j<HEIGHT-3; j++){
                if(board[i][j] == token && board[i-1][j+1] == token &&
                board[i-2][j+2] == token && board[i-3][j+3] == token){
                    return true;
                }
            }
        }
        return false;
    }

    private static void displayBoard(){
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<HEIGHT; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    private static void switchUser(){
        if(!isWin){
            turn++;
            if(turn%2 == 1){
                token = 'X';
            }else if(turn %2 == 0){
                token = 'O';
            }
        }else{
            System.out.println(token+" wins");
        }
    }


}
