package cz.codingmonkeys.ibs.domain;

import lombok.Data;

/**
 * @author Richard Stefanca
 */
@Data
public class DirectChannelUser {

	private String mfaType;

	public DirectChannelUser(String mfaType) {
		this.mfaType = mfaType;
	}

	public String getMfaType() {
		return mfaType;
	}

	public void setMfaType(String mfaType) {
		this.mfaType = mfaType;
	}
}
