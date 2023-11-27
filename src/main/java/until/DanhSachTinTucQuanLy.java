package until;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.DanhMuc;
import model.TinTuc;

public class DanhSachTinTucQuanLy {
	private DataSource  dataSource;

	public DanhSachTinTucQuanLy(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	public void delete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "delete from TINTUC where MATT = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		} finally {
			close(connection, statement, null);
		}
	}
	public void update(int id , TinTuc tinTuc) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "update TINTUC set TIEUDE =? , NOIDUNGTT =?, LIENKET = ? ,MADM = ? where MATT = ?";
			statement= connection.prepareStatement(sql);
			statement.setString(1, tinTuc.getTieuDe());
			statement.setString(2, tinTuc.getNoiDungTT());
			statement.setString(3, tinTuc.getLienKet());
			statement.setInt(4, tinTuc.getMaDM().getMaDM());
			statement.setInt(5, id);
			statement.execute();
		} finally {
			close(connection, statement, null);
		}
	}
	public void insert(TinTuc tinTuc) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "insert into TINTUC values(?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, tinTuc.getTieuDe());
			statement.setString(2, tinTuc.getNoiDungTT());
			statement.setString(3, tinTuc.getLienKet());
			statement.setInt(4, tinTuc.getMaDM().getMaDM());
			statement.execute();
		} finally {
			close(connection, statement, null);
		}
	}
	public List<TinTuc> TTFollowDM(int id) throws SQLException{
		List<TinTuc> tinTucs = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null; 
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select MATT from TINTUC where MADM = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			while(rs.next()) {
				TinTuc find = findOneTT(rs.getInt("MATT"));
				
				
				
				tinTucs.add(find);
			}
			return tinTucs;
		} finally {
			close(connection, statement, rs);
		}
	}
	public List<DanhMuc> listDanhMuc() throws SQLException{
		List<DanhMuc> danhMucs= new ArrayList<>();
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from DANHMUC";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int ma = rs.getInt("maDM");
				String ten = rs.getString("tenDanhMuc");
				String nguoiQL = rs.getString("nguoiQuanLy");
				String ghiChu = rs.getString("ghiChu");
				DanhMuc danhMuc = new DanhMuc(ma, ten, nguoiQL, ghiChu);
				danhMucs.add(danhMuc);
				
			}
			return danhMucs;
		} finally {
			close(connection, statement, rs);
		}
	}
	public TinTuc findOneTT(int id) throws SQLException {
		TinTuc tinTuc = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from TINTUC  where MATT= ? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				int ma = rs.getInt("maTT");
				String tieuDe = rs.getString("tieuDe");
				String noiDung = rs.getString("noiDungTT");
				String lienKet = rs.getString("lienKet");
				int maDM = rs.getInt("maDM");
				DanhMuc dm = findDanhMuc(maDM);
				tinTuc = new TinTuc(ma, tieuDe, noiDung, lienKet, dm);
			}
			return tinTuc;
		} finally {
			close(connection, statement, rs);
		}
		
	}
	public DanhMuc findDanhMuc(int id) throws SQLException {
		DanhMuc danhMuc = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from DANHMUC  where MADM= ? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				int ma = rs.getInt("maDM");
				String ten = rs.getString("tenDanhMuc");
				String nguoiQL = rs.getString("nguoiQuanLy");
				String ghiChu = rs.getString("ghiChu");
				danhMuc = new DanhMuc(ma, ten, nguoiQL, ghiChu);
				return danhMuc;
			}
		} finally {
			close(connection,statement,rs);
		}
		return danhMuc;
		
	}
	private void close(Connection connection, Statement statement, ResultSet rs) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<TinTuc> findAll() throws SQLException{
		List<TinTuc> tintucs= new ArrayList<>();
		Connection connection = null;
		Statement statement = null; 
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select * from TINTUC";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("maTT");
				String tieuDe = rs.getString("tieuDe");
				String noiDung = rs.getString("noiDungTT");
				String lienKet = rs.getString("lienKet");
				int maDM = rs.getInt("maDM");
				DanhMuc dm = findDanhMuc(maDM);
				TinTuc tinTuc = new TinTuc(id, tieuDe, noiDung, lienKet, dm);
				tintucs.add(tinTuc);
				
			}
			return tintucs;
		} finally {
			close(connection, statement, rs);
		}
	}
}
