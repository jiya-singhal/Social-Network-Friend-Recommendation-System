import java.util.*;

public class RecommendationEngine {
    private SocialGraph graph;

    public RecommendationEngine(SocialGraph graph) {
        this.graph = graph;
    }

    public Map<String, List<String>> recommendFriends(String user) {
        user = user.toLowerCase();
        Map<String, List<String>> mutualMap = new HashMap<>();

        if (!graph.userExists(user)) return mutualMap;

        Set<String> directFriends = graph.getFriends(user);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> level = new HashMap<>();

        queue.offer(user);
        visited.add(user);
        level.put(user, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentLevel = level.get(current);

            if (currentLevel >= 2) continue;

            for (String neighbor : graph.getFriends(current)) {
                if (!visited.contains(neighbor)) {
                    level.put(neighbor, currentLevel + 1);
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }

                // Ignore direct friends, self, or blocked users
                if (!directFriends.contains(neighbor) &&
                    !neighbor.equals(user) &&
                    !graph.isBlocked(user, neighbor)) {

                    mutualMap.putIfAbsent(neighbor, new ArrayList<>());
                    mutualMap.get(neighbor).add(current);
                }
            }
        }

        return mutualMap;
    }

    public List<List<String>> findFriendCircles() {
        Set<String> visited = new HashSet<>();
        List<List<String>> components = new ArrayList<>();

        for (String user : graph.getAllUsers()) {
            if (!visited.contains(user)) {
                List<String> component = new ArrayList<>();
                dfs(user, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void dfs(String user, Set<String> visited, List<String> component) {
        visited.add(user);
        component.add(user);
        for (String friend : graph.getFriends(user)) {
            if (!visited.contains(friend)) {
                dfs(friend, visited, component);
            }
        }
    }
}
