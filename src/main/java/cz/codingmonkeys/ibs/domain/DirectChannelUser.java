package cz.codingmonkeys.ibs.domain;

import lombok.*;


import javax.persistence.*;
import javax.swing.text.StringContent;

/**
 * @author Richard Stefanca
 */
@Data
@Entity
public class DirectChannelUser {

	@Id
	@GeneratedValue
	private Long id;

	private String mfaType;

	private String smsNumber;

	protected DirectChannelUser() {
	}

	public DirectChannelUser(String mfaType, String smsNumber) {
		this.mfaType = mfaType;
		this.setSmsNumber(smsNumber);
	}


}
