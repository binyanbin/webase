package com.bin.api.controller.query;

import com.bin.api.dao.repository.view.UserMulView;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.webase.core.model.IdName;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestSwitchBranchQuery extends Query<List<IdName<Long>>, NoParam> {

    @Override
    protected List<IdName<Long>> getData(NoParam param) {
        WebSession webSession = getToken(WebSession.class);
        UserMulView userQueryView = new UserMulView(webSession.getUserId());
        return userQueryView.listBranch();
    }

}
