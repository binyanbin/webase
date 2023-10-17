package com.bin.api.controller.query.settings;

import com.bin.api.controller.query.settings.vo.CampusVo;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.dao.mybatis.model.Campus;
import com.bin.api.dao.repository.view.BranchView;
import com.bin.api.web.base.FunctionDef;
import com.bin.webase.core.query.FunctionQuery;
import com.google.common.collect.Lists;

import java.util.List;

public class CampusListQuery extends FunctionQuery<List<CampusVo>> {

    public CampusListQuery() {
        super();
        WebSession webSession = getToken(WebSession.class);
        BranchView view = new BranchView(webSession.getBranchId());
        List<Campus> campuses = view.listCampus();
        List<CampusVo> result = Lists.newArrayList();
        for (Campus campus : campuses) {
            result.add(new CampusVo(campus));
        }
        setData(result);
    }

    @Override
    public void initFunction() {
        addFunction(FunctionDef.CAMPUS_MANAGE);
    }
}
