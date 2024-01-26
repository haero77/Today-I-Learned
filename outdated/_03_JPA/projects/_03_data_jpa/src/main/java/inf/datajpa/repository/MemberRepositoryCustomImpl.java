package inf.datajpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import inf.datajpa.entity.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

	private final EntityManager em;

	@Override
	public List<Member> findMemberCustom() {
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

}
