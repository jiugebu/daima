package com.tetris.view;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel scoreLabel;
    private JLabel statusLabel;

    public StatusPanel() {
        setLayout(new BorderLayout());
        
        scoreLabel = new JLabel("分数: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        scoreLabel.setForeground(Color.BLUE);
        
        statusLabel = new JLabel("准备开始", JLabel.CENTER);
        statusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        
        add(scoreLabel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        
        setPreferredSize(new Dimension(150, 100));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public void updateScore(int score) {
        scoreLabel.setText("分数: " + score);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }
}