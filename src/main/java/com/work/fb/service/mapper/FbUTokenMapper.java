package com.work.fb.service.mapper;

import com.work.fb.domain.*;
import com.work.fb.service.dto.FbUTokenDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity FbUToken and its DTO FbUTokenDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FbUTokenMapper {

    FbUTokenDTO fbUTokenToFbUTokenDTO(FbUToken fbUToken);

    List<FbUTokenDTO> fbUTokensToFbUTokenDTOs(List<FbUToken> fbUTokens);

    FbUToken fbUTokenDTOToFbUToken(FbUTokenDTO fbUTokenDTO);

    List<FbUToken> fbUTokenDTOsToFbUTokens(List<FbUTokenDTO> fbUTokenDTOs);
}
