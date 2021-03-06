package com.work.fb.service.mapper;

import com.work.fb.domain.*;
import com.work.fb.service.dto.FbpostDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Fbpost and its DTO FbpostDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FbpostMapper {

    FbpostDTO fbpostToFbpostDTO(Fbpost fbpost);

    List<FbpostDTO> fbpostsToFbpostDTOs(List<Fbpost> fbposts);

    Fbpost fbpostDTOToFbpost(FbpostDTO fbpostDTO);

    List<Fbpost> fbpostDTOsToFbposts(List<FbpostDTO> fbpostDTOs);
}
