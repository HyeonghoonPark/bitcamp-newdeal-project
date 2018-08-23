package bcms.dao;

import java.util.List;

import bcms.domain.BusinessCard;

public interface BusinessCardDao {

	List<BusinessCard> getCardList(int mno);

}
