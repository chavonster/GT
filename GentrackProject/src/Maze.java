import java.util.Arrays;

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

    public Maze()
    {

    }

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

    private int findPathRecursive(int x, int y)
    {
        _maze[x][y] = 'V';
        if (x == get_endX() && y == get_endY())
        {
            _foundPath = true;
            _maze[x][y] = 'X';
            return 1;
        }
        if (!canTraverse(x,y))
        {
            return -1;
        }
        if(checkBoundaries("row", x-1))
        {
            if (_maze[x - 1][y] == ' ')
            {
                int res = findPathRecursive(x-1,y);
                if (res == 1)
                {
                    _maze[x][y] = 'X';
                    return 1;
                }
            }
        }
        if(checkBoundaries("row", x+1))
        {
            if (_maze[x + 1][y] == ' ')
            {
                int res = findPathRecursive(x+1,y);
                if (res == 1)
                {
                    _maze[x][y] = 'X';
                    return 1;
                }

            }
        }

        if(checkBoundaries("col", y-1))
        {
            if (_maze[x][y-1] == ' ')
            {
                int res = findPathRecursive(x,y-1);
                if (res == 1)
                {
                    _maze[x][y] = 'X';
                    return 1;
                }
            }
        }
        if(checkBoundaries("col", y+1))
        {
            if (_maze[x][y+1] == ' ')
            {
                int res = findPathRecursive(x,y +1);
                if (res == 1)
                {
                    _maze[x][y] = 'X';
                    return 1;
                }
            }
        }

        return -1;

    }

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


    public void findPath()
    {
        if (canTraverse(get_startX(), get_startY()))
        {
            findPathRecursive(get_startX(), get_startY());
        }
    }

    public void initMaze()
    {
        _maze = new char[_mazeHeight][_mazeWidth];

    }

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

    public boolean is_foundPath() {
        return _foundPath;
    }

    public void set_foundPath(boolean _foundPath) {
        this._foundPath = _foundPath;
    }


    public int get_startX() {
        return _startX;
    }

    public void set_startX(int _startX) {
        this._startX = _startX;
    }

    public int get_startY() {
        return _startY;
    }

    public void set_startY(int _startY) {
        this._startY = _startY;
    }

    public int get_endX() {
        return _endX;
    }

    public void set_endX(int _endX) {
        this._endX = _endX;
    }

    public int get_endY() {
        return _endY;
    }

    public void set_endY(int _endY) {
        this._endY = _endY;
    }

    public char[][] get_maze() {
        return _maze;
    }

    public void set_maze(char[][] _maze) {
        this._maze = _maze;
    }

    public int get_mazeWidth() {
        return _mazeWidth;
    }

    public void set_mazeWidth(int _mazeWidth) {
        this._mazeWidth = _mazeWidth;
    }

    public int get_mazeHeight() {
        return _mazeHeight;
    }

    public void set_mazeHeight(int _mazeHeight) {
        this._mazeHeight = _mazeHeight;
    }


    public void markStartEnd()
    {
        _maze[_startX][_startY] = 'S';
        _maze[_endX][_endY] = 'E';
    }
}
