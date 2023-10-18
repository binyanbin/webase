package com.bin.api.controller.query.settings;

import com.bin.api.controller.query.settings.vo.CampusVo;
import com.bin.api.dao.mybatis.model.Campus;
import com.bin.api.dao.repository.view.BranchView;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.web.base.FunctionDef;
import com.bin.webase.core.model.FunctionId;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.query.Query;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusListQuery extends Query<List<CampusVo>, NoParam> {


    @Override
    protected List<FunctionId> getFunction() {
        return Lists.newArrayList(FunctionDef.CAMPUS_MANAGE);
    }

    @Override
    protected List<CampusVo> getData(NoParam param) {
        WebSession webSession = getToken(WebSession.class);
        BranchView view = new BranchView(webSession.getBranchId());
        List<Campus> campuses = view.listCampus();
        List<CampusVo> result = Lists.newArrayList();
        for (Campus campus : campuses) {
            result.add(new CampusVo(campus));
        }
        return result;
    }


}
