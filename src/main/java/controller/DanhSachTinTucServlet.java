package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.DanhMuc;
import model.TinTuc;
import until.DanhSachTinTucQuanLy;

/**
 * Servlet implementation class DanhSachTinTucServlet
 */
@WebServlet(name = "DanhSachTinTucServlet", urlPatterns = {"/TrangChu","/DanhSachTinTucServlet","/DanhSachTT","/delete","/insert","/TinTucFormServlet","/FormUpdate","/update"})
public class DanhSachTinTucServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/TinTuc")
    private DataSource dataSource;
	
	private DanhSachTinTucQuanLy danhSachTT;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachTinTucServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			danhSachTT = new DanhSachTinTucQuanLy(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void TrangChu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
		rs.forward(request, response);
	}
	private void deleteOne(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("id");
		int ma = Integer.parseInt(id);
		danhSachTT.delete(ma);
		response.sendRedirect(request.getContextPath() + "/DanhSachTinTucServlet");
		
	}
	private void listDanhSachTT(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<TinTuc> tinTucs = danhSachTT.findAll();
		request.setAttribute("model", tinTucs);
		List<DanhMuc> danhMucs = danhSachTT.listDanhMuc();
		request.setAttribute("model1", danhMucs);
		RequestDispatcher rs = request.getRequestDispatcher("DanhSachTinTuc.jsp");
		rs.forward(request, response);
	}
	private void listTTFLDM(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		int ma = Integer.parseInt(id);
		List<TinTuc> tinTucs = danhSachTT.TTFollowDM(ma);
		request.setAttribute("model", tinTucs);
		RequestDispatcher rs = request.getRequestDispatcher("DanhSachTTTheoDM.jsp");
		rs.forward(request, response);
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDungTT");
		String lienKet = request.getParameter("lienKet");
		String ma = request.getParameter("danhMuc");
		int id = Integer.parseInt(ma);
		DanhMuc findDM = danhSachTT.findDanhMuc(id);
		TinTuc tinTuc = new TinTuc(tieuDe, noiDung, lienKet, findDM);
		danhSachTT.insert(tinTuc);
		response.sendRedirect(request.getContextPath() + "/DanhSachTinTucServlet");
	}
	private void formUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String ma = request.getParameter("id");
		int id = Integer.parseInt(ma);
		TinTuc tinTuc = danhSachTT.findOneTT(id);
		List<DanhMuc> danhMucs= danhSachTT.listDanhMuc();
		request.setAttribute("models", danhMucs);
		request.setAttribute("model",tinTuc);
		RequestDispatcher rs = request.getRequestDispatcher("FormUpdate.jsp");
		rs.forward(request, response);
	}
	private void formInsert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<DanhMuc> danhMucs= danhSachTT.listDanhMuc();
		request.setAttribute("model", danhMucs);
		RequestDispatcher rs = request.getRequestDispatcher("TinTucForm..jsp");
		rs.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response, int maTT) throws SQLException, IOException {
		String tieuDe = request.getParameter("tieuDe");
		String noiDung = request.getParameter("noiDungTT");
		String lienKet = request.getParameter("lienKet");
		String ma = request.getParameter("danhMuc");
		int id = Integer.parseInt(ma);
		DanhMuc findDM = danhSachTT.findDanhMuc(id);
		TinTuc tinTuc = new TinTuc(tieuDe, noiDung, lienKet, findDM);
		danhSachTT.update(maTT, tinTuc);
		
		
		response.sendRedirect(request.getContextPath() + "/DanhSachTinTucServlet");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("/TrangChu")) {
			TrangChu(request, response);
		}
		else if (uri.endsWith("/DanhSachTinTucServlet")) {
			try {
				listDanhSachTT(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/DanhSachTT")) {
			try {
				listTTFLDM(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/TinTucFormServlet")) {
			try {
				formInsert(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/FormUpdate")) {
			try {
				formUpdate(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.endsWith("/delete")) {
			try {
				deleteOne(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/insert")) {
			try {
				insert(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (uri.endsWith("/update")) {
			String ma = request.getParameter("id");
			int id = Integer.parseInt(ma);
			try {
				update(request, response, id);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
