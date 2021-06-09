package com.epam.esm.factory.impl;

import com.epam.esm.dao.constant.SqlTagColumnName;
import com.epam.esm.dto.Tag;
import com.epam.esm.factory.EntityFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static com.epam.esm.validator.TagValidator.areRequestParamsValid;

@Component
public class TagFactory implements EntityFactory<Optional<Tag>> {
    @Override // TODO: 6/10/2021 delete tagFactory
    public Optional<Tag> create(MultiValueMap<String, String> requestParams) {
        return (areRequestParamsValid(requestParams) ?
                Optional.of(new Tag(requestParams.get(SqlTagColumnName.TAG_NAME).get(0))) : Optional.empty());
    }
}
