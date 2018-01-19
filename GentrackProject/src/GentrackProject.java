import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GentrackProject
{


    public static void main(String[] args)
    {
        Maze maze = new Maze();

        //Don't forget to subtract 1 from start, end
        try
        {
            File f = new File("/home/tomer/IdeaProjects/GentrackProject/src/large_input.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));

            String[] lineArray = b.readLine().split(" ");
            //Explain the +1
            maze.set_mazeWidth(Integer.parseInt(lineArray[0]));
            maze.set_mazeHeight(Integer.parseInt(lineArray[1]));

            lineArray = b.readLine().split(" ");
            maze.set_startX(Integer.parseInt(lineArray[1]));
            maze.set_startY(Integer.parseInt(lineArray[0]));

            lineArray = b.readLine().split(" ");
            maze.set_endX(Integer.parseInt(lineArray[1]));
            maze.set_endY(Integer.parseInt(lineArray[0]));

            maze.initMaze();

            for (int row = 0; row < maze.get_mazeHeight(); row++)
            {
                lineArray = b.readLine().split(" ");
                maze.assignLine(row, lineArray);
            }
            maze.findPath();
            maze.markStartEnd();

            for (int x = 0; x < maze.get_mazeHeight(); x++) {
                for (int y = 0; y < maze.get_mazeWidth(); y++) {
                    if (maze.get_maze()[x][y] == 'V')
                    {
                        maze.get_maze()[x][y] = ' ';
                    }
                    System.out.print(maze.get_maze()[x][y]);
                }

                System.out.println();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


}



}
