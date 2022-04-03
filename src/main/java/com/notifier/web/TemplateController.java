package com.notifier.web;

import com.notifier.exception.ErrorResponse;
import com.notifier.exception.NotifierException;
import com.notifier.exception.ValidationErrorResponse;
import com.notifier.service.TemplateService;
import com.notifier.web.request.SaveTemplateRq;
import com.notifier.web.request.validation.ValidationGroup;
import com.notifier.web.response.TemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/persons/{idTelegram}/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping("/create")
    public ResponseEntity<?> createTemplate(@PathVariable Long idTelegram, @RequestBody @Validated(ValidationGroup.class) SaveTemplateRq request) throws NotifierException {
        templateService.createTemplate(idTelegram, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTemplate(@PathVariable Long idTelegram, @RequestParam String nameT) throws NotifierException {
        return ResponseEntity.ok(templateService.deleteTemplate(idTelegram, nameT));
    }

    @PostMapping("/select")
    public ResponseEntity<?> selectTemplate(@PathVariable Long idTelegram, @RequestParam String nameT) throws NotifierException {
        return ResponseEntity.ok(templateService.selectTemplate(idTelegram, nameT));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TemplateResponse>> all(@PathVariable Long idTelegram) throws NotifierException {
        return ResponseEntity.ok(templateService.all(idTelegram));
    }

    @ExceptionHandler(value = NotifierException.class) // обработка исключения
    public ResponseEntity<ErrorResponse> handle(NotifierException e) {

        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getCode()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // обработка исключения
    public ResponseEntity<ValidationErrorResponse> handle(MethodArgumentNotValidException e) {
        log.error("Validation error", e);
        List<ValidationErrorResponse.Error> errors = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ValidationErrorResponse.Error(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        ValidationErrorResponse body = new ValidationErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
