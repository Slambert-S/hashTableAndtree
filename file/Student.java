
public class Student {
	private long sidc;
	private String info;
	
	public Student() {
		this.sidc= 00000000;
		this.info = "No custum information";
	}
	
	public Student(long sidc, String info) {
		this.sidc =  sidc;
		this.info = info;
	}
	
	public void setSidc(long sidc) {
		this.sidc = sidc;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}
	public long getSidc() {
		return sidc;
	}
}
