package jdbc.addr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {
	private DBConnect db;

	public Dao() {
		db = DBConnect.getInstance();
	}

	// insert : 한명 추가, num = 자동할당 (seq_addr)
	public int insert(Vo v) {
		Connection conn = db.conn();

		String sql = "INSERT INTO addr VALUES(seq_addr.nextval, ?, ?, ?)";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, v.getName());
			pstmt.setString(2, v.getTel());
			pstmt.setString(3, v.getAddr());

			cnt = pstmt.executeUpdate();

			System.out.println(cnt + " 줄 추가됨");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// update : 번호로 찾아서 전화번호, 주소 수정
	public int update(Vo v) {
		Connection conn = db.conn();

		String sql = "UPDATE addr SET tel = ?, addr = ? WHERE num = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, v.getTel());
			pstmt.setString(2, v.getAddr());
			pstmt.setInt(3, v.getNum());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// delete : 번호로 찾아서 삭제
	public int delete(int num) {
		Connection conn = db.conn();

		String sql = "DELETE FROM addr WHERE num = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// select : 번호로 찾아서 vo 반환
	public Vo select(int num) {
		Connection conn = db.conn();

		String sql = "SELECT * FROM addr WHERE num = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Vo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// selectAll : 전체 검색
	public ArrayList<Vo> selectAll() {
		Connection conn = db.conn();

		String sql = "SELECT * FROM addr ORDER BY num";

		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Vo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// selectByName : name으로 검색 (like 패턴 사용), 여러 줄 검색 ArrayList에 담아서 반환
	public ArrayList<Vo> selectByName(String name) {
		Connection conn = db.conn();

		String sql = "SELECT * FROM addr WHERE name LIKE ?";
		ArrayList<Vo> list = new ArrayList<Vo>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Vo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// selectByTel : tel로 검색(like 패턴으로 검색), 여러 줄 검색 ArrayList에 담아서 반환
	public ArrayList<Vo> selectByTel(String tel) {
		Connection conn = db.conn();

		String sql = "SELECT * FROM addr WHERE tel LIKE ?";
		ArrayList<Vo> list = new ArrayList<Vo>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + tel + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Vo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;

	}

}
