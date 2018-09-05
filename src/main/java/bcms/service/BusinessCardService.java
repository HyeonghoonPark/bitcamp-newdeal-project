package bcms.service;

import java.util.List;

import bcms.domain.BusinessCard;

public interface BusinessCardService {

	List<BusinessCard> getBusinessCardList(int mno);

	int addBusinessCard(BusinessCard businessCard);

	BusinessCard getSingleBusinessCardInfo(int mno, int cardNo);

	int deleteCard(String bcno, int mno);

	
}
