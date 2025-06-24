import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialGraph graph = new SocialGraph();
        RecommendationEngine engine = new RecommendationEngine(graph);

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. Show All Users");
            System.out.println("4. Show Friend Recommendations");
            System.out.println("5. Print Graph");
            System.out.println("6. Block a User");
            System.out.println("7. Show Friend Circles");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter user name: ");
                    String user = sc.nextLine().toLowerCase();
                    graph.addUser(user);
                    System.out.println("User added!");
                    break;

                case 2:
                    System.out.print("Enter first user: ");
                    String user1 = sc.nextLine().toLowerCase();
                    System.out.print("Enter second user: ");
                    String user2 = sc.nextLine().toLowerCase();
                    graph.addFriendship(user1, user2);
                    System.out.println("Friendship added!");
                    break;

                case 3:
                    System.out.println("All users:");
                    for (String u : graph.getAllUsers()) {
                        System.out.println("- " + u);
                    }
                    break;

                    case 4:
                    System.out.print("Enter user to get recommendations: ");
                    String target = sc.nextLine().toLowerCase();
                    Map<String, List<String>> recs = engine.recommendFriends(target);
                
                    if (recs.isEmpty()) {
                        System.out.println("No recommendations found.");
                    } else {
                        System.out.println("Recommended friends for " + target + ":");
                
                        List<Map.Entry<String, List<String>>> sorted = new ArrayList<>(recs.entrySet());
                        sorted.sort((a, b) -> {
                            int cmp = Integer.compare(b.getValue().size(), a.getValue().size());
                            return (cmp != 0) ? cmp : a.getKey().compareTo(b.getKey());
                        });
                
                        for (Map.Entry<String, List<String>> entry : sorted) {
                            System.out.println("- " + entry.getKey() + " (Mutual Friends: " + entry.getValue().size() +
                                               " → " + entry.getValue() + ")");
                        }
                    }
                    break;
                

                case 5:
                    System.out.println("Social Graph:");
                    graph.printGraph();
                    break;

                    case 6:
    System.out.print("Enter your username: ");
    String blocker = sc.nextLine().toLowerCase();
    System.out.print("Enter user to block: ");
    String blocked = sc.nextLine().toLowerCase();
    graph.blockUser(blocker, blocked);
    System.out.println("User " + blocked + " has been blocked by " + blocker);
    break;

case 7:
    List<List<String>> circles = engine.findFriendCircles();
    int id = 1;
    for (List<String> circle : circles) {
        System.out.println("Circle " + id + ": " + circle);
        id++;
    }
    break;


                case 8:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
