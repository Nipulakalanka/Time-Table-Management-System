package application.Model;

public class sessionform {

	int sfid;
	String sgl1,sgl2,sgsc,sgs,sggid,sgt1,sgt2;
	
	
	public sessionform(int sfid, String sgl1, String sgl2, String sgsc, String sgs, String sggid,
			String sgt1, String sgt2) {
		super();
		this.sfid = sfid;
		this.sgl1 = sgl1;
		this.sgl2 = sgl2;
		this.sgsc = sgsc;
		this.sgs = sgs;
		this.sggid = sggid;
		this.sgt1 = sgt1;
		this.sgt2 = sgt2;
	}


	public int getSfid() {
		return sfid;
	}

	public String getSgl1() {
		return sgl1;
	}


	public String getSgl2() {
		return sgl2;
	}

	public String getSgsc() {
		return sgsc;
	}

    public String getSgs() {
		return sgs;
	}


	public String getSggid() {
		return sggid;
	}


	public String getSgt1() {
		return sgt1;
	}

	public String getSgt2() {
		return sgt2;
	}
	
}
