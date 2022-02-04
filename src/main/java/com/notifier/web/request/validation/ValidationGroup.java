package com.notifier.web.request.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, One.class, Two.class})
public interface ValidationGroup {
}
