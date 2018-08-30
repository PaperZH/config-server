package com.ucar.qtcassist.api.model.VO;

import com.ucar.qtcassist.api.model.DO.PlanDO;

public class PlanUserVO {
    private Long userId;
    private PlanDO plan;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PlanDO getPlan() {
        return plan;
    }

    public void setPlan(PlanDO plan) {
        this.plan = plan;
    }
}
