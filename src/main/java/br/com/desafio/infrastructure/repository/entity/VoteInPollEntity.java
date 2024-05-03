package br.com.desafio.infrastructure.repository.entity;

import br.com.desafio.domain.polling.enums.VoteOptionEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.UUID;

@Entity(name = "Vote")
public class VoteInPollEntity {

    @EmbeddedId
    private VoteInPollId voteInPollId;
    private VoteOptionEnum voteOptionEnum;

    public VoteInPollEntity() {
    }

    public VoteInPollEntity(UUID pollId, String cpfNumber, int voteOptionEnum) {
        this.voteInPollId = new VoteInPollId(pollId, cpfNumber);
        this.voteOptionEnum = VoteOptionEnum.fromValue(voteOptionEnum);
    }

    public UUID getPollId() {
        return voteInPollId.getPollId();
    }

    public void setPollId(UUID pollId) {
        this.voteInPollId.setPollId(pollId);
    }

    public String getCpfNumber() {
        return voteInPollId.getCpfNumber();
    }

    public void setCpfNumber(String cpfNumber) {
        this.voteInPollId.setCpfNumber(cpfNumber);
    }

    public VoteOptionEnum getVoteOptionEnum(int votingOption) {
        return VoteOptionEnum.fromValue(votingOption);
    }

    public void setVoteOptionEnum(VoteOptionEnum voteOptionEnum) {
        this.voteOptionEnum = voteOptionEnum;
    }

    @Override
    public String toString() {
        return "VoteInPollEntity{" +
                "pollId=" + voteInPollId.getPollId() +
                ", cpfNumber='" + voteInPollId.getCpfNumber() + '\'' +
                ", voteOptionEnum=" + voteOptionEnum +
                '}';
    }
}
