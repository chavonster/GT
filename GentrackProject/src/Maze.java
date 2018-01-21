import java.util.Arrays;

/**
 * Creates a maze object.
 */
public class Maze
{

    private int _startX;
    private int _startY;
    private int _endX;
    private int _endY;
    private char[][] _maze;
    private int _mazeWidth;
    private int _mazeHeight;
    private boolean _foundPath = false;

    /**
     * Constructor
     */
    public Maze()
    {

    }

    /**
     * Initialize a 2D char array which holds the maze coding.
     */
    public void initMaze()
    {
        _maze = new char[_mazeHeight][_mazeWidth];
    }


    /**
     * Assigns an entire row of the maze based on the map:
     * 1 - #, 0 ' '(whitespace)
     * @param row, the row to be assigned
     * @param line, string array for one line of the maze
     *              as it was parsed by reader
     */
    public void assignLine(int row, String[] line)
    {
        for (int i = 0; i < line.length ; i++)
        {
            if (line[i].equals("1"))
            {
                _maze[row][i] = '#';
            }
            if (line[i].equals("0"))
            {
                _maze[row][i] = ' ';
            }
        }
    }

    /**
     * Searches for a path from start to end
     */
    public void findPath()
    {
        if (canTraverse(get_startX(), get_startY()))
        {
            findPathRecursive(get_startX(), get_startY());
        }
    }

    /**
     * Recursive method for solving the path problem
     * its base condition is whether (x,y) has reached the end
     * or is out of possible legal moves
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if (x,y) is part of the path,
     * false if it has reached a dead end
     */
    private boolean findPathRecursive(int x, int y)
    {
        _maze[x][y] = 'V'; //Marks coordinate as visited
        boolean pathFound;
        if (x == get_endX() && y == get_endY()) //Base condition for reaching the end
        {
            _foundPath = true;
            _maze[x][y] = 'X';
            return true;
        }
        if (!canTraverse(x,y)) //Base condition for a dead end
        {
            return false;
        }
        if(checkBoundaries("row", x-1))
        {
            if (_maze[x - 1][y] == ' ')
            {
                pathFound = findPathRecursive(x-1,y);
                if (pathFound)
                {
                    _maze[x][y] = 'X';
                    return true;
                }
            }
        }
        if(checkBoundaries("row", x+1))
        {
            if (_maze[x + 1][y] == ' ')
            {
                pathFound = findPathRecursive(x+1,y);
                if (pathFound)
                {
                    _maze[x][y] = 'X';
                    return true;
                }

            }
        }

        if(checkBoundaries("col", y-1))
        {
            if (_maze[x][y-1] == ' ')
            {
                pathFound = findPathRecursive(x,y-1);
                if (pathFound)
                {
                    _maze[x][y] = 'X';
                    return true;
                }
            }
        }
        if(checkBoundaries("col", y+1))
        {
            if (_maze[x][y+1] == ' ')
            {
                pathFound = findPathRecursive(x,y +1);
                if (pathFound)
                {
                    _maze[x][y] = 'X';
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * checks whether a coordinate is within the legal boundaries of maze
     * @param flag, determines whether to check
     *              against rows or columns based on the coordinate
     * @param coord, the coordinate to be tested
     * @return true if coordinate is within boundaries, false otherwise
     */
    private boolean checkBoundaries(String flag, int coord)
    {
        if (flag.equals("row"))
        {
            return (0 <= coord && coord < _mazeHeight);
        }
        else
        {
            return (0 <= coord && coord < _mazeWidth);
        }
    }

    /**
     * Checks if there is legal move from x,y to another coordinate
     * @param x, x coordinate
     * @param y, y coordinate
     * @return true if there is a possible move, false otherwise
     */
    private boolean canTraverse(int x, int y)
    {

        boolean[] possibleStep = new boolean[4];
        Arrays.fill(possibleStep, Boolean.FALSE);

        if (checkBoundaries("row", x-1))
        {
            possibleStep[0] = _maze[x - 1][y] == ' ';
        }
        if (checkBoundaries("row", x+1))
        {
            possibleStep[1] = _maze[x + 1][y] == ' ';
        }
        if (checkBoundaries("col", y-1))
        {
            possibleStep[2] = _maze[x][y - 1] == ' ';
        }
        if (checkBoundaries("col", y+1))
        {
            possibleStep[3] = _maze[x][y+1] == ' ';
        }
        return ((possibleStep[0] || possibleStep[1]) ||
                (possibleStep[2] || possibleStep[3]));
    }

    /**
     * @return true if path is found
     */
    public boolean isPathFound() {
        return _foundPath;
    }

    /**
     * getter for x coordinate of inception point
     * @return x of the starting coordinate
     */
    public int get_startX() {
        return _startX;
    }

    /**
     * setter for startX
     * @param _startX
     */
    public void set_startX(int _startX) {
        this._startX = _startX;
    }

    /**
     * getter for y coordinate of inception point
     * @return y coordinate of inception point
     */
    public int get_startY() {
        return _startY;
    }

    /**
     * setter for y coordinate of inception point
     * @param _startY, y coordinate of inception point
     */
    public void set_startY(int _startY) {
        this._startY = _startY;
    }

    /** getter for x of end point
     * @return x coordinate of end point
     */
    public int get_endX() {
        return _endX;
    }

    /**
     * setter for x coordinate of end point
     * @param _endX
     */
    public void set_endX(int _endX) {
        this._endX = _endX;
    }

    /**
     * getter for y of end point
     * @return y coordinate of end point
     */
    public int get_endY() {
        return _endY;
    }

    /**
     * setter for x coordinate of end point
     * @param _endY
     */
    public void set_endY(int _endY) {
        this._endY = _endY;
    }

    /**getter for maze
     * @return 2D char array, representing the maze
     */
    public char[][] get_maze() {
        return _maze;
    }

    /**
     * @return width of the maze
     */
    public int get_mazeWidth() {
        return _mazeWidth;
    }

    /** setter for width of the maze
     * @param _mazeWidth
     */
    public void set_mazeWidth(int _mazeWidth) {
        this._mazeWidth = _mazeWidth;
    }

    /**
     * @return height of the maze
     */
    public int get_mazeHeight() {
        return _mazeHeight;
    }

    /**
     * setter for height of the maze
     * @param _mazeHeight
     */
    public void set_mazeHeight(int _mazeHeight) {
        this._mazeHeight = _mazeHeight;
    }


    /**
     * Marks the start and end point once a path is found
     */
    public void markStartEnd()
    {
        _maze[_startX][_startY] = 'S';
        _maze[_endX][_endY] = 'E';
    }
}
