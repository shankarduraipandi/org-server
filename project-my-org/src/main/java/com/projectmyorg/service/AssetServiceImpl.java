/**
 * 
 */
package com.projectmyorg.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projectmyorg.commons.AppUtils;
import com.projectmyorg.commons.DateUtils;
import com.projectmyorg.commons.StringUtils;
import com.projectmyorg.domain.AssetHistory;
import com.projectmyorg.domain.AssetRequest;
import com.projectmyorg.domain.enums.Status;
import com.projectmyorg.domain.masters.Asset;
import com.projectmyorg.domain.masters.AssetCategory;
import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.PageResponse;
import com.projectmyorg.dto.SingleStrRequestDto;
import com.projectmyorg.dto.request.AssetDto;
import com.projectmyorg.dto.request.AssetRequestDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.exceptions.BadRequestException;
import com.projectmyorg.exceptions.InternalServerErrorException;
import com.projectmyorg.exceptions.RecordNotFoundException;
import com.projectmyorg.repo.AssetCategoryRepository;
import com.projectmyorg.repo.AssetHistoryRepository;
import com.projectmyorg.repo.AssetRepository;
import com.projectmyorg.repo.AssetRequestRepository;

/**
 * @author Shankar D
 *
 */
@Service
public class AssetServiceImpl implements AssetService {

	private static final String MANDATORY_FIELDS_NOT_FOUND = "Mandatory fields not found!";

	@Autowired
	private AssetCategoryRepository assetCategoryRepo;

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AssetRequestRepository assetRequestRepo;

	@Autowired
	private AssetHistoryRepository assetHistoryRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetServiceImpl.class);

	public Optional<Asset> findAssetByNo(String assetNo) {
		return assetRepository.findByAssetNo(assetNo);
	}

	public Optional<AssetCategory> findAssetCategory(String assetName) {
		return assetCategoryRepo.findByCategory(assetName);
	}

	@Override
	public BaseResponse addAssetCategory(SingleStrRequestDto request) {
		Optional<AssetCategory> category = findAssetCategory(request.getValue());
		if (category.isPresent())
			throw new BadRequestException("Asset category is already exist!");

		try {
			AssetCategory assetCategory = new AssetCategory();
			assetCategory.setCategory(request.getValue());
			assetCategoryRepo.save(assetCategory);

			LOGGER.info("Added new asset category. {}", request.getValue());
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while adding the asset. Message {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public BaseResponse addAsset(AssetDto request) {
		Optional<AssetCategory> category = findAssetCategory(request.getCategory());
		if (category.isEmpty())
			throw new BadRequestException("Asset category not found!");

		Optional<Asset> assetOpt = findAssetByNo(request.getAssetNo());
		if (assetOpt.isPresent())
			throw new BadRequestException("Asset number already exists!");

		try {
			Asset asset = new Asset();
			asset.setAssetNo(request.getAssetNo());
			asset.setName(request.getName());
			asset.setBrand(request.getBrand());
			asset.setConfig(request.getConfig());
			asset.setEnrollmentDate(request.getEnrollmentDate());
			asset.setLastServiceDate(request.getLastServiceDate());
			asset.setAssetCategory(category.get());
			assetRepository.save(asset);

			LOGGER.info("Added new asset. {} - {}", asset.getAssetNo(), asset.getName());
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while adding the asset. Message {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public CommonResponse findAllAssetCategories() {
		try {
			List<AssetCategory> categories = assetCategoryRepo.findAll();
			LOGGER.info("Returning asset categories. count {}", categories.size());
			return new CommonResponse(categories);
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching asset categories. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public PageResponse findAllAssetsByCategory(String categoryName, int page, int size) {
		Optional<AssetCategory> category = findAssetCategory(categoryName);
		if (category.isEmpty())
			throw new BadRequestException("Asset category not found!");

		Pageable pageable = AppUtils.getPageable(page, size, null, null);
		try {
			Page<Asset> assets = assetRepository.findByAssetCategory(category.get(), pageable);
			LOGGER.info("Returning assets. count {}", assets.getSize());
			return new PageResponse(assets.getContent(), assets.getTotalElements(), assets.getTotalPages());
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching assets. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	public AssetRequest findAssetRequestByUuid(String uuid) {
		return assetRequestRepo.findByUuid(uuid)
				.orElseThrow(() -> new RecordNotFoundException("Asset request not found"));
	}

	@Override
	public BaseResponse saveAssetRequest(AssetRequestDto request) {
		if (StringUtils.isEmpty(request.getAssetNo()) || request.getTenure() == null)
			throw new BadRequestException(MANDATORY_FIELDS_NOT_FOUND);

		Optional<Asset> assetOpt = findAssetByNo(request.getAssetNo());
		if (!assetOpt.isPresent())
			throw new BadRequestException("Asset not found!");

		try {
			AssetRequest assetRequest = new AssetRequest();
			assetRequest.setUuid(AppUtils.strUUID());
			assetRequest.setAsset(assetOpt.get());
			assetRequest.setTenure(request.getTenure());
			assetRequest.setStatus(Status.PENDING);
			assetRequest.setRequestedBy(userService.findLoggedInUserName());
			assetRequest.setRequestedDate(DateUtils.currentDate());
			assetRequestRepo.save(assetRequest);

			LOGGER.info("New asset requested by {}", assetRequest.getRequestedBy());
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while adding new asset request. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public BaseResponse updateAssetRequest(AssetRequestDto request) {
		if (StringUtils.isEmpty(request.getUuid()))
			throw new BadRequestException(MANDATORY_FIELDS_NOT_FOUND);

		String loggedInUserName = userService.findLoggedInUserName();
		AssetRequest assetRequest = findAssetRequestByUuid(request.getUuid());
		
		if (!assetRequest.getStatus().equals(Status.PENDING))
			throw new BadRequestException("You can't edit the approved/rejected request.");
		
		if (request.getAssetNo() != null) {
			Asset asset = findAssetByNo(request.getAssetNo())
					.orElseThrow(() -> new RecordNotFoundException("Asset not found"));
			assetRequest.setAsset(asset);
		}
		if (request.getTenure() != null) {
			assetRequest.setTenure(request.getTenure());
		}
		try {
			assetRequestRepo.save(assetRequest);
			LOGGER.info("Asset request status has been updated. updated by {}", loggedInUserName);
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while updating asset request. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public BaseResponse updateAssetRequestStatus(AssetRequestDto request) {
		if (StringUtils.isEmpty(request.getUuid()) || request.getStatus() == null)
			throw new BadRequestException(MANDATORY_FIELDS_NOT_FOUND);

		String loggedInUserName = userService.findLoggedInUserName();

		AssetRequest assetRequest = findAssetRequestByUuid(request.getUuid());
		assetRequest.setStatus(request.getStatus());
		assetRequest.setActive(false);
		if (request.getStatus().equals(Status.APPROVED)) {
			assetRequest.setApprovedBy(loggedInUserName);
			assetRequest.setApprovedDate(DateUtils.currentDate());
		} else if (request.getStatus().equals(Status.REJECTED)) {
			assetRequest.setRejectedBy(loggedInUserName);
			assetRequest.setRejectedDate(DateUtils.currentDate());
		} else
			throw new BadRequestException("Invalid status type!");

		try {
			assetRequestRepo.save(assetRequest);
			if (request.getStatus().equals(Status.APPROVED)) {
				AssetHistory history = new AssetHistory();
				history.setAssetRequestId(assetRequest.getId());
				history.setAsset(assetRequest.getAsset());
				history.setEnrollDate(assetRequest.getApprovedDate());
				history.setEnrollExpiryDate(DateUtils.addMonths(history.getEnrollDate(), assetRequest.getTenure()));
				history.setCurrentlyOwnedBy(assetRequest.getRequestedBy());
				assetHistoryRepo.save(history);
				LOGGER.info("Added asset history");
			}

			LOGGER.info("Asset request status has been updated. updated by {}", loggedInUserName);
			return new BaseResponse();
		} catch (Exception e) {
			LOGGER.error("An error occured while updating asset request. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	public Page<AssetRequest> findAllAssetRequestsByUser(String loggedInUserName, String status,  Pageable pageable) {
		Status statusE = Status.valueOf(status);
		return assetRequestRepo.findByRequestedByAndStatus(loggedInUserName, statusE, pageable);
	}

	@Override
	public PageResponse findAllAssetRequestsByUser(String status, int page, int size) {
		String loggedInUserName = userService.findLoggedInUserName();
		Pageable pageable = AppUtils.getPageable(page, size, null, null);
		try {
			Page<AssetRequest> assetRequests = findAllAssetRequestsByUser(loggedInUserName, status, pageable);
			LOGGER.info("Returning {} assetRequests for {}", assetRequests.getSize(), loggedInUserName);
			return new PageResponse(assetRequests.getContent(), assetRequests.getTotalElements(),
					assetRequests.getTotalPages());
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching asset requests. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public PageResponse findAllAssetRequests(String status, int page, int size) {
		Pageable pageable = AppUtils.getPageable(page, size, null, null);
		Status statusE = Status.valueOf(status);
		try {
			Page<AssetRequest> assetRequests = assetRequestRepo.findByStatus(statusE, pageable);
			LOGGER.info("Returning {} assetRequests", assetRequests.getTotalPages());
			return new PageResponse(assetRequests.getContent(), assetRequests.getTotalElements(),
					assetRequests.getTotalPages());
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching asset requests. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

	@Override
	public PageResponse findAllAssetHistories(String assetNo, int page, int size) {
		Asset asset = findAssetByNo(assetNo).orElseThrow(() -> new RecordNotFoundException("Asset not found"));
		Pageable pageable = AppUtils.getPageable(page, size, null, null);
		try {
			Page<AssetHistory> assetHistories = assetHistoryRepo.findByAsset(asset, pageable);
			LOGGER.info("Returning {} assetHistories", assetHistories.getTotalElements());
			return new PageResponse(assetHistories.getContent(), assetHistories.getTotalElements(),
					assetHistories.getTotalPages());
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching asset histories. {}", e.getMessage(), e);
			throw new InternalServerErrorException();
		}
	}

}
