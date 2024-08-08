package com.softeer.podo.admin.model.entity.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroups.LotsRewardValidation.class})
public interface LotsValidationSequence {
}
