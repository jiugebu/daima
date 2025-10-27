package com.tetris.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JButton startButton;
    private JButton pauseButton;
    private JButton aiButton;
    private JButton exitButton;

    public ControlPanel() {
        setLayout(new GridLayout(4, 1, 5, 5));
        
        startButton = new JButton("开始游戏");
        pauseButton = new JButton("暂停游戏");
        aiButton = new JButton("AI模式");
        exitButton = new JButton("退出游戏");
        
        // 设置按钮样式
        Font buttonFont = new Font("微软雅黑", Font.BOLD, 14);
        startButton.setFont(buttonFont);
        pauseButton.setFont(buttonFont);
        aiButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        
        startButton.setBackground(new Color(76, 175, 80));
        pauseButton.setBackground(new Color(255, 193, 7));
        aiButton.setBackground(new Color(33, 150, 243));
        exitButton.setBackground(new Color(244, 67, 54));
        
        startButton.setForeground(Color.WHITE);
        pauseButton.setForeground(Color.WHITE);
        aiButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);
        
        add(startButton);
        add(pauseButton);
        add(aiButton);
        add(exitButton);
        
        setPreferredSize(new Dimension(150, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void setStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void setPauseButtonListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }

    public void setAIButtonListener(ActionListener listener) {
        aiButton.addActionListener(listener);
    }

    public void setExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void setPauseButtonText(boolean isPaused) {
        pauseButton.setText(isPaused ? "继续游戏" : "暂停游戏");
    }
}