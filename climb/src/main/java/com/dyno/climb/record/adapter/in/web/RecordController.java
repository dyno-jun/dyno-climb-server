package com.dyno.climb.record.adapter.in.web;

import com.dyno.climb.record.adapter.in.web.dto.request.*;
import com.dyno.climb.record.adapter.in.web.dto.response.PreSignedResponse;
import com.dyno.climb.record.adapter.in.web.dto.response.RecordResponse;
import com.dyno.climb.record.adapter.in.web.dto.response.UserMissionRecordDto;
import com.dyno.climb.record.adapter.in.web.mapper.RecordMapper;
import com.dyno.climb.record.application.service.*;
import com.dyno.climb.record.application.service.dto.MissionRecordDto;
import com.dyno.climb.record.application.service.dto.PreSignedUrlDto;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import com.dyno.climb.shared.dto.IdDto;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/records")
public class RecordController {
  private final GetRecordUseCase getRecordUseCase;
  private final CreateRecordUseCase createRecordUseCase;
  private final UpdateRecordUseCase updateRecordUseCase;
  private final DeleteRecordUseCase deleteRecordUseCase;
  private final SubmitMissionRecordUseCase submitMissionRecordUseCase;

  private final FileUseCase fileUseCase;
  private final RecordMapper recordMapper;

  public RecordController(
      GetRecordUseCase getRecordUseCase,
      CreateRecordUseCase createRecordUseCase,
      UpdateRecordUseCase updateRecordUseCase,
      DeleteRecordUseCase deleteRecordUseCase,
      SubmitMissionRecordUseCase submitMissionRecordUseCase,
      FileUseCase fileUseCase,
      RecordMapper recordMapper) {
    this.getRecordUseCase = getRecordUseCase;
    this.createRecordUseCase = createRecordUseCase;
    this.updateRecordUseCase = updateRecordUseCase;
    this.deleteRecordUseCase = deleteRecordUseCase;
    this.submitMissionRecordUseCase = submitMissionRecordUseCase;
    this.fileUseCase = fileUseCase;
    this.recordMapper = recordMapper;
  }

  @PostMapping("/{id}/mission")
  public ResponseEntity<?> submitMissionRecord(
      @AuthenticationPrincipal User user,
      @PathVariable("id") String id,
      @Valid @RequestBody SubmitMissionRecordDto request) {
    submitMissionRecordUseCase.submit(
        user, id, request.getMissionGroupId(), request.getMissionId());

    return ResponseEntity.status(201).body(null);
  }

  @GetMapping()
  public ResponseEntity<RecordResponse> getPublicRecords(
      @AuthenticationPrincipal User user, @Valid @ModelAttribute RecordSearchFilter filter) {
    List<MissionRecordDto> missionRecords =
        getRecordUseCase.getRecordsByQuery(
            new RecordSearchQuery(
                filter.getKeyword(), filter.getMissionGroupId(), filter.getMissionId()));

    return ResponseEntity.ok(
        new RecordResponse(
            missionRecords.stream().map(recordMapper::of).collect(Collectors.toList())));
  }

  @GetMapping("/users/me")
  public ResponseEntity<RecordResponse> getUserRecords(@AuthenticationPrincipal User user) {
    List<MissionRecordDto> missionRecords = getRecordUseCase.getRecords(user);

    return ResponseEntity.ok(
        new RecordResponse(
            missionRecords.stream().map(recordMapper::of).collect(Collectors.toList())));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserMissionRecordDto> getRecordById(
      @AuthenticationPrincipal User user, @PathVariable("id") String id) {
    MissionRecordDto missionRecord = getRecordUseCase.getById(user, id);

    return ResponseEntity.ok(recordMapper.of(missionRecord));
  }

  @PostMapping("")
  public ResponseEntity<IdDto> createRecord(
      @AuthenticationPrincipal User user, @Valid @RequestBody CreateRecordRequest request) {
    String recordId = createRecordUseCase.save(user, recordMapper.mapTo(request));

    return ResponseEntity.status(201).body(new IdDto(recordId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateRecord(
      @AuthenticationPrincipal User user,
      @PathVariable("id") String id,
      @Valid @RequestBody CreateRecordRequest request) {
    updateRecordUseCase.update(user, id, recordMapper.mapTo(request));
    return ResponseEntity.ok(null);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteRecord(
      @AuthenticationPrincipal User user, @PathVariable("id") String id) {
    deleteRecordUseCase.delete(user, id);
    return ResponseEntity.ok(null);
  }

  @PutMapping("/media/presigned-url")
  public ResponseEntity<PreSignedResponse> getPreSignedUrl(
      @AuthenticationPrincipal User user, @Valid @RequestBody PreSignedUrlRequest request) {
    PreSignedUrlDto preSignedUrl =
        fileUseCase.generateUploadPreSignedUrl(user, request.getFileName());

    return ResponseEntity.ok(new PreSignedResponse(preSignedUrl.url(), preSignedUrl.filePath()));
  }
}
