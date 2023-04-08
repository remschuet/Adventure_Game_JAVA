package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarAlgorithm {

    static int ROW = 30;
    static int COL = 30;

    static class Node {
        int y, x, f, g, h;
        Node parent;

        Node(int y, int x, int f, int g, int h) {
            this.y = y;
            this.x = x;
            this.f = f;
            this.g = g;
            this.h = h;
        }
    }

    // static int ROW, COL;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    static boolean isValid(int row, int col, int[][] grid, boolean[][] visited) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (grid[row][col] == 0 && !visited[row][col]);
    }

    static int calculateHValue(int row, int col, int destX, int destY) {
        return Math.max(Math.abs(row - destX), Math.abs(col - destY));
    }

    static List<Node> aStarSearch(int[][] grid, Node src, Node dest) {
        List<Node> path = new ArrayList<>();
        if (grid[src.y][src.x] != 0 || grid[dest.y][dest.x] != 0) {
            System.out.println("Source or destination is invalid");
            return path;
        }

        boolean[][] visited = new boolean[ROW][COL];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.f - b.f));
        pq.add(src);
        visited[src.y][src.x] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.y == dest.y && node.x == dest.x) {
                while (node != null) {
                    path.add(0, node);
                    node = node.parent;
                }
                return path;
            }

            for (int i = 0; i < 4; i++) {
                int newRow = node.y + dx[i];
                int newCol = node.x + dy[i];
                if (isValid(newRow, newCol, grid, visited)) {
                    int gValue = node.g + 1;
                    int hValue = calculateHValue(newRow, newCol, dest.y, dest.x);
                    int fValue = gValue + hValue;
                    Node child = new Node(newRow, newCol, fValue, gValue, hValue);
                    child.parent = node;
                    pq.add(child);
                    visited[newRow][newCol] = true;
                }
            }
        }

        System.out.println("Path not found");
        return path;
    }

/*
    public static void main(String[] args) {
        // initialisation de la grille
        int[][] grid = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}};

        // initialisation des noeuds de départ et d'arrivée
        Node src = new Node(0, 0, 0, 0, 0);
        Node dest = new Node(4, 4, 0, 0, 0);

        // appel à l'algorithme A* pour trouver le chemin
        List<Node> path = aStarSearch(grid, src, dest);

        // affichage du chemin trouvé
        if (path.isEmpty()) {
            System.out.println("Chemin non trouvé !");
        } else {
            System.out.println("Chemin trouvé :");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        }
    }*/


}
