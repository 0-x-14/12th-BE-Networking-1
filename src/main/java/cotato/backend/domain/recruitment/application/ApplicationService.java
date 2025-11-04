package cotato.backend.domain.recruitment.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.api.dto.response.request.SubmitApplicationDTO;
import cotato.backend.domain.recruitment.dao.ApplicantRepository;
import cotato.backend.domain.recruitment.dao.ApplicationRepository;
import cotato.backend.domain.recruitment.entity.Applicant;
import cotato.backend.domain.recruitment.entity.Application;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationService {

	private final ApplicationRepository applicationRepository;

	private final ApplicantRepository applicantRepository;

	@Transactional
	public Long submitApplication(SubmitApplicationDTO submitApplicationDTO) {

		Integer age = submitApplicationDTO.age();

		Applicant applicant = applicantRepository.findByPhoneNumber(submitApplicationDTO.phoneNumber())
			.map(existing -> {
				// 만약 이미 저장되어있는 지원자인데 DB에 저장된 나이와 현재 나이가 다르다면,
				// 연도가 바뀌고 재지원한 것이기 때문에 나이를 현재 나이로 수정해준다.
				if (existing.getAge() != submitApplicationDTO.age()) {
					existing.updateAge(submitApplicationDTO.age());
				}
				return existing;
			})
			.orElseGet(() -> applicantRepository.save(
				// 새롭게 지원한 지원자일 경우 입력된 값을 기반으로 DB에 저장한다.
				Applicant.builder()
					.name(submitApplicationDTO.name())
					.age(submitApplicationDTO.age())
					.phoneNumber(submitApplicationDTO.phoneNumber())
					.build()
			));

		Application application = Application.builder()
			.applicant(applicant)
			.period(submitApplicationDTO.period())
			.part(submitApplicationDTO.part())
			.ability(submitApplicationDTO.ability())
			.passion(submitApplicationDTO.passion())
			.applicationTime(LocalDateTime.now())
			.build();

		return applicationRepository.save(application).getApplicationId();
	}
}
