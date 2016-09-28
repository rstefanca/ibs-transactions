package cz.codingmonkeys.ibs.domain;

import lombok.*;


import javax.persistence.*;

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

	protected DirectChannelUser() {
	}

	public DirectChannelUser(String mfaType) {
		this.mfaType = mfaType;
	}


}
