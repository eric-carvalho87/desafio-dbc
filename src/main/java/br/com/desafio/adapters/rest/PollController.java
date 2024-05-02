package br.com.desafio.adapters.rest;

import br.com.desafio.adapters.rest.DTO.StartSessionDTO;
import br.com.desafio.application.service.PollService;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.model.Poll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public ResponseEntity<List<Poll>> listPolls() {
        return ResponseEntity.ok(pollService.listPolls());
    }

    @GetMapping("/{pollId}/result")
    public ResponseEntity<Map<VoteOptionEnum, Long>> getPollResult(@PathVariable UUID pollId) {
        Map<VoteOptionEnum, Long> result = pollService.viewResult(pollId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createPoll(@RequestBody Poll pollRequest) {
        UUID pollId = pollService.createPoll(pollRequest);
        return ResponseEntity.ok("Poll created with ID: " + pollId);
    }

    @PostMapping("/{pollId}/start")
    public ResponseEntity<String> startSession(@PathVariable UUID pollId, @RequestBody StartSessionDTO startSessionDTO) {
        pollService.startPoll(pollId, startSessionDTO.getTimeSession());
        return ResponseEntity.ok("Poll started!");
    }
}
