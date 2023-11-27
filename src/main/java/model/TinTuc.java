package model;

public class TinTuc {
	private int maTT;
	private String tieuDe;
	private String noiDungTT;
	private String lienKet;
	private DanhMuc maDM ;
	public int getMaTT() {
		return maTT;
	}
	public void setMaTT(int maTT) {
		this.maTT = maTT;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getNoiDungTT() {
		return noiDungTT;
	}
	public void setNoiDungTT(String noiDungTT) {
		this.noiDungTT = noiDungTT;
	}
	public String getLienKet() {
		return lienKet;
	}
	public void setLienKet(String lienKet) {
		this.lienKet = lienKet;
	}
	public DanhMuc getMaDM() {
		return maDM;
	}
	public void setMaDM(DanhMuc maDM) {
		this.maDM = maDM;
	}
	public TinTuc(int maTT, String tieuDe, String noiDungTT, String lienKet, DanhMuc maDM) {
		super();
		this.maTT = maTT;
		this.tieuDe = tieuDe;
		this.noiDungTT = noiDungTT;
		this.lienKet = lienKet;
		this.maDM = maDM;
	}
	public TinTuc(String tieuDe, String noiDungTT, String lienKet, DanhMuc maDM) {
		super();
		this.tieuDe = tieuDe;
		this.noiDungTT = noiDungTT;
		this.lienKet = lienKet;
		this.maDM = maDM;
	}
	public TinTuc() {
		
	}
	
}
