import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PathfindingVisualizerGUI {

    static JButton[][] grid = new JButton[5][5];

    static int sx = -1, sy = -1;
    static int ex = -1, ey = -1;
    static int clickMode = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pathfinding Visualizer");
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,5));

        // Create grid
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){

                int x = i, y = j;

                JButton btn = new JButton(".");
                grid[i][j] = btn;

                btn.addActionListener(e -> {

                    if(clickMode == 0){
                        btn.setText("S");
                        sx = x; sy = y;
                        clickMode = 1;
                    }
                    else if(clickMode == 1){
                        btn.setText("E");
                        ex = x; ey = y;
                        clickMode = 2;
                    }
                    else{
                        btn.setText("#");
                    }
                });

                panel.add(btn);
            }
        }

        JButton find = new JButton("Find Path");

        find.addActionListener(e -> bfs());

        frame.add(panel, BorderLayout.CENTER);
        frame.add(find, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    static void bfs(){

        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] visited = new boolean[5][5];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx,sy});
        visited[sx][sy] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == ex && cur[1] == ey){
                JOptionPane.showMessageDialog(null,"Path Found!");
                return;
            }

            for(int[] d : dir){
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(nx>=0 && ny>=0 && nx<5 && ny<5 &&
                   !visited[nx][ny] &&
                   !grid[nx][ny].getText().equals("#")){

                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});

                    if(!grid[nx][ny].getText().equals("E"))
                        grid[nx][ny].setText("*");
                }
            }
        }

        JOptionPane.showMessageDialog(null,"No Path Found!");
    }
}
