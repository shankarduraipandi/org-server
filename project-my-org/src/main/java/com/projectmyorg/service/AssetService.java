/**
 * 
 */
package com.projectmyorg.service;

import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.PageResponse;
import com.projectmyorg.dto.SingleStrRequestDto;
import com.projectmyorg.dto.request.AssetDto;
import com.projectmyorg.dto.request.AssetRequestDto;
import com.projectmyorg.dto.response.BaseResponse;

/**
 * @author Shankar D
 *
 */
public interface AssetService {

	BaseResponse addAssetCategory(SingleStrRequestDto request);

	BaseResponse addAsset(AssetDto request);

	CommonResponse findAllAssetCategories();

	PageResponse findAllAssetsByCategory(String categoryName, int page, int size);

	BaseResponse saveAssetRequest(AssetRequestDto request);

	BaseResponse updateAssetRequest(AssetRequestDto request);

	BaseResponse updateAssetRequestStatus(AssetRequestDto request);

	PageResponse findAllAssetRequestsByUser(String status, int page, int size);

	PageResponse findAllAssetRequests(String status, int page, int size);

	PageResponse findAllAssetHistories(String assetNo, int page, int size);

}
