package br.com.desafio.adapters.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class VoteInPollId implements Serializable {
    @Column(columnDefinition = "uuid")
    private UUID pollId;
    private String cpfNumber;

    public VoteInPollId() {
    }

    public VoteInPollId(UUID pollId, String cpfNumber) {
        this.pollId = pollId;
        this.cpfNumber = cpfNumber;
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
}
