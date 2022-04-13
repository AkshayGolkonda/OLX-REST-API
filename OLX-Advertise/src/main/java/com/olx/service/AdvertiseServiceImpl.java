package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.entity.AdvertiseEntity;
import com.olx.repository.AdvertiseRepo;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	AdvertiseRepo advertiseRepo;
	
	//@Autowired
	ModelMapper modelMapper=new ModelMapper();
	
	@Override
	public Advertise postAdvertise(Advertise adv) {
		return null;
	}

	@Override
	public Advertise updateAdvertise(Advertise adv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertise> getAllAdvByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertise> getAdvByUser(String uname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAdvByUserId() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Advertise> searchAdvertisesByFilterCriteria(String searchText, String category, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {
		List<AdvertiseEntity> entityList=advertiseRepo.findAll();
		CriteriaBuilder critertiaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery=critertiaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity=criteriaQuery.from(AdvertiseEntity.class);
		
		Predicate predicateTitle=critertiaBuilder.and();
		Predicate predicateDescription = critertiaBuilder.and();
		Predicate predicateSearchText= critertiaBuilder.and();
		Predicate predicateCategoryName=critertiaBuilder.and();
		Predicate predicatePostedBy = critertiaBuilder.and();
		Predicate predicateDateCondition=critertiaBuilder.and();
		Predicate predicateDateConditionEquals = critertiaBuilder.and();
		Predicate predicateDateConditionGreaterThan = critertiaBuilder.and();
		Predicate predicateDateConditionLessThan = critertiaBuilder.and();
		Predicate predicateDateConditionBetween = critertiaBuilder.and();
		Predicate predicateFinal = critertiaBuilder.and();
		
		if(searchText!=null && !"".equalsIgnoreCase(searchText)) {
			predicateTitle=critertiaBuilder.like(rootEntity.get("title"),"%"+searchText+"%");
			predicateDescription=critertiaBuilder.like(rootEntity.get("description"),"%"+searchText+"%");
			predicateSearchText=critertiaBuilder.or(predicateTitle,predicateDescription);
		}

		if(postedBy!=null && !"".equalsIgnoreCase(postedBy)) {
			predicatePostedBy=critertiaBuilder.equal(rootEntity.get("username"),postedBy);
		}
		
		if(category!=null && !"".equalsIgnoreCase(category)) {
			predicateCategoryName=critertiaBuilder.equal(rootEntity.get("category"), category);
		}
		
		if(dateCondition!=null && dateCondition.contains("equal")) {
			predicateDateConditionEquals=critertiaBuilder.equal(rootEntity.get("createdDate"),onDate);
		}
		if(dateCondition!=null && dateCondition.contains("greater")) {
			predicateDateConditionGreaterThan=critertiaBuilder.greaterThan(rootEntity.get("createdDate"),fromDate);	
		}
		if(dateCondition!=null && dateCondition.contains("less")) {
			predicateDateConditionLessThan=critertiaBuilder.lessThan(rootEntity.get("createdDate"), fromDate);
		}
		if(dateCondition!=null && dateCondition.contains("between")) {
			predicateDateConditionBetween=critertiaBuilder.between(rootEntity.get("createdDate"), fromDate, toDate);
		}
		predicateDateCondition=critertiaBuilder.and(predicateDateConditionEquals,predicateDateConditionBetween,predicateDateConditionGreaterThan,predicateDateConditionLessThan);
		
		
		predicateFinal=critertiaBuilder.and(predicateSearchText,predicatePostedBy,predicateCategoryName,predicateDateCondition);
		criteriaQuery.where(predicateFinal);
		if(sortedBy!=null && !"".equalsIgnoreCase(sortedBy)) {
			if(sortedBy.equalsIgnoreCase("title")) {
				criteriaQuery.orderBy(critertiaBuilder.asc(rootEntity.get("title")));
			}
			if(sortedBy.equalsIgnoreCase("price")) {
				criteriaQuery.orderBy(critertiaBuilder.asc(rootEntity.get("price")));
			}
		}
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertiseEntity> advertiseEntityList=typedQuery.getResultList();
		//write a convert and return advertise list here
		List<Advertise> advertiseList=new ArrayList<>();
		for(AdvertiseEntity a:advertiseEntityList)
			advertiseList.add(convertEntityIntoDTO(a));
		
		return advertiseList;
	}

	@Override
	public Advertise SearchAdvByText(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertise returnAdv(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private AdvertiseEntity convertDTOIntoEntity(Advertise advertise) {
		AdvertiseEntity advertiseEntity=modelMapper.map(advertise,AdvertiseEntity.class);
		return advertiseEntity;
	}
	
	private Advertise convertEntityIntoDTO(AdvertiseEntity advertiseEntity) {
		Advertise advertise=modelMapper.map(advertiseEntity, Advertise.class);
		return advertise;
	}

	
}
