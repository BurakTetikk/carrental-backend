package com.carrental.service;

import com.carrental.domain.ContactMessage;
import com.carrental.dto.response.CarRentalResponse;
import com.carrental.dto.response.CarrentalResponseMessage;
import com.carrental.exception.ResourceNotFoundException;
import com.carrental.exception.message.ErrorMessage;
import com.carrental.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactMessageService {
    private final ContactMessageRepository contactMessageRepository;


    public CarRentalResponse saveContactMessage(ContactMessage contactMessage) {

        contactMessageRepository.save(contactMessage);

        return new CarRentalResponse("Contact message created successfully.", true);
    }

    public List<ContactMessage> getAllContactMessages() {
        return contactMessageRepository.findAll();
    }

    public Page<ContactMessage> getAllContactMessagesWithPage(Pageable pageable) {
        return contactMessageRepository.findAll(pageable);
    }

    public ContactMessage getContactMessageBy_Id(Long id) {
        return contactMessageRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessage.CONTACT_MESSAGE_RESOURCE_NOT_FOUND, "Burak", id)));
    }

//    public CarRentalResponse deleteContactMessageBy_Id(Long id) {
//        boolean exists = contactMessageRepository.existsById(id);
//        if (exists) {
//            contactMessageRepository.deleteById(id);
//            return new CarRentalResponse(CarrentalResponseMessage.CONTACT_MESSAGE_DELETED_RESPONSE, true);
//        }
//        throw new ResourceNotFoundException(String.format(ErrorMessage.CONTACT_MESSAGE_RESOURCE_NOT_FOUND, "Burak", id));
//    }

    public CarRentalResponse deleteContactMessageBy_Id(Long id) {

        ContactMessage contactMessageById = getContactMessageBy_Id(id);
        contactMessageRepository.delete(contactMessageById);
        return new CarRentalResponse(CarrentalResponseMessage.CONTACT_MESSAGE_DELETED_RESPONSE, true);
    }

    public CarRentalResponse updateContactMessage(Long id, ContactMessage contactMessage) {
        ContactMessage existingMessage = getContactMessageBy_Id(id);
        existingMessage.setEmail(contactMessage.getEmail());
        existingMessage.setMessageTitle(contactMessage.getMessageTitle());
        existingMessage.setMessageBody(contactMessage.getMessageBody());
        contactMessageRepository.save(existingMessage);

        return new CarRentalResponse(CarrentalResponseMessage.CONTACT_MESSAGE_UPDATED_RESPONSE, true);
    }
}
