package hackathon.core.repository;

import hackathon.core.domain.*;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryLandRepository implements LandRepository {

    private final DataSource dataSource;


    public MemoryLandRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Land saveLand(Land land) {

        String sql = "insert into land(address, area_size, money, crops, type, tractor, rice_planting, fluid_fertilizer, combine, tree_crush, picture, incentive) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, land.getAddress());
            pstmt.setInt(2, land.getArea_size());
            pstmt.setLong(3, land.getMoney());
            pstmt.setString(4, land.getCrops());
            pstmt.setString(5, land.getType());
            pstmt.setString(6, land.getTractor());
            pstmt.setString(7, land.getRice_planting());
            pstmt.setString(8, land.getFluid_fertilizer());
            pstmt.setString(9, land.getCombine());
            pstmt.setString(10, land.getTree_crush());
            pstmt.setString(11, land.getPicture());
            pstmt.setInt(12, land.getIncentive());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                land.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }

            return land;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    @Override
    public Land saveCoordinate(Land land) {

        String sql = "insert into coordinates(id, x, y) values(?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, land.getId());
            pstmt.setDouble(2, land.getX());
            pstmt.setDouble(3, land.getY());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            return land;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    @Override
    public Optional<Land> findById(long id) {
        String sql = "select * from land where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            Land land = new Land();
            if (rs.next()) {

                land.setId(rs.getLong("id"));
                land.setAddress(rs.getString("address"));
                land.setArea_size(rs.getInt("area_size"));
                land.setMoney(rs.getLong("money"));
                land.setCrops(rs.getString("crops"));
                land.setType(rs.getString("type"));
                land.setTractor(rs.getString("tractor"));
                land.setRice_planting(rs.getString("rice_planting"));
                land.setFluid_fertilizer(rs.getString("fluid_fertilizer"));
                land.setCombine(rs.getString("combine"));
                land.setTree_crush(rs.getString("tree_crush"));
                land.setIncentive(rs.getInt("incentive"));
                return Optional.of(land);
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    @Override
    public List<Land> findAll() {
        String sql = "select * from land";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Land> lands = new ArrayList<>();

            while (rs.next()) {
                Land land = new Land();

                land.setId(rs.getLong("id"));
                land.setAddress(rs.getString("address"));
                land.setArea_size(rs.getInt("area_size"));
                land.setMoney(rs.getLong("money"));
                land.setCrops(rs.getString("crops"));
                land.setType(rs.getString("type"));
                land.setPicture(rs.getString("picture"));
                land.setTractor(rs.getString("tractor"));
                land.setRice_planting(rs.getString("rice_planting"));
                land.setFluid_fertilizer(rs.getString("fluid_fertilizer"));
                land.setCombine(rs.getString("combine"));
                land.setTree_crush(rs.getString("tree_crush"));
                land.setIncentive(rs.getInt("incentive"));

                lands.add(land);
            }
            return lands;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Land> findByAddress(String address) {
        String sql = "select * from land where address LIKE ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + address + "%");
            rs = pstmt.executeQuery();
            List<Land> lands = new ArrayList<>();

            while (rs.next()) {
                Land land = new Land();

                land.setId(rs.getLong("id"));
                land.setAddress(rs.getString("address"));
                land.setArea_size(rs.getInt("area_size"));
                land.setMoney(rs.getLong("money"));
                land.setCrops(rs.getString("crops"));
                land.setType(rs.getString("type"));
                land.setTractor(rs.getString("tractor"));
                land.setRice_planting(rs.getString("rice_planting"));
                land.setFluid_fertilizer(rs.getString("fluid_fertilizer"));
                land.setCombine(rs.getString("combine"));
                land.setTree_crush(rs.getString("tree_crush"));
                land.setIncentive(rs.getInt("incentive"));

                lands.add(land);
            }
            return lands;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    @Override
    public Coordinates findCoordinateById(Long land_id) {
        String sql = "select * from coordinates where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, land_id);
            rs = pstmt.executeQuery();

            Coordinates coor = new Coordinates();

            if (rs.next()) {

                coor.setX(rs.getDouble("x"));
                coor.setY(rs.getDouble("y"));
            }

            return coor;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Booking saveDate(Booking book, long land_id) {
        String sql = "insert into booking(`booking_datetime`, `current_datetime`,`land_id`, `phone`) values(?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Date currentDate = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        book.setCurrent(format.format(currentDate));

        book.setCurrent_datetime(currentDate);
        book.setLand_id(land_id);


        int year = book.getBooking_datetime().getYear() - 1900;
        int month = book.getBooking_datetime().getMonth();
        int date = book.getBooking_datetime().getDay();
        int hrs = book.getBooking_datetime().getHours();
        int min = book.getBooking_datetime().getMinutes();
        int sec = book.getBooking_datetime().getSeconds();

        Date bookingDate = new Date(year, month, date, hrs, min, sec);

        book.setBooking_datetime(bookingDate);

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setTimestamp(1, new Timestamp(bookingDate.getTime()));
            pstmt.setTimestamp(2, new Timestamp(currentDate.getTime()));
            pstmt.setLong(3, land_id);
            pstmt.setString(4, book.getPhone());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                book.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }

            return book;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Booking> findDate(long id) {
        String sql = "select * from booking where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            Booking book = new Booking();

            if (rs.next()) {
                book.setBooking_datetime(rs.getTimestamp("booking_datetime"));
                book.setCurrent_datetime(rs.getTimestamp("current_datetime"));
                book.setLand_id(rs.getLong("land_id"));
                book.setPhone(rs.getString("phone"));
                book.setBooking(rs.getString("booking_datetime"));
                book.setCurrent(rs.getString("current_datetime"));
                book.setId(id);
                return Optional.of(book);
            }
            return Optional.empty();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Notice> findNotice() {
        String sql = "select * from notice";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Notice> notices = new ArrayList<>();

            while (rs.next()) {
                Notice notice = new Notice();

                notice.setCategory(rs.getString("category"));
                notice.setTitle(rs.getString("title"));
                notice.setDate(rs.getString("date"));

                notices.add(notice);
            }
            return notices;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public News findNews() {

        String sql = "select * from news";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            News news = new News();

            if (rs.next()) {
                news.setTitle(rs.getString("title"));
                news.setText(rs.getString("text"));
                news.setDate(rs.getString("date"));
            }

            return news;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
