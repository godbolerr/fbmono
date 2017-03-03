package com.work.fb.service.mapper;

import com.work.fb.domain.*;
import com.work.fb.service.dto.FbMessageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity FbMessage and its DTO FbMessageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FbMessageMapper {

    FbMessageDTO fbMessageToFbMessageDTO(FbMessage fbMessage);

    List<FbMessageDTO> fbMessagesToFbMessageDTOs(List<FbMessage> fbMessages);

    FbMessage fbMessageDTOToFbMessage(FbMessageDTO fbMessageDTO);

    List<FbMessage> fbMessageDTOsToFbMessages(List<FbMessageDTO> fbMessageDTOs);
}
