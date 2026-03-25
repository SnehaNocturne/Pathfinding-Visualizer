import java.util.*;

class Node {
    int x, y;
    Node parent;

    Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }
}

public class PathFinder {

    static int[][] directions = {
        {0,1}, {1,0}, {0,-1}, {-1,0}
    };

    public static void main(String[] args) {

        char[][] grid = {
            {'S','.','.','#','.'},
            {'.','#','.','#','.'},
            {'.','#','.','.','.'},
            {'.','.','#','#','.'},
            {'#','.','.','E','.'}
        };

        bfs(grid);
    }

    static void bfs(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<Node> q = new LinkedList<>();

        int sx=0, sy=0;

        // find start
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='S'){
                    sx=i; sy=j;
                }
            }
        }

        q.add(new Node(sx, sy, null));
        visited[sx][sy]=true;

        while(!q.isEmpty()) {

            Node cur = q.poll();

            if(grid[cur.x][cur.y]=='E'){
                System.out.println("Path Found!");
                printPath(cur, grid);
                return;
            }

            for(int[] d: directions){
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if(nx>=0 && ny>=0 && nx<n && ny<m &&
                   !visited[nx][ny] && grid[nx][ny] != '#') {

                    visited[nx][ny]=true;
                    q.add(new Node(nx, ny, cur));
                }
            }
        }

        System.out.println("No Path Found");
    }

    static void printPath(Node end, char[][] grid) {

        int steps = 0;

        while(end != null){
            if(grid[end.x][end.y] == '.')
                grid[end.x][end.y] = '*';
            end = end.parent;
            steps++;
        }

        System.out.println("Steps: " + (steps-1));

        System.out.println("Grid with Path:");
        for(char[] row : grid){
            for(char c : row){
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
