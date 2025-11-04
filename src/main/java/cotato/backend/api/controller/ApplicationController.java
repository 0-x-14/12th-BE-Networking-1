package cotato.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.api.dto.response.request.SubmitApplicationDTO;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.recruitment.application.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applications")
public class ApplicationController {
	private final ApplicationService applicationService;

	@Operation(summary = "지원 서류 제출 API")
	@PostMapping("/submit")
	public ResponseEntity<DataResponse<DefaultIdResponse>> submitApplication(@RequestBody SubmitApplicationDTO submitApplicationDTO) {
		return ResponseEntity.ok(
			DataResponse.created(
				DefaultIdResponse.of(applicationService.submitApplication(submitApplicationDTO))
			)
		);
	}
}
