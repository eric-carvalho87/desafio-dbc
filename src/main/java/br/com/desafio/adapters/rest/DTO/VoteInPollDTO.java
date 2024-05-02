package br.com.desafio.adapters.rest.DTO;

import java.util.UUID;

public class VoteInPollDTO {
    private UUID pollId;
    private String cpfNumber;
    private int votingOption;

    public VoteInPollDTO() {
    }

    public VoteInPollDTO(UUID pollId, String cpfNumber, int votingOption) {
        this.pollId = pollId;
        this.cpfNumber = cpfNumber;
        this.votingOption = votingOption;
    }

    public UUID getPollId() {
        return pollId;
    }

    public void setPollId(UUID pollId) {
        this.pollId = pollId;
    }

    public String getCpfNumber() {
        return cpfNumber;
    }

    public void setCpfNumber(String cpfNumber) {
        this.cpfNumber = cpfNumber;
    }

    public int getVotingOption() {
        return votingOption;
    }

    public void setVotingOption(int votingOption) {
        this.votingOption = votingOption;
    }

    @Override
    public String toString() {
        return "VoteInPollDTO{" +
                "pollId=" + pollId +
                ", userId='" + cpfNumber + '\'' +
                ", votingOption=" + votingOption +
                '}';
    }
}
