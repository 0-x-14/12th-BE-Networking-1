package cotato.backend.domain.recruitment.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Applicant {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long applicantId;

	@Column(length = 10, nullable = false)
	@Size(min = 2, max = 10)
	private String name;

	@Min(22) @Max(30)
	@Column(nullable = false)
	private int age;

	@Column(length = 11, nullable = false)
	private String phoneNumber;

	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Application> applications = new ArrayList<>();
}
