package bcms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcms.dao.BusinessCardDao;
import bcms.domain.BusinessCard;
import bcms.service.BusinessCardService;

@Service
public class BusinessCardServiceImpl implements BusinessCardService {

	@Autowired BusinessCardDao businessCardDao;
	
	@Override
	public List<BusinessCard> getBusinessCardList(int mno) {
		System.out.println("mno"+ mno);
		return businessCardDao.getBusinessCardList(mno);
	}
	
	@Override
	public int addBusinessCard(BusinessCard businessCard) {
		return businessCardDao.addBusinessCard(businessCard);
	}

	@Override
	public BusinessCard getSingleBusinessCardInfo(int mno, int cardNo) {
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("mno", mno);
		params.put("cardNo", cardNo);
		return businessCardDao.getSingleBusinessCardInfo(params);
	}

	@Override
	public int deleteCard(String bcno, int mno) {
		
		HashMap<String, Object> params = new HashMap<>();
		
		params.put("mno", mno);
		params.put("bcno", bcno);
		
		return businessCardDao.deleteCard(params);
	}
	
}
