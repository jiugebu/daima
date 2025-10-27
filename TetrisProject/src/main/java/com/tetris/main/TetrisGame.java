package com.tetris.main;

import com.tetris.model.*;
import com.tetris.view.*;
import com.tetris.controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisGame extends JFrame {
    private Board board;
    private GameState gameState;
    private GamePanel gamePanel;
    private StatusPanel statusPanel;
    private ControlPanel controlPanel;
    private GameController gameController;
    private Timer aiTimer;

    public TetrisGame() {
        initializeModels();
        initializeViews();
        initializeController();
        setupUI();
        setupAITimer();
    }

    private void initializeModels() {
        board = new Board();
        gameState = new GameState();
        gameState.getNextPiece().setRandomShape();
    }

    private void initializeViews() {
        gamePanel = new GamePanel(board);
        statusPanel = new StatusPanel();
        controlPanel = new ControlPanel();
    }

    private void initializeController() {
        gameController = new GameController(board, gameState, gamePanel, statusPanel);
        
        // 设置控制面板监听器
        controlPanel.setStartButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.startGame();
                controlPanel.setPauseButtonText(false);
            }
        });
        
        controlPanel.setPauseButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.pauseGame();
                controlPanel.setPauseButtonText(gameState.isPaused());
            }
        });
        
        controlPanel.setAIButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.toggleAI();
            }
        });
        
        controlPanel.setExitButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.exitGame();
            }
        });
        
        // 设置输入处理器
        InputHandler inputHandler = new InputHandler(gameController);
        gamePanel.addKeyListener(inputHandler);
        gamePanel.setFocusable(true);
    }

    private void setupUI() {
        setTitle("俄罗斯方块 - AI导论大作业");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // 创建主面板
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        
        // 创建侧边面板
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(statusPanel);
        sidePanel.add(controlPanel);
        
        mainPanel.add(sidePanel, BorderLayout.EAST);
        add(mainPanel);
        
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setupAITimer() {
        aiTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.performAIMove();
            }
        });
        aiTimer.start();
    }

    public static void main(String[] args) {
        // 设置系统外观
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 创建并显示游戏窗口
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TetrisGame();
            }
        });
    }
}