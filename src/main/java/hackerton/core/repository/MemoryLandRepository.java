package hackerton.core.repository;

import hackerton.core.domain.Land;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryLandRepository implements LandRepository {

    private final DataSource dataSource;


    public MemoryLandRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Land save(Land land) {

        String sql = "insert into land(address, area_size, money, crops, type, tractor, rice_planting, fluid_fertilizer, combine, tree_crush) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    public Land findById(long id) {
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

            }

            return land;

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
                land.setTractor(rs.getString("tractor"));
                land.setRice_planting(rs.getString("rice_planting"));
                land.setFluid_fertilizer(rs.getString("fluid_fertilizer"));
                land.setCombine(rs.getString("combine"));
                land.setTree_crush(rs.getString("tree_crush"));

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

                lands.add(land);
            }
            return lands;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    public List<String> findPictureById(Long land_id) {
        String sql = "select name from picture where land_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, land_id);
            rs = pstmt.executeQuery();
            List<String> pictures = new ArrayList<>();
            while (rs.next()) {
                String picture = null;
                picture = rs.getString("name");
                pictures.add(picture);
            }

            return pictures;

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
