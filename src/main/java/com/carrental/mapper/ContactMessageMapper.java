package com.carrental.mapper;

import com.carrental.domain.ContactMessage;
import com.carrental.dto.ContactMessageDTO;
import com.carrental.dto.payload.ContactMessagePayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMessageMapper {

    ContactMessageDTO contactMessageToContactMessageDTO(ContactMessage contactMessage);

    @Mapping(target = "id", ignore = true)
    ContactMessage contactMessagePayloadToContactMessage(ContactMessagePayload contactMessagePayload);

    List<ContactMessageDTO> map(List<ContactMessage> contactMessageList);
}
