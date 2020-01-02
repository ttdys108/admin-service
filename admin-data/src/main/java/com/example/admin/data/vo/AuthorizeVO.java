package com.example.admin.data.vo;

import com.example.admin.data.entity.GrantTypes;
import com.ttdys108.commons.utils.CollectionUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AuthorizeVO {

    private Long userId;

    private Map<String, AuthrInfo> authrInfos;

    @Getter
    @Setter
    public static class AuthrInfo {
        private Boolean readable;
        private Boolean creatable;
        private Boolean updatable;
        private Boolean deletable;

        public int calculateGrants() {
            int grants = 0;
            if(Boolean.TRUE.equals(readable))
                grants |= GrantTypes.READABLE.getCode();
            if(Boolean.TRUE.equals(creatable))
                grants |= GrantTypes.CREATABLE.getCode();
            if(Boolean.TRUE.equals(updatable))
                grants |= GrantTypes.UPDATABLE.getCode();
            if(Boolean.TRUE.equals(deletable))
                grants |= GrantTypes.DELETABLE.getCode();
            return grants;
        }

    }
}
