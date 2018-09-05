package bcms.dao;

import java.util.HashMap;
import java.util.List;

import bcms.domain.BusinessCard;

public interface BusinessCardDao {

	List<BusinessCard> getBusinessCardList(int mno);

	int addBusinessCard(BusinessCard businessCard);

	int updateBusinessCard(BusinessCard businessCard);

	int deleteCard(HashMap<String, Object> params);

	BusinessCard getSingleBusinessCardInfo(HashMap<String, Object>params);



}
