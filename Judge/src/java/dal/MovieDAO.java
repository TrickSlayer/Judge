package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ActorRole;
import model.FileAction;
import model.Genre;
import model.Movie;

public class MovieDAO extends DBContext {

    public List<Movie> getListMoviePage(int page, String order) {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT *FROM (\n"
                + "	SELECT ROW_NUMBER() OVER (ORDER BY Year " + order.toUpperCase() + ",MovieID ASC) AS 'Num',\n"
                + "	* FROM [Judge].[dbo].[Movie]) AS a \n"
                + "WHERE a.Num BETWEEN ? AND ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, 10 * (page - 1) + 1);
            st.setInt(2, 10 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                int id = rs.getInt("MovieID");
                m.setMovieID(id);
                m.setName(rs.getNString("Name"));
                m.setDescription(rs.getNString("Description"));
                m.setImage(rs.getString("Image"));
                RateDAO r = new RateDAO();
                m.setScore(r.getScoreMovie(id));
                m.setNumScore(r.getNumScoreMovie(id));
                list.add(m);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int size() {
        String sql = "SELECT COUNT(*) AS 'size' FROM dbo.Movie";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Movie> getMovieByActorsIdPage(int id, int page, String order) {
        List<Movie> list = new ArrayList<>();
        MovieDAO m = new MovieDAO();
        String sql = "SELECT *FROM (\n"
                + "	SELECT ROW_NUMBER() OVER (ORDER BY Year " + order.toUpperCase() + ",MovieID ASC) AS 'Num',\n"
                + "	* FROM dbo.Movie \n"
                + "	WHERE MovieID IN (\n"
                + "		SELECT MovieID FROM dbo.ActorRole WHERE ActorID=?)\n"
                + "	) AS a \n"
                + "WHERE a.Num BETWEEN ? AND ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, 10 * (page - 1) + 1);
            st.setInt(3, 10 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                int idM = rs.getInt("MovieID");
                movie.setMovieID(idM);
                movie.setName(rs.getNString("Name"));
                movie.setDescription(rs.getNString("Description"));
                movie.setImage(rs.getString("Image"));
                RateDAO r = new RateDAO();
                movie.setScore(r.getScoreMovie(idM));
                movie.setNumScore(r.getNumScoreMovie(idM));
                list.add(movie);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public int sizeMovieByActorsId(int id) {
        String sql
                = "SELECT COUNT(*) AS 'size' "
                + "FROM dbo.Movie "
                + "WHERE MovieID IN ("
                + "   SELECT MovieID FROM dbo.ActorRole "
                + "   WHERE ActorID=?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Movie> getMovieByGenreIDPage(int id, int page, String order) {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT *FROM (\n"
                + "	SELECT ROW_NUMBER() OVER (ORDER BY Year " + order.toUpperCase() + ",MovieID ASC) AS 'Num',\n"
                + "	* FROM dbo.Movie \n"
                + "	WHERE MovieID IN (\n"
                + "		SELECT MovieID FROM dbo.GenreMovie WHERE GenreID = ?)\n"
                + "	) AS a \n"
                + "WHERE a.Num BETWEEN ? AND ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, 10 * (page - 1) + 1);
            st.setInt(3, 10 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                int idM = rs.getInt("MovieID");
                movie.setMovieID(idM);
                movie.setName(rs.getNString("Name"));
                movie.setDescription(rs.getNString("Description"));
                movie.setImage(rs.getString("Image"));
                RateDAO r = new RateDAO();
                movie.setScore(r.getScoreMovie(idM));
                movie.setNumScore(r.getNumScoreMovie(idM));
                list.add(movie);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int sizeMovieByGenreID(int id) {
        String sql = "SELECT COUNT(*) AS 'size' FROM dbo.Movie WHERE MovieID IN (SELECT MovieID FROM dbo.GenreMovie WHERE GenreID = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Movie> getSearchMoviePage(String name, int page) {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT *FROM ("
                + "select ROW_NUMBER() OVER (ORDER BY Year DESC,MovieID ASC) AS 'Num',"
                + "*from Movie where Name like ?) AS a "
                + "WHERE a.Num BETWEEN ? AND ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + name + "%");
            st.setInt(2, 10 * (page - 1) + 1);
            st.setInt(3, 10 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                int id = rs.getInt("MovieID");
                m.setMovieID(id);
                m.setName(rs.getNString("Name"));
                m.setDescription(rs.getNString("Description"));
                m.setImage(rs.getString("Image"));
                RateDAO r = new RateDAO();
                m.setScore(r.getScoreMovie(id));
                m.setNumScore(r.getNumScoreMovie(id));
                list.add(m);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int sizeSearchMovie(String name) {
        String sql = "SELECT COUNT(*) AS 'size' FROM dbo.Movie WHERE MovieID IN (SELECT MovieID FROM Movie where Name like ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Movie> getMovieByUserIDPage(int id, int page) {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT *FROM (\n"
                + "	SELECT ROW_NUMBER() OVER (ORDER BY Year DESC,MovieID ASC) AS 'Num',\n"
                + "	* FROM dbo.Movie \n"
                + "	WHERE MovieID IN (\n"
                + "		SELECT MovieID FROM dbo.WatchList WHERE UserID = ?)\n"
                + "	) AS a \n"
                + "WHERE a.Num BETWEEN ? AND ?";
        MovieDAO m = new MovieDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, 10 * (page - 1) + 1);
            st.setInt(3, 10 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                int idM = rs.getInt("MovieID");
                movie.setMovieID(idM);
                movie.setName(rs.getNString("Name"));
                movie.setDescription(rs.getNString("Description"));
                movie.setImage(rs.getString("Image"));
                RateDAO r = new RateDAO();
                movie.setScore(r.getScoreMovie(idM));
                movie.setNumScore(r.getNumScoreMovie(idM));
                list.add(movie);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int sizeMovieByUserID(int id) {
        String sql = "SELECT COUNT(*) AS 'size' FROM dbo.Movie WHERE MovieID IN (SELECT MovieID FROM dbo.WatchList WHERE UserID = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("size");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Movie> getPage(List<Movie> listMovie, int page) {
        List<Movie> list = new ArrayList<>();
        for (int i = 10 * (page - 1); i < (listMovie.size() < 10 * page ? listMovie.size() : 10 * page); i++) {
            list.add(listMovie.get(i));
        }
        return list;
    }

    public Movie getAll(int i) {
        String sql = "select *from Movie where MovieID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                int id = rs.getInt("MovieID");
                m.setMovieID(id);
                m.setName(rs.getNString("Name"));
                m.setYear(rs.getInt("Year"));
                m.setDescription(rs.getNString("Description"));
                m.setImage(rs.getString("Image"));
                m.setTrailer(rs.getString("Trailer"));
                m.setActors(getActorByMovieID(id));
                m.setGenres(getGenreByMovieID(id));
                RateDAO r = new RateDAO();
                m.setScore(r.getScoreMovie(id));
                m.setNumScore(r.getNumScoreMovie(id));
                return m;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public Movie get(int i) {
        String sql = "select *from Movie where MovieID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                int id = rs.getInt("MovieID");
                m.setMovieID(id);
                m.setName(rs.getNString("Name"));
                m.setYear(rs.getInt("Year"));
                m.setDescription(rs.getNString("Description"));
                m.setImage(rs.getString("Image"));
                m.setTrailer(rs.getString("Trailer"));
                RateDAO r = new RateDAO();
                m.setScore(r.getScoreMovie(id));
                m.setNumScore(r.getNumScoreMovie(id));
                return m;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public int getID(String name, int year) {
        String sql = "select *from Movie where Name like ? and year = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + name + "%");
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("MovieID");
            }

        } catch (SQLException e) {

        }
        return 0;
    }

    public boolean exits(String name, int year) {
        int id = getID(name, year);
        if (id > 0) {
            if (get(id).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int maxID() {
        String sql = "SELECT MAX(MovieID) as max FROM dbo.Movie";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException e) {

        }

        return 0;
    }

    public SQLException insert(Movie m) {
        String sql = "insert into Movie values (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, m.getName());
            st.setInt(2, m.getYear());
            st.setNString(3, m.getDescription());
            if (m.getImage() != null) {
                st.setString(4, "images/" + m.getImage());
            } else {
                st.setString(4, "images/unnamed.jpg");
            }
            if (m.getTrailer() != null) {
                st.setString(5, "images/" + m.getTrailer());
            } else {
                st.setString(5, "images/test.mp4");
            }
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public SQLException insertGenre(int MovieID, int GenreID) {
        String sql = "insert into GenreMovie values (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, MovieID);
            st.setInt(2, GenreID);
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public SQLException insertRole(ActorRole m) {
        String sql = "insert into ActorRole values (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, m.getActorID());
            st.setInt(2, m.getMovieID());
            st.setNString(3, m.getRole());
            st.executeUpdate();
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    public List<ActorRole> getActorByMovieID(int ID) {
        List<ActorRole> list = new ArrayList<>();
        String sql = "select *from ActorRole where MovieID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ActorRole a = new ActorRole();
                ActorDAO aDao = new ActorDAO();
                a.setActor(aDao.get(rs.getInt("ActorID")));
                a.setRole(rs.getNString("Role"));
                list.add(a);

            }

        } catch (SQLException e) {

        }

        return list;
    }

    public List<Genre> getGenreByMovieID(int ID) {
        List<Genre> list = new ArrayList<>();
        String sql = "select *from GenreMovie where MovieID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GenreDAO gDao = new GenreDAO();
                Genre m = gDao.get(rs.getInt("GenreID"));
                list.add(m);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int deleteTable(int id, String table) {
        String sql = "delete from " + table + " where MovieID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public String deleteMovie(int id, String realPath) {
        Movie movie = get(id);
        String mess = "";
        mess = mess + "Delete " + deleteTable(id, "ActorRole") + "from ActorRole\n";
        mess = mess + "Delete " + deleteTable(id, "GenreMovie") + "from GenreMovie\n";
        mess = mess + "Delete " + deleteTable(id, "Rate") + "from Rate\n";
        mess = mess + "Delete " + deleteTable(id, "RecentlyView") + "from RecentlyView\n";
        mess = mess + "Delete " + deleteTable(id, "WatchList") + "from WatchList\n";
        mess = mess + "Delete " + deleteTable(id, "Movie") + "from Movie\n";
        mess = mess + "Delete " + movie.getImage() + ": " + FileAction.deleteFile(movie.getImage(), realPath) + "\n";
        mess = mess + "Delete " + movie.getTrailer() + ": " + FileAction.deleteFile(movie.getTrailer(), realPath) + "\n";
        return mess;
    }

    public int updateDescription(String Description, int id) {
        String sql = "update Movie set Description=?  where MovieID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, Description);
            st.setInt(2, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int updateImgTrailer(String img, int id, String table) {
        String sql = "update Movie set " + table + "=?  where MovieID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "images/" + img);
            st.setInt(2, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public List<Movie> getTopListMovie() {
        List<Movie> list = new ArrayList<>();
        String sql
                = "SELECT TOP(5) a.MovieID,a.Name,a.Image,ROUND(AVG(CAST(Rate AS FLOAT)),1) as 'avg' FROM dbo.Movie a \n"
                + "INNER JOIN dbo.Rate b\n"
                + "ON a.MovieID = b.MovieID\n"
                + "GROUP BY a.MovieID,a.Name,a.Image\n"
                + "ORDER BY AVG(CAST(Rate AS FLOAT)) * LOG(COUNT(*),1000) DESC, a.MovieID ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                RateDAO r = new RateDAO();
                m.setMovieID(rs.getInt("MovieID"));
                m.setName(rs.getNString("Name"));
                m.setImage(rs.getString("Image"));
                m.setScore(rs.getFloat("avg"));
                m.setNumScore(r.getNumScoreMovie(rs.getInt("MovieID")));
                list.add(m);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public List<Movie> getTopTimeMovie(String typeTime) {
        List<Movie> list = new ArrayList<>();
        String sql
                = "SELECT TOP(5) a.MovieID FROM dbo.Movie a INNER JOIN(\n"
                + "SELECT UserID,MovieID,\n"
                + "1 / ( (CAST(DATEDIFF(" + typeTime + ",Time,GETDATE())AS FLOAT)) +1)* Rate  AS 'Score'\n"
                + "FROM dbo.Rate\n"
                + ") AS b\n"
                + "ON b.MovieID = a.MovieID\n"
                + "GROUP BY a.MovieID,a.Name\n"
                + "ORDER BY avg(b.Score) * LOG(COUNT(*),1000) DESC, a.MovieID ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MovieDAO m = new MovieDAO();
                Movie movie = m.get(rs.getInt("MovieID"));
                list.add(movie);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public List<Movie> getTopOrderMovie(String typeOrder) {
        List<Movie> list = new ArrayList<>();
        String sql
                = "SELECT TOP(5) Name,Rate.MovieID, "
                + "AVG(CAST(Rate AS FLOAT)) AS 'Rate',"
                + "COUNT(*) AS 'Vote' FROM dbo.Movie "
                + "INNER JOIN dbo.Rate\n"
                + "ON Rate.MovieID = Movie.MovieID\n"
                + "GROUP BY Name, Rate.MovieID\n"
                + "ORDER BY " + typeOrder + " DESC, Rate.MovieID ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MovieDAO m = new MovieDAO();
                Movie movie = m.get(rs.getInt("MovieID"));
                list.add(movie);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Movie> getSerieMovie(Movie currentMovie) {
        List<Movie> list = new ArrayList<>();
        String sql
                = "DECLARE @Num INT\n"
                + "SET @Num = (\n"
                + "	SELECT num from (\n"
                + "		SELECT ROW_NUMBER() OVER (ORDER BY [Name]) AS 'Num',*\n"
                + "		FROM [Judge].[dbo].[Movie]\n"
                + "	) AS a\n"
                + "	WHERE MovieID = ? \n"
                + ")\n"
                + "\n"
                + "SELECT *FROM (\n"
                + "	SELECT ROW_NUMBER() OVER (ORDER BY [Name]) AS 'Num',\n"
                + "	* FROM [Judge].[dbo].[Movie]\n"
                + "	)\n"
                + "AS a WHERE (a.Num BETWEEN @Num -2 AND @Num + 3) AND NOT MovieID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, currentMovie.getMovieID());
            st.setInt(2, currentMovie.getMovieID());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                boolean realated = true;
                try {
                    String currentName = currentMovie.getName();
                    String name = rs.getNString("name");
                    String[] part1 = currentName.split(" ");
                    String[] part2 = name.split(" ");
                    int length = (part1.length < part2.length ? part1.length : part2.length) / 5 + 1;
                    for (int i = 0; i < length; i++) {
                        realated = realated && (part1[i].equals(part2[i]));
                        if (i == 0 && realated) {
                            String check = part1[0];
                            if (check.equalsIgnoreCase("the") || check.equalsIgnoreCase("a") || check.equalsIgnoreCase("an")) {
                                length++;
                            }
                        }
                    }
                    if (realated) {
                        Movie m = new Movie();
                        int id = rs.getInt("MovieID");
                        m.setMovieID(id);
                        m.setName(rs.getNString("Name"));
                        m.setDescription(rs.getNString("Description"));
                        m.setImage(rs.getString("Image"));
                        RateDAO r = new RateDAO();
                        m.setScore(r.getScoreMovie(id));
                        m.setNumScore(r.getNumScoreMovie(id));
                        list.add(m);
                    }
                } catch (SQLException e) {
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Movie> getSearchGenrePage(String[] genresID, int page) {
        List<Movie> list = new ArrayList<>();
        if (genresID == null || genresID.length == 0) {
            return getListMoviePage(page, "desc");
        }
        String where = "where ";
        for (String i : genresID) {
            where = where + " GenreID = " + i;
            if (!i.equals(genresID[genresID.length - 1])) {
                where = where + " or ";
            }
        }
        String sql = "Select a.* from Movie a,(\n"
                + "	Select a.MovieID, ROW_NUMBER() OVER (ORDER BY MovieID ASC) as 'Num' from(\n"
                + "		Select a.MovieID, Count(*) as 'Count' from(\n"
                + "			Select *from GenreMovie\n" + where
                + "			) as a\n"
                + "		group by a.MovieID\n"
                + "	) as a\n"
                + "	where a.Count = ?\n"
                + ") as b\n"
                + "where a.MovieID = b.MovieID and (b.Num between ? and ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, genresID.length);
            st.setInt(2, 10 * (page - 1) + 1);
            st.setInt(3, 10 * page);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie m = new Movie();
                int id = rs.getInt("MovieID");
                m.setMovieID(id);
                m.setName(rs.getNString("Name"));
                m.setDescription(rs.getNString("Description"));
                m.setImage(rs.getString("Image"));
                RateDAO r = new RateDAO();
                m.setScore(r.getScoreMovie(id));
                m.setNumScore(r.getNumScoreMovie(id));
                list.add(m);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public int sizeSearchGenrePage(String[] genresID) {
        if (genresID == null || genresID.length == 0) {
            return size();
        }
        String where = "where ";
        for (String i : genresID) {
            where = where + " GenreID = " + i;
            if (!i.equals(genresID[genresID.length - 1])) {
                where = where + " or ";
            }
        }
        String sql = "Select COUNT(*) as 'Num' from Movie a,(\n"
                + "	Select a.MovieID, ROW_NUMBER() OVER (ORDER BY MovieID ASC) as 'Num' from(\n"
                + "		Select a.MovieID, Count(*) as 'Count' from(\n"
                + "			Select *from GenreMovie\n"
                + where
                + "			) as a\n"
                + "		group by a.MovieID\n"
                + "	) as a\n"
                + "	where a.Count = ?\n"
                + ") as b\n"
                + "where a.MovieID = b.MovieID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, genresID.length);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("Num");
            }

        } catch (SQLException e) {

        }

        return 0;
    }

    public List<Movie> getRelatedMovie(Movie currentMovie) {
        List<Movie> list = getSerieMovie(currentMovie);
        try {
            if (list.size() < 5) {
                String[] genres = new String[currentMovie.getGenres().size()];
                for (int i = 0; i < currentMovie.getGenres().size(); i++) {
                    genres[i] = Integer.toString(currentMovie.getGenres().get(i).getGenreId());
                }
                List<Movie> add = getSearchGenrePage(genres, 1);
                for (Movie m : add) {
                    int movieID = m.getMovieID();
                    boolean duplicate = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (movieID == list.get(i).getMovieID() || movieID == currentMovie.getMovieID()) {
                            duplicate = true;
                        }
                    }
                    if (!duplicate) {
                        list.add(m);
                    }
                    if (list.size() == 5) {
                        break;
                    }
                }
            }
            if (list.size() < 5) {
                String[] genres = new String[currentMovie.getGenres().size() - 1];
                for (int i = 0; i < currentMovie.getGenres().size() - 1; i++) {
                    genres[i] = Integer.toString(currentMovie.getGenres().get(i).getGenreId());
                }
                List<Movie> add = getSearchGenrePage(genres, 1);
                for (Movie m : add) {
                    int movieID = m.getMovieID();
                    boolean duplicate = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (movieID == list.get(i).getMovieID() || movieID == currentMovie.getMovieID()) {
                            duplicate = true;
                        }
                    }
                    if (!duplicate) {
                        list.add(m);
                    }
                    if (list.size() == 5) {
                        break;
                    }
                }
            }
            if (list.size() < 5) {
                String[] genres = new String[1];
                genres[0] = Integer.toString(currentMovie.getGenres().get(0).getGenreId());

                List<Movie> add = getSearchGenrePage(genres, 1);
                for (Movie m : add) {
                    int movieID = m.getMovieID();
                    boolean duplicate = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (movieID == list.get(i).getMovieID() || movieID == currentMovie.getMovieID()) {
                            duplicate = true;
                        }
                    }
                    if (!duplicate) {
                        list.add(m);
                    }
                    if (list.size() == 5) {
                        break;
                    }
                }
            }
        } catch (Exception e) {

        }

        return list;
    }

    public static void main(String[] args) {
        MovieDAO m = new MovieDAO();
        System.out.println(m.getListMoviePage(1, "ASC").size());
    }
}
