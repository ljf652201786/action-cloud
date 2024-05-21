package com.action.auth.jackson;

import com.action.auth.entity.SecurityUser;
import com.action.common.core.base.BaseSecurityMenu;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.util.List;

class SysUserDeserializer extends JsonDeserializer<SecurityUser> {

    private static final TypeReference<List<? extends BaseSecurityMenu>> MENU_SCOPE_LIST = new TypeReference<List<? extends BaseSecurityMenu>>() {
    };

    @Override
    public SecurityUser deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        List<? extends BaseSecurityMenu> menuScopeList = mapper.convertValue(jsonNode.get("menuScopeList"),
                MENU_SCOPE_LIST);
        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        String userId = readJsonNode(jsonNode, "id").asText();
        String username = readJsonNode(jsonNode, "username").asText();
        String password = passwordNode.asText("");
        String status = readJsonNode(jsonNode, "status").asText();
        SecurityUser result = new SecurityUser(menuScopeList, userId, username, password, status);
        if (passwordNode.asText(null) == null) {
            result.eraseCredentials();
        }
        return result;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

}
