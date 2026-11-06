package com.carrental.controller;

import com.carrental.domain.ContactMessage;
import com.carrental.dto.ContactMessageDTO;
import com.carrental.dto.payload.ContactMessagePayload;
import com.carrental.dto.response.CarRentalResponse;
import com.carrental.mapper.ContactMessageMapper;
import com.carrental.service.ContactMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-message")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;
    private final ContactMessageMapper contactMessageMapper;


    @PostMapping("/visitors")
    public ResponseEntity<CarRentalResponse> createContactMessage(@Valid @RequestBody ContactMessagePayload contactMessagePayload) {

        ContactMessage contactMessage = contactMessageMapper.contactMessagePayloadToContactMessage(contactMessagePayload);

        CarRentalResponse carRentalResponse = contactMessageService.saveContactMessage(contactMessage);

        return new ResponseEntity<>(carRentalResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllContactMessages() {

        List<ContactMessage> contactMessageList = contactMessageService.getAllContactMessages();

        List<ContactMessageDTO> map = contactMessageMapper.map(contactMessageList);

        //return new ResponseEntity<>(map, HttpStatus.OK);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<ContactMessageDTO>> getAllContactMessagesWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<ContactMessage> contactMessages = contactMessageService.getAllContactMessagesWithPage(pageable);

        Page<ContactMessageDTO> contactMessageDTOPage = map(contactMessages);

        return ResponseEntity.ok(contactMessageDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactMessageDTO> getContactMessageByIdPathVariable(@PathVariable(name = "id") Long id) {
        ContactMessage contactMessage = contactMessageService.getContactMessageBy_Id(id);
        ContactMessageDTO contactMessageDTO = contactMessageMapper.contactMessageToContactMessageDTO(contactMessage);
        return ResponseEntity.ok(contactMessageDTO);
    }

    @GetMapping("/request")
    public ResponseEntity<ContactMessageDTO> getContactMessageByIdRequestParam(@RequestParam("id") Long id) {
        ContactMessage contactMessage = contactMessageService.getContactMessageBy_Id(id);
        ContactMessageDTO contactMessageDTO = contactMessageMapper.contactMessageToContactMessageDTO(contactMessage);
        return ResponseEntity.ok(contactMessageDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarRentalResponse> deleteContactMessageById(@PathVariable(name = "id") Long id) {
        CarRentalResponse carRentalResponse = contactMessageService.deleteContactMessageBy_Id(id);
        return ResponseEntity.ok(carRentalResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CarRentalResponse> updateContactMessage(@PathVariable("id") Long id, @RequestBody ContactMessagePayload contactMessagePayload) {
        ContactMessage contactMessage = contactMessageMapper.contactMessagePayloadToContactMessage(contactMessagePayload);
        CarRentalResponse carRentalResponse = contactMessageService.updateContactMessage(id, contactMessage);
        return ResponseEntity.ok(carRentalResponse);

    }


    // ================== YARDIMCI METHODLAR ==================
    private Page<ContactMessageDTO> map(Page<ContactMessage> contactMessages) {

        return contactMessages.map(contactMessageMapper::contactMessageToContactMessageDTO);

    }


}
