package bcms.service;

import java.util.List;

import bcms.domain.BusinessCard;

public interface BusinessCardService {

	List<BusinessCard> list(int mno);
	
}
