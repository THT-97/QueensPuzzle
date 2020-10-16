
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TranHuyThinh
 */
public class Menu extends JFrame implements MouseListener{
    public JPanel panel;
    public JLabel label;
    public JButton solve, nextStep, nextResult;
    private JPanel buttonZone;
    private Board board;

    public Menu(String title) throws HeadlessException {
        super(title);
        setSize(500,530);
	setVisible(true);
//        setResizable(false);
        setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createPanel();
        createBoard();
        solve.addMouseListener(this);
        nextStep.addMouseListener(this);
        nextResult.addMouseListener(this);
    }
    
    
    private void createPanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(50*8, 50*8));
        panel.setLayout(new GridLayout(8,8,0,0));
        buttonZone = new JPanel();
        buttonZone.setPreferredSize(new Dimension(500, 10));
        buttonZone.add(solve = new JButton("Solve"));
        buttonZone.add(nextStep = new JButton("Next Step"));
        buttonZone.add(nextResult = new JButton("Next Result"));
        label = new JLabel();
        label.setPreferredSize(new Dimension(250, 20));
        label.setFont(new Font("Mangal", Font.BOLD, 12));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setText("Steps: 0    ||   Results: 0");
        add(panel, BorderLayout.NORTH);
        buttonZone.add(label,BorderLayout.CENTER);
        add(buttonZone);
    }

    private void createBoard() {
        board = new Board(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==solve) board.solve(0);
        if(e.getSource()==nextStep) board.nextStep();
        if(e.getSource()==nextResult) board.nextResult();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }
}

