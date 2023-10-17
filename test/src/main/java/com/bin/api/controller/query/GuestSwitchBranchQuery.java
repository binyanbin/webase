package com.bin.api.controller.query;

import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.dao.repository.view.UserQueryView;
import com.bin.webase.core.model.IdName;
import com.bin.webase.core.query.FunctionQuery;


import java.util.List;

public class GuestSwitchBranchQuery extends FunctionQuery<List<IdName<Long>>> {

    public GuestSwitchBranchQuery() {
        super();
        WebSession webSession = getToken(WebSession.class);
        setData(getIdNames(webSession.getUserId()));
    }

    private List<IdName<Long>> getIdNames(Long id) {
        UserQueryView userQueryView = new UserQueryView(id);
        return userQueryView.listBranch();
    }
}
