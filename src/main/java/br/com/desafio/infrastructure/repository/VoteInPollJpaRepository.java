package br.com.desafio.infrastructure.repository;

import br.com.desafio.infrastructure.repository.entity.VoteInPollEntity;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteInPollJpaRepository extends JpaRepository<VoteInPollEntity, Void> {
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vote v WHERE v.voteInPollId.pollId = :pollId AND v.voteInPollId.cpfNumber = :cpfNumber")
    boolean hasVoted(UUID pollId, String cpfNumber);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.voteInPollId.pollId = :pollId AND v.voteOptionEnum = :option")
    Long getTotalVotesForOption(UUID pollId, VoteOptionEnum option);
}