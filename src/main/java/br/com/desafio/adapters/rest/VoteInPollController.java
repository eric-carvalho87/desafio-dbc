package br.com.desafio.adapters.rest;

import br.com.desafio.adapters.rest.DTO.VoteInPollDTO;
import br.com.desafio.application.service.VoteInPollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
public class VoteInPollController {

    private final VoteInPollService voteInPollService;

    public VoteInPollController(VoteInPollService voteInPollService) {
        this.voteInPollService = voteInPollService;
    }

    @PostMapping
    public ResponseEntity<String> vote(@RequestBody VoteInPollDTO voteInPollDTO) {
        voteInPollService.vote(voteInPollDTO);
        return ResponseEntity.ok("Voted!");
    }
}
