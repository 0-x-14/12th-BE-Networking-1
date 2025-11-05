package cotato.backend.domain.recruitment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cotato.backend.domain.recruitment.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	// ApplicationId로 application과 applicant 함께 조회
	// LikeService에서 지연 로딩하는 findByApplicationId가 필요하므로 중복된 네이밍 변경하였음
	@EntityGraph(attributePaths = "applicant")
	Optional<Application> findWithApplicantByApplicationId(Long applicationId);
}
