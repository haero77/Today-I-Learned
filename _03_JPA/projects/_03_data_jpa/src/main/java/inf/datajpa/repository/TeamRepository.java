package inf.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.datajpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
