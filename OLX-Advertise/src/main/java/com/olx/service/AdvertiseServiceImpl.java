package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.olx.exception.FromDateMissingException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.exception.InvalidPageIdException;
import com.olx.exception.InvalidStatusIdException;
import com.olx.exception.OnDateMissingException;
import com.olx.exception.SearchTextMissingException;
import com.olx.exception.ToDateMissingException;
import com.olx.exception.UserNameDoesNotExistException;
import com.olx.repository.AdvertiseRepo;
import com.olx.security.JwtUtil;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	AdvertiseRepo advertiseRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	LoginServiceDelegate loginServiceDelegate;
	
	@Autowired
	MasterDataServiceDelegate masterDataServiceDelegate;
	
	@Autowired
	JwtUtil jwtUtil;

	//8
	@Override
	public Advertise createNewAdvertise(Advertise adv, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)){
			AdvertiseEntity advertiseEntity=convertDTOIntoEntity(adv);
			if(adv.getCategoryId()<1)
				throw new InvalidCategoryIdException();
			if(adv.getStatusId()<1)
				throw new InvalidStatusIdException();
			advertiseEntity.setCategory(masterDataServiceDelegate.getCategoryValue(adv.getCategoryId()));
			advertiseEntity.setStatus(masterDataServiceDelegate.getStatusValue(adv.getStatusId()));
			advertiseEntity.setCreatedDate(LocalDate.now());
			advertiseEntity.setModifiedDate(LocalDate.now());
			authToken=authToken.substring(7);
			advertiseEntity.setUsername(jwtUtil.extractUsername(authToken));
			advertiseRepo.save(advertiseEntity);
			return convertEntityIntoDTO(advertiseEntity);
		}
		else{
			throw new InvalidAuthTokenException();
		}
	}

	//13
	@Override
	public List<Advertise> searchAdvertisesByFilterCriteria(String searchText, String category, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy,
			int startIndex, int records) {
		//List<AdvertiseEntity> entityList=advertiseRepo.findAll();
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
			if(onDate==null)
				throw new OnDateMissingException();
			predicateDateConditionEquals=critertiaBuilder.equal(rootEntity.get("createdDate"),onDate);
		}
		if(dateCondition!=null && dateCondition.contains("greater")) {
			if(fromDate==null)
				throw new FromDateMissingException();
			predicateDateConditionGreaterThan=critertiaBuilder.greaterThan(rootEntity.get("createdDate"),fromDate);	
		}
		if(dateCondition!=null && dateCondition.contains("less")) {
			if(fromDate==null)
				throw new FromDateMissingException();
			predicateDateConditionLessThan=critertiaBuilder.lessThan(rootEntity.get("createdDate"), fromDate);
		}
		if(dateCondition!=null && dateCondition.contains("between")) {
			if(fromDate==null)
				throw new FromDateMissingException();
			if(toDate==null)
				throw new ToDateMissingException();
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
		if(startIndex<0)
			throw new InvalidPageIdException();
		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertiseEntity> advertiseEntityList=typedQuery.getResultList();
		//write a convert and return advertise list here
		List<Advertise> advertiseList=new ArrayList<>();
		for(AdvertiseEntity a:advertiseEntityList)
			advertiseList.add(convertEntityIntoDTO(a));
		
		return advertiseList;
	}

	//14
	@Override
	public List<Advertise> SearchAdvByText(String searchText) {
		CriteriaBuilder critertiaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery=critertiaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> rootEntity=criteriaQuery.from(AdvertiseEntity.class);
		
		Predicate predicateTitle=critertiaBuilder.and();
		Predicate predicateDescription = critertiaBuilder.and();
		Predicate predicateSearchText= critertiaBuilder.and();
		if(searchText!=null && !"".equalsIgnoreCase(searchText)) {
			predicateTitle=critertiaBuilder.like(rootEntity.get("title"),"%"+searchText+"%");
			predicateDescription=critertiaBuilder.like(rootEntity.get("description"),"%"+searchText+"%");
			predicateSearchText=critertiaBuilder.or(predicateTitle,predicateDescription);
		}
		else
			throw new SearchTextMissingException();
		criteriaQuery.where(predicateSearchText);
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		List<AdvertiseEntity> advertiseEntityList=typedQuery.getResultList();
		//write a convert and return advertise list here
		List<Advertise> advertiseList=new ArrayList<>();
		for(AdvertiseEntity a:advertiseEntityList)
			advertiseList.add(convertEntityIntoDTO(a));
		
		return advertiseList;
	}

	
	private AdvertiseEntity convertDTOIntoEntity(Advertise advertise) {
		AdvertiseEntity advertiseEntity=modelMapper.map(advertise,AdvertiseEntity.class);
		return advertiseEntity;
	}
	
	private Advertise convertEntityIntoDTO(AdvertiseEntity advertiseEntity) {
		Advertise advertise=modelMapper.map(advertiseEntity, Advertise.class);
		return advertise;
	}


	//9
	@Override
	public Advertise updateAdvertise(int id,Advertise adv, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertiseEntity> optionalAdvertiseEntity=advertiseRepo.findById(id);
			if(optionalAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity=optionalAdvertiseEntity.get();
				advertiseEntity.setTitle(adv.getTitle());
				advertiseEntity.setDescription(adv.getDescription());
				advertiseEntity.setPrice(adv.getPrice());
				if(adv.getCategoryId()<1)
					throw new InvalidCategoryIdException();
				if(adv.getStatusId()<1)
					throw new InvalidStatusIdException();
				advertiseEntity.setCategory(masterDataServiceDelegate.getCategoryValue(adv.getCategoryId()));
				advertiseEntity.setStatus(masterDataServiceDelegate.getStatusValue(adv.getStatusId()));
				advertiseEntity.setModifiedDate(LocalDate.now());
				advertiseRepo.save(advertiseEntity);
				return convertEntityIntoDTO(advertiseEntity);
			}
			else
				throw new InvalidAdvertiseIdException();
		}
		else {
			throw new InvalidAuthTokenException();
		}
	}

	//10
	@Override
	public List<Advertise> getAllAdvByUser(String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			authToken=authToken.substring(7);
			List<AdvertiseEntity> advertiseEntities=advertiseRepo.findByUsername(jwtUtil.extractUsername(authToken));
			if (advertiseEntities.isEmpty()) {
				throw new UserNameDoesNotExistException();
			}
			List<Advertise> advertises=new ArrayList<>();
			for(AdvertiseEntity advertiseEntity:advertiseEntities)
				advertises.add(convertEntityIntoDTO(advertiseEntity));
			return advertises;
		}
		else {
			throw new InvalidAuthTokenException();
		}
	}


	//11
	@Override
	public Advertise getAdvById(int id,String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			authToken=authToken.substring(7);
			Optional<AdvertiseEntity> optionalAdvertiseEntity=advertiseRepo.findById(id);
			if(optionalAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity=optionalAdvertiseEntity.get();
				if(advertiseEntity.getUsername().equalsIgnoreCase(jwtUtil.extractUsername(authToken)))
					return convertEntityIntoDTO(advertiseEntity);
				else
					throw new InvalidAuthTokenException();
			}
			else
				throw new InvalidAdvertiseIdException();
		}
		else {
			throw new InvalidAuthTokenException();
		}
	}

	//12
	@Override
	public boolean deleteAdvById(int id,String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			if(advertiseRepo.existsById(id)) {
				advertiseRepo.deleteById(id);
				return true;
			}
			else
				throw new InvalidAdvertiseIdException();
		}
		else {
			throw new InvalidAuthTokenException();
		}
	}

	//15
	@Override
	public Advertise returnAdv(int id, String authToken) {
		if(loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertiseEntity> optionalAdvertiseEntity=advertiseRepo.findById(id);
			if(optionalAdvertiseEntity.isPresent())
				return convertEntityIntoDTO(optionalAdvertiseEntity.get());
			else
				throw new InvalidAdvertiseIdException();
		}
		else {
			throw new InvalidAuthTokenException();
		}
	}

	
}
