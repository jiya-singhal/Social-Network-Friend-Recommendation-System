import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;  
import java.awt.event.*;
import java.util.*;

public class UI extends JFrame {
    private SocialGraph graph;
    private RecommendationEngine engine;

    private JTextField userField, user1Field, user2Field, targetUserField, blockerField, blockedField;
    private JTextArea outputArea;

    public UI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        graph = new SocialGraph();
        engine = new RecommendationEngine(graph);

        setTitle("🌐 Friend Recommendation System");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(230, 245, 255));

        // Add User Section
        JPanel addUserPanel = createTitledPanel("➕ Add User");
        userField = new JTextField(15);
        JButton addUserBtn = new JButton("Add");
        styleButton(addUserBtn);
        addUserPanel.add(userField);
        addUserPanel.add(addUserBtn);

        // Friendship Section
        JPanel addFriendshipPanel = createTitledPanel("🤝 Add Friendship");
        user1Field = new JTextField(10);
        user2Field = new JTextField(10);
        JButton addFriendshipBtn = new JButton("Connect");
        styleButton(addFriendshipBtn);
        addFriendshipPanel.add(new JLabel("User 1:"));
        addFriendshipPanel.add(user1Field);
        addFriendshipPanel.add(new JLabel("User 2:"));
        addFriendshipPanel.add(user2Field);
        addFriendshipPanel.add(addFriendshipBtn);

        // Recommendations Section
        JPanel recommendationPanel = createTitledPanel("💡 Get Recommendations");
        targetUserField = new JTextField(15);
        JButton recommendBtn = new JButton("Suggest");
        styleButton(recommendBtn);
        recommendationPanel.add(new JLabel("Target User:"));
        recommendationPanel.add(targetUserField);
        recommendationPanel.add(recommendBtn);

        // Block Section
        JPanel blockPanel = createTitledPanel("🚫 Block User");
        blockerField = new JTextField(10);
        blockedField = new JTextField(10);
        JButton blockBtn = new JButton("Block");
        styleButton(blockBtn);
        blockPanel.add(new JLabel("Blocker:"));
        blockPanel.add(blockerField);
        blockPanel.add(new JLabel("User to Block:"));
        blockPanel.add(blockedField);
        blockPanel.add(blockBtn);

        // Graph & Circle Buttons
        JPanel toolsPanel = createTitledPanel("📊 Graph Tools");
        JButton showGraphBtn = new JButton("Show Graph 🌐");
        JButton showCirclesBtn = new JButton("Friend Circles 🔄");
        styleButton(showGraphBtn);
        styleButton(showCirclesBtn);
        toolsPanel.add(showGraphBtn);
        toolsPanel.add(showCirclesBtn);

        inputPanel.add(addUserPanel);
        inputPanel.add(addFriendshipPanel);
        inputPanel.add(recommendationPanel);
        inputPanel.add(blockPanel);
        inputPanel.add(toolsPanel);

        outputArea = new JTextArea(14, 70);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 255, 250));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("🖥️ Output Console"));

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Actions
        addUserBtn.addActionListener(e -> {
            String user = userField.getText().trim().toLowerCase();
            if (!user.isEmpty()) {
                graph.addUser(user);
                outputArea.append("✅ Added user: " + user + "\n");
            }
        });

        addFriendshipBtn.addActionListener(e -> {
            String u1 = user1Field.getText().trim().toLowerCase();
            String u2 = user2Field.getText().trim().toLowerCase();
            if (!u1.isEmpty() && !u2.isEmpty()) {
                graph.addFriendship(u1, u2);
                outputArea.append("🤝 Added friendship: " + u1 + " - " + u2 + "\n");
            }
        });

        recommendBtn.addActionListener(e -> {
            String target = targetUserField.getText().trim().toLowerCase();
            Map<String, List<String>> recs = engine.recommendFriends(target);
            if (recs.isEmpty()) {
                outputArea.append("❌ No recommendations found for " + target + "\n");
            } else {
                outputArea.append("💡 Recommendations for " + target + ":\n");
                List<Map.Entry<String, List<String>>> sorted = new ArrayList<>(recs.entrySet());
                sorted.sort((a, b) -> {
                    int cmp = Integer.compare(b.getValue().size(), a.getValue().size());
                    return (cmp != 0) ? cmp : a.getKey().compareTo(b.getKey());
                });
                for (Map.Entry<String, List<String>> entry : sorted) {
                    outputArea.append("- " + entry.getKey() + " (Mutual Friends: " + entry.getValue().size() + " → " + entry.getValue() + ")\n");
                }
            }
        });

        blockBtn.addActionListener(e -> {
            String blocker = blockerField.getText().trim().toLowerCase();
            String blocked = blockedField.getText().trim().toLowerCase();
            if (!blocker.isEmpty() && !blocked.isEmpty()) {
                graph.blockUser(blocker, blocked);
                outputArea.append("🚫 " + blocker + " has blocked " + blocked + "\n");
            }
        });

        showGraphBtn.addActionListener(e -> {
            outputArea.append("🌐 Social Graph:\n");
            for (String user : graph.getAllUsers()) {
                outputArea.append(user + " -> " + graph.getFriends(user) + "\n");
            }
        });

        showCirclesBtn.addActionListener(e -> {
            List<List<String>> circles = engine.findFriendCircles();
            int id = 1;
            for (List<String> group : circles) {
                outputArea.append("🔄 Circle " + id++ + ": " + group + "\n");
            }
        });

        setVisible(true);
    }

    private JPanel createTitledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));
        panel.setBackground(new Color(245, 250, 255));
        return panel;
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(50, 130, 230));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UI::new);
    }
}
