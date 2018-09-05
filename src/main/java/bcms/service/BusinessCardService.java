package bcms.service;

import java.util.List;

import bcms.domain.BusinessCard;

public interface BusinessCardService {

	List<BusinessCard> getBusinessCardList(int mno);

	int addBusinessCard(BusinessCard businessCard);

	int updateBusinessCard(BusinessCard businessCard);

	int deleteCard(String bcno, int mno);

	BusinessCard getSingleBusinessCardInfo(int mno, int cardNo);

	
}
