package com.bin.api.controller.query;

import com.bin.api.dao.repository.view.UserQueryView;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.web.base.FunctionDef;
import com.bin.webase.core.entity.FunctionId;
import com.bin.webase.core.model.IdName;
import com.bin.webase.core.query.Query;
import com.google.common.collect.Lists;

import java.util.List;

public class SwitchBranchQuery extends Query<List<IdName<Long>>> {

    public SwitchBranchQuery() {
        super();
        WebSession webSession = getToken(WebSession.class);
        setData(getIdNames(webSession.getUserId()));
    }

    private List<IdName<Long>> getIdNames(Long id) {
        UserQueryView userQueryView = new UserQueryView(id);
        return userQueryView.listBranch();
    }

    @Override
    protected List<FunctionId> getFunctionId() {
        return Lists.newArrayList(FunctionDef.GUEST_VISIT);
    }
}
