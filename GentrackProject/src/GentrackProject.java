import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to Gentrack project
 */
public class GentrackProject
{

    /**
     * Parses a maze file, extract data and runs a solution finder.
     * Prints the solution if it exists.
     * @param f - file with a maze description
     */
    private static void testMazeFile(File f)
    {
        Maze maze = new Maze();
        try
        {
            BufferedReader b = new BufferedReader(new FileReader(f));

            String[] lineArray = b.readLine().split(" ");
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
            String output = String.format("Printing solution for %s", f.getName());
            System.out.println(output);
            if (maze.isPathFound())
            {
                maze.markStartEnd();

                for (int x = 0; x < maze.get_mazeHeight(); x++) {
                    for (int y = 0; y < maze.get_mazeWidth(); y++) {
                        if (maze.get_maze()[x][y] == 'V') //replaces visited vertex with the right symbol
                        {
                            maze.get_maze()[x][y] = ' ';
                        }
                        System.out.print(maze.get_maze()[x][y]);
                    }
                    System.out.println();
                }
                System.out.println();
            }
            else
            {
                System.out.println("Path not found. There is no solution to this maze!\n");
            }

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * opens tests directory, and runs all tests within
     * @param args
     */
    public static void main(String[] args)
    {
        try {
            //REPLACE WITH PATH OF TESTS DIRECTORY!
            File[] files = new File
                    ("/home/tomer/IdeaProjects/GentrackProject/tests").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    testMazeFile(file);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
