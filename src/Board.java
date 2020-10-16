
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TranHuyThinh
 */
class Board {
    private Menu menu;
    private JButton[][] board;
    private List<int[][]> steps;
    private List<int[][]> results;
    int[][]map;
    private int[] trace;
    private int step, result;
    
    public Board(Menu menu) {
        this.menu = menu;
        board = new JButton[8][8];
        map = new int[8][8];
        trace = new int[8];
        steps = new ArrayList<>();
        results = new ArrayList();
        step = 0;
        result = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new JButton();
                board[i][j].setPreferredSize(new Dimension(50,50));
                if((i%2==0 && j%2!=0)||(i%2!=0 && j%2==0)) board[i][j].setBackground(Color.black);
                else board[i][j].setBackground(Color.white);
                menu.panel.add(board[i][j]);
            }
        }
    }
    //Using Depth First Search
    public void solve(int i){
        for (int j = 0; j < 8; j++) {
            if(checkPos(i,j+1)){ //Check if it can be placed at this position
                map[i][j] = 1;
                trace[i] = j+1;
                steps.add(CopyOf(map)); //Save step
                if(i<7) solve(i+1); //Search next rows (go deeper)
                else {
                    results.add(CopyOf(map)); //Save result
                }
                //Back track
                map[i][j] = 0;
                trace[i] = 0;
            } 
        }
        menu.solve.setEnabled(false);
        menu.label.setText("Steps: " + String.valueOf(steps.size()) + "    ||    " + "Results: " + String.valueOf(results.size()));
    }

    private boolean checkPos(int i, int j) {
        for (int p = 0; p < 8; p++) {
            if(trace[p] != 0)
            {
                if((p==i) || (trace[p]==j)) return false;
                if(((p-i)==(trace[p]-j)) || ((p-i)== -(trace[p]-j))) return false;
            }
        }
        return true;
    }
    
    public void nextStep(){
        int total = steps.size();
        if(step < total){
            showBoard((int[][])steps.get(step));
            menu.label.setText("Steps: " + String.valueOf(step+1) + "/" + String.valueOf(total));
            step += 1;
        }
    }
    
    public void nextResult(){
        int total = results.size();
        if(result < total){
            showBoard((int[][])results.get(result));
            menu.label.setText("Results: " + String.valueOf(result+1) + "/" + String.valueOf(total));
            result += 1;
        }
    }
    
    public void showBoard(int[][] b){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(b[i][j]==1) board[i][j].setIcon(new ImageIcon(this.getClass().getResource("queen.png")));
                else board[i][j].setIcon(null);
            }
        }
    }

    private int[][] CopyOf(int[][] map) {
        int[][] copy = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
}
