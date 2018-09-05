package bcms.dao;

import java.util.HashMap;
import java.util.List;

import bcms.domain.BusinessCard;

public interface BusinessCardDao {

	List<BusinessCard> getBusinessCardList(int mno);

	int addBusinessCard(BusinessCard businessCard);

	BusinessCard getSingleBusinessCardInfo(HashMap<String, Object>params);
	
}
