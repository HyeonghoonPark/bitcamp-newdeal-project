package bcms.service.impl;

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
	public List<BusinessCard> list(int mno) {
		System.out.println("mno"+ mno);
		return businessCardDao.getCardList(mno);
	}
}
