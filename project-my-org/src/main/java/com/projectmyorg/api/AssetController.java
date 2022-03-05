/**
 * 
 */
package com.projectmyorg.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectmyorg.domain.enums.Status;
import com.projectmyorg.dto.CommonResponse;
import com.projectmyorg.dto.PageResponse;
import com.projectmyorg.dto.SingleStrRequestDto;
import com.projectmyorg.dto.request.AssetDto;
import com.projectmyorg.dto.request.AssetRequestDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.service.AssetService;

/**
 * @author Shankar D
 *
 */
@RestController
@RequestMapping("/api/v1/asset/")
public class AssetController {

	@Autowired
	private AssetService assetService;

	@PostMapping("/category/add")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> addAssetCategory(@Valid @RequestBody SingleStrRequestDto request) {
		return ResponseEntity.ok(assetService.addAssetCategory(request));
	}

	@GetMapping("/category/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<CommonResponse> findAllAssetCategories() {
		return ResponseEntity.ok(assetService.findAllAssetCategories());
	}

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> addAsset(@Valid @RequestBody AssetDto request) {
		return ResponseEntity.ok(assetService.addAsset(request));
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<PageResponse> findAllAssetsByCategory(@RequestParam("category") String categoryName,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseEntity.ok(assetService.findAllAssetsByCategory(categoryName, page, size));
	}

	@GetMapping("/request/status")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<CommonResponse> findAllRequestStatus() {
		return ResponseEntity.ok(new CommonResponse(Status.values()));
	}

	@PostMapping("/request")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<BaseResponse> saveAssetRequest(@Valid @RequestBody AssetRequestDto request) {
		return ResponseEntity.ok(assetService.saveAssetRequest(request));
	}

	@PutMapping("/request")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<BaseResponse> updateAssetRequest(@Valid @RequestBody AssetRequestDto request) {
		return ResponseEntity.ok(assetService.updateAssetRequest(request));
	}

	@PutMapping("/request/status")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<BaseResponse> updateAssetRequestStatus(@Valid @RequestBody AssetRequestDto request) {
		return ResponseEntity.ok(assetService.updateAssetRequestStatus(request));
	}

	@GetMapping("/request/user/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<PageResponse> findAllAssetRequestsByUser(@RequestParam("status") String status,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseEntity.ok(assetService.findAllAssetRequestsByUser(status, page, size));
	}

	@GetMapping("/request/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<PageResponse> findAllAssetRequests(@RequestParam("status") String status,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseEntity.ok(assetService.findAllAssetRequests(status, page, size));
	}

	@GetMapping("/history/all")
	@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
	public ResponseEntity<PageResponse> findAllAssetHistories(@RequestParam("assetNo") String assetNo,
			@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseEntity.ok(assetService.findAllAssetHistories(assetNo, page, size));
	}

}
