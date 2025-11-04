package cotato.backend.domain.recruitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cotato.backend.domain.recruitment.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
